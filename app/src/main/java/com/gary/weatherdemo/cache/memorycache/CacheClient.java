package com.gary.weatherdemo.cache.memorycache;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.utils.CLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by GaryCao on 2019/03/14.
 * 高德城市配置缓存实现
 * 优点：统一提供，供不同UI直接获取，不再需要重复&频繁请求DB或assert文件城市配置信息数据
 */
public class CacheClient {
    private static final String TAG = CacheClient.class.getSimpleName();
    private static final Object mLock = new Object();

    /**
     * process main-thread(UI thread) works
     */
    private static Handler mUiHandler = new Handler(Looper.getMainLooper());

    /**
     * process sub-thread works
     */
    private static Handler mWorkHandler;

    private static CacheClient mCacheClient;

    private static CountDownLatch mCacheLoaderLatch;

    private CacheManager mCacheManager;

    private List<ICityConfigCallback> mCityConfigCallbacks = new ArrayList<>();

    /**
     * 主Pager页显示的城市列表
     */
    private List<CityBean> mFixedCityBeans = Constants.COMMON_CITY_BEANS; //TODO

    /**
     * 缓存是否加载OK
     */
    private volatile AtomicBoolean mCityCacheLoaded = new AtomicBoolean(false);

    public interface ICityConfigCallback {
        void onCityConfigChanged();
    }

    /**
     * 私有构造
     */
    private CacheClient() {
        initWorkHandlerThread();
        mCacheManager = new CacheManager();
        mCacheLoaderLatch = new CountDownLatch(1);
    }

    private void initWorkHandlerThread() {
        CLog.i(TAG, "initWorkHandlerThread()");
        HandlerThread handlerThread = new HandlerThread("city_thread");
        handlerThread.start();
        mWorkHandler = new Handler(handlerThread.getLooper());
    }

    /**
     * 加载缓存
     */
    public void loadCityConfigCache() {
        runOnWorkThread(new Runnable() {
            @Override
            public void run() {
                mCacheManager.loadCityConfigFromAssets(Constants.AMAP_ADCODE_CONFIG_FILE_NAME);
                mCityCacheLoaded.set(true);
                notifyCityConfigChanged();
                mCacheLoaderLatch.countDown();
            }
        });
    }

    /**
     * 通知UI数据变化：场景1：加载OK； 场景2：DB变化（TODO）
     */
    private void notifyCityConfigChanged() {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                for (ICityConfigCallback callback : mCityConfigCallbacks) {
                    callback.onCityConfigChanged();
                }
            }
        });
    }

    /**
     * UI主线程
     */
    private void runOnUIThread(Runnable runnable) {
        mUiHandler.post(runnable);
    }

    /**
     * 工作子线程
     */
    private void runOnWorkThread(Runnable runnable) {
        mWorkHandler.post(runnable);
    }

    /**
     * 通过关键字，返回匹配数据
     */
    public List<IViewItemBean> getPairedBeansByKeyWord(String keyword) {
        if (!isCityCacheLoaded()) {
            return new ArrayList<>();
        }
        return mCacheManager.getPairedBeansByKeyWord(keyword);
    }

    public List<IViewItemBean> getCityItemBeans() {
        if (!isCityCacheLoaded()) {
            return new ArrayList<>();
        }
        return mCacheManager.getCityItemBeans();
    }

    public List<CityBean> getFixedCityBeans() {
        return mFixedCityBeans;
    }

    public void addListener(ICityConfigCallback callback) {
        mCityConfigCallbacks.add(callback);
    }

    public void removeListener(ICityConfigCallback callback) {
        mCityConfigCallbacks.remove(callback);
    }

    public boolean isCityCacheLoaded() {
        return mCityCacheLoaded.get();
    }

    public static CacheClient getInstance() {
        synchronized (mLock) {
            if (mCacheClient == null) {
                mCacheClient = new CacheClient();
            }
            return mCacheClient;
        }
    }
}

package com.gary.weatherdemo.cache.memcache;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.filter.FilterChain;
import com.gary.weatherdemo.refresh.PeriodicUpdateManager;
import com.gary.weatherdemo.refresh.PeriodicUpdateManager.IWeatherQuery;
import com.gary.weatherdemo.utils.CLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by GaryCao on 2019/03/14.
 * 高德城市配置缓存实现
 * 优点：统一提供，供不同UI直接获取，不再需要重复&频繁请求DB或assert文件城市配置信息数据
 */
public class CacheClient implements IWeatherQuery {
    private static final String TAG = CacheClient.class.getSimpleName();

    public interface ICacheDataListener {
        void onCityConfigChanged();

        void onCityWeatherChanged();
    }

    /**
     * process main-thread(UI thread) works
     */
    private Handler mUiHandler = new Handler(Looper.getMainLooper());

    /**
     * process sub-thread works
     */
    private Handler mWorkHandler;
    private CountDownLatch mCacheLoaderLatch;
    private CacheManager mCacheManager;
    private List<ICacheDataListener> mCacheListeners = new CopyOnWriteArrayList<ICacheDataListener>();
    private static CacheClient mCacheClient;

    /**
     * 主Pager页显示的城市列表
     */
    private List<CityBean> mFixedCityBeans = Constants.COMMON_CITY_BEANS; //TODO

    /**
     * 缓存是否加载OK
     */
    private volatile AtomicBoolean mCityCacheLoaded = new AtomicBoolean(false);


    /**
     * 私有构造
     */
    private CacheClient() {
        initWorkHandlerThread();
        mCacheManager = new CacheManager();
        mCacheLoaderLatch = new CountDownLatch(1);
        PeriodicUpdateManager.getInstant().addListener(this);
    }

    private void initWorkHandlerThread() {
        CLog.i(TAG, "initWorkHandlerThread()");
        HandlerThread handlerThread = new HandlerThread("city_thread");
        handlerThread.start();
        mWorkHandler = new Handler(handlerThread.getLooper());
    }

    private void runOnUIThread(Runnable runnable) {
        mUiHandler.post(runnable);
    }

    private void runOnWorkThread(Runnable runnable) {
        mWorkHandler.post(runnable);
    }

    private boolean isInUiThread() {
        return Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId();
    }

    private void notifyCityConfigChanged() {
        if (isInUiThread()) {
            for (ICacheDataListener callback : mCacheListeners) {
                callback.onCityConfigChanged();
            }
        } else {
            runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    for (ICacheDataListener callback : mCacheListeners) {
                        callback.onCityConfigChanged();
                    }
                }
            });
        }
    }

    private void notifyCityWeatherChanged() {
        if (isInUiThread()) {
            for (ICacheDataListener listener : mCacheListeners) {
                listener.onCityWeatherChanged();
            }
        } else {
            runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    for (ICacheDataListener listener : mCacheListeners) {
                        listener.onCityWeatherChanged();
                    }
                }
            });
        }
    }

    @Override
    public void onWeatherQueryCompleted() {
        notifyCityWeatherChanged();
    }

    /**
     * 加载缓存
     */
    public void loadCityConfig() {
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

    public void waitForCacheLoadCompleted() {
        try {
            mCacheLoaderLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException ignore) {
            CLog.d("waitForCacheLoader" + ignore);
        }
    }

    public void loadCityItemBeansByFilters(final FilterChain filterChain) {
        runOnWorkThread(new Runnable() {
            @Override
            public void run() {
                if (isCityCacheLoaded()) {
                    mCacheManager.loadCityItemBeansByFilters(filterChain);
                    notifyCityConfigChanged();
                }
            }
        });
    }

    public synchronized List<IViewItemBean> getAllCityItemBeans() {
        if (!isCityCacheLoaded()) {
            return new ArrayList<>();
        }
        return mCacheManager.getAllCityItemBeans();
    }

    /**
     * Global Search city items apis
     */
    public synchronized List<IViewItemBean> getSearchedCityItemBeans() {
        if (!isCityCacheLoaded()) {
            return new ArrayList<>();
        }
        return mCacheManager.getSearchedCityItemBeans();
    }

    public synchronized List<CityBean> getFixedCityBeans() {
        return mFixedCityBeans;
    }

    public void addListener(ICacheDataListener callback) {
        mCacheListeners.add(callback);
    }

    public void removeListener(ICacheDataListener callback) {
        if (callback != null) {
            mCacheListeners.remove(callback);
        }
    }

    public synchronized boolean isCityCacheLoaded() {
        return mCityCacheLoaded.get();
    }

    public synchronized static CacheClient getInstance() {
        if (mCacheClient == null) {
            mCacheClient = new CacheClient();
        }
        return mCacheClient;
    }
}

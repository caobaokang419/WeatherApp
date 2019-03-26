package com.gary.weatherdemo.cache.memorycache;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.gary.weatherdemo.bean.base.BaseItemBean;
import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.utils.CLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/03/14.
 * 高德城市配置缓存实现
 * 优点：统一提供，供不同UI直接获取，不再需要重复&频繁请求DB或assert文件城市配置信息数据
 */
public class CityCacheClient {
    private static final String TAG = CityCacheClient.class.getSimpleName();
    private static final Object mLock = new Object();

    /**
     * process main-thread(UI thread) works
     */
    private static Handler mUiHandler = new Handler(Looper.getMainLooper());

    /**
     * process sub-thread works
     */
    private static Handler mWorkHandler;

    private static CityCacheClient mCityCacheClient;


    private CityCacheManager mCityCacheManager;

    private List<ICityConfigCallback> mCityConfigCallbacks = new ArrayList<>();

    public interface ICityConfigCallback {
        void onCityConfigChanged();
    }

    /**
     * 私有构造
     */
    private CityCacheClient() {
        initWorkHandlerThread();
        mCityCacheManager = new CityCacheManager();
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
                mCityCacheManager.loadCityConfigFromAssets(Constants.AMAP_ADCODE_CONFIG_FILE_NAME);
                notifyCityConfigChanged();
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
     * 通过地区名称，返回匹配成功的数据
     */
    public String getAdcodeByAddrName(String addrName) {
        return mCityCacheManager.getAdcodeByAddrName(addrName);
    }

    public List<BaseItemBean> getSearchCityBeans() {
        return mCityCacheManager.getSearchCityBeans();
    }

    public void addCallbackListener(ICityConfigCallback callback) {
        mCityConfigCallbacks.add(callback);
    }

    public void removeCallbackListener(ICityConfigCallback callback) {
        mCityConfigCallbacks.remove(callback);
    }

    public static CityCacheClient getInstance() {
        synchronized (mLock) {
            if (mCityCacheClient == null) {
                mCityCacheClient = new CityCacheClient();
            }
            return mCityCacheClient;
        }
    }
}

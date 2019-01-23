package com.gary.weatherdemo.asyncmanager.handlerthread;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class ForecastTaskManager {
    private final String TAG = "ForecastTaskManager";
    private static ForecastTaskManager mForecastTaskManager;

    /**
     * process sub-thread works
     */
    private Handler mThreadHandler;

    /**
     * process main-thread(UI thread) works
     */
    private Handler mUiHandler = new Handler();

    private ForecastTaskManager(Context context) {
        LogUtils.i(TAG, "ForecastTaskManager()");
        initWorkHandlerThread();
    }

    private void initWorkHandlerThread() {
        LogUtils.i(TAG, "initWorkHandlerThread()");
        HandlerThread handlerThread = new HandlerThread("weather_thread");
        handlerThread.start();

        mThreadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(android.os.Message msg) {
                //TODO... 用于处理耗时流程
            }
        };
    }

    public synchronized static ForecastTaskManager getInstance(Context context) {
        if (mForecastTaskManager == null) {
            mForecastTaskManager = new ForecastTaskManager(context);
        }
        return mForecastTaskManager;
    }
    //===================================================================================================
    //for test

}

package com.gary.weatherdemo.parallel;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class ForecastTaskManager {
    private final String TAG = "ForecastTaskManager";
    private static ForecastTaskManager forecastTaskManager;

    /**
     * process sub-thread works
     */
    private Handler threadHandler;

    /**
     * process menu_analytics_options-thread(UI thread) works
     */
    private Handler uiHandler = new Handler();

    private ForecastTaskManager(Context context) {
        LogUtils.i(TAG, "ForecastTaskManager()");
        initWorkHandlerThread();
    }

    private void initWorkHandlerThread() {
        LogUtils.i(TAG, "initWorkHandlerThread()");
        HandlerThread handlerThread = new HandlerThread("weather_thread");
        handlerThread.start();

        threadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(android.os.Message msg) {
                //TBD... 处理耗时流程
            }
        };
    }

    public synchronized static ForecastTaskManager getInstance(Context context) {
        if (forecastTaskManager == null) {
            forecastTaskManager = new ForecastTaskManager(context);
        }
        return forecastTaskManager;
    }
    //===================================================================================================
    //for test

}

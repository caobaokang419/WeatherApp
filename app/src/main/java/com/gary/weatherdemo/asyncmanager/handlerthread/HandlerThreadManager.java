package com.gary.weatherdemo.asyncmanager.handlerthread;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class HandlerThreadManager {
    private final String TAG = "HandlerThreadManager";
    private static HandlerThreadManager mHandlerThreadManager;

    /**
     * process sub-thread works
     */
    private Handler mThreadHandler;

    /**
     * process main-thread(UI thread) works
     */
    private Handler mUiHandler = new Handler();

    private HandlerThreadManager(Context context) {
        LogUtils.i(TAG, "HandlerThreadManager()");
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

    public synchronized static HandlerThreadManager getInstance(Context context) {
        if (mHandlerThreadManager == null) {
            mHandlerThreadManager = new HandlerThreadManager(context);
        }
        return mHandlerThreadManager;
    }

    //===================================================================================================
    //for test

}

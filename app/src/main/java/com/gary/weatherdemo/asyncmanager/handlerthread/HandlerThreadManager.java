package com.gary.weatherdemo.asyncmanager.handlerthread;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.gary.weatherdemo.utils.CLog;

/**
 * Created by GaryCao on 2018/10/25.
 * 异步任务 Type2
 * 缺陷：单个线程轮询任务，容易阻塞消息队列
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
    private Handler mUiHandler = new Handler(Looper.getMainLooper());

    private HandlerThreadManager(Context context) {
        CLog.i(TAG, "HandlerThreadManager()");
        initWorkHandlerThread();
    }

    private void initWorkHandlerThread() {
        CLog.i(TAG, "initWorkHandlerThread()");
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

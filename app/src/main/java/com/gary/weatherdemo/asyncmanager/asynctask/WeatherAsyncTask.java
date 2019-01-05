package com.gary.weatherdemo.asyncmanager.asynctask;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class WeatherAsyncTask {
    private final String TAG = "WeatherAsyncTask";
    private static WeatherAsyncTask weatherAsyncTask;


    private WeatherAsyncTask(Context context) {
        LogUtils.i(TAG, "WeatherAsyncTask()");
    }


    public synchronized static WeatherAsyncTask getInstance(Context context) {
        if (weatherAsyncTask == null) {
            weatherAsyncTask = new WeatherAsyncTask(context);
        }
        return weatherAsyncTask;
    }
    //===================================================================================================
    //for test

}

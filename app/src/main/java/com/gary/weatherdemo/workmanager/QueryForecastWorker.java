package com.gary.weatherdemo.workmanager;

import android.support.annotation.NonNull;

import com.gary.weatherdemo.utils.LogUtils;

import androidx.work.Worker;

/**
 * Created by GaryCao on 2018/11/04.
 */
public class QueryForecastWorker extends Worker {

    private static final String TAG = QueryForecastWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {
        //TBD

        LogUtils.d(TAG,"doWork()");
        // If there were no errors, return SUCCESS
        return Result.SUCCESS;
    }
}

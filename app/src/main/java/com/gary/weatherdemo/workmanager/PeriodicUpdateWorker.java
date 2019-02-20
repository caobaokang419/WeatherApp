package com.gary.weatherdemo.workmanager;

import android.support.annotation.NonNull;

import com.gary.weatherdemo.utils.CLog;

import androidx.work.Worker;

/**
 * Created by GaryCao on 2018/11/04.
 */
public class PeriodicUpdateWorker extends Worker {
    private static final String TAG = PeriodicUpdateWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {
        CLog.d(TAG,"doWork()");
        return Result.SUCCESS;
    }
}

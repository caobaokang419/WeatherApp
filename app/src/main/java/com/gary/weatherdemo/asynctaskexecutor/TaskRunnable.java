package com.gary.weatherdemo.asynctaskexecutor;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by GaryCao on 2019/03/13.
 *
 */
public abstract class TaskRunnable<Params, Result> implements Runnable {
    private final static String TAG = "Task";
    private static long mId = 0;
    private Context mContext;
    private Params[] mParams;

    private TaskRunnable(Context context, Params[] params) {
        mContext = context;
        mId++;
        mParams = params;
    }

    @Override
    public final void run() { //1. 算法骨架（模板方法）
        Result result = null;
        try {
            result = doInBackground(mParams);
            postComplete(result);
        } catch (Exception e) {
            postComplete(result);
        }
    }

    private void postComplete(final Result result) {
        if (TaskExecutor.mUiHandler != null) {
            TaskExecutor.mUiHandler.post(new Runnable() {
                @Override
                public void run() {
                    onComplete(result);
                }
            });
        }
    }

    @WorkerThread
    protected abstract Result doInBackground(Params... params); //算法步骤1：执行后台耗时，延迟由子类实现

    @UiThread
    protected abstract void onComplete(Result result);//算法步骤2：通知UI刷新，延迟由子类实现
}
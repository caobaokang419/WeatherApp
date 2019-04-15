package com.gary.weatherdemo.asynctaskexecutor;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

/**
 * Created by GaryCao on 2019/03/13.
 * <p>
 * GoF23 设计模式 9：模板方法模式
 */
public abstract class TaskRunnable<Params, Result> implements Runnable {
    private final static String TAG = "Task";
    private long mId;
    private Params[] mParams;

    private TaskRunnable(Params[] params) {
        mParams = params;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public long getId() {
        return mId;
    }

    @Override
    public final void run() {
        Result result = null;
        try {
            result = doInBackground(mParams);
            postComplete(result);
        } catch (Exception e) {
            postComplete(result);
        }
    }

    private void postComplete(final Result result) {
        TaskExecutor.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                onComplete(result);
            }
        });
    }

    @WorkerThread
    protected abstract Result doInBackground(Params... params);

    @UiThread
    protected abstract void onComplete(Result result);
}
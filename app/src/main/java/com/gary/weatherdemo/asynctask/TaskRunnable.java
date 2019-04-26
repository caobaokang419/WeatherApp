package com.gary.weatherdemo.asynctask;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.gary.weatherdemo.exception.CancelException;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by GaryCao on 2019/03/13.
 * <p>
 * GoF23 设计模式 9：模板方法模式
 */
public abstract class TaskRunnable<Params, Result> implements Runnable {
    private final static String TAG = "Task";
    private long mId;
    private Params[] mParams;
    private AtomicBoolean isCancelled = new AtomicBoolean(false);

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
            postStart();
            result = doInBackground(mParams);
            postComplete(result);
        } catch (CancelException e) {
            postCancel();
        } catch (Exception e) {
            postFail();
        }
    }

    private void postStart() throws CancelException {
        if (isCancelled.get()) {
            throw new CancelException("Task has been cancelled");
        }

        TaskExecutor.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                onStart();
            }
        });
    }

    private void postCancel() {
        TaskExecutor.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                onCancel();
            }
        });
    }

    private void postFail() {
        TaskExecutor.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                onFail();
            }
        });
    }

    private void postComplete(final Result result) {
        TaskExecutor.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                onComplete(result);
            }
        });
    }

    public void cancel() {
        isCancelled.set(true);
    }

    @WorkerThread
    protected abstract Result doInBackground(Params... params) throws CancelException;

    @UiThread
    protected abstract void onStart();

    @UiThread
    protected abstract void onCancel();

    @UiThread
    protected abstract void onFail();

    @UiThread
    protected abstract void onComplete(Result result);
}
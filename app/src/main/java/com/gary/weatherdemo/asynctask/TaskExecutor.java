package com.gary.weatherdemo.asynctask;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.gary.weatherdemo.utils.CLog;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by GaryCao on 2018/10/25.
 * <p>
 * 技术选型：
 * Type1：异步任务 AsyncTask --缺陷：无法中止&取消已execute()的任务
 * Type2：异步任务 HandlerThread --缺陷：单个线程轮询任务，容易阻塞消息队列
 * Type3: 线程池机制
 * *******优点：线程管理类，管理线程池，一个应用中可以有多个线程池，每个线程池做自己相关的业务
 * *******缺陷：需自行封装实现机制(Ju)，建议可转为采用成熟开源框架
 */
public class TaskExecutor {
    private static final String TAG = "TaskExecutor";
    /**
     * process main-thread(UI thread) works
     */
    public Handler mUiHandler = new Handler(Looper.getMainLooper());

    /**
     * process sub-thread works
     */
    private Handler mWorkHandler;

    private HandlerThread mHandlerThread;

    private static TaskExecutor mTaskExecutor;

    //普通线程池
    private ThreadPoolProxy mWorkPool =
            new ThreadPoolProxy(1, 3, 5 * 1000);

    //下载专用线程池
    private ThreadPoolProxy mDownloadPool =
            new ThreadPoolProxy(3, 3, 5 * 1000);

    private AtomicLong mTaskIdCounter = new AtomicLong();

    /*私有构造*/
    private TaskExecutor() {
        initWorkHandlerThread();
    }

    public ThreadPoolProxy getWorkPool() {
        return mWorkPool;
    }

    public ThreadPoolProxy getDownloadPool() {
        return mDownloadPool;
    }

    private void initWorkHandlerThread() {
        CLog.i(TAG, "initWorkHandlerThread()");
        mHandlerThread = new HandlerThread("weather_thread");
        mHandlerThread.start();
        mWorkHandler = new Handler(mHandlerThread.getLooper());
    }

    public void shutdown() {
        if (mHandlerThread != null) {
            mHandlerThread.quit();
        }
    }

    public void runOnWorkThread(Runnable runnable) {
        mWorkHandler.post(runnable);
    }

    public void runOnUIThread(Runnable runnable) {
        mUiHandler.post(runnable);
    }

    public synchronized static TaskExecutor getInstance() {
        if (mTaskExecutor == null) {
            mTaskExecutor = new TaskExecutor();
        }
        return mTaskExecutor;
    }

    /**
     * 泛型方法
     */
    public <Params, Result> void submit(TaskRunnable<Params, Result> task) {
        long id = mTaskIdCounter.getAndIncrement();
        task.setId(id);
        TaskExecutor.getInstance().getWorkPool().submit(task);
    }

    public void remove(Runnable task) {
        TaskExecutor.getInstance().getWorkPool().remove(task);
    }
}
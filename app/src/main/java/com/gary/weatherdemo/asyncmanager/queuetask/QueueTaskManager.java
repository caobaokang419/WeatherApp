package com.gary.weatherdemo.asyncmanager.queuetask;

import android.content.Context;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 * 异步任务：处理先进先出的任务链表(FIFO:first-in first-out)
 */
public class QueueTaskManager {
    private final String TAG = "QueueTaskManager";
    private static QueueTaskManager mQueueTaskManager;
    private Context mContext;

    /*私有构造*/
    private QueueTaskManager(Context cont) {
        mContext = cont;
        LogUtils.i(TAG, "mQueueTaskManager()");
    }

    public synchronized static QueueTaskManager getInstance(Context context) {
        if (mQueueTaskManager == null) {
            mQueueTaskManager = new QueueTaskManager(context);
        }
        return mQueueTaskManager;
    }
    //===================================================================================================
    //for test
}

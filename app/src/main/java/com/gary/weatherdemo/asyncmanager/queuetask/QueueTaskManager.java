package com.gary.weatherdemo.asyncmanager.queuetask;

import android.content.Context;
import android.os.AsyncTask;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 * 异步任务：处理先进先出的任务链表(FIFO:first-in first-out)
 */
public class QueueTaskManager {
    private final String TAG = "QueueTaskManager";
    private static QueueTaskManager queueTaskManager;
    private Context context;

    /*私有构造*/
    private QueueTaskManager(Context cont) {
        context = cont;
        LogUtils.i(TAG, "queueTaskManager()");
    }

    public synchronized static QueueTaskManager getInstance(Context context) {
        if (queueTaskManager == null) {
            queueTaskManager = new QueueTaskManager(context);
        }
        return queueTaskManager;
    }
    //===================================================================================================
    //for test
}

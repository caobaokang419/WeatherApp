package com.gary.weatherdemo.asyncmanager.stacktask;

import android.content.Context;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 * 异步任务：处理先进后出的任务链表(LIFO:last-in first-out)
 */
public class StackTaskManager {
    private final String TAG = "stackTaskManager";
    private static StackTaskManager stackTaskManager;
    private Context context;

    /*私有构造*/
    private StackTaskManager(Context cont) {
        context = cont;
        LogUtils.i(TAG, "stackTaskManager()");
    }

    public synchronized static StackTaskManager getInstance(Context context) {
        if (stackTaskManager == null) {
            stackTaskManager = new StackTaskManager(context);
        }
        return stackTaskManager;
    }
    //===================================================================================================
    //for test
}

package com.gary.weatherdemo.asyncmanager.stacktask;

import android.content.Context;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 * 异步任务：处理先进后出的任务链表(LIFO:last-in first-out)
 */
public class StackTaskManager {
    private final String TAG = "mStackTaskManager";
    private static StackTaskManager mStackTaskManager;
    private Context mContext;

    /*私有构造*/
    private StackTaskManager(Context cont) {
        mContext = cont;
        LogUtils.i(TAG, "mStackTaskManager()");
    }

    public synchronized static StackTaskManager getInstance(Context context) {
        if (mStackTaskManager == null) {
            mStackTaskManager = new StackTaskManager(context);
        }
        return mStackTaskManager;
    }
    //===================================================================================================
    //for test
}

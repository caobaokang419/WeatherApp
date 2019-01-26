package com.gary.weatherdemo.asyncmanager.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2018/10/25.
 * 异步任务
 * 缺陷：无法中止&取消已execute()的任务
 */
public class AsyncTaskManager {
    private final String TAG = "AsyncTaskManager";
    private static AsyncTaskManager mAsyncTaskManager;
    private Context mContext;

    /*私有构造*/
    private AsyncTaskManager(Context cont) {
        mContext = cont;
        LogUtils.i(TAG, "AsyncTaskManager()");
    }

    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        /*执行异步任务前的执行，可执行UI操作*/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /*执行异步任务，耗时流程，不可执行UI操作？？*/
        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        /*执行异步任务中执行，可执行UI操作*/
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        /*执行异步任务后执行，可执行UI操作*/
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        /*异步任务取消时执行，可执行UI操作*/
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public synchronized static AsyncTaskManager getInstance(Context context) {
        if (mAsyncTaskManager == null) {
            mAsyncTaskManager = new AsyncTaskManager(context);
        }
        return mAsyncTaskManager;
    }
    //===================================================================================================
    //for test
    private void testAsyncTask(){
        MyAsyncTask task = new MyAsyncTask();
        task.execute();
    }
}

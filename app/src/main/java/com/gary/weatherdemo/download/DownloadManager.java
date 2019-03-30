package com.gary.weatherdemo.download;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.gary.weatherdemo.network.ApiContants;
import com.gary.weatherdemo.utils.CLog;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadManager {
    private final String TAG = "DownloadManager";
    private static DownloadManager mInstance;
    private Context mContext;
    private boolean mIsBinded;

    private DownloadManager(Context context) {
        mContext = context;
    }

    private void checkAndBindService() {
        if (mIsBinded) {
            return;
        }
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.setAction("com.gary.weather.action.DOWNLOAD_SERVICE");
        if (mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)) {
            CLog.d(TAG, "bind service success!");
        }
    }

    private DownloadService.DownloadBinder mDownloadBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (DownloadService.DownloadBinder) service;
            mIsBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mDownloadBinder = null;
            mIsBinded = false;
        }
    };

    /**
     * 启动文件下载
     */
    public void startDownload(String url, IDownloadCallback iDownloadCallback) {
        checkAndBindService();
        if (null != mDownloadBinder) {
            mDownloadBinder.startDownload(url, iDownloadCallback);
        }
    }

    /**
     * 取消文件下载
     */
    public void cancelDownload() {
        if (null != mDownloadBinder) {
            mDownloadBinder.cancelDownload();
        }
    }

    /**
     * 暂停文件下载
     */
    public void pauseDownload() {
        if (null != mDownloadBinder) {
            mDownloadBinder.pauseDownload();
        }
    }

    /**
     * GoF23 设计模式 2：单例模式：延迟加载
     */
    public static DownloadManager getInstance(Context context) {
        synchronized (DownloadManager.class) {
            if (mInstance == null) {
                mInstance = new DownloadManager(context);
            }
        }
        return mInstance;
    }

    //===================================================================================================
    //for test
    /*高德天气城市配置表文件下载*/
    private void downloadConfigFile(Context context) {
        DownloadManager.getInstance(context).startDownload(ApiContants.AMAP_CITY_CONFIG_FILE_URL, null);
    }
}

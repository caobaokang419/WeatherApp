package com.gary.weatherdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.gary.weatherdemo.download.DownloadClient;
import com.gary.weatherdemo.download.IDownloadListener;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();

    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    public class DownloadBinder extends Binder {
        public void startDownload(String url) {
            DownloadClient.getInstance().startDownload(url, mDownloadListener);
        }

        public void pauseDownload() {
            DownloadClient.getInstance().pauseDownload();
        }

        public void cancelDownload() {
            DownloadClient.getInstance().cancelDownload();
        }
    }

    /*UI 通知刷新*/
    private IDownloadListener mDownloadListener = new IDownloadListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onUpdate() {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFail() {

        }

        @Override
        public void onCancel() {

        }
    };
}

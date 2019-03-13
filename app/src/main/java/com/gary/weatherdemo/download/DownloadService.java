package com.gary.weatherdemo.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();
    private IDownload mIDownload;

    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        /*GoF23 设计原则3：依赖倒转原则（Dependence Inversion Principle）：针对interface编程，不针对对象编程*/
        mIDownload = DownloadFactory.createDownloadImpl();
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
        public void startDownload(String url, IDownloadCallback callback) {
            if (mIDownload != null) {
                mIDownload.startDownload(url, callback);
            }
        }

        public void pauseDownload() {
            if (mIDownload != null) {
                mIDownload.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (mIDownload != null) {
                mIDownload.cancelDownload();
            }
        }
    }
}

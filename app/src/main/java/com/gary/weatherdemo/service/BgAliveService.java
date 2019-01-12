package com.gary.weatherdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class BgAliveService extends Service {
    public BgAliveService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}

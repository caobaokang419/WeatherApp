package com.gary.weatherdemo.base;

import android.app.Application;

import com.gary.weatherdemo.push.UmPushManager;
import com.gary.weatherdemo.workmanager.WorkerManagerClient;

import org.xutils.x;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class WtApplication extends /*MultiDex*/Application {
    private static WtApplication mWTApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mWTApplication = this;
        initConfig();
    }

    private void initConfig(){
        x.Ext.init(this);
        x.Ext.setDebug(true);

        /*load amap weather address-adcode infos*/
        WorkerManagerClient.getInstance().loadAdrAdcodeConfig();

        /*start periodic update current city weather*/
        WorkerManagerClient.getInstance().startPeriodicWeatherUpdate();

        //umeng push register
        UmPushManager.register(this);
    }

    public static WtApplication getInstance() {
        return mWTApplication;
    }
}

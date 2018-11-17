package com.gary.weatherdemo.base;

import android.app.Application;

import com.gary.weatherdemo.workmanager.WorkerManagerUtils;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class MyApplication extends /*MultiDex*/Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initConfig();
    }

    private void initConfig(){
        /*load amap weather address-adcode infos*/
        WorkerManagerUtils.getInstance().startLoadAdrAdcodeConfig();

        /*start periodic update current city weather*/
        WorkerManagerUtils.getInstance().startPeriodicWeatherUpdate();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}

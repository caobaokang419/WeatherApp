package com.gary.weatherdemo.base;

import android.app.Application;

import com.gary.weatherdemo.workmanager.WorkerManagerClient;

import org.xutils.x;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class WeatherApplication extends /*MultiDex*/Application {
    private static WeatherApplication mWTApplication;

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
        WorkerManagerClient.getInstance().startLoadAdrAdcodeConfig();

        /*start periodic update current city weather*/
        WorkerManagerClient.getInstance().startPeriodicWeatherUpdate();
    }

    public static WeatherApplication getInstance() {
        return mWTApplication;
    }
}

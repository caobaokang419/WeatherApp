package com.gary.weatherdemo.base;

import android.app.Application;

import com.gary.weatherdemo.workmanager.WorkerManagerUtils;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class WeatherApplication extends /*MultiDex*/Application {
    private static WeatherApplication weatherApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        weatherApplication = this;
        initConfig();
    }

    private void initConfig(){
        /*load amap weather address-adcode infos*/
        WorkerManagerUtils.getInstance().startLoadAdrAdcodeConfig();

        /*start periodic update current city weather*/
        WorkerManagerUtils.getInstance().startPeriodicWeatherUpdate();
    }

    public static WeatherApplication getInstance() {
        return weatherApplication;
    }
}

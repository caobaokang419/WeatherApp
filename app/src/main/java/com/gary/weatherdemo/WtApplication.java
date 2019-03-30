package com.gary.weatherdemo;

import android.app.Application;

import com.gary.weatherdemo.cache.memorycache.CacheClient;
import com.gary.weatherdemo.push.UmPushManager;

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
        /*xutils configs*/
        x.Ext.init(this);
        x.Ext.setDebug(true);

        /*load amap weather address-adcode infos*/
        //WtWorkerManager.loadAdrAdcodeConfig();
        /*start periodic update current city weather*/
        //WtWorkerManager.startPeriodicWeatherUpdate();

        CacheClient.getInstance().loadCityConfigCache();

        //umeng push register
        UmPushManager.register(this);
    }

    public static WtApplication getInstance() {
        return mWTApplication;
    }
}

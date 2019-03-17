package com.gary.weatherdemo.mvp;

import com.gary.weatherdemo.cache.memorycache.CityCacheClient;
import com.gary.weatherdemo.model.CityBean;

/**
 * Created by GaryCao on 2019/03/13.
 * <p>
 * MVP应用框架元素6：Model （用于MVVM框架对比&参考）
 */
public class CityWeatherModel implements IModel{
    private static final Object mLock = new Object();
    private static CityWeatherModel mCityWeatherModel;

    private CityWeatherModel(){

    }

    @Override
    public void getCityWeather(CityBean citybean) {

    }

    public static CityWeatherModel getInstance() {
        synchronized (mLock) {
            if (mCityWeatherModel == null) {
                mCityWeatherModel = new CityWeatherModel();
            }
            return mCityWeatherModel;
        }
    }
}

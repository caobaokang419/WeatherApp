package com.gary.weatherdemo.provider.db;

import com.gary.weatherdemo.bean.CityBean;

/**
 * Created by GaryCao on 2018/10/25.
 * <p>
 * db configs:across-process global access entries
 */
public interface DbProviderManager {
    void insertCityBean(CityBean cityBean);
}
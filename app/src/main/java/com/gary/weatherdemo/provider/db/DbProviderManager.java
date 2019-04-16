package com.gary.weatherdemo.provider.db;

import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.DayForecastBean;
import com.gary.weatherdemo.room.city.CityBeanEntity;
import com.gary.weatherdemo.ui.ItemView.ForecastDayViewItem;

import java.util.List;

/**
 * Created by GaryCao on 2018/10/25.
 * <p>
 * db configs:across-process global access entries
 */
public interface DbProviderManager {
    List<CityBean> getAllCityBeans();

    void insertCityBean(CityBean cityBean);

    void bulkInsertCityBeans(CityBean[] cityBeans);

    List<DayForecastBean> getCityForecast(CityBean cityBean);

    void insertCityForecast(DayForecastBean forecastBean);
}
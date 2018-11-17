package com.gary.weatherdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gary.weatherdemo.base.MyApplication;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class WeatherConfigs {
    private static final String WEATHER_CONFIG = "weather_config";
    private static final String KEY_CURRENT_CITY_ADCODE = "current_adcode";
    private static final String KEY_UPDATE_PERIODIC_HOUR_COUNT = "update_hour_count";

    /*def amap adcode*/
    private final String VALUE_DEFAULT_CITY_ADCODE = "440300"; //深圳:adcode:440300 citycode:0755
    /*def weather update interval hour count*/
    private final int VALUE_DEFAULT_UPDATE_PERIODIC_HOUR_COUNT = 1; //深圳:adcode:440300 citycode:0755

    private Context context;
    private static SharedPreferences sharedPreferences;

    private static WeatherConfigs weatherConfigs;

    public synchronized static WeatherConfigs getInstance() {
        if (weatherConfigs == null) {
            weatherConfigs = new WeatherConfigs();
        }
        return weatherConfigs;
    }

    private WeatherConfigs() {
        this.context = MyApplication.getInstance();
        sharedPreferences = context.getSharedPreferences(WEATHER_CONFIG, Context.MODE_PRIVATE);
    }

    public int getUpdatePeriodicHourCount() {
        return sharedPreferences.getInt(KEY_UPDATE_PERIODIC_HOUR_COUNT, VALUE_DEFAULT_UPDATE_PERIODIC_HOUR_COUNT);
    }

    public String getCurrentCityAdcode() {
        return sharedPreferences.getString(KEY_CURRENT_CITY_ADCODE, VALUE_DEFAULT_CITY_ADCODE);
    }

    public void setCurrentCityAdcode(String adcode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CURRENT_CITY_ADCODE, adcode);
        editor.commit();
    }

    //===================================================================================================
    //for test
    /*默认天气：深圳*/
    private void setShenzhenForecastWeather() {
        //深圳:adcode:440300 citycode:0755
        WeatherConfigs.getInstance().setCurrentCityAdcode("440300");
    }
}

package com.gary.weatherdemo.provider.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.gary.weatherdemo.base.MyApplication;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class WeatherSPConfigs {
    public static final String AUTOHORITY = "com.gary.weatherdemo.sp.provider";
    public static final String SP_CONTENT_URI = "content://"+AUTOHORITY;

    private static final String WEATHER_CONFIG = "weather_config";

    public static final String KEY_CURRENT_CITY_NAME = "cur_city_name";
    public static final String KEY_CURRENT_CITY_ADCODE = "cur_city_adcode";
    public static final String KEY_UPDATE_PERIODIC_HOUR_COUNT = "update_periodic_hour_count";

    public static final String KEY_SET_INT_VALUE = "set_int_value";
    public static final String KEY_SET_STRING_VALUE = "set_string_value";
    public static final String KEY_SET_BOOLEAN_VALUE = "set_boolean_value";

    /*def city name*/
    private final String VALUE_DEFAULT_CITY_NAME = "深圳"; //深圳:adcode:440300 citycode:0755
    /*def city adcode*/
    private final String VALUE_DEFAULT_CITY_ADCODE = "440300"; //深圳:adcode:440300 citycode:0755
    /*def weather update interval hour count*/
    private final int VALUE_DEFAULT_UPDATE_PERIODIC_HOUR_COUNT = 1; //深圳:adcode:440300 citycode:0755

    private Context context;
    private static SharedPreferences sharedPreferences;

    private static WeatherSPConfigs weatherSPConfigs;

    public synchronized static WeatherSPConfigs getInstance() {
        if (weatherSPConfigs == null) {
            weatherSPConfigs = new WeatherSPConfigs();
        }
        return weatherSPConfigs;
    }

    private WeatherSPConfigs() {
        this.context = MyApplication.getInstance();
        sharedPreferences = context.getSharedPreferences(WEATHER_CONFIG, Context.MODE_PRIVATE);
    }

    public int getInt(String keyname){
        return sharedPreferences.getInt(keyname, 0);
    }

    public void setInt(String keyname, int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(keyname, value);
        editor.commit();
    }

    public boolean getBoolean(String keyname){
        return sharedPreferences.getBoolean(keyname, false);
    }

    public void setBoolean(String keyname, boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(keyname, value);
        editor.commit();
    }

    public String getString(String keyname){
        return sharedPreferences.getString(keyname, "");
    }

    public void setString(String keyname, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(keyname, value);
        editor.commit();
    }


    public int getIntInProvider(String keyname){
        Bundle bundle = context.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), keyname, null, null);
        return bundle.getInt(keyname);
    }

    public void setIntInProvider(String keyname, int value){
        Bundle bundle = new Bundle();
        bundle.putInt(keyname, value);
        context.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_INT_VALUE, keyname, bundle);
    }

    public boolean getBooleanInProvider(String keyname){
        Bundle bundle = context.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), keyname, null, null);
        return bundle.getBoolean(keyname);
    }

    public void setBooleanInProvider(String keyname, boolean value){
        Bundle bundle = new Bundle();
        bundle.putBoolean(keyname, value);
        context.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_BOOLEAN_VALUE, keyname, bundle);
    }

    public String getStringInProvider(String keyname){
        Bundle bundle = context.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), keyname, null, null);
        return bundle.getString(keyname);
    }

    public void setStringInProvider(String keyname, String value){
        Bundle bundle = new Bundle();
        bundle.putString(keyname, value);
        context.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_STRING_VALUE, keyname, bundle);
    }
    //===================================================================================================
    //for test
}

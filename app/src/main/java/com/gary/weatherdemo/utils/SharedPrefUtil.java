package com.gary.weatherdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gary.weatherdemo.WtApplication;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class SharedPrefUtil {
    private static final String WEATHER_CONFIG = "weather_config";
    public static final String KEY_DEF_CITY_ADCODE = "def_city_adcode";
    public static final String KEY_UPDATE_SWITCH = "update_switch";
    public static final String KEY_UPDATE_PERIODIC_HOUR_COUNT = "update_periodic_hour_count";
    public interface DefConfig {
        public String DEF_CITY_ADCODE = "440300"; //深圳: 440300
        public boolean DEF_UPDATE_SWITCH = true;
        public int DEF_UPDATE_PERIODIC_HOUR_COUNT = 1; //1 hour
    }

    private Context mContext;
    private static SharedPreferences mSharedPreferences;
    private static SharedPrefUtil mSharedPrefUtil;

    public synchronized static SharedPrefUtil getInstance() {
        if (mSharedPrefUtil == null) {
            mSharedPrefUtil = new SharedPrefUtil();
        }
        return mSharedPrefUtil;
    }

    private SharedPrefUtil() {
        this.mContext = WtApplication.getContext();
        mSharedPreferences = mContext.getSharedPreferences(WEATHER_CONFIG, Context.MODE_PRIVATE);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return mSharedPreferences.getInt(key, def);
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean def) {
        return mSharedPreferences.getBoolean(key, def);
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String def) {
        return mSharedPreferences.getString(key, def);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}

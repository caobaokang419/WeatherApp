package com.gary.weatherdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gary.weatherdemo.WtApplication;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class SpConfigUtil {
    private static final String WEATHER_CONFIG = "weather_config";
    public static final String KEY_CURRENT_CITY_ADCODE = "cur_city_adcode";
    public static final String KEY_UPDATE_SWITCH = "wt_update_switch";
    public static final String KEY_UPDATE_PERIODIC_HOUR_COUNT = "update_periodic_hour_count";

    public static final class DefaultConfig {
        /*def city adcode*/
        public static final String VALUE_DEF_CITY_ADCODE = "440300"; //深圳: 440300
        /*def update switch*/
        public static final boolean VALUE_DEF_UPDATE_SWITCH = true;
        /*def update interval hour count*/
        public static final int VALUE_DEF_UPDATE_PERIODIC_HOUR_COUNT = 1; //1 hour
    }

    private Context mContext;
    private static SharedPreferences mSharedPreferences;
    private static SpConfigUtil mSpConfigUtil;

    public synchronized static SpConfigUtil getInstance() {
        if (mSpConfigUtil == null) {
            mSpConfigUtil = new SpConfigUtil();
        }
        return mSpConfigUtil;
    }

    private SpConfigUtil() {
        this.mContext = WtApplication.getInstance();
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

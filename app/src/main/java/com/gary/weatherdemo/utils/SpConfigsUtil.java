package com.gary.weatherdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.gary.weatherdemo.base.WtApplication;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class SpConfigsUtil {
    public static final String AUTOHORITY = "com.gary.weatherdemo.sp.provider";
    public static final String SP_CONTENT_URI = "content://"+AUTOHORITY;

    private static final String WEATHER_CONFIG = "weather_config";

    public static final String KEY_CURRENT_CITY_NAME = "cur_city_name";
    public static final String KEY_CURRENT_CITY_ADCODE = "cur_city_adcode";
    public static final String KEY_UPDATE_SWITCH = "wt_update_switch";
    public static final String KEY_UPDATE_PERIODIC_HOUR_COUNT = "update_periodic_hour_count";

    public static final String KEY_SET_INT_VALUE = "set_int_value";
    public static final String KEY_SET_STRING_VALUE = "set_string_value";
    public static final String KEY_SET_BOOLEAN_VALUE = "set_boolean_value";

    /*def city name*/
    private final String VALUE_DEF_CITY_NAME = "深圳"; //深圳
    /*def city adcode*/
    private final String VALUE_DEF_CITY_ADCODE = "440300"; //深圳: 440300
    /*def update switch*/
    private final boolean VALUE_DEF_UPDATE_SWITCH = true;
    /*def update interval hour count*/
    private final int VALUE_DEF_UPDATE_PERIODIC_HOUR_COUNT = 1; //1 hour

    private Context mContext;
    private static SharedPreferences mSharedPreferences;
    private static SpConfigsUtil mSpConfigsUtil;

    public synchronized static SpConfigsUtil getInstance() {
        if (mSpConfigsUtil == null) {
            mSpConfigsUtil = new SpConfigsUtil();
        }
        return mSpConfigsUtil;
    }

    private SpConfigsUtil() {
        this.mContext = WtApplication.getInstance();
        mSharedPreferences = mContext.getSharedPreferences(WEATHER_CONFIG, Context.MODE_PRIVATE);
    }

    public int getInt(String keyname){
        return mSharedPreferences.getInt(keyname, 0);
    }

    public void setInt(String keyname, int value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(keyname, value);
        editor.commit();
    }

    public boolean getBoolean(String keyname){
        return mSharedPreferences.getBoolean(keyname, false);
    }

    public void setBoolean(String keyname, boolean value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(keyname, value);
        editor.commit();
    }

    public String getString(String keyname){
        return mSharedPreferences.getString(keyname, "");
    }

    public void setString(String keyname, String value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(keyname, value);
        editor.commit();
    }


    public int getIntInProvider(String keyname){
        Bundle bundle = mContext.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), keyname, null, null);
        return bundle.getInt(keyname);
    }

    public void setIntInProvider(String keyname, int value){
        Bundle bundle = new Bundle();
        bundle.putInt(keyname, value);
        mContext.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_INT_VALUE, keyname, bundle);
    }

    public boolean getBooleanInProvider(String keyname){
        Bundle bundle = mContext.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), keyname, null, null);
        return bundle.getBoolean(keyname);
    }

    public void setBooleanInProvider(String keyname, boolean value){
        Bundle bundle = new Bundle();
        bundle.putBoolean(keyname, value);
        mContext.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_BOOLEAN_VALUE, keyname, bundle);
    }

    public String getStringInProvider(String keyname){
        Bundle bundle = mContext.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), keyname, null, null);
        return bundle.getString(keyname);
    }

    public void setStringInProvider(String keyname, String value){
        Bundle bundle = new Bundle();
        bundle.putString(keyname, value);
        mContext.getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_STRING_VALUE, keyname, bundle);
    }
    //===================================================================================================
    //for test
}

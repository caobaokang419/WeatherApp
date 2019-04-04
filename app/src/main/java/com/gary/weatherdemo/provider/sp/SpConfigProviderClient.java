package com.gary.weatherdemo.provider.sp;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;

import com.gary.weatherdemo.WtApplication;

/**
 * Created by GaryCao on 2018/10/25.
 * <p>
 * sp configs:across-process global access entries
 */
public class SpConfigProviderClient {
    public static final String SP_AUTHORITY = "com.gary.weatherdemo.sp.provider";
    public static final String SP_CONTENT_URI = "content://" + SP_AUTHORITY;

    public static final String KEY_SET_INT_VALUE = "set_int_value";
    public static final String KEY_SET_STRING_VALUE = "set_string_value";
    public static final String KEY_SET_BOOLEAN_VALUE = "set_boolean_value";

    public static int getIntInProvider(String key) {
        Bundle bundle = WtApplication.getInstance().getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), key, null, null);
        return bundle.getInt(key);
    }

    public static void setIntInProvider(String key, int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        WtApplication.getInstance().getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_INT_VALUE, key, bundle);
    }

    public static boolean getBooleanInProvider(String key) {
        Bundle bundle = WtApplication.getInstance().getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), key, null, null);
        return bundle.getBoolean(key);
    }

    public static void setBooleanInProvider(String key, boolean value) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        WtApplication.getInstance().getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_BOOLEAN_VALUE, key, bundle);
    }

    public static String getStringInProvider(String key) {
        Bundle bundle = WtApplication.getInstance().getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), key, null, null);
        return bundle.getString(key);
    }

    public static void setStringInProvider(String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        WtApplication.getInstance().getContentResolver().call(
                Uri.parse(SP_CONTENT_URI), KEY_SET_STRING_VALUE, key, bundle);
    }
}

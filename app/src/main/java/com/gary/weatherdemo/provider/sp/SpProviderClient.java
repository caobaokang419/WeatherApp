package com.gary.weatherdemo.provider.sp;

import android.net.Uri;
import android.os.Bundle;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.repository.Repository;

/**
 * Created by GaryCao on 2018/10/25.
 * <p>
 * sp configs:across-process global access entries
 */
public class SpProviderClient implements ISpProviderClient {
    public static final String SP_AUTHORITY = "com.gary.weatherdemo.sp.provider";
    public static final Uri SP_CONTENT_URI = Uri.parse("content://" + SP_AUTHORITY);

    public static final String KEY_SET_INT_VALUE = "set_int_value";
    public static final String KEY_SET_STRING_VALUE = "set_string_value";
    public static final String KEY_SET_BOOLEAN_VALUE = "set_boolean_value";

    private static SpProviderClient spProviderManagerImpl;

    private SpProviderClient() {
    }

    public synchronized static void registerInstance() {
        if (spProviderManagerImpl == null) {
            spProviderManagerImpl = new SpProviderClient();
        }
        Repository.Ext.setSpProviderManager(spProviderManagerImpl);
    }

    @Override
    public int getSharePrefInt(String key) {
        Bundle bundle = WtApplication.getContext().getContentResolver().call(
                SP_CONTENT_URI, key, null, null);
        return bundle.getInt(key);
    }

    @Override
    public void setSharePrefInt(String key, int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        WtApplication.getContext().getContentResolver().call(
                SP_CONTENT_URI, KEY_SET_INT_VALUE, key, bundle);
    }

    @Override
    public boolean getSharePrefBoolean(String key) {
        Bundle bundle = WtApplication.getContext().getContentResolver().call(
                SP_CONTENT_URI, key, null, null);
        return bundle.getBoolean(key);
    }

    @Override
    public void setSharePrefBoolean(String key, boolean value) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        WtApplication.getContext().getContentResolver().call(
                SP_CONTENT_URI, KEY_SET_BOOLEAN_VALUE, key, bundle);
    }

    @Override
    public String getSharePrefString(String key) {
        Bundle bundle = WtApplication.getContext().getContentResolver().call(
                SP_CONTENT_URI, key, null, null);
        return bundle.getString(key);
    }

    @Override
    public void setSharePrefString(String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        WtApplication.getContext().getContentResolver().call(
                SP_CONTENT_URI, KEY_SET_STRING_VALUE, key, bundle);
    }
}

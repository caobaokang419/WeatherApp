package com.gary.weatherdemo.provider.sp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * Created by GaryCao on 2019/01/05.
 * 提供供第三方应用统一的SharedPreference data访问API:
 */
public class WtSharedPreferenceProvider extends ContentProvider {
    public WtSharedPreferenceProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        if (method == null || method.isEmpty()) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (method.equalsIgnoreCase(WeatherSPConfigs.KEY_CURRENT_CITY_NAME)) {
            bundle.getString(
                    method,
                    WeatherSPConfigs.getInstance().getString(WeatherSPConfigs.KEY_CURRENT_CITY_NAME));
        } else if (method.equalsIgnoreCase(WeatherSPConfigs.KEY_CURRENT_CITY_ADCODE)) {
            bundle.getString(
                    method,
                    WeatherSPConfigs.getInstance().getString(WeatherSPConfigs.KEY_CURRENT_CITY_ADCODE));
        } else if (method.equalsIgnoreCase(WeatherSPConfigs.KEY_UPDATE_PERIODIC_HOUR_COUNT)) {
            bundle.getInt(
                    method,
                    WeatherSPConfigs.getInstance().getInt(WeatherSPConfigs.KEY_UPDATE_PERIODIC_HOUR_COUNT));
        } else if (method.equalsIgnoreCase(WeatherSPConfigs.KEY_SET_INT_VALUE)) {
            WeatherSPConfigs.getInstance().setInt(arg, extras.getInt(arg));
        } else if (method.equalsIgnoreCase(WeatherSPConfigs.KEY_SET_STRING_VALUE)) {
            WeatherSPConfigs.getInstance().setString(arg, extras.getString(arg));
        } else if (method.equalsIgnoreCase(WeatherSPConfigs.KEY_SET_BOOLEAN_VALUE)) {
            WeatherSPConfigs.getInstance().setBoolean(arg, extras.getBoolean(arg));
        }

        //return super.call(method, arg, extras);
        return bundle;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

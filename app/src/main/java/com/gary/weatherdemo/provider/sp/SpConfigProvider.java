package com.gary.weatherdemo.provider.sp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gary.weatherdemo.utils.SpConfigUtil;


/**
 * Created by GaryCao on 2019/01/05.
 * <p>
 * 跨进程访问SharedPreference:
 */
public class SpConfigProvider extends ContentProvider {
    public SpConfigProvider() {
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        if (method == null || method.isEmpty()) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (method.equalsIgnoreCase(SpConfigUtil.KEY_DEF_CITY_ADCODE)) {
            String adcode = SpConfigUtil.getInstance().getString(
                    SpConfigUtil.KEY_DEF_CITY_ADCODE,
                    SpConfigUtil.DefConfig.DEF_CITY_ADCODE);
            bundle.putString(method, adcode);
        } else if (method.equalsIgnoreCase(SpConfigUtil.KEY_UPDATE_SWITCH)) {
            boolean isEnable = SpConfigUtil.getInstance().getBoolean(
                    SpConfigUtil.KEY_UPDATE_SWITCH,
                    SpConfigUtil.DefConfig.DEF_UPDATE_SWITCH);
            bundle.putBoolean(method, isEnable);
        } else if (method.equalsIgnoreCase(SpConfigUtil.KEY_UPDATE_PERIODIC_HOUR_COUNT)) {
            int hourCount = SpConfigUtil.getInstance().getInt(
                    SpConfigUtil.KEY_UPDATE_PERIODIC_HOUR_COUNT,
                    SpConfigUtil.DefConfig.DEF_UPDATE_PERIODIC_HOUR_COUNT);
            bundle.putInt(method, hourCount);
        } else if (method.equalsIgnoreCase(SpConfigProviderClient.KEY_SET_INT_VALUE)) {
            SpConfigUtil.getInstance().setInt(arg, extras.getInt(arg));
        } else if (method.equalsIgnoreCase(SpConfigProviderClient.KEY_SET_STRING_VALUE)) {
            SpConfigUtil.getInstance().setString(arg, extras.getString(arg));
        } else if (method.equalsIgnoreCase(SpConfigProviderClient.KEY_SET_BOOLEAN_VALUE)) {
            SpConfigUtil.getInstance().setBoolean(arg, extras.getBoolean(arg));
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
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
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

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

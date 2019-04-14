package com.gary.weatherdemo.provider.sp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gary.weatherdemo.utils.SharedPrefUtil;


/**
 * Created by GaryCao on 2019/01/05.
 * <p>
 * 跨进程访问SharedPreference:
 */
public class SpProvider extends ContentProvider {
    public SpProvider() {
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
        if (method.equalsIgnoreCase(SharedPrefUtil.KEY_DEF_CITY_ADCODE)) {
            String adcode = SharedPrefUtil.getInstance().getString(
                    SharedPrefUtil.KEY_DEF_CITY_ADCODE,
                    SharedPrefUtil.DefConfig.DEF_CITY_ADCODE);
            bundle.putString(method, adcode);
        } else if (method.equalsIgnoreCase(SharedPrefUtil.KEY_UPDATE_SWITCH)) {
            boolean isEnable = SharedPrefUtil.getInstance().getBoolean(
                    SharedPrefUtil.KEY_UPDATE_SWITCH,
                    SharedPrefUtil.DefConfig.DEF_UPDATE_SWITCH);
            bundle.putBoolean(method, isEnable);
        } else if (method.equalsIgnoreCase(SharedPrefUtil.KEY_UPDATE_PERIODIC_HOUR_COUNT)) {
            int hourCount = SharedPrefUtil.getInstance().getInt(
                    SharedPrefUtil.KEY_UPDATE_PERIODIC_HOUR_COUNT,
                    SharedPrefUtil.DefConfig.DEF_UPDATE_PERIODIC_HOUR_COUNT);
            bundle.putInt(method, hourCount);
        } else if (method.equalsIgnoreCase(SpProviderManagerImpl.KEY_SET_INT_VALUE)) {
            SharedPrefUtil.getInstance().setInt(arg, extras.getInt(arg));
        } else if (method.equalsIgnoreCase(SpProviderManagerImpl.KEY_SET_STRING_VALUE)) {
            SharedPrefUtil.getInstance().setString(arg, extras.getString(arg));
        } else if (method.equalsIgnoreCase(SpProviderManagerImpl.KEY_SET_BOOLEAN_VALUE)) {
            SharedPrefUtil.getInstance().setBoolean(arg, extras.getBoolean(arg));
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

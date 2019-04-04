package com.gary.weatherdemo.provider.db;

import android.content.Context;
import android.net.Uri;

/**
 * Created by GaryCao on 2019/01/05.
 *
 * db data: across-process global access entries
 */
public class DbProviderClient {
    private static final Uri URI_CUR_CITY_NAME = Uri.parse("content://com.gary.weatherdemo.provider/cur_city_name");
    private static final Uri URI_CUR_CITY_ADCODE = Uri.parse("content://com.gary.weatherdemo.provider/cur_city_adcode");

    private static DbProviderClient mInstant;

    public DbProviderClient(Context context) {
    }

    public static DbProviderClient getInstant(Context context) {
        synchronized(DbProviderClient.class){
            if (mInstant == null) {
                mInstant = new DbProviderClient(context);
            }
        }
        return mInstant;
    }
}

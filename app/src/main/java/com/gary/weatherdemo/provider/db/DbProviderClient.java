package com.gary.weatherdemo.provider.db;

import android.content.Context;
import android.net.Uri;

/**
 * Created by GaryCao on 2019/01/05.
 *
 * db data: across-process global access entries
 */
public class DbProviderClient {
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

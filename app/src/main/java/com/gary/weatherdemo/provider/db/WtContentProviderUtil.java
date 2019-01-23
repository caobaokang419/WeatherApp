package com.gary.weatherdemo.provider.db;

import android.content.Context;
import android.net.Uri;

/**
 * Created by GaryCao on 2019/01/05.
 * access to db data apis；
 */
public class WtContentProviderUtil {
    private static final Uri URI_CUR_CITY_NAME = Uri.parse("content://com.gary.weatherdemo.provider/cur_city_name");
    private static final Uri URI_CUR_CITY_ADCODE = Uri.parse("content://com.gary.weatherdemo.provider/cur_city_adcode");

    private static WtContentProviderUtil mInstant;

    public WtContentProviderUtil(Context context) {
    }

    public static synchronized WtContentProviderUtil getInstant(Context context) {
        if (mInstant == null) {
            mInstant = new WtContentProviderUtil(context);
        }

        return mInstant;
    }
}

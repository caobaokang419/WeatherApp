package com.gary.weatherdemo.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by GaryCao on 2019/01/05.
 * 1.第三方应用采用统一的API访问数据；
 * 2.第三方应用不需要Care本应用数据的具体存储方式：the type of DB？ SharedPreference ？ or other store method ?
 */
public class WeatherProviderUtil {
    private static final Uri URI_CUR_CITY_NAME = Uri.parse("content://com.gary.weatherdemo.provider/cur_city_name");
    private static final Uri URI_CUR_CITY_ADCODE = Uri.parse("content://com.gary.weatherdemo.provider/cur_city_adcode");

    private static WeatherProviderUtil instant;

    public WeatherProviderUtil(Context context) {
    }

    public String getCurCityName(Context context) {
        // 指定URI
        Uri uri_user = URI_CUR_CITY_NAME;

        // 插入表中数据组装
        ContentValues values = new ContentValues();
        values.put("_id", 3);
        values.put("name", "Iverson");

        // 获取ContentResolver
        ContentResolver resolver = context.getContentResolver();

        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver.insert(uri_user, values);

//        // 通过ContentResolver 根据URI 从ContentProvider中读取数据
//        resolver.insert(uri_user, values);
//
//        // 插入表中数据
//        ContentValues values2 = new ContentValues();
//        values2.put("_id",4);
//        values2.put("job","NBA Player");
//
//        // 获取ContentResolver
//        ContentResolver resolver2 = getContentResolver();
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
//        resolver2.insert(uri_job,values2);
//
//        // 通过ContentResolver 向ContentProvider中查询数据
//        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id", "job"}, null, null, null);
//        while(cursor2.moveToNext())
//
//        {
//            System.out.println("query job:" + cursor2.getInt(0) + " " + cursor2.getString(1));
//            // 将表中数据全部输出
//        }
//        cursor2.close();
//        // 关闭游标

        return null;
    }

    public static synchronized WeatherProviderUtil getInstant(Context context) {
        if (instant == null) {
            instant = new WeatherProviderUtil(context);
        }

        return instant;
    }


}

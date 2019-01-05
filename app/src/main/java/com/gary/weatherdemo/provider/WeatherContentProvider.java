package com.gary.weatherdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;


/**
 * Created by GaryCao on 2019/01/05.
 * 1.提供供第三方应用统一的数据访问API；
 * 2.第三方应用不需要Care本应用数据的具体存储方式：the type of DB？ SharedPreference ？ or other store method ?
 */
public class WeatherContentProvider extends ContentProvider {
    // 设置ContentProvider的唯一标识
    public static final String AUTOHORITY = "com.gary.weatherdemo.myprovider";

    public static final int CUR_CITY_NAME_CODE = 1;
    public static final int CUR_CITY_ADCODE_CODE = 2;

    // UriMatcher类使用:在ContentProvider 中注册URI
    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        // 若URI资源路径 = content://com.gary.weatherdemo.provider/cur_city_name ，则返回注册码CUR_CITY_NAME_CODE
        mMatcher.addURI(AUTOHORITY, "cur_city_name", CUR_CITY_NAME_CODE);
        mMatcher.addURI(AUTOHORITY, "cur_city_adcode", CUR_CITY_ADCODE_CODE);
    }

    public WeatherContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (mMatcher.match(uri)) {
            case CUR_CITY_NAME_CODE:
                //TODO
                break;
            case CUR_CITY_ADCODE_CODE:
                //TODO
                break;
        }

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
        switch (mMatcher.match(uri)) {
            case CUR_CITY_NAME_CODE:
                break;
            case CUR_CITY_ADCODE_CODE:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}

package com.gary.weatherdemo.provider.db;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by GaryCao on 2019/01/05.
 * 提供供第三方应用统一的DB访问API
 */
public class WtContentProvider extends ContentProvider {
    public static final String AUTOHORITY = "com.gary.weatherdemo.db.provider";

    public static final int CODE_CUR_CITY_WEATHER_INFO = 1;
    public static final int CODE_SELECT_CITY_WEATHER_INFO = 2;

    public static final String PATH_CUR_CITY_WEATHER_INFO = "cur_city_weather_info";
    public static final String PATH_SELECT_CITY_WEATHER_INFO = "select_city_weather_info";

    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTOHORITY, PATH_CUR_CITY_WEATHER_INFO, CODE_CUR_CITY_WEATHER_INFO);
        mMatcher.addURI(AUTOHORITY, PATH_SELECT_CITY_WEATHER_INFO, CODE_SELECT_CITY_WEATHER_INFO);
    }

    public WtContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (mMatcher.match(uri)) {
            case CODE_CUR_CITY_WEATHER_INFO:
                break;
            case CODE_SELECT_CITY_WEATHER_INFO:
                break;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (mMatcher.match(uri)) {
            case CODE_CUR_CITY_WEATHER_INFO:
                //TODO
                break;
            case CODE_SELECT_CITY_WEATHER_INFO:
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
            case CODE_CUR_CITY_WEATHER_INFO:
                break;
            case CODE_SELECT_CITY_WEATHER_INFO:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (mMatcher.match(uri)) {
            case CODE_CUR_CITY_WEATHER_INFO:
                break;
            case CODE_SELECT_CITY_WEATHER_INFO:
                break;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        return super.applyBatch(operations);
    }
}

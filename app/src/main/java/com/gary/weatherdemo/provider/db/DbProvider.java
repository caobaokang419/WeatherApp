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

import com.gary.weatherdemo.room.city.CityBeanEntity;
import com.gary.weatherdemo.room.weather.CityForecastEntity;

import java.util.ArrayList;

/**
 * Created by GaryCao on 2019/01/05.
 * 提供供第三方应用统一的Weather-data访问API
 */
public class DbProvider extends ContentProvider {
    public static final String DB_AUTHORITY = "com.gary.weatherdemo.db.provider";

    public static final int CODE_CITY_CONFIG = 101;
    public static final int CODE_CITY_WEATHER = 201;

    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(DB_AUTHORITY, CityBeanEntity.TABLE_NAME, CODE_CITY_CONFIG);
        mMatcher.addURI(DB_AUTHORITY, CityForecastEntity.TABLE_NAME, CODE_CITY_WEATHER);
    }

    public DbProvider() {
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /*switch (mMatcher.match(uri)) {
            case CityBeanEntity.TABLE_NAME:
                //TODO
                break;
            case CityForecastEntity.TABLE_NAME:
                //TODO
                break;
        }*/

        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        /*switch (mMatcher.match(uri)) {
            case CityBeanEntity.TABLE_NAME:
                break;
            case CityForecastEntity.TABLE_NAME:
                break;
        }*/
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        /*switch (mMatcher.match(uri)) {
            case CityBeanEntity.TABLE_NAME:
                break;
            case CityForecastEntity.TABLE_NAME:
                break;
        }*/

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        /*switch (mMatcher.match(uri)) {
            case CityBeanEntity.TABLE_NAME:
                break;
            case CityForecastEntity.TABLE_NAME:
                break;
        }*/
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        return super.applyBatch(operations);
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

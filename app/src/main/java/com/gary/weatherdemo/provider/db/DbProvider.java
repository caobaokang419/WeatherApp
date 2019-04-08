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

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.room.WtDatabase;
import com.gary.weatherdemo.room.city.CityBeanEntity;
import com.gary.weatherdemo.room.weather.CityForecastEntity;

import java.util.ArrayList;

/**
 * Created by GaryCao on 2019/01/05.
 * 提供供第三方应用统一的Weather-data访问API
 */
public class DbProvider extends ContentProvider {
    public static final String DB_AUTHORITY = "com.gary.weatherdemo.db.provider";

    //高德城市配置表
    public static final int CODE_CITY_CONFIG = 101;
    public static final int CODE_CITY_CONFIG_ID = 102;
    public static final int CODE_CITY_CONFIG_ADCODE = 103;

    //城市天气表
    public static final int CODE_CITY_FORECAST = 201;
    public static final int CODE_CITY_FORECAST_ID = 202;
    public static final int CODE_CITY_FORECAST_ADCODE = 203;

    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(DB_AUTHORITY, CityBeanEntity.TABLE_NAME, CODE_CITY_CONFIG);
        mMatcher.addURI(DB_AUTHORITY, CityBeanEntity.TABLE_NAME + "/#", CODE_CITY_CONFIG_ID);
        mMatcher.addURI(DB_AUTHORITY, CityBeanEntity.TABLE_NAME + "/adcode", CODE_CITY_CONFIG_ADCODE);

        mMatcher.addURI(DB_AUTHORITY, CityForecastEntity.TABLE_NAME, CODE_CITY_FORECAST);
        mMatcher.addURI(DB_AUTHORITY, CityForecastEntity.TABLE_NAME + "/#", CODE_CITY_FORECAST_ID);
        mMatcher.addURI(DB_AUTHORITY, CityForecastEntity.TABLE_NAME + "/adcode", CODE_CITY_FORECAST_ADCODE);
    }

    public DbProvider() {
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (mMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                CityBeanEntity cityBeanEntity = CityBeanEntity.fromContentValues(values);
                WtDatabase.getInstance(WtApplication.getContext()).cityInfoDAO().insert(cityBeanEntity);
                break;
            case CODE_CITY_CONFIG_ID:
                break;
            case CODE_CITY_CONFIG_ADCODE:
                break;

            case CODE_CITY_FORECAST:
                CityForecastEntity cityForecastEntity = CityForecastEntity.fromContentValues(values);
                WtDatabase.getInstance(WtApplication.getContext()).cityForecastDAO().insert(cityForecastEntity);
                break;
            case CODE_CITY_FORECAST_ID:
                break;
            case CODE_CITY_FORECAST_ADCODE:
                break;
            default:
                break;
        }

        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (mMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
            case CODE_CITY_CONFIG_ID:
                break;
            case CODE_CITY_CONFIG_ADCODE:
                String adcode1 = uri.getLastPathSegment();
                WtDatabase.getInstance(WtApplication.getContext())
                        .cityForecastDAO().deleteByCityAdcode(adcode1);
                break;

            case CODE_CITY_FORECAST:
            case CODE_CITY_FORECAST_ID:
                break;
            case CODE_CITY_FORECAST_ADCODE:
                String adcode2 = uri.getLastPathSegment();
                WtDatabase.getInstance(WtApplication.getContext())
                        .cityForecastDAO().deleteByCityAdcode(adcode2);
                break;
            default:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (mMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                break;
            case CODE_CITY_CONFIG_ID:
                break;
            case CODE_CITY_CONFIG_ADCODE:
                break;

            case CODE_CITY_FORECAST:
                break;
            case CODE_CITY_FORECAST_ID:
                break;
            case CODE_CITY_FORECAST_ADCODE:
                break;
            default:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (mMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                break;
            case CODE_CITY_CONFIG_ID:
                break;
            case CODE_CITY_CONFIG_ADCODE:
                break;

            case CODE_CITY_FORECAST:
                break;
            case CODE_CITY_FORECAST_ID:
                break;
            case CODE_CITY_FORECAST_ADCODE:
                break;
            default:
                break;
        }
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

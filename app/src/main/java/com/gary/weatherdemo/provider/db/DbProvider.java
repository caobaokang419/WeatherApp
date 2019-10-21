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
 * <p>
 * 提供供第三方应用统一的Weather-data访问API
 */
public class DbProvider extends ContentProvider {
    public static final String DB_AUTHORITY = "com.gary.weatherdemo.db.provider";
    public static final Uri DB_CONTENT_URI = Uri.parse("content://" + DB_AUTHORITY);

    public static final String CITY_CONFIG_CONTENT_TYPE = "vnd.android.cursor.dir/city_info";
    public static final String CITY_CONFIG_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/city_info";

    public static final String CITY_FORECAST_CONTENT_TYPE = "vnd.android.cursor.dir/city_forecast";
    public static final String CITY_FORECAST_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/city_forecast";

    //高德城市配置表
    public static final int CODE_CITY_CONFIG = 101;
    public static final int CODE_CITY_CONFIG_ITEM = 102;

    //城市天气表
    public static final int CODE_CITY_FORECAST = 201;
    public static final int CODE_CITY_FORECAST_ITEM = 202;

    private static final UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(DB_AUTHORITY, CityBeanEntity.TABLE_NAME, CODE_CITY_CONFIG);
        mUriMatcher.addURI(DB_AUTHORITY, CityBeanEntity.TABLE_NAME + "/#", CODE_CITY_CONFIG_ITEM);

        mUriMatcher.addURI(DB_AUTHORITY, CityForecastEntity.TABLE_NAME, CODE_CITY_FORECAST);
        mUriMatcher.addURI(DB_AUTHORITY, CityForecastEntity.TABLE_NAME + "/#", CODE_CITY_FORECAST_ITEM);
    }

    /**
     * Thread local various
     */
    private ThreadLocal<Boolean> mNeedNotifyChanged = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.valueOf(false);
        }
    };

    public DbProvider() {
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (mUriMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                break;
            case CODE_CITY_CONFIG_ITEM:
                CityBeanEntity cityBeanEntity = CityBeanEntity.fromContentValues(values);
                WtDatabase.getInstance(WtApplication.getContext()).cityInfoDAO().insert(cityBeanEntity);
                break;

            case CODE_CITY_FORECAST:
                break;
            case CODE_CITY_FORECAST_ITEM:
                CityForecastEntity cityForecastEntity = CityForecastEntity.fromContentValues(values);
                WtDatabase.getInstance(WtApplication.getContext()).cityForecastDAO().insert(cityForecastEntity);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        switch (mUriMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                break;
            case CODE_CITY_CONFIG_ITEM:
                break;

            case CODE_CITY_FORECAST:
                break;
            case CODE_CITY_FORECAST_ITEM:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return -1;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (mUriMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                break;
            case CODE_CITY_CONFIG_ITEM:
                String adcode1 = uri.getLastPathSegment();
                WtDatabase.getInstance(WtApplication.getContext()).cityForecastDAO().deleteByCityAdcode(adcode1);
                break;

            case CODE_CITY_FORECAST:
                break;
            case CODE_CITY_FORECAST_ITEM:
                String adcode2 = uri.getLastPathSegment();
                WtDatabase.getInstance(WtApplication.getContext()).cityForecastDAO().deleteByCityAdcode(adcode2);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }


        getContext().getContentResolver().notifyChange(uri, null);
        return -1;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (mUriMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                break;
            case CODE_CITY_CONFIG_ITEM:
                break;

            case CODE_CITY_FORECAST:
                break;
            case CODE_CITY_FORECAST_ITEM:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (mUriMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                break;
            case CODE_CITY_CONFIG_ITEM:
                break;

            case CODE_CITY_FORECAST:
                break;
            case CODE_CITY_FORECAST_ITEM:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return -1;
    }

    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        return super.applyBatch(operations);
    }

    @Override
    public String getType(Uri uri) {
        /*using for */
        switch (mUriMatcher.match(uri)) {
            case CODE_CITY_CONFIG:
                return CITY_CONFIG_CONTENT_TYPE;
            case CODE_CITY_CONFIG_ITEM:
                return CITY_CONFIG_CONTENT_ITEM_TYPE;

            case CODE_CITY_FORECAST:
                return CITY_FORECAST_CONTENT_TYPE;
            case CODE_CITY_FORECAST_ITEM:
                return CITY_FORECAST_CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}

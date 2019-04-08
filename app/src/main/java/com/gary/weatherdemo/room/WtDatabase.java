package com.gary.weatherdemo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.room.city.CityBeanDAO;
import com.gary.weatherdemo.room.city.CityBeanEntity;
import com.gary.weatherdemo.room.weather.CityForecastDAO;
import com.gary.weatherdemo.room.weather.CityForecastEntity;

/**
 * Created by GaryCao on 2018/10/25.
 */
@Database(entities = {CityForecastEntity.class, CityBeanEntity.class}, version = 1, exportSchema = false)
public abstract class WtDatabase extends RoomDatabase {
    public static final String WEATHER_DB_NAME = "weather_db";
    private static WtDatabase mWtDatabase;
    private static final Object mLock = new Object();

    /*城市天气数据DB*/
    public abstract CityForecastDAO cityForecastDAO();

    /*城市配置表DB*/
    public abstract CityBeanDAO cityInfoDAO();

    @Override
    public void clearAllTables() {

    }

    /*TODO: DB耗时操作如何处理:RXJava2(推荐) -- HandleThread(UI订阅复杂) -- AsyncTask(线程阻塞)*/
    public static WtDatabase getInstance(Context context) {
        synchronized (mLock) {
            if (mWtDatabase == null) {
                mWtDatabase =
                        Room.databaseBuilder(context.getApplicationContext(),
                                WtDatabase.class,
                                WtDatabase.WEATHER_DB_NAME)
                                /*.allowMainThreadQueries()*//*db 耗时不应在UI线程发起*/
                                .build();
            }
        }
        return mWtDatabase;
    }

    //===================================================================================================
    //for test
    private void insertCityInfo(CityBeanEntity entity) {
        WtDatabase.getInstance(WtApplication.getContext()).cityInfoDAO().insert(entity);
    }
}
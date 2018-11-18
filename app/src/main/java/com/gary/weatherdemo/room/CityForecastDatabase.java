package com.gary.weatherdemo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by GaryCao on 2018/10/25.
 */
@Database(entities = {CityForecastEntity.class}, version = 1, exportSchema = false)
public abstract class CityForecastDatabase extends RoomDatabase {
    public static final String WEATHER_DB_NAME = "weather_db";
    private static CityForecastDatabase cityForecastDatabase;
    private static final Object lock = new Object();

    public abstract CityForecastDAO cityForecastDAO();

    @Override
    public void clearAllTables() {

    }

    /*TODO: DB耗时操作如何处理:RXJava2(推荐) -- HandleThread(UI订阅复杂) -- AsyncTask(线程阻塞)*/
    public static CityForecastDatabase getInstance(Context context) {
        synchronized (lock) {
            if (cityForecastDatabase == null) {
                cityForecastDatabase =
                        Room.databaseBuilder(context.getApplicationContext(),
                                    CityForecastDatabase.class,
                                    CityForecastDatabase.WEATHER_DB_NAME)
                                /*.allowMainThreadQueries()*//*db 耗时不应在UI线程发起*/
                                .build();
            }
            return cityForecastDatabase;
        }
    }
}
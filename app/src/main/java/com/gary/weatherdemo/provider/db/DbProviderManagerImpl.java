package com.gary.weatherdemo.provider.db;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.DayForecastBean;
import com.gary.weatherdemo.repository.WtRepository;
import com.gary.weatherdemo.room.WtDatabase;
import com.gary.weatherdemo.room.city.CityBeanEntity;
import com.gary.weatherdemo.room.weather.CityForecastEntity;

import java.security.Provider;
import java.util.List;

/**
 * Created by GaryCao on 2019/01/05.
 * <p>
 * db data: across-process global access entries
 */
public class DbProviderManagerImpl implements DbProviderManager {
    private static final int MAX_BATCH_APPLY_NUM = 100;

    private static DbProviderManagerImpl dbProviderManagerImpl;
    private Context context;

    private DbProviderManagerImpl() {
        context = WtApplication.getContext();
    }

    public synchronized static void registerInstance() {
        if (dbProviderManagerImpl == null) {
            dbProviderManagerImpl = new DbProviderManagerImpl();
        }

        WtRepository.Ext.setDbProviderManager(dbProviderManagerImpl);
    }


    @Override
    public List<CityBean> getAllCityBeans() {
        return null;
    }

    @Override
    public void insertCityBean(CityBean cityBean) {
        ContentValues values = CityBeanEntity.toContentValues(cityBean);
        context.getContentResolver().insert(CityBeanEntity.DB_CONTENT_URI, values);
    }

    @Override
    public void bulkInsertCityBeans(CityBean[] cityBeans) {
        if (cityBeans == null || cityBeans.length <= 0) {
            return;
        }

        int length = cityBeans.length;
        for (int startIndex = 0; startIndex < length; ) {
            int endIndex;
            if ((length - startIndex) <= MAX_BATCH_APPLY_NUM) {
                endIndex = length - 1;
                bulkInsertBatchCityBeans(cityBeans, startIndex, endIndex);
                break;
            } else {
                endIndex = startIndex + MAX_BATCH_APPLY_NUM;
                bulkInsertBatchCityBeans(cityBeans, startIndex, endIndex);
                startIndex = endIndex + 1;
            }
        }
    }

    private void bulkInsertBatchCityBeans(CityBean[] cityBeans, int start, int end) {
        int length = end - start + 1;
        ContentValues[] values = new ContentValues[length];
        for (int index = 0; index < length; index++) {
            values[index] = CityBeanEntity.toContentValues(cityBeans[start + index]);
        }
        context.getContentResolver().bulkInsert(CityBeanEntity.DB_CONTENT_URI, values);
    }

    @Override
    public List<DayForecastBean> getCityForecast(CityBean cityBean) {
        return null;
    }

    @Override
    public void insertCityForecast(DayForecastBean forecastBean) {
        ContentValues values = CityForecastEntity.toContentValues(forecastBean);
        context.getContentResolver().insert(CityForecastEntity.DB_CONTENT_URI, values);
    }
}

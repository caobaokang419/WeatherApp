package com.gary.weatherdemo.room.weather;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by GaryCao on 2018/10/25.
 */
@Dao
public interface CityForecastDAO {
    @Query("SELECT * FROM city_forecast")
    List<CityForecastEntity> getAll();

    @Query("SELECT * FROM  city_forecast WHERE forecast_id IN (:ids)")
    List<CityForecastEntity> getAllByIds(long[] ids);

    @Insert
    void insert(CityForecastEntity... entities);

    @Delete
    void delete(CityForecastEntity entity);

    @Query("DELETE FROM city_forecast  WHERE adcode LIKE :cityAdcode")
    void deleteByCityAdcode(String cityAdcode);

    @Query("DELETE FROM city_forecast")
    void deleteAll();

    @Update
    void update(CityForecastEntity entity);
}
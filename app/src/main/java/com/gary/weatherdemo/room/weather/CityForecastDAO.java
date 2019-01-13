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
    @Query("SELECT * FROM " + CityForecastEntity.TABLE_NAME)
    List<CityForecastEntity> getAll();

    @Query("SELECT * FROM " + CityForecastEntity.TABLE_NAME + " WHERE id IN (:ids)")
    List<CityForecastEntity> getAllByIds(long[] ids);

    @Insert
    void insert(CityForecastEntity... entities);

    @Delete
    void delete(CityForecastEntity entity);

    @Update
    void update(CityForecastEntity entity);
}
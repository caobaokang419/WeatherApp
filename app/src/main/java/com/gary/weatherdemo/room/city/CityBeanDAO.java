package com.gary.weatherdemo.room.city;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gary.weatherdemo.room.weather.CityForecastEntity;

import java.util.List;

/**
 * Created by GaryCao on 2019/01/13.
 * 高德城市配置信息DAO
 */
@Dao
public interface CityBeanDAO {
    @Query("SELECT * FROM city_info")
    List<CityBeanEntity> getAll();

    @Query("SELECT * FROM city_info WHERE city_id IN (:ids)")
    List<CityBeanEntity> getAllByIds(long[] ids);

    @Insert
    void insert(CityBeanEntity... entities);

    @Delete
    void delete(CityBeanEntity entity);

    @Query("DELETE FROM city_info  WHERE adcode LIKE :cityAdcode")
    void deleteByCityAdcode(String cityAdcode);

    @Query("DELETE FROM city_info")
    void deleteAll();

    @Update
    void update(CityBeanEntity entity);
}
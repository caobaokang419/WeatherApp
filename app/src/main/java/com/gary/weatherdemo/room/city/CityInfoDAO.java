package com.gary.weatherdemo.room.city;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by GaryCao on 2019/01/13.
 * 高德城市配置信息DAO
 */
@Dao
public interface CityInfoDAO {
    @Query("SELECT * FROM " + CityInfoEntity.TABLE_NAME)
    List<CityInfoEntity> getAll();

    @Query("SELECT * FROM " + CityInfoEntity.TABLE_NAME + " WHERE id IN (:ids)")
    List<CityInfoEntity> getAllByIds(long[] ids);

    @Insert
    void insert(CityInfoEntity... entities);

    @Delete
    void delete(CityInfoEntity entity);

    @Update
    void update(CityInfoEntity entity);
}
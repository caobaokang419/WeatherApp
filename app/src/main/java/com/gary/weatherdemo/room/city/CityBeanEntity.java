package com.gary.weatherdemo.room.city;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

import com.gary.weatherdemo.bean.CityBean;

/**
 * Created by GaryCao on 2019/01/13.
 * 高德城市配置表DB Table
 */
@Entity(tableName = CityBeanEntity.TABLE_NAME)
public class CityBeanEntity {
    public static final String TABLE_NAME = "city_info";
    public static final String COLUMN_CITY_NAME = "city_name";
    public static final String COLUMN_CITY_CODE = "adcode";

    @PrimaryKey
    public long id;

    @ColumnInfo(name = COLUMN_CITY_NAME)
    public String city_name;

    @ColumnInfo(name = COLUMN_CITY_CODE)
    public String adcode;

    public void setCityName(String cityname) {
        city_name = cityname;
    }

    public String getCityName() {
        return city_name;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getAdcode() {
        return adcode;
    }

    public static ContentValues toContentValues(CityBean cityBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CITY_NAME, cityBean.cityName);
        contentValues.put(COLUMN_CITY_CODE, cityBean.cityCode);
        return contentValues;
    }

    public static CityBeanEntity fromContentValues(ContentValues contentValues) {
        CityBeanEntity cityBeanEntity = new CityBeanEntity();
        if (contentValues == null) {
            return cityBeanEntity;
        }
        String cityName = contentValues.getAsString(COLUMN_CITY_NAME);
        cityBeanEntity.setCityName(cityName);
        String cityAdcode = contentValues.getAsString(COLUMN_CITY_CODE);
        cityBeanEntity.setAdcode(cityAdcode);
        return cityBeanEntity;
    }

    public static CityBeanEntity fromCityBean(CityBean cityBean) {
        if (cityBean == null) {
            return null;
        }
        CityBeanEntity entity = new CityBeanEntity();
        entity.setAdcode(cityBean.cityCode);
        entity.setCityName(cityBean.cityName);
        return entity;
    }

    public static CityBean toCityInfoEntity(CityBeanEntity entity) {
        return entity == null ? null : new CityBean(entity.adcode, entity.city_name);
    }
}
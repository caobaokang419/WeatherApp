package com.gary.weatherdemo.room.city;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by GaryCao on 2019/01/13.
 * 高德城市配置信息DB Table
 */
@Entity(tableName = CityInfoEntity.TABLE_NAME)
public class CityInfoEntity {
    public static final String TABLE_NAME = "city_info";

    @PrimaryKey
    public long id;

    @ColumnInfo(name = "city_name")
    public String city_name;

    @ColumnInfo(name = "adcode")
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
}
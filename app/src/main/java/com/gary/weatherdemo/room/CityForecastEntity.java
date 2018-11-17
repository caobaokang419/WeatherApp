package com.gary.weatherdemo.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by GaryCao on 2018/10/25.
 */
@Entity(tableName = CityForecastEntity.TABLE_NAME)
public class CityForecastEntity {
    public static final String TABLE_NAME = "city_forecast";

    @PrimaryKey
    public long id;

    @ColumnInfo(name = "city_name")
    public String city_name;

    @ColumnInfo(name = "adcode")
    public String adcode;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "week")
    public String week;

    @ColumnInfo(name = "dayweather")
    public String dayweather;

    @ColumnInfo(name = "nightweather")
    public String nightweather;

    @ColumnInfo(name = "daywind")
    public String daywind;

    @ColumnInfo(name = "nightwind")
    public String nightwind;

    @ColumnInfo(name = "daypower")
    public String daypower;

    @ColumnInfo(name = "nightpower")
    public String nightpower;

    public void setCityName(String cityname) {
        city_name = cityname;
    }

    public String getCityName() {
        return city_name;
    }

    public void setForecastDate(String date) {
        this.date = date;
    }

    public String getForecastDate() {
        return date;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public void setDayWeather(String dayweather) {
        this.dayweather = dayweather;
    }

    public void setNightWeather(String nightweather) {
        this.nightweather = nightweather;
    }

    public void setDayWind(String daywind) {
        this.daywind = daywind;
    }

    public void setNightWind(String nightwind) {
        this.nightwind = nightwind;
    }

    public void setDayPower(String daypower) {
        this.daypower = daypower;
    }

    public void setNightPower(String nightpower) {
        this.daypower = daypower;
    }
}
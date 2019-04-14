package com.gary.weatherdemo.room.weather;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.net.Uri;

import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.DayForecastBean;
import com.gary.weatherdemo.provider.db.DbProvider;
import com.gary.weatherdemo.room.city.CityBeanEntity;

/**
 * Created by GaryCao on 2018/10/25.
 * 高德城市天气DB Table
 */
@Entity(tableName = CityForecastEntity.TABLE_NAME)
public class CityForecastEntity {
    public static final String TABLE_NAME = "city_forecast";
    public static final String COLUMN_NAME = "city_name";
    public static final String COLUMN_CODE = "adcode";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_WEEK = "week";
    public static final String COLUMN_DAY_WEATHER = "dayweather";
    public static final String COLUMN_NIGHT_WEATHER = "nightweather";
    public static final String COLUMN_DAY_WIND = "daywind";
    public static final String COLUMN_NIGHT_WIND = "nightwind";
    public static final String COLUMN_DAY_POWER = "daypower";
    public static final String COLUMN_NIGHT_POWER = "nightpower";

    public static final Uri DB_CONTENT_URI
            = Uri.parse("content://" + DbProvider.DB_AUTHORITY + "/" + TABLE_NAME);

    @PrimaryKey
    public long id;

    @ColumnInfo(name = COLUMN_NAME)
    public String city_name;

    @ColumnInfo(name = COLUMN_CODE)
    public String adcode;

    @ColumnInfo(name = COLUMN_DATE)
    public String date;

    @ColumnInfo(name = COLUMN_WEEK)
    public String week;

    @ColumnInfo(name = COLUMN_DAY_WEATHER)
    public String dayweather;

    @ColumnInfo(name = COLUMN_NIGHT_WEATHER)
    public String nightweather;

    @ColumnInfo(name = COLUMN_DAY_WIND)
    public String daywind;

    @ColumnInfo(name = COLUMN_NIGHT_WIND)
    public String nightwind;

    @ColumnInfo(name = COLUMN_DAY_POWER)
    public String daypower;

    @ColumnInfo(name = COLUMN_NIGHT_POWER)
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

    public void setWeek(String week) {
        this.week = week;
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
        this.nightpower = nightpower;
    }

    public static ContentValues toContentValues(DayForecastBean forecastBean) {
        ContentValues contentValues = new ContentValues();

        /*contentValues.put(COLUMN_NAME, );
        contentValues.put(COLUMN_CODE, );*/
        contentValues.put(COLUMN_DATE, forecastBean.date);
        contentValues.put(COLUMN_WEEK, forecastBean.week);
        contentValues.put(COLUMN_DAY_WEATHER, forecastBean.dayweather);
        contentValues.put(COLUMN_NIGHT_WEATHER, forecastBean.nightweather);
        contentValues.put(COLUMN_DAY_WIND, forecastBean.daywind);
        contentValues.put(COLUMN_NIGHT_WIND, forecastBean.nightwind);
        contentValues.put(COLUMN_DAY_POWER, forecastBean.daypower);
        contentValues.put(COLUMN_NIGHT_POWER, forecastBean.nightpower);
        return contentValues;
    }

    public static CityForecastEntity fromContentValues(ContentValues contentValues) {
        CityForecastEntity cityForecastEntity = new CityForecastEntity();
        if (contentValues == null) {
            return cityForecastEntity;
        }
        cityForecastEntity.setCityName(contentValues.getAsString(COLUMN_NAME));
        cityForecastEntity.setAdcode(contentValues.getAsString(COLUMN_CODE));
        cityForecastEntity.setForecastDate(contentValues.getAsString(COLUMN_DATE));
        cityForecastEntity.setWeek(contentValues.getAsString(COLUMN_WEEK));
        cityForecastEntity.setDayWeather(contentValues.getAsString(COLUMN_DAY_WEATHER));
        cityForecastEntity.setNightWeather(contentValues.getAsString(COLUMN_NIGHT_WEATHER));
        cityForecastEntity.setDayWind(contentValues.getAsString(COLUMN_DAY_WIND));
        cityForecastEntity.setNightWind(contentValues.getAsString(COLUMN_NIGHT_WIND));
        cityForecastEntity.setDayPower(contentValues.getAsString(COLUMN_DAY_POWER));
        cityForecastEntity.setNightPower(contentValues.getAsString(COLUMN_NIGHT_POWER));
        return cityForecastEntity;
    }
}
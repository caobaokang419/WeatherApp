package com.gary.weatherdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class DayForecastData {
    @SerializedName("date")
    @Expose
    public final String date = null;

    @SerializedName("week")
    @Expose
    public final String week = null;

    @SerializedName("dayweather")
    @Expose
    public final String dayweather = null;

    @SerializedName("nightweather")
    @Expose
    public final String nightweather = null;

    @SerializedName("daytemp")
    @Expose
    public final String daytemp = null;

    @SerializedName("nighttemp")
    @Expose
    public final String nighttemp = null;

    @SerializedName("daywind")
    @Expose
    public final String daywind = null;

    @SerializedName("nightwind")
    @Expose
    public final String nightwind = null;

    @SerializedName("daypower")
    @Expose
    public final String daypower = null;

    @SerializedName("nightpower")
    @Expose
    public final String nightpower = null;


    @Override
    public String toString() {
        return "[date = " + date
                + ",week =" + week
                + ",dayweather =" + dayweather
                + ",nightweather =" + nightweather
                + ",daytemp =" + daytemp
                + ",nighttemp =" + nighttemp
                + ",daywind =" + daywind
                + ",nightwind =" + nightwind
                + ",daypower =" + daypower
                + ",nightpower =" + nightpower
                +"]";
    }
}

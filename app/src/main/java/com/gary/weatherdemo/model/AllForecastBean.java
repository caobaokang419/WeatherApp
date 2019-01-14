package com.gary.weatherdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by GaryCao on 2018/10/25.
 */
public class AllForecastBean {
    @SerializedName("city")
    @Expose
    public final String city = null;

    @SerializedName("adcode")
    @Expose
    public final String adcode = null;

    @SerializedName("province")
    @Expose
    public final String province = null;

    @SerializedName("reporttime")
    @Expose
    public final String reporttime = null;

    @SerializedName("casts")
    @Expose
    public final List<DayForecastBean> dayForecastBeanList = null;

    @Override
    public String toString() {
        return "AllForecastBean: province = " + province
                + ",city =" + city
                + ",adcode =" + adcode
                + ",reporttime" + reporttime;
    }
}

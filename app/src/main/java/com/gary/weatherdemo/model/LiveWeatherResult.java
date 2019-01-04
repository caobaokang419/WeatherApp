package com.gary.weatherdemo.model;

import com.gary.weatherdemo.model.base.BaseItemDataBean;
import com.gary.weatherdemo.model.base.ItemViewType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class LiveWeatherResult extends BaseItemDataBean {
    @SerializedName("province")
    @Expose
    public final String province = null;

    @SerializedName("city")
    @Expose
    public final String city = null;

    @SerializedName("adcode")
    @Expose
    public final String adcode = null;

    @SerializedName("weather")
    @Expose
    public final String weather = null;

    @SerializedName("temperature")
    @Expose
    public final String temperature = null;

    @SerializedName("winddirection")
    @Expose
    public final String winddirection = null;

    @SerializedName("windpower")
    @Expose
    public final String windpower = null;

    @SerializedName("humidity")
    @Expose
    public final String humidity = null;

    @SerializedName("reporttime")
    @Expose
    public final String reporttime = null;

    LiveWeatherResult(){
        super(ItemViewType.RV_CURRENT_WEATHER);
    }

    @Override
    public String toString() {
        return "LiveWeatherResult: province = " + province
                + ",city =" + city
                + ",adcode =" + adcode
                + ",weather =" + weather
                + ",temperature =" + temperature
                + ",winddirection =" + winddirection
                + ",windpower =" + windpower
                + ",humidity =" + humidity;
    }
}

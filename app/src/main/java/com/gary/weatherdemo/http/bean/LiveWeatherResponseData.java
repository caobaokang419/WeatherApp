package com.gary.weatherdemo.http.bean;

import com.gary.weatherdemo.bean.LiveWeatherBean;
import com.gary.weatherdemo.http.bean.base.BaseResponseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class LiveWeatherResponseData extends BaseResponseData {
    @SerializedName("lives")
    @Expose
    private final List<LiveWeatherBean> liveWeatherBeanList = null;

    public LiveWeatherBean getWeatherLiveResult() {
        return liveWeatherBeanList ==null ? null: liveWeatherBeanList.get(0);
    }
}

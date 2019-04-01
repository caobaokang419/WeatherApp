package com.gary.weatherdemo.http.response;

import com.gary.weatherdemo.bean.AllForecastBean;
import com.gary.weatherdemo.http.response.base.BaseResponseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class AllForecastResponseData extends BaseResponseData {
    @SerializedName("forecasts")
    @Expose
    private final List<AllForecastBean> allForecastBeanList = null;

    public AllForecastBean getWeatherAllResult() {
        return allForecastBeanList == null ? null : allForecastBeanList.get(0);
    }
}

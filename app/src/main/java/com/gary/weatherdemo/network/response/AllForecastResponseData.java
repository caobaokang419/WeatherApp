package com.gary.weatherdemo.network.response;

import com.gary.weatherdemo.model.AllForecastResult;
import com.gary.weatherdemo.network.response.base.BaseResponseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class AllForecastResponseData extends BaseResponseData {
    @SerializedName("forecasts")
    @Expose
    private final List<AllForecastResult> allForecastResultList = null;

    public AllForecastResult getWeatherAllResult() {
        return allForecastResultList == null ? null : allForecastResultList.get(0);
    }
}

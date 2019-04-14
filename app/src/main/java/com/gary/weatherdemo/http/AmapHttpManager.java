package com.gary.weatherdemo.http;

import com.gary.weatherdemo.http.response.AllForecastResponseData;
import com.gary.weatherdemo.http.response.LiveWeatherResponseData;

import io.reactivex.Observable;

/**
 * Created by GaryCao on 2018/10/25.
 * <p>
 * sp configs:across-process global access entries
 */
public interface AmapHttpManager {
    /*高德天气：当前天气查询接口 Post*/
    Observable<LiveWeatherResponseData> liveWeatherPost(final String adcode);

    /*高德天气：预报天气查询接口 Post*/
    Observable<AllForecastResponseData> forecastWeatherPost(final String adcode);

    /*高德天气：当前天气查询接口 Get*/
    Observable<LiveWeatherResponseData> livesWeatherGet(final String adcode);

    /*高德天气：预报天气查询接口 Get*/
    Observable<AllForecastResponseData> forecastWeatherGet(final String adcode);
}
package com.gary.weatherdemo.network;

import com.gary.weatherdemo.network.response.AllForecastResponseData;
import com.gary.weatherdemo.network.response.LiveWeatherResponseData;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class WeatherRequestClient {
    private static WeatherRequestClient weatherRequestClient;
    private static ApiService apiService;

    private WeatherRequestClient() {
        createApiClient();
    }

    private ApiService createApiClient() {
        if (apiService == null) {
            apiService = RetrofitManager.getInstance().create(ApiService.class);
        }
        return apiService;
    }

    public static WeatherRequestClient getInstance() {
        synchronized (WeatherRequestClient.class) {
            if (weatherRequestClient == null) {
                weatherRequestClient = new WeatherRequestClient();
            }
        }
        return weatherRequestClient;
    }

    /*高德天气：当前天气查询接口 Post*/
    public Observable<LiveWeatherResponseData> liveWeatherPost(final String adcode) {
        return apiService.livesweatherPost(adcode,
                ApiContants.AMAP_USER_KEY_VALUE,
                ApiContants.AMAP_USER_EXTENSION_VALUE_BASE,
                ApiContants.AMAP_USER_OUTPUT_VALUE)
                .subscribeOn(Schedulers.io());
    }

    /*高德天气：预报天气查询接口 Post*/
    public Observable<AllForecastResponseData> forecastWeatherPost(final String adcode) {
        return apiService.allweatherPost(adcode,
                ApiContants.AMAP_USER_KEY_VALUE,
                ApiContants.AMAP_USER_EXTENSION_VALUE_ALL,
                ApiContants.AMAP_USER_OUTPUT_VALUE)
                .subscribeOn(Schedulers.io());
    }

    /*高德天气：预报天气查询接口 Get*/
    /*public Observable<AllForecastResponseData> forecastWeatherGet(final String adcode) {
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=3b6729d0c40f23fde7c55ae90ee0921d&extensions=all&output=JSON\n";
        return apiService.weatherGet(url).subscribeOn(Schedulers.io());
    }*/

    /*高德天气：当前天气查询接口 Get*/
    public Observable<LiveWeatherResponseData> livesWeatherGet(final String adcode) {
        return apiService.livesweatherGet(adcode,
                ApiContants.AMAP_USER_KEY_VALUE,
                ApiContants.AMAP_USER_EXTENSION_VALUE_ALL,
                ApiContants.AMAP_USER_OUTPUT_VALUE)
                .subscribeOn(Schedulers.io());
    }

    /*高德天气：预报天气查询接口 Get*/
    public Observable<AllForecastResponseData> forecastWeatherGet(final String adcode) {
        return apiService.allweatherGet(adcode,
                ApiContants.AMAP_USER_KEY_VALUE,
                ApiContants.AMAP_USER_EXTENSION_VALUE_ALL,
                ApiContants.AMAP_USER_OUTPUT_VALUE)
                .subscribeOn(Schedulers.io());
    }

    //===================================================================================================
    //for test
    /*高德天气：当前天气查询接口:深圳*/
    private void getShenzhenForecastWeather() {
        WeatherRequestClient.getInstance().liveWeatherPost("440300");//深圳:adcode:440300 citycode:0755
    }
}
package com.gary.weatherdemo.repository;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.network.WeatherRequestClient;
import com.gary.weatherdemo.network.response.AllForecastResponseData;
import com.gary.weatherdemo.network.response.LiveWeatherResponseData;
import com.gary.weatherdemo.room.WtDatabase;
import com.gary.weatherdemo.room.city.CityInfoEntity;

import io.reactivex.Observable;


/**
 * Created by GaryCao on 2019/03/30.
 * GoF23 设计模式 10：外观模式: db+http apis
 */
public class WtRepository {
    public static void insertCityBean(CityBean cityBean){
        CityInfoEntity entity = CityInfoEntity.fromCityBean(cityBean);
        WtDatabase.getInstance(WtApplication.getInstance()).cityInfoDAO().insert(entity);
    }

    public static Observable<LiveWeatherResponseData> queryCityCurWeather(CityBean cityBean){
        return WeatherRequestClient.getInstance().liveWeatherPost(cityBean.adcCode);
    }

    public static Observable<AllForecastResponseData> queryCityForecast(CityBean cityBean){
        return WeatherRequestClient.getInstance().forecastWeatherPost(cityBean.adcCode);
    }
}

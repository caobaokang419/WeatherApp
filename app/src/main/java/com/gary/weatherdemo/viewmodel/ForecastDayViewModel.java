package com.gary.weatherdemo.viewmodel;

import com.gary.weatherdemo.model.DayForecastData;

public class ForecastDayViewModel {
    public final DayForecastData weatherForecast;

    public ForecastDayViewModel(DayForecastData DayForecastData) {
        weatherForecast = DayForecastData;
    }

    //==============================================================================================
    //for test //TODO :需考虑并发和耗时
    private void insertCityForecastInfo(){
        /*CityForecastEntity entity = new CityForecastEntity(weatherForecast);
        entity.setAdcode("440300"); //深圳:adcode:440300 citycode:0755
        entity.setCityName("深圳");
        WtDatabase.getInstance().getDb().cityForecastDAO().insert(entity);*/
    }
}

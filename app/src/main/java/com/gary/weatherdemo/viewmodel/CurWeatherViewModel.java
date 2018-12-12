package com.gary.weatherdemo.viewmodel;

import com.gary.weatherdemo.model.LiveWeatherResult;

public class CurWeatherViewModel {
    public final LiveWeatherResult liveWeatherResult;

    public CurWeatherViewModel(LiveWeatherResult result) {
        liveWeatherResult = result;
    }
}

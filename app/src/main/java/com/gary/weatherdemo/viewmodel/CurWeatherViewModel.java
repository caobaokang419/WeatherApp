package com.gary.weatherdemo.viewmodel;

import com.gary.weatherdemo.model.LiveWeatherBean;

public class CurWeatherViewModel {
    public final LiveWeatherBean liveWeatherBean;

    public CurWeatherViewModel(LiveWeatherBean result) {
        liveWeatherBean = result;
    }
}

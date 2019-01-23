package com.gary.weatherdemo.viewmodel;

import com.gary.weatherdemo.model.LiveWeatherBean;

public class CurWeatherViewModel {
    public final LiveWeatherBean mLiveWeatherBean;

    public CurWeatherViewModel(LiveWeatherBean result) {
        mLiveWeatherBean = result;
    }
}

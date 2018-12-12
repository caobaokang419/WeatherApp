package com.gary.weatherdemo.bean.uibean;

import com.gary.weatherdemo.model.LiveWeatherResult;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class CurWeatherDataBean extends BaseItemDataBean<LiveWeatherResult>{
    public CurWeatherDataBean(LiveWeatherResult liveWeatherResult) {
        super(ViewItemType.RV_CURRENT_WEATHER,liveWeatherResult);
    }
}

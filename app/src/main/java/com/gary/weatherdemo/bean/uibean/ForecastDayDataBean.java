package com.gary.weatherdemo.bean.uibean;

import com.gary.weatherdemo.model.DayForecastData;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class ForecastDayDataBean extends BaseItemDataBean<DayForecastData>{
    public ForecastDayDataBean(DayForecastData dayForecastData) {
        super(ViewItemType.RV_FORECAST_DAY_WEATHER,dayForecastData);
    }
}

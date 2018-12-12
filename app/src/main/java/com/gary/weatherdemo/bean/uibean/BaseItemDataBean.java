package com.gary.weatherdemo.bean.uibean;

/**
 * Created by GaryCao on 2018/12/12.
 */
public abstract class BaseItemDataBean<T> {
    final T data;
    private final ViewItemType viewItemType;

    public BaseItemDataBean(ViewItemType viewItemType, T data) {
        this.viewItemType = viewItemType;
        this.data = data;
    }

    public enum ViewItemType {
        RV_CURRENT_WEATHER,
        RV_FORECAST_DAY_WEATHER
    }
}

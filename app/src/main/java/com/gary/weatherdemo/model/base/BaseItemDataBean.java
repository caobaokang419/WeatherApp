package com.gary.weatherdemo.model.base;

/**
 * Created by GaryCao on 2018/12/12.
 */
public abstract class BaseItemDataBean{
    private final int viewItemType;

    public BaseItemDataBean(int viewItemType) {
        this.viewItemType = viewItemType;
    }

    public int getViewItemType() {
        return viewItemType;
    }
}

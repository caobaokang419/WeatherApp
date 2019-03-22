package com.gary.weatherdemo.bean.base;

/**
 * Created by GaryCao on 2018/12/12.
 */
public abstract class BaseItemBean {
    private final int mViewItemType;

    public BaseItemBean(int viewItemType) {
        this.mViewItemType = viewItemType;
    }

    public int getViewItemType() {
        return mViewItemType;
    }
}

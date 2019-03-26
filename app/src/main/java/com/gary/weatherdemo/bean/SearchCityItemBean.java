package com.gary.weatherdemo.bean;

import com.gary.weatherdemo.bean.base.BaseItemBean;
import com.gary.weatherdemo.bean.base.ItemViewType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class SearchCityItemBean extends BaseItemBean {
    private CityBean mCityBean;

    public SearchCityItemBean(CityBean cityBean) {
        super(ItemViewType.RV_SEARCH_CITY_ITEM_TYPE);
        mCityBean = cityBean;
    }

    public void setCityBean(CityBean mCityBean) {
        this.mCityBean = mCityBean;
    }

    public CityBean getCityBean() {
        return mCityBean;
    }

    @Override
    public String toString() {
        return "SearchCityItemBean: " + mCityBean.toString();
    }
}

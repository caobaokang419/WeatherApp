package com.gary.weatherdemo.bean;

import com.gary.weatherdemo.bean.base.BaseItemBean;
import com.gary.weatherdemo.bean.base.ItemViewType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class SearchCityItemBean extends BaseItemBean {
    public String adrName;
    public String adcCode;

    SearchCityItemBean() {
        super(ItemViewType.RV_SEARCH_CITY_ITEM_TYPE);
    }

    public void setAdcCode(String adcCode) {
        this.adcCode = adcCode;
    }

    public void setAdrName(String adrName) {
        this.adrName = adrName;
    }

    @Override
    public String toString() {
        return "SearchCityItemBean: adrName = " + adrName + ",adcCode =" + adcCode;
    }
}

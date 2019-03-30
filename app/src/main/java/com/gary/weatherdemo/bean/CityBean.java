package com.gary.weatherdemo.bean;

/**
 * Created by GaryCao on 2018/10/28.
 */
public class CityBean {
    public String cityName;
    public String cityCode;

    public CityBean(String addr, String adcode) {
        cityName = addr;
        cityCode = adcode;
    }

    @Override
    public String toString() {
        return "CityBean: cityName = " + cityName + ",cityCode =" + cityCode;
    }
}

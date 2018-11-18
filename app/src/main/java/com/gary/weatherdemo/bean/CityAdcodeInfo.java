package com.gary.weatherdemo.bean;

/**
 * Created by GaryCao on 2018/10/28.
 */
public class CityAdcodeInfo {
    public String adrName;
    public String adcCode;

    public CityAdcodeInfo(String addr, String adcode) {
        adrName = addr;
        adcCode = adcode;
    }
    public boolean isAddrSearched(String addr) {
        return null != addr && null != adrName && adrName.indexOf(addr) == 0;
    }

    @Override
    public String toString() {
        return "CityAdcodeInfo: adrName = " + adrName + ",adcCode =" + adcCode;
    }
}

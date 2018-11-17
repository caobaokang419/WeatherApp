package com.gary.weatherdemo.bean;

/**
 * Created by GaryCao on 2018/10/28.
 */
public class AdcodeConfigInfo {
    public String adrName;
    public String adcCode;

    public AdcodeConfigInfo(String addr, String adcode) {
        adrName = addr;
        adcCode = adcode;
    }

    /*TBD : waiting for modified here*/
    public boolean isAddrSearched(String addr) {
        return null != addr && null != adrName && adrName.indexOf(addr) == 0;
    }

    @Override
    public String toString() {
        return "AdcodeConfigInfo: adrName = " + adrName + ",adcCode =" + adcCode;
    }
}

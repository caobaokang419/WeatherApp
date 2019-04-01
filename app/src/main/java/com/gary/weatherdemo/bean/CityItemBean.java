package com.gary.weatherdemo.bean;

import android.support.annotation.NonNull;

import com.gary.weatherdemo.bean.base.BaseItemBean;
import com.gary.weatherdemo.pinyin.HanziToPinyinUtils;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class CityItemBean extends BaseItemBean implements Comparable<CityItemBean> {
    private CityBean mCityBean;
    private String mCityName;
    private String mCityCode;
    private String mCityNameFirstChar; //首字母
    private String mCityNameFirstLetter; //首個汉字拼音
    private String mCityBeanPinYin;//汉字全拼音

    public CityItemBean(CityBean cityBean) {
        super(ItemViewType.RV_SEARCH_CITY_ITEM_TYPE);
        mCityName = cityBean.cityName;
        mCityCode = cityBean.cityCode;
        mCityBeanPinYin = HanziToPinyinUtils.getPinYin(mCityName);
        mCityNameFirstChar = HanziToPinyinUtils.getPinYinHeadChar(mCityName);
        mCityNameFirstLetter = HanziToPinyinUtils.getPinYinFirstLetter(mCityName);
    }

    public void setCityBean(CityBean mCityBean) {
        this.mCityBean = mCityBean;
    }

    public CityBean getCityBean() {
        return mCityBean;
    }

    /**
     * 拼音|汉字是否匹配 判断
     */
    public boolean isSearched(String cityname) {
        return null != mCityName
                && null != cityname
                && (mCityName.contains(cityname) || mCityBeanPinYin.contains(cityname));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CityItemBean) {
            return false;
        }

        if (obj == null || mCityBean == null) {
            return false;
        }

        return ((CityItemBean) obj).mCityBean.cityCode.equals(mCityBean.cityCode);
    }

    @Override
    public int hashCode() {
        int index = 1;
        index = index * 31 + Integer.valueOf(mCityBean.cityCode);
        return index;
    }

    @Override
    public int compareTo(@NonNull CityItemBean cityItemBean) {
        return 0;
    }

    @Override
    public String toString() {
        return "CityItemBean: " + mCityBean.toString();
    }
}

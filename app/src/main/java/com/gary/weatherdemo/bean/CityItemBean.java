package com.gary.weatherdemo.bean;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.gary.weatherdemo.pinyin.HanziToPinyinHelper;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class CityItemBean implements IViewItemBean, Comparable<CityItemBean> {
    private CityBean mCityBean;
    private String mCityName;
    private String mCityCode;
    private String mCityNameFirstChar; //首字母
    private String mCityNameFirstLetter; //首個汉字拼音
    private String mCityBeanPinYin;//汉字全拼音

    public CityItemBean(CityBean cityBean) {
        mCityBean = cityBean;
        if (cityBean != null) {
            mCityName = cityBean.cityName;
            mCityCode = cityBean.cityCode;
        }
        if (mCityName != null) {
            mCityBeanPinYin = HanziToPinyinHelper.getPinYin(mCityName);
            mCityNameFirstChar = HanziToPinyinHelper.getPinYinHeadChar(mCityName);
            mCityNameFirstLetter = HanziToPinyinHelper.getPinYinFirstLetter(mCityName);
        }
    }

    @Override
    public ItemViewType getViewItemType() {
        return ItemViewType.RV_SEARCH_CITY_ITEM_TYPE;
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
        index = index * 31 + Integer.parseInt(mCityBean.cityCode);
        return index;
    }

    /**
     * @return 1:bigger -1:smaller
     */
    @Override
    public int compareTo(@NonNull CityItemBean cityItemBean) {
        if (cityItemBean == null || TextUtils.isEmpty(cityItemBean.mCityCode)) {
            return 1;
        }

        if (mCityBean == null || TextUtils.isEmpty(mCityCode)) {
            return -1;
        }

        return (Integer.valueOf(mCityCode)).compareTo(Integer.valueOf(cityItemBean.mCityCode));
    }

    @Override
    public String toString() {
        return "CityItemBean: " + mCityBean.toString();
    }
}

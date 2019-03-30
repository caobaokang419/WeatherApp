package com.gary.weatherdemo.ui.adapter;

import com.gary.weatherdemo.ui.adapter.base.CommonRecyclerAdapter;

public class CitySearchRecyclerAdapter extends CommonRecyclerAdapter {
    public enum CityListMode {
        CITY_LIST_NORMAL_MODE,
        CITY_LIST_SEARCH_MODE
    }
    private CityListMode mCurMode = CityListMode.CITY_LIST_NORMAL_MODE;

    public CitySearchRecyclerAdapter() {
    }

    public void setCurMode(CityListMode mode){
        mCurMode = mode;
    }
}

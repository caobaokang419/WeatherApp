package com.gary.weatherdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gary.weatherdemo.bean.base.BaseItemBean;
import com.gary.weatherdemo.ui.viewholder.ItemViewHolder;
import com.gary.weatherdemo.ui.viewholder.ItemViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

public class CitySearchRecyclerAdapter extends CommonRecyclerAdapter {
    public enum CityListMode {
        CITY_LIST_NORMAL_MODE,
        CITY_LIST_SEARCH_MODE
    }
    private CityListMode mCurCityListMode = CityListMode.CITY_LIST_NORMAL_MODE;

    public CitySearchRecyclerAdapter() {
    }

    public void onCityListModeChanged(CityListMode mode){
        mCurCityListMode = mode;
    }
}

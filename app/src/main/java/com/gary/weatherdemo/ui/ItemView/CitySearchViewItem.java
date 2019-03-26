package com.gary.weatherdemo.ui.ItemView;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.DayForecastBean;
import com.gary.weatherdemo.bean.SearchCityItemBean;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class CitySearchViewItem implements IViewItem<SearchCityItemBean> {
    private TextView mCityNameTxt;

    /**
     * 静态工厂方法
     */
    public static CitySearchViewItem createViewItem() {
        return new CitySearchViewItem();
    }

    @Override
    public View createView(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_city, parent, false);
        mCityNameTxt = itemView.findViewById(R.id.city_name);
        return itemView;
    }

    @Override
    public void bindView(@NonNull SearchCityItemBean data) {
        mCityNameTxt.setText(data.getCityBean().adrName);
    }
}


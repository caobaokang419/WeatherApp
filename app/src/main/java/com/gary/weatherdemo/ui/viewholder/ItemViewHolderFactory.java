package com.gary.weatherdemo.ui.viewholder;

import android.view.ViewGroup;

import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.ui.ItemView.CitySearchViewItem;
import com.gary.weatherdemo.ui.ItemView.CurWeatherViewItem;
import com.gary.weatherdemo.ui.ItemView.ForecastDayViewItem;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class ItemViewHolderFactory {
    public static ItemViewHolder createViewHolder(ViewGroup parent, int type) {
        if (type == IViewItemBean.ItemViewType.RV_CURRENT_WEATHER_ITEM_TYPE.value()) {
            return new ItemViewHolder(CurWeatherViewItem.createViewItem(), parent);
        } else if (type == IViewItemBean.ItemViewType.RV_FORECAST_DAY_WEATHER_ITEM_TYPE.value()) {
            return new ItemViewHolder(ForecastDayViewItem.createViewItem(), parent);
        } else if (type == IViewItemBean.ItemViewType.RV_SEARCH_CITY_ITEM_TYPE.value()) {
            return new ItemViewHolder(CitySearchViewItem.createViewItem(), parent);
        }
        return null;
    }
}


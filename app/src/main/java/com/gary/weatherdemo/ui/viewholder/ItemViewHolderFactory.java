package com.gary.weatherdemo.ui.viewholder;

import android.view.ViewGroup;

import com.gary.weatherdemo.model.base.ItemViewType;
import com.gary.weatherdemo.ui.ItemView.CurWeatherViewItem;
import com.gary.weatherdemo.ui.ItemView.ForecastDayViewItem;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class ItemViewHolderFactory {
    public static ItemViewHolder createViewHolderByType(ViewGroup parent, int type) {
        if (type == ItemViewType.RV_CURRENT_WEATHER) {
            return new ItemViewHolder(CurWeatherViewItem.createViewItem(),parent);
        } else if (type == ItemViewType.RV_FORECAST_DAY_WEATHER) {
            return new ItemViewHolder(ForecastDayViewItem.createViewItem(),parent);
        }
        return null;
    }
}


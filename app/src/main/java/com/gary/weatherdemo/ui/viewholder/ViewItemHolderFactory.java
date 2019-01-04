package com.gary.weatherdemo.ui.viewholder;

import android.view.ViewGroup;

import com.gary.weatherdemo.model.base.ItemViewType;
import com.gary.weatherdemo.ui.ItemView.CurWeatherViewItem;
import com.gary.weatherdemo.ui.ItemView.ForcastDayViewItem;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class ViewItemHolderFactory {
    public static BaseViewItemHolder getViewHolderByType(ViewGroup parent,int type) {
        if (type == ItemViewType.RV_CURRENT_WEATHER) {
            return new BaseViewItemHolder(CurWeatherViewItem.getViewItem(),parent);
        } else if (type == ItemViewType.RV_FORECAST_DAY_WEATHER) {
            return new BaseViewItemHolder(ForcastDayViewItem.getViewItem(),parent);
        }
        return null;
    }
}


package com.gary.weatherdemo.ui.ItemView;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.databinding.WeatherForecastDayBinding;
import com.gary.weatherdemo.model.DayForecastBean;
import com.gary.weatherdemo.viewmodel.ForecastDayViewModel;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class ForcastDayViewItem implements IViewItem<DayForecastBean> {
    private WeatherForecastDayBinding mDataBinding;

    public static ForcastDayViewItem createViewItem() {
        return new ForcastDayViewItem();
    }

    public View createView(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast_day, parent, false);
        mDataBinding = WeatherForecastDayBinding.bind(itemView);
        return itemView;
    }

    public void bindView(@NonNull DayForecastBean data) {
        mDataBinding.setViewModel(new ForecastDayViewModel(data));
    }
}


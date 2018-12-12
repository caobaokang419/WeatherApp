package com.gary.weatherdemo.ui.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.uibean.ForecastDayDataBean;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class ForcastDayViewHolder implements IViewHolder<ForecastDayDataBean> {
    public void createViewHolder(ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast_day, parent, false);
    }

    public void bindDataToViewHolder(@NonNull ForecastDayDataBean data){

    }
}


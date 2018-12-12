package com.gary.weatherdemo.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.uibean.CurWeatherDataBean;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class CurWeatherViewHolder /*extends RecyclerView.ViewHolder*/ implements IViewHolder<CurWeatherDataBean> {
    public void createViewHolder(ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cur_weather, parent, false);
    }

    public void bindDataToViewHolder(@NonNull CurWeatherDataBean data){

    }
}

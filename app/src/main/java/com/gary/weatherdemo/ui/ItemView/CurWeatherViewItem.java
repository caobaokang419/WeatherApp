package com.gary.weatherdemo.ui.ItemView;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.databinding.CurWeatherBinding;
import com.gary.weatherdemo.bean.LiveWeatherBean;
import com.gary.weatherdemo.viewmodel.CurWeatherViewModel;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class CurWeatherViewItem implements IViewItem<LiveWeatherBean> {
    private CurWeatherBinding mDataBinding;

    /**
     * 静态工厂方法
     */
    public static CurWeatherViewItem createViewItem() {
        return new CurWeatherViewItem();
    }

    @Override
    public View createView(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cur_weather, parent, false);
        //获取CurWeatherViewItem 的CurWeatherBinding对象
        mDataBinding = CurWeatherBinding.bind(itemView);
        return itemView;
    }

    @Override
    public void bindView(@NonNull LiveWeatherBean data) {
        //关联CurWeatherBinding对象 & 数据 CurWeatherViewModel(data)
        mDataBinding.setViewModel(new CurWeatherViewModel(data));
    }
}

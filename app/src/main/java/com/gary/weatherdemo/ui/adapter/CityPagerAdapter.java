package com.gary.weatherdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gary.weatherdemo.model.CityBean;

import java.util.List;

public class CityPagerAdapter extends PagerAdapter {
    private List<CityBean> cityBeanList;
    private LayoutInflater layoutInflater;

    public CityPagerAdapter(Context context, List<CityBean> cityBeanList) {
        this.cityBeanList = cityBeanList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cityBeanList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    class ViewHolder {
        TextView city_name;
    }

}

package com.gary.weatherdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.viewmodel.MainActivity2ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityPagerAdapter extends PagerAdapter {
    private Context context;
    private List<CityBean> cityBeanList;
    private Map<CityBean, View> viewDatas = new HashMap<>();
    private LayoutInflater layoutInflater;
    private MainActivity2ViewModel viewModel;

    public CityPagerAdapter(Context context, MainActivity2ViewModel viewmodel) {
        this.context = context;
        this.viewModel = viewmodel;
        this.cityBeanList = viewmodel.getCityInfoList();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cityBeanList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        //return false;
        return view == object;
    }


    @Override
    public View instantiateItem(ViewGroup container, int position) {
        //demo code
        /*TextView textView = new TextView(context);
        textView.setText(cityBeanList.get(position).adrName);
        container.addView(textView);
        return textView;*/
        View createdView = getViewItemByCity(cityBeanList.get(position));
        if (createdView == null) {
            View newView = layoutInflater.inflate(R.layout.item_city_pager_view, container);
            RecyclerView recyclerView = newView.findViewById(R.id.recycler_view);
            recyclerView.setAdapter(viewModel.getCityWeatherRecyclerAdapter(cityBeanList.get(position)));
            createdView = newView;
        }

        container.addView(createdView);
        return createdView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return cityBeanList.get(position).adrName;
    }*/

    public View getViewItemByCity(CityBean cityinfo) {
        if (viewDatas == null) {
            return null;
        }
        return viewDatas.get(cityinfo);
    }
}

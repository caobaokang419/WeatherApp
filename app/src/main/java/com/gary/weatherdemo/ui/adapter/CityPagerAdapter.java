package com.gary.weatherdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;

import java.util.List;

public class CityPagerAdapter extends PagerAdapter {
    private List<CityBean> cityBeanList;
    private List<View> viewList;
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


    @Override
    public View instantiateItem(ViewGroup container, int position) {
        /*TextView cityView= cityBeanList.get(position).adrName;
        container.addView(imageView);
        return imageView;*/

        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView=(ImageView)object;
        container.removeView(imageView);
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return cityBeanList.get(position).adrName;
    }*/
}

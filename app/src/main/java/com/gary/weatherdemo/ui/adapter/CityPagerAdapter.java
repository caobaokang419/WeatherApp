package com.gary.weatherdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.viewmodel.MainActivityViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<CityBean> mCityBeans;
    private Map<CityBean, View> mViewDatas = new HashMap<>();
    private LayoutInflater mLayoutInflater;
    private MainActivityViewModel mViewModel;

    public CityPagerAdapter(Context context, MainActivityViewModel viewmodel) {
        this.mContext = context;
        this.mViewModel = viewmodel;
        this.mCityBeans = viewmodel.getCityInfoList();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mCityBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        //return false;
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        //TaskManagerImpl code
        /*TextView textView = new TextView(mContext);
        textView.setText(mCityBeans.get(position).cityName);
        container.addView(textView);
        return textView;*/
        View createdView = getViewItemByCity(mCityBeans.get(position));
        if (createdView == null) {
            View newView = mLayoutInflater.inflate(R.layout.item_city_pager_view, container,false);
            RecyclerView recyclerView = newView.findViewById(R.id.recycler_view);
            recyclerView.setAdapter(mViewModel.getCityWeatherRecyclerAdapter(mCityBeans.get(position)));
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
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
        return mCityBeans.get(position).cityName;
    }*/

    public View getViewItemByCity(CityBean cityinfo) {
        if (mViewDatas == null) {
            return null;
        }
        return mViewDatas.get(cityinfo);
    }
}

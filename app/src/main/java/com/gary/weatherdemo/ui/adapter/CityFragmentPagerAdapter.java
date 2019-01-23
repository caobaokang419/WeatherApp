package com.gary.weatherdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by GaryCao on 2019/01/12.
 * --弃用: 左右滑动切换城市，FragmentPagerAdapter不适用此场景
 */
public class CityFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public CityFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CityFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mFragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList != null && position < mFragmentList.size()) {
            return mFragmentList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (mFragmentList == null) {
            return 0;
        }
        return mFragmentList.size();
    }

    /**
     * 使用这个方式，让页面不缓存，能够在清除fragment的时候对其做了删除??
     *
     * @param object
     * @return
     *//*
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }*/
}
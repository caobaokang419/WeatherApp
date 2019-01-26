package com.gary.weatherdemo.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.commonui.ActionBar;
import com.example.commonui.PageIndicatorView;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.ui.adapter.CityPagerAdapter;
import com.gary.weatherdemo.utils.LogUtils;
import com.gary.weatherdemo.utils.WtUtil;
import com.gary.weatherdemo.viewmodel.MainActivityViewModel;

/**
 * Created by GaryCao on 2019/01/13.
 * 左右滑动切换：不同城市天气信息：ViewPager+PagerAdapter
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateNew(savedInstanceState);
        setSystemUISytle();
    }

    private void setSystemUISytle(){
        //TODO
    }

    protected abstract void onCreateNew(Bundle savedInstanceState);
}

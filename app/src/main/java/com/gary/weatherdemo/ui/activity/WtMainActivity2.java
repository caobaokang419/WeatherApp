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
import com.gary.weatherdemo.utils.WeatherUtils;
import com.gary.weatherdemo.viewmodel.MainActivity2ViewModel;
import com.gary.weatherdemo.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/01/13.
 * 左右滑动切换：不同城市天气信息：ViewPager+PagerAdapter
 */
public class WtMainActivity2 extends AppCompatActivity {
    private MainActivity2ViewModel viewModel;
    private ActionBar actionBar;

    private ViewPager viewPager;
    private CityPagerAdapter cityPagerAdapter;
    private PageIndicatorView pageIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main_2);
        initView();
    }

    private void initView(){
        viewModel = ViewModelProviders.of(this).get(MainActivity2ViewModel.class);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        cityPagerAdapter = new CityPagerAdapter(this,viewModel);
        viewPager.setAdapter(cityPagerAdapter);

        pageIndicatorView = findViewById(R.id.page_indicatior);
        pageIndicatorView.registerPageChangeListener(viewPager);

        initActionBar();
        updateCityTitleView();
    }

    private void initActionBar() {
        actionBar = findViewById(R.id.action_bar);
        actionBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("onClickedLeftBtn()");
                WeatherUtils.startActivity(getApplicationContext(), WtSearchActivity.class);
            }
        });

        actionBar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("onClickedRightBtn()");
                WeatherUtils.startActivity(getApplicationContext(), WtSettingActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        if (viewModel != null) {
            viewModel.loadCurCityInfo(new CityBean("深圳", "440300"));
        }
        super.onResume();
    }

    private void updateCityTitleView() {
        viewModel.getCurCityInfo().observe(this, new Observer<CityBean>() {
            @Override
            public void onChanged(@Nullable CityBean cityBean) {
                if (null != actionBar) {
                    actionBar.setTitle(cityBean.adrName);
                }
                viewModel.queryCityWeather(cityBean);
            }
        });
    }
}

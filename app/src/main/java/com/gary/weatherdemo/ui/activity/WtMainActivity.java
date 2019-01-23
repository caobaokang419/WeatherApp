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
import com.gary.weatherdemo.viewmodel.MainActivityViewModel;

/**
 * Created by GaryCao on 2019/01/13.
 * 左右滑动切换：不同城市天气信息：ViewPager+PagerAdapter
 */
public class WtMainActivity extends AppCompatActivity {
    private MainActivityViewModel mViewModel;
    private ActionBar mActionBar;
    private ViewPager mViewPager;
    private CityPagerAdapter mCityPagerAdapter;
    private PageIndicatorView mPageIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
        initView();
    }

    private void initView(){
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mCityPagerAdapter = new CityPagerAdapter(this, mViewModel);
        mViewPager.setAdapter(mCityPagerAdapter);

        mPageIndicatorView = findViewById(R.id.page_indicatior);
        mPageIndicatorView.registerPageChangeListener(mViewPager);

        initActionBar();
        updateCityTitleView();
    }

    private void initActionBar() {
        mActionBar = findViewById(R.id.action_bar);
        mActionBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("onClickedLeftBtn()");
                WeatherUtils.startActivity(getApplicationContext(), WtSearchActivity.class);
            }
        });

        mActionBar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("onClickedRightBtn()");
                WeatherUtils.startActivity(getApplicationContext(), WtSettingActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        if (mViewModel != null) {
            mViewModel.loadCurCityInfo(new CityBean("深圳", "440300"));
        }
        super.onResume();
    }

    private void updateCityTitleView() {
        mViewModel.getCurCityInfo().observe(this, new Observer<CityBean>() {
            @Override
            public void onChanged(@Nullable CityBean cityBean) {
                if (null != mActionBar) {
                    mActionBar.setTitle(cityBean.adrName);
                }
                mViewModel.queryCityWeather(cityBean);
            }
        });
    }
}

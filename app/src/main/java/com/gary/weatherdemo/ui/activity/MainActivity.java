package com.gary.weatherdemo.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.example.commonui.PageIndicatorView;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.ui.activity.base.BaseActivity;
import com.gary.weatherdemo.ui.adapter.CityPagerAdapter;
import com.gary.weatherdemo.utils.WtUtil;
import com.gary.weatherdemo.viewmodel.MainActivityViewModel;

/**
 * Created by GaryCao on 2019/01/13.
 * 左右滑动切换：不同城市天气信息：ViewPager+PagerAdapter
 */
public class MainActivity extends BaseActivity {
    private MainActivityViewModel mViewModel;
    private ViewPager mViewPager;
    private CityPagerAdapter mCityPagerAdapter;
    private PageIndicatorView mPageIndicatorView;

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
        initView();

        /*if(PermissionActivity.startRequestAllPermission(this)){
            finish();
        }*/
    }

    private void initView() {
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mCityPagerAdapter = new CityPagerAdapter(this, mViewModel);
        /**
         * GoF23 设计模式 7：适配器模式
         */
        mViewPager.setAdapter(mCityPagerAdapter);
        mViewModel.registerPageChangeListener(mViewPager);

        mPageIndicatorView = findViewById(R.id.page_indicatior);
        mPageIndicatorView.registerPageChangeListener(mViewPager);

        updateCityTitleView();
    }

    @Override
    protected void onResume() {
        if (mViewModel != null) {
            mViewModel.loadCurCityInfo(new CityBean("深圳", "440300"));
        }
        super.onResume();
    }


    @Override
    protected void onActionBarLeftClicked() {
        WtUtil.startActivity(getApplicationContext(), CitySearchActivity.class);
    }

    @Override
    protected void onActionBarRightClicked() {
        WtUtil.startActivity(getApplicationContext(), SettingActivity.class);
    }

    private void updateCityTitleView() {
        mViewModel.getCurCityInfo().observe(this, new Observer<CityBean>() {
            @Override
            public void onChanged(@Nullable CityBean cityBean) {
                if (null != mActionBar) {
                    mActionBar.setTitle(cityBean.cityName);
                }
                mViewModel.queryCityWeather(cityBean);
            }
        });
    }
}

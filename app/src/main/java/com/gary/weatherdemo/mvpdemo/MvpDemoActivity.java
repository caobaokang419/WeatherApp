package com.gary.weatherdemo.mvpdemo;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.ui.activity.base.BaseActivity;
import com.gary.weatherdemo.ui.adapter.CityFragmentPagerAdapter;

/**
 * Created by GaryCao on 2019/01/13.
 * <p>
 * MVP应用框架元素4：Activity or Fragment （仅用于MVVM|MVP|MVC框架对比&参考）
 * <p>
 * MVC:View可以直接访问MODEL
 * MVP：相对于MVC的区别：View不可以直接访问MODEL，需要通过Presenter跳转。
 * MVVM：相对于MVP的区别：不用应用实现Presenter->View的逻辑，LiveData机制自动实现。
 */
public class MvpDemoActivity extends BaseActivity implements IView {
    private ViewPager mViewPager;
    private CityFragmentPagerAdapter mCityPagerAdapter;
    private IPresenter iPresenter;

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main1);
        initView();
        iPresenter = new MvpDemoPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        iPresenter.onUiReady(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        iPresenter.onUiUnready();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mCityPagerAdapter = new CityFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mCityPagerAdapter);
    }

    @Override
    protected void onActionBarLeftClicked() {
    }

    @Override
    protected void onActionBarRightClicked() {

    }

    @Override
    public void updateCityWeather() {
        /**MVP特征1：View不直接访问Model，只访问Presenter*/
        iPresenter.queryCityWeather(null);
    }
}

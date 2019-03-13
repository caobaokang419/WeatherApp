package com.gary.weatherdemo.mvp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.ui.activity.BaseActivity;
import com.gary.weatherdemo.ui.adapter.CityFragmentPagerAdapter;

/**
 * Created by GaryCao on 2019/01/13.
 *
 *  MVP应用框架元素4：Activity or Fragment （仅用于MVVM框架对比&参考）
 *
 *  UI弃用: 左右滑动切换城市：ViewPager+FragmentPagerAdapter,不适合此场景（View一致，data不同，count动态）
 */
public class CityWeatherActivity extends BaseActivity implements IView{
    private ViewPager mViewPager;
    private CityFragmentPagerAdapter mCityPagerAdapter;
    private IPresenter iPresenter;

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main1);
        initView();
        iPresenter = new CityWeatherPresenter();
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

    }
}

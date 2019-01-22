package com.gary.weatherdemo.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.ui.adapter.CityFragmentPagerAdapter;

/**
 * Created by GaryCao on 2019/01/13.
 * 弃用: 左右滑动切换城市：ViewPager+FragmentPagerAdapter,不适合此场景（View一致，data不同，count动态）
 */
public class WtMainActivity1 extends AppCompatActivity {
    private ViewPager viewPager;
    CityFragmentPagerAdapter cityPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main_1);
        initView();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        cityPagerAdapter = new CityFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(cityPagerAdapter);
    }
}

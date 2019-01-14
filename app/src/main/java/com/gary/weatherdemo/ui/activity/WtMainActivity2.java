package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.commonui.PageIndicatorView;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.ui.adapter.CityPagerAdapter;

/**
 * Created by GaryCao on 2019/01/13.
 * 左右滑动切换：不同城市天气信息：ViewPager+PagerAdapter
 */
public class WtMainActivity2 extends AppCompatActivity {
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
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        cityPagerAdapter = new CityPagerAdapter(this,null);
        viewPager.setAdapter(cityPagerAdapter);

        pageIndicatorView = findViewById(R.id.page_indicatior);
    }
}

package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.commonui.PageIndicatorView;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.ui.adapter.CityPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/01/13.
 * 左右滑动切换：不同城市天气信息：ViewPager+PagerAdapter
 */
public class WtMainActivity2 extends AppCompatActivity {
    private ViewPager viewPager;
    private CityPagerAdapter cityPagerAdapter;
    private PageIndicatorView pageIndicatorView;
    private String[] cityNames =
            new String[]{"深圳", "西安", "合肥", "武汉", "巢湖市", "北京", "上海", "广州", "成都", "南京", "杭州", "苏州"};
    private String[] cityAdcode =
            new String[]{"440300", "610100", "340100", "420100", "340181", "110000", "310000", "440100", "510100", "320100", "330100", "320500"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main_2);
        initView();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        List<CityBean> cityBeanList = new ArrayList<>();
        for (int i = 0; i < cityNames.length; i++) {
            CityBean CityBean = new CityBean(cityNames[i],cityAdcode[i]);
            cityBeanList.add(CityBean);
        }
        cityPagerAdapter = new CityPagerAdapter(this,cityBeanList);
        viewPager.setAdapter(cityPagerAdapter);

        pageIndicatorView = findViewById(R.id.page_indicatior);
        pageIndicatorView.registerPageChangeListener(viewPager);
    }
}

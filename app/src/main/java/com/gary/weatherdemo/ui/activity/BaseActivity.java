package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

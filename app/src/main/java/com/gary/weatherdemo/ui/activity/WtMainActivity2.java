package com.gary.weatherdemo.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.commonui.ActionBar;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.databinding.WeatherMainActivityBinding;
import com.gary.weatherdemo.utils.LogUtils;
import com.gary.weatherdemo.utils.WeatherUtils;
import com.gary.weatherdemo.viewmodel.MainActivityViewModel2;

/**
 * Created by GaryCao on 2019/01/22.
 * 单个城市天气信息：不支持左右滑动切换城市
 */
public class WtMainActivity2 extends AppCompatActivity {
    private MainActivityViewModel2 mViewModel;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        /*EventBus.getDefault().register(this);*/
    }

    private void initViews() {
        WeatherMainActivityBinding binding = DataBindingUtil.<WeatherMainActivityBinding>setContentView(this, R.layout.activity_weather_main2);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel2.class);
        binding.setViewModel(mViewModel);

        initActionBar();
        updateCityTitleView();
    }

    @Override
    protected void onResume() {
        if (mViewModel != null) {
            mViewModel.loadCurCityInfo();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }*/
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

    private void updateCityTitleView() {
        mViewModel.getCurCityInfo().observe(this, new Observer<CityBean>() {
            @Override
            public void onChanged(@Nullable CityBean cityBean) {
                if (null != mActionBar) {
                    mActionBar.setTitle(cityBean.adrName);
                }
                mViewModel.queryCityWeather(cityBean.adcCode);
            }
        });
    }

    /*@Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        mText.setText(messageEvent.getMessage());
    }*/
}

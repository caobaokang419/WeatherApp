package com.gary.weatherdemo.view.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.commonui.ActionBar;
import com.example.commonui.IActionBarOnClickListener;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.databinding.WeatherMainActivityBinding;
import com.gary.weatherdemo.firebase.admob.BannerAdActivity;
import com.gary.weatherdemo.model.LiveWeatherResult;
import com.gary.weatherdemo.utils.LogUtils;
import com.gary.weatherdemo.viewmodel.MainActivityViewModel;

public class WeatherMainBannerAdActivity extends BannerAdActivity implements IActionBarOnClickListener {
    private MainActivityViewModel viewModel;
    private ActionBar actionBar;
    private TextView curTempView;
    private TextView curWeatherView;
    private View curView;

    @Override
    public void onCreateNew(Bundle savedInstanceState) {
        initViews();
    }

    private void initViews() {
        WeatherMainActivityBinding binding = DataBindingUtil.<WeatherMainActivityBinding>setContentView(this, R.layout.activity_weather_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new MainActivityViewModel();
        binding.setViewModel(viewModel);

        initActionBar();
        initCurWeather();
    }

    @Override
    protected void onResume() {
        if (viewModel != null) {
            viewModel.requestWeatherByCityName();
        }
        super.onResume();
    }

    private void initActionBar() {
        actionBar = (ActionBar) findViewById(R.id.action_bar);
        actionBar.setOnClickListener(this);
    }

    private void initCurWeather() {
        curTempView = findViewById(R.id.cur_temp);
        curWeatherView = findViewById(R.id.cur_weather);
        curView = findViewById(R.id.cur_weather_view);
        updateCurWeatherView();
    }

    private void updateCurWeatherView() {
        viewModel.getLiveWeatherData().observe(this, new Observer<LiveWeatherResult>() {
            @Override
            public void onChanged(@Nullable LiveWeatherResult liveWeatherResult) {
                curTempView.setText(liveWeatherResult.temperature + getResources().getString(R.string.temperature_signal));
                curWeatherView.setText(liveWeatherResult.weather);
                curView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void leftActBarItemClicked() {
        LogUtils.d("leftActBarItemClicked()");
    }

    @Override
    public void rightActBarItemClicked() {
        LogUtils.d("rightActBarItemClicked()");
        startActivity("com.gary.weatherdemo.firebase.FirebaseListActivity");
    }

    private void startActivity(String classname) {
        Intent intent = new Intent();
        intent.setClassName("com.gary.weatherdemo", classname);
        startActivity(intent);
    }
}

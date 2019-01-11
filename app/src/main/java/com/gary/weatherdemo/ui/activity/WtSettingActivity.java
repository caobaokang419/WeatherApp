package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gary.weatherdemo.R;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class WtSettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_setting);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*EventBus.getDefault().post(new MessageEvent("Just for test"));*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

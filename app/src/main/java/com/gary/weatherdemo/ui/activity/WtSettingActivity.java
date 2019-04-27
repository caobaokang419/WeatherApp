package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.gary.weatherdemo.ui.fragment.WtSettingFragment;

/**
 * Created by GaryCao on 2019/01/12.
 * Android推荐使用PreferenceActivity+PreferenceFragment，替代单独使用PreferenceActivity.
 */
public class WtSettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSettingFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showSettingFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new WtSettingFragment())
                .commit();
    }
}

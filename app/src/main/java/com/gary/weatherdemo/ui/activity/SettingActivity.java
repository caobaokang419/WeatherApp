package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.gary.weatherdemo.ui.fragment.SettingFragment;

/**
 * Created by GaryCao on 2019/01/12.
 * Android推荐使用PreferenceActivity+PreferenceFragment，替代单独使用PreferenceActivity.
 */
public class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replacePreferenceFragment(new SettingFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void replacePreferenceFragment(PreferenceFragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }
}

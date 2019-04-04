package com.gary.weatherdemo.ui.fragment;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.view.View;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.provider.sp.SpConfigProviderClient;
import com.gary.weatherdemo.utils.SpConfigUtil;

/**
 * Created by GaryCao on 2019/01/12.
 * PreferenceFragment设置界面
 */
public class WtSettingFragment extends PreferenceFragment {
    private static final String KEY_PREF_ITEM_UPDATE_SWITCH = "auto_update_switch_key";
    private static final String KEY_PREF_ITEM_UPDATE_VALUE = "auto_update_internal_key";
    private static final String KEY_PREF_ITEM_OWNER_LABEL = "owner_label_key";

    private SwitchPreference mAutoUpdateSwitchPref;
    private ListPreference mAutoUpdateValuePref;
    private EditTextPreference mOwnerLabelPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_fragment_setting);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initViewState();
    }

    private void initView() {
        mAutoUpdateSwitchPref = (SwitchPreference) findPreference(KEY_PREF_ITEM_UPDATE_SWITCH);
        mAutoUpdateSwitchPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //SpConfigUtil.getInstance().setBoolean(SpConfigUtil.KEY_UPDATE_SWITCH, mAutoUpdateSwitchPref.isChecked());
                SpConfigProviderClient.setBooleanInProvider(
                        SpConfigUtil.KEY_UPDATE_SWITCH,
                        mAutoUpdateSwitchPref.isChecked());
                return true;
            }
        });

        mAutoUpdateValuePref = (ListPreference) findPreference(KEY_PREF_ITEM_UPDATE_VALUE);
        mAutoUpdateValuePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });
        mOwnerLabelPref = (EditTextPreference) findPreference(KEY_PREF_ITEM_OWNER_LABEL);
        mOwnerLabelPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });
    }

    private void initViewState() {
        mAutoUpdateSwitchPref.setChecked(
                SpConfigProviderClient.getBooleanInProvider(SpConfigUtil.KEY_UPDATE_SWITCH)
                /*SpConfigUtil.getInstance().getBoolean(SpConfigUtil.KEY_UPDATE_SWITCH)*/);
    }
}
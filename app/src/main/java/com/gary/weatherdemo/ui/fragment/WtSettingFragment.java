package com.gary.weatherdemo.ui.fragment;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.view.View;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.utils.SPConfigsUtils;

/**
 * Created by GaryCao on 2019/01/12.
 * PreferenceFragment设置界面
 */
public class WtSettingFragment extends PreferenceFragment {
    private static final String KEY_PREF_ITEM_UPDATE_SWITCH ="auto_update_switch_key";
    private static final String KEY_PREF_ITEM_UPDATE_VALUE ="auto_update_internal_key";
    private static final String KEY_PREF_ITEM_OWNER_LABEL ="owner_label_key";
    private SwitchPreference updateSwitchPref;
    private ListPreference updateValuePref;
    private EditTextPreference ownerLabelPref;

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

    private void initView(){
        updateSwitchPref = (SwitchPreference)findPreference(KEY_PREF_ITEM_UPDATE_SWITCH);
        updateSwitchPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SPConfigsUtils.getInstance().setBoolean(SPConfigsUtils.KEY_UPDATE_SWITCH, updateSwitchPref.isChecked());
                return true;
            }
        });

        updateValuePref = (ListPreference)findPreference(KEY_PREF_ITEM_UPDATE_VALUE);
        updateValuePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });
        ownerLabelPref = (EditTextPreference)findPreference(KEY_PREF_ITEM_OWNER_LABEL);
        ownerLabelPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });
    }

    private void initViewState(){
        updateSwitchPref.setChecked(
                SPConfigsUtils.getInstance().getBoolean(SPConfigsUtils.KEY_UPDATE_SWITCH));
    }
}
package com.gary.weatherdemo.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.firebase.admob.InterstitialAdActivity;
import com.gary.weatherdemo.firebase.admob.RewardedVideoAdActivity;
import com.gary.weatherdemo.firebase.analytics.AnalyticsActivity;
import com.gary.weatherdemo.firebase.auth.AuthChooseActivity;
import com.gary.weatherdemo.firebase.crashreport.CrashReportActivity;
import com.gary.weatherdemo.firebase.fcm.FcmActivity;
import com.gary.weatherdemo.firebase.remoteconfig.RemoteConfigActivity;
import com.gary.weatherdemo.utils.WeatherUtils;

public class FirebaseListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button interstitialAdBtn;
    private Button rewardedVideoAdBtn;

    private Button remoteConfigBtn;
    private Button fcmBtn;
    private Button analyticsBtn;
    private Button crashLogBtn;
    private Button authBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_list);

        interstitialAdBtn = findViewById(R.id.interstitial_btn);
        interstitialAdBtn.setOnClickListener(this);

        rewardedVideoAdBtn = findViewById(R.id.rewarded_video_btn);
        rewardedVideoAdBtn.setOnClickListener(this);

        remoteConfigBtn = findViewById(R.id.remote_config_btn);
        remoteConfigBtn.setOnClickListener(this);

        fcmBtn = findViewById(R.id.fcm_btn);
        fcmBtn.setOnClickListener(this);

        analyticsBtn = findViewById(R.id.analytics_btn);
        analyticsBtn.setOnClickListener(this);

        crashLogBtn = findViewById(R.id.crash_report_btn);
        crashLogBtn.setOnClickListener(this);

        authBtn = findViewById(R.id.auth_btn);
        authBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.interstitial_btn:
                WeatherUtils.startActivity(this, InterstitialAdActivity.class);
                break;
            case R.id.rewarded_video_btn:
                WeatherUtils.startActivity(this, RewardedVideoAdActivity.class);
                break;
            case R.id.remote_config_btn:
                WeatherUtils.startActivity(this, RemoteConfigActivity.class);
                break;
            case R.id.fcm_btn:
                WeatherUtils.startActivity(this, FcmActivity.class);
                break;
            case R.id.analytics_btn:
                WeatherUtils.startActivity(this, AnalyticsActivity.class);
                break;
            case R.id.crash_report_btn:
                WeatherUtils.startActivity(this, CrashReportActivity.class);
                break;
            case R.id.auth_btn:
                WeatherUtils.startActivity(this, AuthChooseActivity.class);
                break;
            default:
                break;
        }
    }
}

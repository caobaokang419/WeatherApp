package com.gary.weatherdemo.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gary.weatherdemo.R;


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
                startActivity("com.gary.weatherdemo.firebase.admob.InterstitialAdActivity");
                break;
            case R.id.rewarded_video_btn:
                startActivity("com.gary.weatherdemo.firebase.admob.RewardedVideoAdActivity");
                break;
            case R.id.remote_config_btn:
                startActivity("com.gary.weatherdemo.firebase.remoteconfig.RemoteConfigActivity");
                break;
            case R.id.fcm_btn:
                startActivity("com.gary.weatherdemo.firebase.fcm.FcmActivity");
                break;
            case R.id.analytics_btn:
                startActivity("com.gary.weatherdemo.firebase.analytics.AnalyticsActivity");
                break;
            case R.id.crash_report_btn:
                startActivity("com.gary.weatherdemo.firebase.crashreport.CrashReportActivity");
                break;
            case R.id.auth_btn:
                startActivity("com.gary.weatherdemo.firebase.auth.AuthChooseActivity");
                break;
            default:
                break;
        }
    }

    private void startActivity(String classname) {
        Intent intent = new Intent();
        intent.setClassName("com.gary.weatherdemo", classname);
        startActivity(intent);
    }
}

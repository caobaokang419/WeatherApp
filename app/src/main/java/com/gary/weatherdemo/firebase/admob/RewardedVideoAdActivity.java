package com.gary.weatherdemo.firebase.admob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.firebase.admob.callback.RewardedVideoAdListenerImpl;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by GaryCao on 2018/11/16.
 *
 * Admob 激励广告单元显示
 */
public class RewardedVideoAdActivity extends AppCompatActivity {
    private RewardedVideoAd mRewardedVideoAd;
    private RewardedVideoAdListener listener;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_rewarded_video);
        initAdMob();
    }

    private void initAdMob() {
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        listener = new RewardedVideoAdListenerImpl();

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(listener);
        loadRewardedVideoAd();

        btn = findViewById(R.id.rewarded_video_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        });
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.admob_rewarded_video_ads_id),
                new AdRequest.Builder().build());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

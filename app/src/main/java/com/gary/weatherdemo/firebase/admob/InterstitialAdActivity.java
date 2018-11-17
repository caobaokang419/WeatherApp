package com.gary.weatherdemo.firebase.admob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.firebase.admob.callback.AdListenerImpl;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by GaryCao on 2018/11/16.
 *
 * Admob 插页广告单元显示
 */
public class InterstitialAdActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private Button btn;
    private AdListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_interstitial);
        initAdMob();
    }

    private void initAdMob(){
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, getString(R.string.admob_app_id));

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_ads_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mListener = new AdListenerImpl();
        mInterstitialAd.setAdListener(mListener);

        btn = findViewById(R.id.interstitial_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

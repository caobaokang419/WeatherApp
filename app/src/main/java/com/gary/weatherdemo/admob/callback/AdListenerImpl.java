package com.gary.weatherdemo.admob.callback;

import com.google.android.gms.ads.AdListener;

/**
 * Created by GaryCao on 2018/11/16.
 *
 * Admob 横幅&插页广告单元回调：应用实现
 */
public class AdListenerImpl extends AdListener {
    @Override
    public void onAdLoaded() {
        // Code to be executed when an ad finishes loading.
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        // Code to be executed when an ad request fails.
    }

    @Override
    public void onAdOpened() {
        // Code to be executed when an ad opens an overlay that
        // covers the screen.
    }

    @Override
    public void onAdLeftApplication() {
        // Code to be executed when the user has left the app.
    }

    @Override
    public void onAdClosed() {
        // Code to be executed when when the user is about to return
        // to the app after tapping on an ad.
    }
}
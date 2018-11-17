package com.gary.weatherdemo.firebase.admob.callback;

import com.gary.weatherdemo.utils.WeatherUtils;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by GaryCao on 2018/11/16.
 *
 * Admob 激励广告单元回调：应用实现
 */
public class RewardedVideoAdListenerImpl implements RewardedVideoAdListener{

    @Override
    public void onRewarded(RewardItem reward) {
        // TBD: 激励广告触发：此时应实现应用奖励逻辑（奖励：积分？游戏金币？）

        WeatherUtils.showToast("onRewarded! currency: " + reward.getType() + "  amount: " + reward.getAmount());
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        WeatherUtils.showToast("onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        // TBD: 激励广告结束，可触发下一个激励广告：
        // loadRewardedVideoAd();

        WeatherUtils.showToast("onRewardedVideoAdClosed");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        // 激励广告加载失败（比如ID error case）
        WeatherUtils.showToast("onRewardedVideoAdFailedToLoad");
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        WeatherUtils.showToast("onRewardedVideoAdLoaded");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        WeatherUtils.showToast("onRewardedVideoAdLoaded");
    }

    @Override
    public void onRewardedVideoStarted() {
        WeatherUtils.showToast("onRewardedVideoAdLoaded");
    }

    @Override
    public void onRewardedVideoCompleted() {
        WeatherUtils.showToast("onRewardedVideoAdLoaded");
    }
}
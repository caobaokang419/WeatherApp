package com.gary.weatherdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gary.weatherdemo.WtApplication;

/**
 * Created by GaryCao on 2018/04/01.
 * <p>
 * 网络相关工具类
 */
public class NetworkUtil {
    public static boolean isNetworkConnected() {
        Context context = WtApplication.getContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
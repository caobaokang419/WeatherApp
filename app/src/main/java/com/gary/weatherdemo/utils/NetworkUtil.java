package com.gary.weatherdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gary.weatherdemo.WtApplication;

import java.io.ByteArrayOutputStream;

/**
 * Created by GaryCao on 2018/04/01.
 * <p>
 * 网络相关工具类
 */
public class NetworkUtil {
    public static boolean isNetworkConnected() {
        Context context = WtApplication.getInstance();
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
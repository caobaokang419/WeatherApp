package com.gary.weatherdemo.utils;

import android.widget.Toast;

import com.gary.weatherdemo.base.MyApplication;

public class WeatherUtils {
    public static void showToast(String msg){
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}

package com.gary.weatherdemo.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.gary.weatherdemo.base.MyApplication;

public class WeatherUtils {
    public static void showToast(String msg) {
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void startActivity(Context context, String classname) {
        if (null != context) {
            Intent intent = new Intent();
            intent.setClassName(context, classname);
            context.startActivity(intent);
        }
    }

    public static void startActivity(Context context, Class<?> cls) {
        if (null != context) {
            context.startActivity(new Intent(context, cls));
        }
    }
}

package com.gary.weatherdemo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.gary.weatherdemo.WtApplication;

public class WtUtil {
    public static void showToast(String msg) {
        Toast.makeText(WtApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
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

    /**
     * 获取应用版本号
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}

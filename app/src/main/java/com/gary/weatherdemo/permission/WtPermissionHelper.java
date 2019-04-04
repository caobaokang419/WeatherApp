package com.gary.weatherdemo.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

public class WtPermissionHelper {
    /**
     * 需动态申请的权限列表
     */
    public final static String[] mAllPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    /**
     * 获取待用户授权的权限列表
     *
     * @param activity
     * @return
     */
    public static List<String> getNeedGrantPermissions(Activity activity) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : mAllPermissions) {
            if (PermissionChecker.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    /**
     * 检测用户是否授权所有权限
     *
     * @param grantResults
     * @return
     */
    public static boolean checkPermissionsGrantResult(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 唯一入口：请求权限
     *
     * @return true:启动授权界面 false:已授权，直接return走正常流程
     */
    public static boolean startRequestAllPermission(Context context) {
        if (!checkPermissionsGranted(context, mAllPermissions)) {
            Intent intent = new Intent();
            intent.setAction("android.wt.action.PERMISSION_REQUEST");
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 查询应用是否有未授权项
     *
     * @param permissions
     * @return
     */
    private static boolean checkPermissionsGranted(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //===================================================================================================
    //for test

    /**
     * 发起权限申请
     */
    public void test(Context context) {
        WtPermissionHelper.startRequestAllPermission(context);
    }
}

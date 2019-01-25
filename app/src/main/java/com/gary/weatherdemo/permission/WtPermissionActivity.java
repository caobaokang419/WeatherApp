package com.gary.weatherdemo.permission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;

import com.gary.weatherdemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/01/13.
 * 动态申请权限
 */
public class WtPermissionActivity extends AppCompatActivity {
    public static int REQUEST_CODE_PERMISSION = 0x10001;

    /**/
    private static String[] mAllNeededPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> needPermissions = getDeniedPermissions(mAllNeededPermissions);
        ActivityCompat.requestPermissions(
                this,
                needPermissions.toArray(new String[needPermissions.size()]),
                REQUEST_CODE_PERMISSION);
    }

    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissionsGrantResult(grantResults)) {
                LogUtils.d("onRequestPermissionsResult success");
            }

            finish();
        }
    }

    /**
     * 检测所有的权限是否都已授权
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

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissionsGrantResult(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请求权限
     */
    public static void startRequestAllPermission(Context context) {
        if (!checkPermissionsGranted(context, mAllNeededPermissions)) {
            Intent intent = new Intent();
            intent.setAction("android.wt.action.PERMISSION_REQUEST");
            intent.setClassName(
                    "com.gary.weatherdemo.permission",
                    "com.gary.weatherdemo.permission.WtPermissionActivity");
            context.startActivity(intent);
        }
    }
}

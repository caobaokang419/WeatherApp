package com.gary.weatherdemo.permission;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.gary.weatherdemo.utils.CLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/01/13.
 * 动态申请权限
 */
public class WtPermissionActivity extends AppCompatActivity {
    public static int REQUEST_CODE_PERMISSION = 0x10001;

    private static List<IPermitRequestCallback> mCallbacks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //发起权限申请
        List<String> needPermissions = WtPermissionUtils.getNeedGrantPermissions(this);
        ActivityCompat.requestPermissions(
                this,
                needPermissions.toArray(new String[needPermissions.size()]),
                REQUEST_CODE_PERMISSION);
    }

    /**
     * 系统API: 系统请求权限结束时自动回调
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
            if (WtPermissionUtils.checkPermissionsGrantResult(grantResults)) {
                CLog.d("onRequestPermissionsResult success");
                for (IPermitRequestCallback callback : mCallbacks) {
                    callback.onPermitRequestedSuccess();
                }
            } else {
                CLog.d("onRequestPermissionsResult fail");
                for (IPermitRequestCallback callback : mCallbacks) {
                    callback.onPermitRequestedFail();
                }
            }
            finish();
        }
    }

    /**
     * GoF23 设计模式 6：观察者模式: 订阅&通知UI刷新
     */
    public static void addListener(IPermitRequestCallback callback) {
        mCallbacks.add(callback);
    }

    public static void removeListener(IPermitRequestCallback callback) {
        mCallbacks.remove(callback);
    }

    public interface IPermitRequestCallback {
        /**
         * 授权成功
         */
        void onPermitRequestedSuccess();

        /**
         * 授权失败
         */
        void onPermitRequestedFail();
    }
}

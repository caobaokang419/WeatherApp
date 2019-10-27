package com.gary.weatherdemo.permission;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.gary.weatherdemo.utils.CLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by GaryCao on 2019/01/13.
 * 动态申请权限
 */
public class PermissionActivity extends AppCompatActivity {
    public final static int REQUEST_CODE_PERMISSION = 10001;

    private static List<IPermitRequestResult> mListeners = new CopyOnWriteArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //发起权限申请
        List<String> needPermissions = PermissionUtils.getNeedGrantPermissions(this);
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
            if (PermissionUtils.checkPermissionsGrantResult(grantResults)) {
                CLog.d("onRequestPermissionsResult success");
                for (IPermitRequestResult listener : mListeners) {
                    listener.onPermitRequestSuccess();
                }
            } else {
                CLog.d("onRequestPermissionsResult fail");
                for (IPermitRequestResult listener : mListeners) {
                    listener.onPermitRequestFail();
                }
            }
            finish();
        }
    }

    /**
     * GoF23 设计模式 6：观察者模式: 订阅&通知UI刷新
     */
    public static void addPermitRequestListener(IPermitRequestResult listener) {
        mListeners.add(listener);
    }

    public static void removePermitRequestListener(IPermitRequestResult listener) {
        mListeners.remove(listener);
    }

    public interface IPermitRequestResult {
        /**
         * 授权成功
         */
        void onPermitRequestSuccess();

        /**
         * 授权失败
         */
        void onPermitRequestFail();
    }
}

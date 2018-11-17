package com.gary.weatherdemo.workmanager;

import android.support.annotation.NonNull;

import com.gary.weatherdemo.utils.AdcodeQueryUtils;
import com.gary.weatherdemo.utils.LogUtils;

import androidx.work.Worker;

/**
 * Created by GaryCao on 2018/11/04.
 * 加载Adcode-address 对照表文件，供天气查询使用
 */
public class LoadAdcodeConfigWorker extends Worker {

    private static final String TAG = LoadAdcodeConfigWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {
        //加载高德Address-Adcode对照配置表
        LogUtils.d(TAG, "doWork() start");
        AdcodeQueryUtils.getInstance().loadAdcodeConfig();
        LogUtils.d(TAG, "doWork() end");
        return Result.SUCCESS;
    }
}

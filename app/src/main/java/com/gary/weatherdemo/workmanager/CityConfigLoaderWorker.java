package com.gary.weatherdemo.workmanager;

import android.support.annotation.NonNull;

import com.gary.weatherdemo.utils.CityInfoUtil;
import com.gary.weatherdemo.utils.CLog;

import androidx.work.Worker;

/**
 * Created by GaryCao on 2018/11/04.
 * 加载Adcode-address 对照表文件，供天气查询使用
 */
public class CityConfigLoaderWorker extends Worker {
    private static final String TAG = CityConfigLoaderWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {
        CLog.d(TAG, "doWork()");
        CityInfoUtil.getInstance().loadCityConfig();
        return Result.SUCCESS;
    }
}

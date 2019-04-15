package com.gary.weatherdemo.workmanager;

import android.support.annotation.NonNull;

import com.gary.weatherdemo.cache.memcache.CacheClient;
import com.gary.weatherdemo.refresh.PeriodicUpdateManager;
import com.gary.weatherdemo.repository.WtRepository;
import com.gary.weatherdemo.utils.CLog;
import com.gary.weatherdemo.utils.SharedPrefUtil;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

/**
 * Created by GaryCao on 2018/11/04.
 */
public class WtWorkerManager {
    private static final String TAG = WtWorkerManager.class.getSimpleName();

    // work manager types
    public static final String PERIODIC_UPATE_WORK_NAME = "periodic_update_work";
    public static final String LOAD_ADCODES_CONFIG_WORK_NAME = "load_adcodes_config_work";

    public static void loadAdrAdcodeConfig() {
        // 单次任务：OneTimeWorkRequest
        OneTimeWorkRequest.Builder loadConfigRequestBuilder =
                new OneTimeWorkRequest.Builder(CityConfigLoaderWorker.class);
        loadConfigRequestBuilder.addTag(LOAD_ADCODES_CONFIG_WORK_NAME);
        OneTimeWorkRequest loadConfigWorkRequest = loadConfigRequestBuilder.build();
        WorkManager.getInstance().enqueue(loadConfigWorkRequest);
    }

    public static void startPeriodicWeatherUpdate() {
        // 设置任务约束
        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .build();

        // 定时任务：PeriodicWorkRequest
        PeriodicWorkRequest.Builder timerQueryRequestBuilder =
                new PeriodicWorkRequest.Builder(
                        PeriodicUpdateWorker.class,
                        /*SharedPrefUtil.getContext().getInt(SharedPrefUtil.KEY_UPDATE_PERIODIC_HOUR_COUNT),*/
                        WtRepository.sp().getSharePrefInt(SharedPrefUtil.KEY_UPDATE_PERIODIC_HOUR_COUNT),
                        TimeUnit.HOURS,
                        5,
                        TimeUnit.MINUTES);

        timerQueryRequestBuilder.setConstraints(constraints);
        timerQueryRequestBuilder.addTag(PERIODIC_UPATE_WORK_NAME);

        PeriodicWorkRequest timerWorkRequest = timerQueryRequestBuilder.build();
        WorkManager.getInstance().enqueue(timerWorkRequest);
    }

    /*package 可见*/
    class PeriodicUpdateWorker extends Worker {
        @NonNull
        @Override
        public Result doWork() {
            CLog.d(TAG, "doWork()");
            PeriodicUpdateManager.getInstant().startPeriodicUpdate();
            return Result.SUCCESS;
        }
    }

    /*package 可见*/
    class CityConfigLoaderWorker extends Worker {
        @NonNull
        @Override
        public Result doWork() {
            CLog.d(TAG, "doWork()");
            CacheClient.getInstance().loadCityConfig();
            return Result.SUCCESS;
        }
    }
}

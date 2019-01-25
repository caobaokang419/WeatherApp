package com.gary.weatherdemo.workmanager;

import com.gary.weatherdemo.base.Constants;
import com.gary.weatherdemo.utils.SpConfigsUtil;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

/**
 * Created by GaryCao on 2018/11/04.
 */
public class WorkerManagerImpl implements IWorkerManager {
    @Override
    public void loadAdrAdcodeConfig() {
        // 单次任务：OneTimeWorkRequest
        OneTimeWorkRequest.Builder loadConfigRequestBuilder =
                new OneTimeWorkRequest.Builder(CityConfigLoaderWorker.class);
        loadConfigRequestBuilder.addTag(Constants.LOAD_ADCODES_CONFIG_WORK_NAME);
        OneTimeWorkRequest loadConfigWorkRequest = loadConfigRequestBuilder.build();
        WorkManager.getInstance().enqueue(loadConfigWorkRequest);
    }

    @Override
    public void periodicQueryWeather() {
        // 设置任务约束
        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .build();

        // 定时任务：PeriodicWorkRequest
        PeriodicWorkRequest.Builder timerQueryRequestBuilder =
                new PeriodicWorkRequest.Builder(
                        PeriodicUpdateWorker.class,
                        SpConfigsUtil.getInstance().getInt(SpConfigsUtil.KEY_UPDATE_PERIODIC_HOUR_COUNT),
                        TimeUnit.HOURS,
                        5,
                        TimeUnit.MINUTES);

        timerQueryRequestBuilder.setConstraints(constraints);
        timerQueryRequestBuilder.addTag(Constants.PERIODIC_UPATE_WORK_NAME);

        PeriodicWorkRequest timerWorkRequest = timerQueryRequestBuilder.build();
        WorkManager.getInstance().enqueue(timerWorkRequest);
    }
}

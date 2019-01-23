package com.gary.weatherdemo.workmanager;

/**
 * Created by GaryCao on 2018/11/04.
 */
public class WorkerManagerClient {
    private static final String TAG = WorkerManagerClient.class.getSimpleName();
    private static WorkerManagerClient mInstance = new WorkerManagerClient();
    private IWorkerManager mIWorkerManager;

    private WorkerManagerClient(){
        init();
    }

    private void init(){
        mIWorkerManager = new WorkerManagerImpl();
    }

    public void loadAdrAdcodeConfig(){
        mIWorkerManager.loadAdrAdcodeConfig();
    }

    public void startPeriodicWeatherUpdate(){
        mIWorkerManager.periodicQueryWeather();
    }

    public static WorkerManagerClient getInstance() {
        return mInstance;
    }
}

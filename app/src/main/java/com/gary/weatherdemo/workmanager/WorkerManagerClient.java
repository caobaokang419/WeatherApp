package com.gary.weatherdemo.workmanager;

/**
 * Created by GaryCao on 2018/11/04.
 */
public class WorkerManagerClient {
    private static final String TAG = WorkerManagerClient.class.getSimpleName();
    private static WorkerManagerClient instance = new WorkerManagerClient();
    private IWorkerManager iWorkerManager;

    private WorkerManagerClient(){
        init();
    }

    private void init(){
        iWorkerManager = new WorkerManagerImpl();
    }

    public void startLoadAdrAdcodeConfig(){
        iWorkerManager.loadAdrAdcodeConfig();
    }

    public void startPeriodicWeatherUpdate(){
        iWorkerManager.periodicQueryWeather();
    }

    public void startQueryCityWeather(){
        iWorkerManager.queryCityWeather();
    }

    public static WorkerManagerClient getInstance() {
        return instance ;
    }
}

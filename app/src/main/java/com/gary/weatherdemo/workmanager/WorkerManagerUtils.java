package com.gary.weatherdemo.workmanager;

/**
 * Created by GaryCao on 2018/11/04.
 */
public class WorkerManagerUtils  {
    private static final String TAG = WorkerManagerUtils.class.getSimpleName();
    private static WorkerManagerUtils instance = new WorkerManagerUtils();
    private IWorkerManager iWorkerManager;

    private WorkerManagerUtils(){
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

    public static WorkerManagerUtils getInstance() {
        return instance ;
    }
}

package com.gary.weatherdemo.workmanager;

/**
 * Created by GaryCao on 2018/11/04.
 */
public interface IWorkerManager {
    /**
     * 单次任务：加载配置文件*/
    void loadAdrAdcodeConfig();

    /**
     * 定时任务：定时更新天气*/
    void periodicQueryWeather();

    /**
     * 分步任务：1:查询当前天气 2:查询天气预报*/
    void queryCityWeather();
}

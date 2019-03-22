package com.gary.weatherdemo.cache.memorycache;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.utils.CLog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/03/14.
 * 高德城市配置缓存实现
 * 优点：统一提供，供不同UI直接获取，不再需要重复&频繁请求DB或assert文件城市配置信息数据
 */
public class CityCacheManager {
    private static final String TAG = CityCacheManager.class.getSimpleName();
    /**
     * 缓存是否加载OK
     */
    private volatile boolean mCityCacheLoaded = false;

    public CityCacheManager() {

    }

    public boolean loadCityConfigFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    WtApplication.getInstance().getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);

            List<CityBean> cityBeans = new ArrayList<>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line == null || line.isEmpty()) {
                    continue;
                }

                String[] lineInfo = line.split(":");
                if (lineInfo != null && lineInfo.length == 2) {
                    cityBeans.add(new CityBean(lineInfo[0], lineInfo[1]));
                    CLog.d(TAG, "loadCityConfigFromAssets() " + lineInfo[0] + ":" + lineInfo[1]);
                }
            }

            CityCacheClient.getInstance().refreshCacheData(cityBeans);
            mCityCacheLoaded = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isCityCacheLoaded() {
        return mCityCacheLoaded;
    }
}

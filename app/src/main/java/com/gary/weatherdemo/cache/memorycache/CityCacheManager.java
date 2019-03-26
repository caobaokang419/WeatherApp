package com.gary.weatherdemo.cache.memorycache;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.SearchCityItemBean;
import com.gary.weatherdemo.bean.base.BaseItemBean;
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

    /**
     * 高德天气城市配置表缓存数组（1.首次读取assert配置文件获取 2.后续读取DB获取）
     */
    private List<CityBean> mCityBeans = new ArrayList<>();

    /**
     * 高德天气城市配置数据列表
     */
    private List<BaseItemBean> mSearchCityBeans = new ArrayList<>();

    public CityCacheManager() {

    }

    public boolean loadCityConfigFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    WtApplication.getInstance().getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);

            List<CityBean> cityBeans = new ArrayList<>();
            List<BaseItemBean> searchCityBeans = new ArrayList<>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line == null || line.isEmpty()) {
                    continue;
                }

                String[] lineInfo = line.split(":");
                if (lineInfo != null && lineInfo.length == 2) {
                    CityBean cityBean = new CityBean(lineInfo[0], lineInfo[1]);
                    cityBeans.add(cityBean);
                    searchCityBeans.add(new SearchCityItemBean(cityBean));
                    CLog.d(TAG, "loadCityConfigFromAssets() " + lineInfo[0] + ":" + lineInfo[1]);
                }
            }

            synchronized (CityCacheManager.this) {
                mCityBeans = cityBeans;
                mSearchCityBeans = searchCityBeans;
            }
            mCityCacheLoaded = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 通过地区名称，返回匹配成功的数据
     */
    public String getAdcodeByAddrName(String addrName) {
        if (!isCityCacheLoaded()) {
            return null;
        }

        if (null == addrName || addrName.isEmpty() || null == mCityBeans || mCityBeans.isEmpty()) {
            return null;
        }

        for (CityBean adinfo : mCityBeans) {
            if (adinfo.isAddrSearched(addrName)) {
                CLog.d("getAdcodeByAddrName() " + addrName + ": " + adinfo);
                return adinfo.adcCode;
            }
        }

        return null;
    }

    public List<BaseItemBean> getSearchCityBeans() {
        if (!isCityCacheLoaded()) {
            return new ArrayList<>();
        }

        return mSearchCityBeans;
    }

    public boolean isCityCacheLoaded() {
        return mCityCacheLoaded;
    }
}

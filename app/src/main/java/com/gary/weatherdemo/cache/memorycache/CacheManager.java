package com.gary.weatherdemo.cache.memorycache;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.CityItemBean;
import com.gary.weatherdemo.bean.base.BaseItemBean;
import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.utils.CLog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by GaryCao on 2019/03/14.
 * 高德城市配置缓存实现
 * 优点：统一提供，供不同UI直接获取，不再需要重复&频繁请求DB或assert文件城市配置信息数据
 */
public class CacheManager {
    private static final String TAG = CacheManager.class.getSimpleName();
    /**
     * 缓存是否加载OK
     */
    private volatile AtomicBoolean mCityCacheLoaded = new AtomicBoolean(false);

    /**
     * 高德天气城市配置表缓存数组（1.首次读取assert配置文件获取 2.后续读取DB获取）
     */
    private List<CityBean> mCityBeans = new ArrayList<>();

    /**
     * 高德天气城市配置数据列表
     */
    private List<BaseItemBean> mCityItemBeans = new ArrayList<>();

    /**
     * 主Pager页显示的城市列表
     */
    private List<CityBean> mFixedCityBeans = new ArrayList<>();

    public CacheManager() {
        mFixedCityBeans = Constants.COMMON_CITY_BEANS; //TODO
    }

    public boolean loadCityConfigFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    WtApplication.getInstance().getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);

            List<CityBean> cityBeans = new ArrayList<>();
            List<BaseItemBean> cityItemBeans = new ArrayList<>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line == null || line.isEmpty()) {
                    continue;
                }

                String[] lineInfo = line.split(":");
                if (lineInfo != null && lineInfo.length == 2) {
                    CityBean cityBean = new CityBean(lineInfo[0], lineInfo[1]);
                    cityBeans.add(cityBean);
                    cityItemBeans.add(new CityItemBean(cityBean));
                    //WtRepository.insertCityBean(cityBean);
                    CLog.d(TAG, "loadCityConfigFromAssets() " + lineInfo[0] + ":" + lineInfo[1]);
                }
            }

            synchronized (CacheManager.this) {
                mCityBeans = cityBeans;
                mCityItemBeans = cityItemBeans;
            }
            mCityCacheLoaded.set(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 通过关键字，返回匹配数据
     */
    public List<BaseItemBean> getPairedBeansByKeyWord(String keyword) {
        if (!isCityCacheLoaded()) {
            return null;
        }
        if (null == keyword
                || keyword.isEmpty()
                || null == mCityItemBeans
                || mCityItemBeans.isEmpty()) {
            return null;
        }

        List<BaseItemBean> itemBeans = new ArrayList<>();
        for (BaseItemBean item : mCityItemBeans) {
            if (item instanceof CityItemBean) {
                CityItemBean itemBean = (CityItemBean) item;
                if (itemBean.isSearched(keyword)) {
                    itemBeans.add(item);
                }
            }
        }
        return itemBeans;
    }

    public List<BaseItemBean> getCityItemBeans() {
        if (!isCityCacheLoaded()) {
            return new ArrayList<>();
        }

        return mCityItemBeans;
    }

    public List<CityBean> getFixedCityBeans() {
        return mFixedCityBeans;
    }

    public boolean isCityCacheLoaded() {
        return mCityCacheLoaded.get();
    }
}

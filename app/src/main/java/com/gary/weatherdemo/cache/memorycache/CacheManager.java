package com.gary.weatherdemo.cache.memorycache;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.CityItemBean;
import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.filter.FilterChain;
import com.gary.weatherdemo.utils.CLog;
import com.gary.weatherdemo.utils.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/03/14.
 * 高德城市配置缓存实现
 * 优点：统一提供，供不同UI直接获取，不再需要重复&频繁请求DB或assert文件城市配置信息数据
 */
public class CacheManager {
    private static final String TAG = CacheManager.class.getSimpleName();

    /**
     * 高德天气城市配置表缓存数组（1.首次读取assert配置文件获取 2.后续读取DB获取）
     */
    private List<CityBean> mCityBeans = new ArrayList<>();

    /**
     * 高德天气城市配置全部列表数据
     */
    private List<IViewItemBean> mCityItemBeans = new ArrayList<>();

    /**
     * 高德天气城市配置搜索数据列表
     */
    private List<IViewItemBean> mSearchCityItemBeans = new ArrayList<>();

    public CacheManager() {
    }

    public boolean loadCityConfigFromAssets(String fileName) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;

        try {
            inputReader = new InputStreamReader(
                    WtApplication.getContext().getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);

            List<CityBean> cityBeans = new ArrayList<>();
            List<IViewItemBean> cityItemBeans = new ArrayList<>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] lineInfo = line.split(":");
                if (lineInfo != null && lineInfo.length == 2) {
                    CityBean cityBean = new CityBean(lineInfo[0], lineInfo[1]);
                    cityBeans.add(cityBean);
                    cityItemBeans.add(new CityItemBean(cityBean));
                    //WtRepository.insertCityBeanEntity(cityBean);
                    CLog.d(TAG, "loadCityConfigFromAssets() " + lineInfo[0] + ":" + lineInfo[1]);
                }
            }

            synchronized (this) {
                mCityBeans = cityBeans;
                mCityItemBeans = cityItemBeans;
                mSearchCityItemBeans = cityItemBeans;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(inputReader);
            IOUtil.closeQuietly(bufReader);
        }
        return false;
    }

    /*public List<IViewItemBean> getCityItemBeans() {
        synchronized (this) {
            return mCityItemBeans;
        }
    }*/

    public List<IViewItemBean> getSearchedCityItemBeans() {
        synchronized (this) {
            return mSearchCityItemBeans;
        }
    }

    public void loadCityItemBeansByFilters(final FilterChain filterChain) {
        synchronized (this) {
            mSearchCityItemBeans = filterChain.doFilter(mCityItemBeans);
        }
    }
}

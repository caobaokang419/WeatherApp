package com.gary.weatherdemo.utils;

import com.gary.weatherdemo.base.WeatherApplication;
import com.gary.weatherdemo.bean.CityBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by GaryCao on 2018/10/28.
 */
public class CityQueryUtils {
    private static final String TAG = CityQueryUtils.class.getSimpleName();
    private static CityQueryUtils instance = new CityQueryUtils();
    private ArrayList<CityBean> cityBeans = new ArrayList<>();
    private volatile boolean isLoaded = false;

    /*avoid create() invoke by others users*/
    private CityQueryUtils() {
    }

    public void loadAdcodeConfig() {
        /*praseFromAssets(Constants.AMAP_ADCODE_CONFIG_FILE_NAME);

        //for test
        forTest();*/
    }

    private boolean praseFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    WeatherApplication.getInstance().getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            cityBeans.clear();
            while ((line = bufReader.readLine()) != null) {
                praseFileLineStr(line);
            }
            isLoaded = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private void praseFileLineStr(String lineStr) {
        if (lineStr == null || lineStr.isEmpty()) {
            return;
        }

        String[] strings = lineStr.split(":");
        if (strings != null && strings.length == 2) {
            cityBeans.add(new CityBean(strings[0], strings[1]));
            LogUtils.d("praseAdcodeConfigLineStr() " + strings[0] + ":" + strings[1]);
        }
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public String getAdcodeByAddress(String addr) {
        if (!isLoaded) {
            return null;
        }

        if (null == addr || addr.isEmpty() || null == cityBeans || cityBeans.isEmpty()) {
            return null;
        }

        for (CityBean adinfo : cityBeans) {
            if (adinfo.isAddrSearched(addr)) {
                LogUtils.d("getAdcodeByAddress() " + addr + ": " + adinfo);
                return adinfo.adcCode;
            }
        }

        return null;
    }

    public static CityQueryUtils getInstance() {
        return instance;
    }


    //===================================================================================================
    //for test
    private void forTest() {
        //深圳:adcode:440300 citycode:0755
        getAdcodeByAddress("深圳");
    }
}

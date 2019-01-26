package com.gary.weatherdemo.utils;

import com.gary.weatherdemo.base.WtApplication;
import com.gary.weatherdemo.model.CityBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2018/10/28.
 */
public class CityInfoUtil {
    private static final String TAG = CityInfoUtil.class.getSimpleName();
    private static CityInfoUtil mInstance = new CityInfoUtil();
    private List<CityBean> mCityBeans = new ArrayList<>();
    private volatile boolean mIsCityConfigLoaded = false;

    /*私有构造*/
    private CityInfoUtil() {
    }

    public void loadCityConfig() {
        /*praseFromAssets(Constants.AMAP_ADCODE_CONFIG_FILE_NAME);

        forTest();*/
    }

    private boolean praseFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    WtApplication.getInstance().getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            mCityBeans.clear();
            while ((line = bufReader.readLine()) != null) {
                praseFileLineStr(line);
            }
            mIsCityConfigLoaded = true;
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
            mCityBeans.add(new CityBean(strings[0], strings[1]));
            LogUtils.d("praseAdcodeConfigLineStr() " + strings[0] + ":" + strings[1]);
        }
    }

    public boolean isCityConfigLoaded() {
        return mIsCityConfigLoaded;
    }

    public void setCityConfigLoaded(boolean loaded) {
        mIsCityConfigLoaded = loaded;
    }

    public String getAdcodeByAddress(String addr) {
        if (!mIsCityConfigLoaded) {
            return null;
        }

        if (null == addr || addr.isEmpty() || null == mCityBeans || mCityBeans.isEmpty()) {
            return null;
        }

        for (CityBean adinfo : mCityBeans) {
            if (adinfo.isAddrSearched(addr)) {
                LogUtils.d("getAdcodeByAddress() " + addr + ": " + adinfo);
                return adinfo.adcCode;
            }
        }

        return null;
    }

    public static CityInfoUtil getInstance() {
        return mInstance;
    }


    //===================================================================================================
    //for test
    private void forTest() {
        //深圳:adcode:440300 citycode:0755
        getAdcodeByAddress("深圳");
    }
}

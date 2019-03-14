package com.gary.weatherdemo.cache.memorycache;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.utils.CLog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/03/14.
 * 高德城市配置缓存实现，供UI直接获取，不再需要重复&频繁请求DB或assert文件数据
 */
public class CityCacheClient {
    private static final String TAG = CityCacheClient.class.getSimpleName();
    private static final Object mLock = new Object();

    /**
     * process main-thread(UI thread) works
     */
    public static Handler mUiHandler = new Handler(Looper.getMainLooper());

    /**
     * process sub-thread works
     */
    private static Handler mWorkHandler;

    private static CityCacheClient mCityCacheClient;

    /**
     * 缓存是否加载OK
     */
    private volatile boolean mCityCacheLoaded = false;

    /**
     * 高德天气城市配置表缓存数组（1.首次读取assert配置文件获取 2.后续读取DB获取）
     */
    private static List<CityBean> mCityBeans;


    /**
     * 私有构造
     */
    private CityCacheClient() {
        initWorkHandlerThread();
        mCityBeans = new ArrayList<>();
    }

    private void initWorkHandlerThread() {
        CLog.i(TAG, "initWorkHandlerThread()");
        HandlerThread handlerThread = new HandlerThread("city_thread");
        handlerThread.start();
        mWorkHandler = new Handler(handlerThread.getLooper());
    }

    public void loadCityConfigCache() {
        /*praseFromAssets(Constants.AMAP_ADCODE_CONFIG_FILE_NAME);*/
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
            mCityCacheLoaded = true;
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
            CLog.d("praseFileLineStr() " + strings[0] + ":" + strings[1]);
        }
    }


    /**
     * 通过地区名称，返回匹配成功的数据
     */
    public String getAdcodeByAddrName(String addrName) {
        if (!mCityCacheLoaded) {
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

    public static CityCacheClient getInstance() {
        synchronized (mLock) {
            if (mCityCacheClient == null) {
                mCityCacheClient = new CityCacheClient();
            }
            return mCityCacheClient;
        }
    }
}

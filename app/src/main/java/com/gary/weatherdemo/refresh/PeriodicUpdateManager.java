package com.gary.weatherdemo.refresh;

import android.os.Handler;
import android.os.HandlerThread;

import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.cache.memorycache.CacheClient;
import com.gary.weatherdemo.repository.WtRepository;
import com.gary.weatherdemo.repository.WtRepositoryHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PeriodicUpdateManager {
    private static PeriodicUpdateManager mInstant;
    private static Object mLock = new Object();
    private static BlockingQueue<CityBean> mQueue = new LinkedBlockingQueue<>();
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;

    private PeriodicUpdateManager() {
        mHandlerThread = new HandlerThread("PeriodicUpdate");
        mHandlerThread.run();
        mWorkHandler = new Handler(mHandlerThread.getLooper());
    }

    public static PeriodicUpdateManager getInstant() {
        synchronized (mLock) {
            if (mInstant == null) {
                mInstant = new PeriodicUpdateManager();
            }
        }
        return mInstant;
    }

    public interface IWeatherQueryCompletedCallback {
        void onCityWeatherQueryCompleted();
    }

    private List<IWeatherQueryCompletedCallback> mCallbacks = new ArrayList<>();

    public void addListener(IWeatherQueryCompletedCallback callback) {
        mCallbacks.add(callback);
    }

    public void removeListener(IWeatherQueryCompletedCallback callback) {
        mCallbacks.remove(callback);
    }

    public void startPeriodicUpdate() {
        synchronized (mLock) {
            mQueue.clear();
            List<CityBean> cityBeans = CacheClient.getInstance().getFixedCityBeans();
            for (CityBean cityBean : cityBeans) {
                mQueue.add(cityBean);
            }
            executeCurrentTask();
        }
    }

    private void executeCurrentTask() {
        if (mQueue.isEmpty()) {
            notifyCityWeatherChanged();
        } else {
            CityBean cityBean = mQueue.poll();
            WtRepositoryHelper.queryCityWeather(cityBean, new WtRepositoryHelper.IQueryWeather() {
                @Override
                public void onWeatherQueryCompleted(List<IViewItemBean> data) {
                    executeCurrentTask();
                }
            });
        }
    }

    public void notifyCityWeatherChanged(){
        for (IWeatherQueryCompletedCallback callback : mCallbacks) {
            callback.onCityWeatherQueryCompleted();
        }
    }

    public void addTaskToQueue(CityBean cityBean) {
        mQueue.add(cityBean);
    }

    public void removeTaskToQueue(CityBean cityBean) {
        mQueue.remove(cityBean);
    }
}

package com.gary.weatherdemo.refresh;

import android.os.Handler;
import android.os.HandlerThread;

import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.cache.memcache.CacheClient;
import com.gary.weatherdemo.repository.WtRepositoryHelper;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class PeriodicUpdateManager {
    private static PeriodicUpdateManager mInstant;
    private static BlockingQueue<CityBean> mQueue = new LinkedBlockingQueue<>();
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;

    private PeriodicUpdateManager() {
        mHandlerThread = new HandlerThread("update_thread");
        mHandlerThread.run();
        mWorkHandler = new Handler(mHandlerThread.getLooper());
    }

    public synchronized static PeriodicUpdateManager getInstant() {
        if (mInstant == null) {
            mInstant = new PeriodicUpdateManager();
        }
        return mInstant;
    }

    public interface IWeatherQuery {
        void onWeatherQueryCompleted();
    }

    private List<IWeatherQuery> mListener = new CopyOnWriteArrayList<>();

    public void addListener(IWeatherQuery callback) {
        mListener.add(callback);
    }

    public void removeListener(IWeatherQuery callback) {
        if (callback != null) {
            mListener.remove(callback);
        }
    }

    public synchronized void startPeriodicUpdate() {
        mQueue.clear();
        List<CityBean> cityBeans = CacheClient.getInstance().getFixedCityBeans();
        for (CityBean cityBean : cityBeans) {
            mQueue.add(cityBean);
        }
        executeCurrentTask();
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

    public void notifyCityWeatherChanged() {
        for (IWeatherQuery callback : mListener) {
            callback.onWeatherQueryCompleted();
        }
    }

    public void addTaskToQueue(CityBean cityBean) {
        mQueue.add(cityBean);
    }

    public void removeTaskToQueue(CityBean cityBean) {
        mQueue.remove(cityBean);
    }

    public void release() {
        if (mHandlerThread != null) {
            mHandlerThread.quit();
        }
    }
}

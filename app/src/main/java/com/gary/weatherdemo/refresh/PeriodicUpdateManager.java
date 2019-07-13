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
    private static PeriodicUpdateManager mInstance;
    private static BlockingQueue<CityBean> mQueue = new LinkedBlockingQueue<>();
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;
    private List<IWeatherQueryListener> mListener = new CopyOnWriteArrayList<>();

    public interface IWeatherQueryListener {
        void onWeatherQueryCompleted();
    }

    public synchronized static PeriodicUpdateManager getInstance() {
        if (mInstance == null) {
            mInstance = new PeriodicUpdateManager();
        }
        return mInstance;
    }

    private PeriodicUpdateManager() {
        mHandlerThread = new HandlerThread("update_thread");
        mHandlerThread.run();
        mWorkHandler = new Handler(mHandlerThread.getLooper());
    }

    public void release() {
        if (mHandlerThread != null) {
            mHandlerThread.quit();
        }
    }

    public void addListener(IWeatherQueryListener listener) {
        mListener.add(listener);
    }

    public void removeListener(IWeatherQueryListener listener) {
        if (listener != null) {
            mListener.remove(listener);
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
            WtRepositoryHelper.queryCityWeather(cityBean,
                    new WtRepositoryHelper.IQueryWeatherCallback() {
                @Override
                public void onWeatherQueryCompleted(List<IViewItemBean> data) {
                    executeCurrentTask();
                }
            });
        }
    }

    public void notifyCityWeatherChanged() {
        for (IWeatherQueryListener listener : mListener) {
            listener.onWeatherQueryCompleted();
        }
    }

    public void addTaskToQueue(CityBean cityBean) {
        mQueue.add(cityBean);
    }

    public void removeTaskToQueue(CityBean cityBean) {
        mQueue.remove(cityBean);
    }
}

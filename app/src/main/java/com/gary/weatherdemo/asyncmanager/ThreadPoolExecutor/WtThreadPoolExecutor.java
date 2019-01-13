package com.gary.weatherdemo.asyncmanager.ThreadPoolExecutor;

import com.gary.weatherdemo.exception.WtException;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by GaryCao on 2018/10/25.
 * //TODO 线程池：处理并发任务( Concurrent or parallel??)
 */
public class WtThreadPoolExecutor {
    public WtThreadPoolExecutor() {
    }

    /**
     * 创建线程池
     *
     * @param corePoolSize    同java自带ThreadPoolExecutor初始化参数
     * @param maximumPoolSize 同java自带ThreadPoolExecutor初始化参数
     * @param keepAliveTime   同java自带ThreadPoolExecutor初始化参数
     * @param timeUnit        同java自带ThreadPoolExecutor初始化参数
     * @param parmHMap        key-value方式的参数:
     * @return
     * @throws WtException
     */
    public static ThreadPoolExecutor generateThreadPoolExecutor(
            int corePoolSize, int maximumPoolSize, long keepAliveTime,
            TimeUnit timeUnit, HashMap parmHMap)
            throws WtException {
        int tmpCapacity = 500;

        ThreadPoolExecutor tmpThreadPool = null;
        try {
            ArrayBlockingQueue arrayBlockingQueue =
                    new ArrayBlockingQueue(tmpCapacity, true);
            tmpThreadPool =
                    new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit,
                            arrayBlockingQueue);
            // arrayBlockingQueue.doFetchData_init(tmpThreadPool);
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new WtException(ex.getLocalizedMessage());
        }

        return tmpThreadPool;
    }
}

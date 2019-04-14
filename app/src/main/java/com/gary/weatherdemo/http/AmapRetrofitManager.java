package com.gary.weatherdemo.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class AmapRetrofitManager {
    private static final int DEFAULT_CONNECT_TIME = 20;
    private static final int DEFAULT_WRITE_TIME = 50;
    private static final int DEFAULT_READ_TIME = 30;

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private static AmapRetrofitManager mAmapRetrofitManager;

    private AmapRetrofitManager() {
        initOkHttpClient();
        initRetrofit();
    }

    private void initRetrofit() {
        /**
         * GoF23 设计模式 3：Builder模式
         */
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AmapContants.AMAP_BASE_URL)//设置服务器路径
                .client(mOkHttpClient)//设置使用okhttp网络请求
                .addConverterFactory(GsonConverterFactory.create())//添加转化库，默认是Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加回调库，采用RxJava
                .build();
    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)//设置写操作超时时间
                .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)//设置读操作超时时间
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public synchronized static AmapRetrofitManager getInstance() {
        if (mAmapRetrofitManager == null) {
            mAmapRetrofitManager = new AmapRetrofitManager();
        }
        return mAmapRetrofitManager;
    }

    /**
     * 泛型方法
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }
}
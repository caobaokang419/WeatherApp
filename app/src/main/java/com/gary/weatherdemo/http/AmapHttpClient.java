package com.gary.weatherdemo.http;

import com.gary.weatherdemo.http.bean.AllForecastResponseData;
import com.gary.weatherdemo.http.bean.LiveWeatherResponseData;
import com.gary.weatherdemo.repository.Repository;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class AmapHttpClient implements IAmapHttpClient {
    private static AmapHttpClient amapHttpClient;
    private static AmapService amapService;

    private AmapHttpClient() {
        createApiClient();
    }

    private AmapService createApiClient() {
        if (amapService == null) {
            amapService = AmapRetrofitHelper.getInstance().create(AmapService.class);
        }
        return amapService;
    }

    public synchronized static void registerInstance() {
        if (amapHttpClient == null) {
            amapHttpClient = new AmapHttpClient();
        }

        Repository.Ext.setAmapHttpManager(amapHttpClient);
    }

    @Override
    public Observable<LiveWeatherResponseData> liveWeatherPost(final String adcode) {
        return amapService.livesweatherPost(adcode,
                AmapContants.AMAP_USER_KEY_VALUE,
                AmapContants.AmapUserExt.BASE,
                AmapContants.AmapUserOutput.JSON)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<AllForecastResponseData> forecastWeatherPost(final String adcode) {
        return amapService.allweatherPost(adcode,
                AmapContants.AMAP_USER_KEY_VALUE,
                AmapContants.AmapUserExt.ALL,
                AmapContants.AmapUserOutput.JSON)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<LiveWeatherResponseData> livesWeatherGet(final String adcode) {
        return amapService.livesweatherGet(adcode,
                AmapContants.AMAP_USER_KEY_VALUE,
                AmapContants.AmapUserExt.BASE,
                AmapContants.AmapUserOutput.JSON)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<AllForecastResponseData> forecastWeatherGet(final String adcode) {
        return amapService.allweatherGet(adcode,
                AmapContants.AMAP_USER_KEY_VALUE,
                AmapContants.AmapUserExt.ALL,
                AmapContants.AmapUserOutput.JSON)
                .subscribeOn(Schedulers.io());
    }
}
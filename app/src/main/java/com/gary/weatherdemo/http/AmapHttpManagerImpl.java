package com.gary.weatherdemo.http;

import com.gary.weatherdemo.http.response.AllForecastResponseData;
import com.gary.weatherdemo.http.response.LiveWeatherResponseData;
import com.gary.weatherdemo.repository.WtRepository;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class AmapHttpManagerImpl implements AmapHttpManager {
    private static AmapHttpManagerImpl amapHttpManagerImpl;
    private static AmapService amapService;

    private AmapHttpManagerImpl() {
        createApiClient();
    }

    private AmapService createApiClient() {
        if (amapService == null) {
            amapService = AmapRetrofitHelper.getInstance().create(AmapService.class);
        }
        return amapService;
    }

    public synchronized static void registerInstance() {
        if (amapHttpManagerImpl == null) {
            amapHttpManagerImpl = new AmapHttpManagerImpl();
        }

        WtRepository.Ext.setAmapHttpManager(amapHttpManagerImpl);
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
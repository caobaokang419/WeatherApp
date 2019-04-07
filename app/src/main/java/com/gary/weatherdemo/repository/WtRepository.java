package com.gary.weatherdemo.repository;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.DayForecastBean;
import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.http.WeatherRequestClient;
import com.gary.weatherdemo.http.response.AllForecastResponseData;
import com.gary.weatherdemo.http.response.LiveWeatherResponseData;
import com.gary.weatherdemo.room.WtDatabase;
import com.gary.weatherdemo.room.city.CityBeanEntity;
import com.gary.weatherdemo.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by GaryCao on 2019/03/30.
 * GoF23 设计模式 10：外观模式: db+http apis
 */
public class WtRepository {
    public static void insertCityBeanEntity(CityBean cityBean) {
        CityBeanEntity entity = CityBeanEntity.fromCityBean(cityBean);
        WtDatabase.getInstance(WtApplication.getInstance()).cityInfoDAO().insert(entity);
    }

    public static Observable<LiveWeatherResponseData> queryCityCurWeather(CityBean cityBean) {
        return WeatherRequestClient.getInstance().liveWeatherPost(cityBean.cityCode);
    }

    public static Observable<AllForecastResponseData> queryCityForecast(CityBean cityBean) {
        return WeatherRequestClient.getInstance().forecastWeatherPost(cityBean.cityCode);
    }

    public static void queryCityWeather(final CityBean citybean, final IQueryWeather iQueryWeather) {
        if (!NetworkUtil.isNetworkConnected()) {
            return;
        }

        /**task1: 查询当前天气*/
        Observable<LiveWeatherResponseData> observable1 =
                queryCityCurWeather(citybean).subscribeOn(Schedulers.io());//被观察者Observable运行在子线程

        /**task2: 查询未来天气预报*/
        Observable<AllForecastResponseData> observable2 =
                queryCityForecast(citybean).subscribeOn(Schedulers.io());

        /**Observable.zip: 实现task1+ task2 异步任务都完成时，回调 订阅的UI刷新*/
        Observable.zip(observable1, observable2,
                new BiFunction<LiveWeatherResponseData, AllForecastResponseData, List<IViewItemBean>>() {
                    @Override
                    public List<IViewItemBean> apply(LiveWeatherResponseData livedata,
                                                     AllForecastResponseData allForecastdata) throws Exception {
                        /**task1+task2 ，此处可以处理耗时流程（子线程）*/
                        return combineWeatherData(livedata, allForecastdata);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//观察者运行在UI线程
                .subscribe(new Consumer<List<IViewItemBean>>() {
                    @Override
                    public void accept(List<IViewItemBean> dataList) throws Exception {
                        /**实现UI订阅逻辑（AndroidSchedulers.mainThread）*/
                        iQueryWeather.onWeatherQueryCompleted(dataList);
                    }
                });
    }

    private static List<IViewItemBean> combineWeatherData(
            LiveWeatherResponseData livedata,
            AllForecastResponseData allForecastdata) {
        List<DayForecastBean> dayForecastList = null;
        List<IViewItemBean> dataList = new ArrayList<>();
        dataList.clear();
        if (livedata != null && livedata.isSuccessful()) {
            dataList.add(livedata.getWeatherLiveResult());
        }
        if (allForecastdata != null
                && allForecastdata.isSuccessful()
                && allForecastdata.getWeatherAllResult() != null) {
            dayForecastList = allForecastdata.getWeatherAllResult().dayForecastBeanList;
        }
        for (DayForecastBean fdata : dayForecastList) {
            dataList.add(fdata);
        }
        return dataList;
    }

    public interface IQueryWeather {
        void onWeatherQueryCompleted(List<IViewItemBean> data);
    }
}

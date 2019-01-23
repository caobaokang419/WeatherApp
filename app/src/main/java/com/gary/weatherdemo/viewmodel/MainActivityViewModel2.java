package com.gary.weatherdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.model.DayForecastBean;
import com.gary.weatherdemo.model.LiveWeatherBean;
import com.gary.weatherdemo.model.base.BaseItemBean;
import com.gary.weatherdemo.network.WeatherRequestClient;
import com.gary.weatherdemo.network.response.AllForecastResponseData;
import com.gary.weatherdemo.network.response.LiveWeatherResponseData;
import com.gary.weatherdemo.ui.adapter.CityWeatherRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivityViewModel2 extends AndroidViewModel {
    public final ObservableField<CityWeatherRecyclerAdapter> mWeatherAdapter = new ObservableField<>();
    private final CityWeatherRecyclerAdapter mAdapter;

    private MutableLiveData<CityBean> mCurCityBean = new MutableLiveData<>();
    private List<BaseItemBean> mRecycleItemDatas = new ArrayList<>();
    private MutableLiveData<LiveWeatherBean> mLiveWeatherData = new MutableLiveData<>();

    public MainActivityViewModel2(@NonNull Application application) {
        super(application);
        mAdapter = new CityWeatherRecyclerAdapter();
    }

    public void queryCityWeather(String adcode) {
        Observable<LiveWeatherResponseData> observable1 =
                WeatherRequestClient.getInstance().liveWeatherPost(adcode).subscribeOn(Schedulers.io());

        Observable<AllForecastResponseData> observable2 =
                WeatherRequestClient.getInstance().forecastWeatherPost(adcode).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2,
                new BiFunction<LiveWeatherResponseData, AllForecastResponseData, ArrayList<BaseItemBean>>() {
                    @Override
                    public ArrayList<BaseItemBean> apply(LiveWeatherResponseData livedata,
                                                         AllForecastResponseData allForecastdata) throws Exception {
                        List<DayForecastBean> dayForecastList = null;
                        ArrayList<BaseItemBean> dataList = new ArrayList<>();

                        if (livedata != null && livedata.isSuccessful()) {
                            mLiveWeatherData.postValue(livedata.getWeatherLiveResult());
                        }
                        if (allForecastdata != null
                                && allForecastdata.isSuccessful()
                                && allForecastdata.getWeatherAllResult() != null) {
                            dayForecastList = allForecastdata.getWeatherAllResult().dayForecastBeanList;
                        }

                        dataList.clear();
                        dataList.add(mLiveWeatherData.getValue());
                        for (DayForecastBean fdata : dayForecastList) {
                            dataList.add(fdata);
                        }

                        return dataList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseItemBean>>() {
                    @Override
                    public void accept(List<BaseItemBean> dataList) throws Exception {
                        mRecycleItemDatas = dataList;
                        mAdapter.setAdapterData(mRecycleItemDatas);
                        mWeatherAdapter.set(mAdapter);
                    }
                });
    }

    public void loadCurCityInfo() {
        mCurCityBean.setValue(new CityBean("深圳", "440300"));
    }

    public MutableLiveData<CityBean> getCurCityInfo() {
        return mCurCityBean;
    }
}

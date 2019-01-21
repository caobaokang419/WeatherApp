package com.gary.weatherdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.example.commonui.PageIndicatorView;
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


public class MainActivity2ViewModel extends AndroidViewModel {
    private int pageCount;
    private int pageSelectedIndex;
    private PageChangeListener pageChangeListener = new PageChangeListener();

    public final ObservableField<CityWeatherRecyclerAdapter> weatherAdapter = new ObservableField<>();
    private final CityWeatherRecyclerAdapter adapter;
    private MutableLiveData<CityBean> curCityBean = new MutableLiveData<>();
    private MutableLiveData<List<CityBean>> cityBeanList = new MutableLiveData<>();

    private List<BaseItemBean> itemDataList = new ArrayList<>();
    private MutableLiveData<LiveWeatherBean> liveWeatherData = new MutableLiveData<>();

    private String[] cityNames =
            new String[]{"深圳", "西安", "合肥", "武汉", "巢湖市", "北京", "上海", "广州", "成都", "南京", "杭州", "苏州"};
    private String[] cityAdcode =
            new String[]{"440300", "610100", "340100", "420100", "340181", "110000", "310000", "440100", "510100", "320100", "330100", "320500"};

    public MainActivity2ViewModel(@NonNull Application application) {
        super(application);
        adapter = new CityWeatherRecyclerAdapter();

        List<CityBean> data = new ArrayList<>();
        for (int i = 0; i < cityNames.length; i++) {
            CityBean CityBean = new CityBean(cityNames[i],cityAdcode[i]);
            data.add(CityBean);
        }
        cityBeanList.setValue(data);
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
                            liveWeatherData.postValue(livedata.getWeatherLiveResult());
                        }
                        if (allForecastdata != null
                                && allForecastdata.isSuccessful()
                                && allForecastdata.getWeatherAllResult() != null) {
                            dayForecastList = allForecastdata.getWeatherAllResult().dayForecastBeanList;
                        }

                        dataList.clear();
                        dataList.add(liveWeatherData.getValue());
                        for (DayForecastBean fdata : dayForecastList) {
                            dataList.add(fdata);
                        }

                        return dataList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseItemBean>>() {
                    @Override
                    public void accept(List<BaseItemBean> dataList) throws Exception {
                        itemDataList = dataList;
                        adapter.setAdapterData(itemDataList);
                        weatherAdapter.set(adapter);
                    }
                });
    }

    public void loadCurCityInfo(CityBean cityBean) {
        curCityBean.setValue(cityBean);
    }

    public MutableLiveData<CityBean> getCurCityInfo() {
        return curCityBean;
    }

    public List<CityBean> getCityInfoList() {
        return cityBeanList.getValue();
    }

    private void updateCurPageIndex(int index) {
        pageSelectedIndex = index;
        loadCurCityInfo(cityBeanList.getValue().get(index));
    }

    private void updatePageCount(int count) {
        pageCount = count;
        //updateView();
    }

    class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageSelected(int position) {
            updateCurPageIndex(position);
        }
    }

    public void registerPageChangeListener(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(pageChangeListener);
        updatePageCount(viewPager.getAdapter().getCount());
    }
}

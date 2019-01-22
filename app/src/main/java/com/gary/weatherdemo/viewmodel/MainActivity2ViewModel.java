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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity2ViewModel extends AndroidViewModel {
    private final String[] cityNames =
            new String[]{"深圳", "西安", "合肥", "武汉", "巢湖市", "北京", "上海", "广州", "成都", "南京", "杭州", "苏州"};
    private final String[] cityAdcode =
            new String[]{"440300", "610100", "340100", "420100", "340181", "110000", "310000", "440100", "510100", "320100", "330100", "320500"};

    private final Map<CityBean, CityWeatherRecyclerAdapter> adapterDatas = new HashMap<>();
    private MutableLiveData<CityBean> curCityBean = new MutableLiveData<>();
    private MutableLiveData<List<CityBean>> cityBeanList = new MutableLiveData<>();
    private Map<CityBean, List<BaseItemBean>> cityWeatherDatas = new HashMap<>();
    private PageChangeListener pageChangeListener = new PageChangeListener();
    private int pageCount;
    private int pageSelectedIndex;

    public MainActivity2ViewModel(@NonNull Application application) {
        super(application);

        List<CityBean> data = new ArrayList<>();
        for (int i = 0; i < cityNames.length; i++) {
            CityBean cityBean = new CityBean(cityNames[i], cityAdcode[i]);
            data.add(cityBean);
            adapterDatas.put(cityBean, new CityWeatherRecyclerAdapter());
        }
        cityBeanList.setValue(data);
    }

    public void queryCityWeather(final CityBean cityinfo) {
        Observable<LiveWeatherResponseData> observable1 =
                WeatherRequestClient.getInstance().liveWeatherPost(cityinfo.adcCode).subscribeOn(Schedulers.io());

        Observable<AllForecastResponseData> observable2 =
                WeatherRequestClient.getInstance().forecastWeatherPost(cityinfo.adcCode).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2,
                new BiFunction<LiveWeatherResponseData, AllForecastResponseData, ArrayList<BaseItemBean>>() {
                    @Override
                    public ArrayList<BaseItemBean> apply(LiveWeatherResponseData livedata,
                                                         AllForecastResponseData allForecastdata) throws Exception {
                        List<DayForecastBean> dayForecastList = null;
                        ArrayList<BaseItemBean> dataList = new ArrayList<>();

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
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseItemBean>>() {
                    @Override
                    public void accept(List<BaseItemBean> dataList) throws Exception {
                        cityWeatherDatas.put(cityinfo,dataList);
                        CityWeatherRecyclerAdapter adapter = getCityWeatherRecyclerAdapter(cityinfo);
                        adapter.setAdapterData(dataList);
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

    public CityWeatherRecyclerAdapter getCityWeatherRecyclerAdapter(CityBean cityinfo) {
        return adapterDatas.get(cityinfo);
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

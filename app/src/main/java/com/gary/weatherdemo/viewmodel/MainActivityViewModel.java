package com.gary.weatherdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.model.DayForecastBean;
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


public class MainActivityViewModel extends AndroidViewModel {
    private final String[] mCityNames =
            new String[]{"深圳", "西安", "合肥", "武汉"};
    private final String[] mCityAdcode =
            new String[]{"440300", "610100", "340100", "420100"};

    private final Map<CityBean, CityWeatherRecyclerAdapter> mAdapterDatas = new HashMap<>();
    private MutableLiveData<CityBean> mCurCityBean = new MutableLiveData<>();
    private MutableLiveData<List<CityBean>> mCityBeans = new MutableLiveData<>();
    private Map<CityBean, List<BaseItemBean>> mCityWeatherDatas = new HashMap<>();

    private PageChangeListener mPageChangeListener = new PageChangeListener();
    private int mPageCount;
    private int mPageSelectedIndex;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        List<CityBean> data = new ArrayList<>();
        for (int i = 0; i < mCityNames.length; i++) {
            CityBean cityBean = new CityBean(mCityNames[i], mCityAdcode[i]);
            data.add(cityBean);
            mAdapterDatas.put(cityBean, new CityWeatherRecyclerAdapter());
        }
        mCityBeans.setValue(data);
    }

    /**
     * 查询城市天气
     */
    public void queryCityWeather(final CityBean cityinfo) {
        /**task1: 查询当前天气*/
        Observable<LiveWeatherResponseData> observable1 =
                WeatherRequestClient.getInstance().liveWeatherPost(cityinfo.adcCode)
                        .subscribeOn(Schedulers.io());//被观察者Observable运行在子线程

        /**task2: 查询未来天气预报*/
        Observable<AllForecastResponseData> observable2 =
                WeatherRequestClient.getInstance().forecastWeatherPost(cityinfo.adcCode)
                        .subscribeOn(Schedulers.io());

        /**Observable.zip: 实现task1+ task2 异步任务都完成时，回调 订阅的UI刷新*/
        Observable.zip(observable1, observable2,
                new BiFunction<LiveWeatherResponseData, AllForecastResponseData, ArrayList<BaseItemBean>>() {
                    @Override
                    public ArrayList<BaseItemBean> apply(LiveWeatherResponseData livedata,
                                                         AllForecastResponseData allForecastdata) throws Exception {
                        /**task1+task2 ，此处可以处理耗时流程（子线程）*/
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
                })
                .observeOn(AndroidSchedulers.mainThread())//观察者运行在UI线程
                .subscribe(new Consumer<List<BaseItemBean>>() {
                    @Override
                    public void accept(List<BaseItemBean> dataList) throws Exception {
                        /**实现UI订阅逻辑（AndroidSchedulers.mainThread）*/
                        mCityWeatherDatas.put(cityinfo, dataList);
                        CityWeatherRecyclerAdapter adapter = getCityWeatherRecyclerAdapter(cityinfo);
                        adapter.setAdapterData(dataList);
                    }
                });
    }

    public void loadCurCityInfo(CityBean cityBean) {
        mCurCityBean.setValue(cityBean);
    }

    public MutableLiveData<CityBean> getCurCityInfo() {
        return mCurCityBean;
    }

    public List<CityBean> getCityInfoList() {
        return mCityBeans.getValue();
    }

    public CityWeatherRecyclerAdapter getCityWeatherRecyclerAdapter(CityBean cityinfo) {
        CityWeatherRecyclerAdapter adapter = mAdapterDatas.get(cityinfo);
        if (adapter == null) {
            mAdapterDatas.put(cityinfo, new CityWeatherRecyclerAdapter());
        }

        return mAdapterDatas.get(cityinfo);
    }

    private void updateCurPageIndex(int index) {
        mPageSelectedIndex = index;
        loadCurCityInfo(mCityBeans.getValue().get(index));
    }

    private void updatePageCount(int count) {
        mPageCount = count;
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
        viewPager.addOnPageChangeListener(mPageChangeListener);
        updatePageCount(viewPager.getAdapter().getCount());
    }
}

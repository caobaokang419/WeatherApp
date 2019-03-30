package com.gary.weatherdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.base.BaseItemBean;
import com.gary.weatherdemo.repository.WtRepository;
import com.gary.weatherdemo.ui.adapter.CommonRecyclerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivityViewModel extends AndroidViewModel {
    private final Map<CityBean, CommonRecyclerAdapter> mAdapterDatas = new HashMap<>();
    private MutableLiveData<CityBean> mCurCityBean = new MutableLiveData<>();
    private MutableLiveData<List<CityBean>> mCityBeans = new MutableLiveData<>();
    private Map<CityBean, List<BaseItemBean>> mCityWeatherDatas = new HashMap<>();

    private PageChangeListener mPageChangeListener = new PageChangeListener();
    private int mPageCount;
    private int mPageSelectedIndex;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        for (CityBean cityBean : Constants.COMMON_CITY_BEANS) {
            mAdapterDatas.put(cityBean, new CommonRecyclerAdapter());
        }
        mCityBeans.setValue(Constants.COMMON_CITY_BEANS);
    }

    /**
     * 查询城市天气
     */
    public void queryCityWeather(final CityBean cityBean) {
        WtRepository.queryCityWeather(cityBean, new WtRepository.IQueryWeather() {
            @Override
            public void onWeatherQueryCompleted(List<BaseItemBean> data) {
                /**实现UI订阅逻辑（AndroidSchedulers.mainThread）*/
                mCityWeatherDatas.put(cityBean, data);
                CommonRecyclerAdapter adapter = getCityWeatherRecyclerAdapter(cityBean);
                adapter.setAdapterData(data);
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

    public CommonRecyclerAdapter getCityWeatherRecyclerAdapter(CityBean cityinfo) {
        CommonRecyclerAdapter adapter = mAdapterDatas.get(cityinfo);
        if (adapter == null) {
            mAdapterDatas.put(cityinfo, new CommonRecyclerAdapter());
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

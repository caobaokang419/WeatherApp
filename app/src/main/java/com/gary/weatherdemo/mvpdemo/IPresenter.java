package com.gary.weatherdemo.mvpdemo;

import com.gary.weatherdemo.model.CityBean;

/**
 * Created by GaryCao on 2019/03/13.
 * <p>
 * MVP应用框架元素2：IPresenter （用于MVVM框架对比&参考）
 */
public interface IPresenter {
    void onUiReady(IView iview);
    void onUiUnready();
    void queryCityWeather(CityBean cityBean);
}

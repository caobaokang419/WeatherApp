package com.gary.weatherdemo.mvpdemo;

import com.gary.weatherdemo.bean.CityBean;

/**
 * Created by GaryCao on 2019/03/13.
 * <p>
 * MVP应用框架元素3：Presenter （用于MVVM框架对比&参考）
 */
public class MvpDemoPresenter implements IPresenter{
    private IView iView;
    public MvpDemoPresenter(){
    }

    @Override
    public void onUiReady(IView iview) {
        iView = iview;
    }

    @Override
    public void onUiUnready() {
        iView = null;
    }

    @Override
    public void queryCityWeather(CityBean cityBean) {
        MvpDemoModel.getInstance().getCityWeather(cityBean);
    }
}

package com.gary.weatherdemo.mvpdemo;

import com.gary.weatherdemo.bean.CityBean;

/**
 * Created by GaryCao on 2019/03/13.
 * <p>
 * MVP应用框架元素6：Model （用于MVVM框架对比&参考）
 */
public class MvpDemoModel implements IModel{
    private static MvpDemoModel mMvpDemoModel;

    private MvpDemoModel(){

    }

    @Override
    public void getCityWeather(CityBean citybean) {

    }

    public synchronized static MvpDemoModel getInstance() {
        if (mMvpDemoModel == null) {
            mMvpDemoModel = new MvpDemoModel();
        }
        return mMvpDemoModel;
    }
}

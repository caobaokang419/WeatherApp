package com.gary.weatherdemo.mvcdemo;

import com.gary.weatherdemo.model.CityBean;

public class MvcDemoController implements IController {
    private IModel iModel;

    public MvcDemoController() {
        iModel = new MvcDemoModel();
    }

    @Override
    public void queryCityWeather(CityBean cityBean) {
        iModel.getCityWeather(cityBean);
    }
}

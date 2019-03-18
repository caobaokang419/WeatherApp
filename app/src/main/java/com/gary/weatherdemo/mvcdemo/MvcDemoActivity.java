package com.gary.weatherdemo.mvcdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;

/**
 * Created by GaryCao on 2019/01/13.
 * <p>
 * MVC：Activity or Fragment （仅用于MVVM|MVP|MVC框架对比&参考）
 * <p>
 * MVC:View可以直接访问MODEL
 * MVP：相对于MVC的区别：View不可以直接访问MODEL，需要通过Presenter跳转。
 * MVVM：相对于MVP的区别：不用应用实现Presenter->View的逻辑，LiveData机制自动实现。
 */
public class MvcDemoActivity extends AppCompatActivity implements IView {
    private IController iController;
    private IModel iModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_demo);

        iController = new MvcDemoController();
        iModel = new MvcDemoModel();
    }

    @Override
    public void updateCityWeather() {
        iController.queryCityWeather(new CityBean("深圳", "440300"));
    }

    /**
     * TODO: 待确认此Case!
     */
    private void test() {
        iModel.getCityWeather(new CityBean("深圳", "440300"));
    }

}

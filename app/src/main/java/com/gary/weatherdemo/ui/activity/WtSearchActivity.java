package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.commonui.IActionBarOnClickListener;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.ui.adapter.CityGridAdapter;
import com.gary.weatherdemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class WtSearchActivity extends AppCompatActivity implements IActionBarOnClickListener {
    private GridView commonCityGridView;
    private EditText citySearchEditText;
    private ImageButton citySearchBtn;

    private CityGridAdapter cityGridAdapter;
    private String[] cityNames =
            new String[]{"深圳", "西安", "合肥", "武汉", "巢湖市", "北京", "上海", "广州", "成都", "南京", "杭州", "苏州"};
    private String[] cityAdcode =
            new String[]{"440300", "610100", "340100", "420100", "340181", "110000", "310000", "440100", "510100", "320100", "330100", "320500"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_search);

        initView();
    }

    private void initView(){
        citySearchEditText = findViewById(R.id.city_edit_text);

        citySearchBtn = findViewById(R.id.city_search_btn);
        citySearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        initGridView();
    }

    private void initGridView(){
        commonCityGridView = findViewById(R.id.city_grid_view);

        List<CityBean> cityBeanList = new ArrayList<>();
        for (int i = 0; i < cityNames.length; i++) {
            CityBean CityBean = new CityBean(cityNames[i],cityAdcode[i]);
            cityBeanList.add(CityBean);
        }
        cityGridAdapter = new CityGridAdapter(this, cityBeanList);
        commonCityGridView.setAdapter(cityGridAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*EventBus.getDefault().post(new MessageEvent("Just for test"));*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClickedActBarLeftBtn() {
        LogUtils.d("onClickedActBarLeftBtn()");
    }

    @Override
    public void onClickedActBarRightBtn() {
        LogUtils.d("onClickedActBarRightBtn()");
    }
}

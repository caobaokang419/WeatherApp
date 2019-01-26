package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.commonui.ActionBar;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;
import com.gary.weatherdemo.ui.adapter.CitySearchGridAdapter;
import com.gary.weatherdemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class WtSearchActivity extends BaseActivity {
    private GridView mCityGridView;
    private EditText mCitySearchEditText;
    private ImageButton mCitySearchBtn;
    private ActionBar mActionBar;
    private CitySearchGridAdapter mCitySearchGridAdapter;

    private final String[] mCityNames =
            new String[]{"深圳", "西安", "合肥", "武汉", "巢湖", "北京", "上海", "广州", "成都", "南京", "杭州", "苏州"};
    private final String[] mCityAdcode =
            new String[]{"440300", "610100", "340100", "420100", "340181", "110000", "310000", "440100", "510100", "320100", "330100", "320500"};

    @Override
    protected void onCreateNew(@Nullable Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_search);

        initView();
    }

    private void initView(){
        mCitySearchEditText = findViewById(R.id.city_edit_text);

        mCitySearchBtn = findViewById(R.id.city_search_btn);
        mCitySearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        initActionBar();
        initGridView();
    }

    private void initActionBar() {
        mActionBar = findViewById(R.id.action_bar);
        mActionBar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("onClickedLeftBtn()");
                finish();
            }
        });
    }

    private void initGridView(){
        mCityGridView = findViewById(R.id.city_grid_view);

        List<CityBean> cityBeanList = new ArrayList<>();
        for (int i = 0; i < mCityNames.length; i++) {
            CityBean CityBean = new CityBean(mCityNames[i], mCityAdcode[i]);
            cityBeanList.add(CityBean);
        }
        mCitySearchGridAdapter = new CitySearchGridAdapter(this, cityBeanList);
        mCityGridView.setAdapter(mCitySearchGridAdapter);
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
}

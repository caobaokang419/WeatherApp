package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.ui.adapter.CitySearchGridAdapter;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class WtSearchActivity extends BaseActivity {
    private GridView mCityGridView;
    private EditText mCitySearchEditText;
    private ImageButton mCitySearchBtn;
    private CitySearchGridAdapter mCitySearchGridAdapter;

    @Override
    protected void onCreateNew(@Nullable Bundle savedInstanceState) {
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

        initGridView();
    }

    @Override
    protected void onActionBarLeftClicked() {
        finish();
    }

    @Override
    protected void onActionBarRightClicked() {

    }

    private void initGridView(){
        mCityGridView = findViewById(R.id.city_grid_view);
        mCitySearchGridAdapter = new CitySearchGridAdapter(this, Constants.COMMON_CITY_BEANS);
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

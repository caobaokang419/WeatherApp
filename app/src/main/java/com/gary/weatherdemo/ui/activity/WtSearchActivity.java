package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.cache.memorycache.CityCacheClient;
import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.ui.adapter.CitySearchGridAdapter;
import com.gary.weatherdemo.ui.adapter.CitySearchRecyclerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class WtSearchActivity extends BaseActivity {
    private GridView mCityGridView;
    private EditText mCitySearchEditText;
    private ImageButton mCitySearchBtn;
    private CitySearchGridAdapter mCitySearchGridAdapter;

    private CitySearchRecyclerAdapter mCitySearchRecycleAdapter;

    @Override
    protected void onCreateNew(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_weather_search);
        initView();
    }

    private void initView() {
        mCitySearchEditText = findViewById(R.id.city_edit_text);
        mCitySearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || charSequence.toString().isEmpty()) {
                    mCitySearchRecycleAdapter.onCityListModeChanged(
                            CitySearchRecyclerAdapter.CityListMode.CITY_LIST_NORMAL_MODE);
                } else {
                    mCitySearchRecycleAdapter.onCityListModeChanged(
                            CitySearchRecyclerAdapter.CityListMode.CITY_LIST_SEARCH_MODE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCitySearchBtn = findViewById(R.id.city_search_btn);
        mCitySearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //initGridView();
        initRecyclerView();
    }

    @Override
    protected void onActionBarLeftClicked() {
        finish();
    }

    @Override
    protected void onActionBarRightClicked() {

    }

    private void initGridView() {
        mCityGridView = findViewById(R.id.city_grid_view);
        mCitySearchGridAdapter = new CitySearchGridAdapter(this, Constants.COMMON_CITY_BEANS);
        mCityGridView.setAdapter(mCitySearchGridAdapter);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.city_list_view);
        mCitySearchRecycleAdapter = new CitySearchRecyclerAdapter();
        mCitySearchRecycleAdapter.setAdapterData(CityCacheClient.getInstance().getSearchCityBeans());
        recyclerView.setAdapter(mCitySearchRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

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
import com.gary.weatherdemo.cache.memcache.CacheClient;
import com.gary.weatherdemo.constant.Constants;
import com.gary.weatherdemo.filter.FilterChain;
import com.gary.weatherdemo.filter.bean.FixedItemFilter;
import com.gary.weatherdemo.filter.bean.SearchWordFilter;
import com.gary.weatherdemo.filter.bean.NoFilter;
import com.gary.weatherdemo.ui.activity.base.BaseActivity;
import com.gary.weatherdemo.ui.adapter.CitySearchGridAdapter;
import com.gary.weatherdemo.ui.adapter.CitySearchRecyclerAdapter;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class CitySearchActivity extends BaseActivity {
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
                    FilterChain filterChain = new FilterChain();
                    filterChain.addFilter(new NoFilter());
                    CacheClient.getInstance().loadCityItemBeansByFilters(filterChain);
                    mCitySearchRecycleAdapter.setCurMode(
                            CitySearchRecyclerAdapter.CityListMode.CITY_LIST_NORMAL_MODE);
                } else {
                    String keyword = charSequence.toString();
                    FilterChain filterChain = new FilterChain();
                    filterChain.addFilter(new SearchWordFilter(keyword));
                    filterChain.addFilter(new FixedItemFilter());
                    CacheClient.getInstance().loadCityItemBeansByFilters(filterChain);
                    mCitySearchRecycleAdapter.setCurMode(
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
        mCitySearchRecycleAdapter.setAdapterData(CacheClient.getInstance().getSearchedCityItemBeans());
        recyclerView.setAdapter(mCitySearchRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        CacheClient.getInstance().addListener(mListener);
        /*EventBus.getDefault().post(new MessageEvent("Just for test"));*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        CacheClient.getInstance().removeListener(mListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private CacheClient.ICacheDataListener mListener = new CacheClient.ICacheDataListener(){
        @Override
        public void onCityConfigChanged() {
            mCitySearchRecycleAdapter.setAdapterData(CacheClient.getInstance().getSearchedCityItemBeans());
        }

        @Override
        public void onCityWeatherChanged() {

        }
    };
}

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
import com.gary.weatherdemo.utils.LogUtils;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class WtSearchActivity extends AppCompatActivity implements IActionBarOnClickListener {
    private GridView commonCityGridView;
    private EditText citySearchEditText;
    private ImageButton citySearchBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_search);

        citySearchEditText = findViewById(R.id.city_edit_text);

        citySearchBtn = findViewById(R.id.city_search_btn);
        citySearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        commonCityGridView = findViewById(R.id.common_city_grid);
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

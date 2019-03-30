package com.gary.weatherdemo.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.commonui.ActionBar;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.utils.CLog;

/**
 * Created by GaryCao on 2019/01/13.
 *
 * GoF23 设计模式 9：模板方法模式
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateNew(savedInstanceState);
        initActionBar();
        setSystemUIStyle();
    }

    private void setSystemUIStyle() {
        //TODO
    }

    private void initActionBar() {
        mActionBar = findViewById(R.id.action_bar);

        if (mActionBar != null) {
            mActionBar.setLeftOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CLog.d("onClickedLeftBtn()");
                    onActionBarLeftClicked();
                }
            });

            mActionBar.setRightOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CLog.d("onClickedRightBtn()");
                    onActionBarRightClicked();
                }
            });
        }
    }

    protected abstract void onCreateNew(Bundle savedInstanceState);

    protected abstract void onActionBarLeftClicked();

    protected abstract void onActionBarRightClicked();
}

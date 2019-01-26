package com.gary.weatherdemo.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.commonui.ActionBar;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.service.DownloadService;
import com.gary.weatherdemo.network.ApiContants;
import com.gary.weatherdemo.utils.LogUtils;
import com.gary.weatherdemo.utils.WtUtil;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by GaryCao on 2019/01/13.
 * 高德城市配置表文件下載管理
 */
public class WtDownloadActivity extends BaseActivity {
    private ActionBar mActionBar;

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_download);
        initView();
        bindDLService();
    }

    private void initView() {
        findViewById(R.id.download_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadBinder.startDownload(ApiContants.AMAP_CITY_CONFIG_FILE_URL);
            }
        });

        findViewById(R.id.pause_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadBinder.pauseDownload();
            }
        });

        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadBinder.cancelDownload();
            }
        });

        initActionBar();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindDLService();
    }

    private void bindDLService() {
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private void unbindDLService() {
        unbindService(connection);
    }

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            downloadBinder = null;
        }
    };
}

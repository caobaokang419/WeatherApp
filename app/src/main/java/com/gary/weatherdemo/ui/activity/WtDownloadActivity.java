package com.gary.weatherdemo.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.commonui.ActionBar;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.download.DownloadManager;
import com.gary.weatherdemo.download.IDownloadCallback;
import com.gary.weatherdemo.network.ApiContants;
import com.gary.weatherdemo.permission.WtPermissionActivity;
import com.gary.weatherdemo.utils.CLog;

/**
 * Created by GaryCao on 2019/01/13.
 * 高德城市配置表文件下載管理
 */
public class WtDownloadActivity extends BaseActivity implements WtPermissionActivity.IPermitRequestCallback {
    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_download);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        WtPermissionActivity.addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        WtPermissionActivity.removeListener(this);
    }


    @Override
    public void onPermitRequestedSuccess() {
        CLog.d("WtDownloadActivity", "onClickedLeftBtn()");
    }

    @Override
    public void onPermitRequestedFail() {
        CLog.d("WtDownloadActivity", "onClickedLeftBtn()");
    }

    private void initView() {
        findViewById(R.id.download_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance(WtDownloadActivity.this).
                        startDownload(ApiContants.AMAP_CITY_CONFIG_FILE_URL, mDownloadCallback);
            }
        });

        findViewById(R.id.pause_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance(WtDownloadActivity.this).pauseDownload();
            }
        });

        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance(WtDownloadActivity.this).cancelDownload();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActionBarLeftClicked() {
        finish();
    }

    @Override
    protected void onActionBarRightClicked() {

    }

    private IDownloadCallback mDownloadCallback = new IDownloadCallback() {
        @Override
        public void onStart() {

        }

        @Override
        public void onUpdate() {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFail() {

        }

        @Override
        public void onCancel() {

        }
    };
}

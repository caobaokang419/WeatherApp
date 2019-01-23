package com.gary.weatherdemo.download;

import com.gary.weatherdemo.network.ApiContants;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadClient {
    private static DownloadClient mInstance = new DownloadClient();
    private static IDownload mIDownload;

    private DownloadClient() {
        init();
    }

    private void init() {
        mIDownload = DownloadFactory.createDownloadImpl();
    }

    /**
     * 启动文件下载
     */
    public void startDownload(String url, IDownloadListener iDownloadListener) {
        if (null != mIDownload) {
            mIDownload.startDownload(url, iDownloadListener);
        }
    }

    /**
     * 取消文件下载
     */
    public void cancelDownload() {
        if (null != mIDownload) {
            mIDownload.cancelDownload();
        }
    }

    /**
     * 暂停文件下载
     */
    public void pauseDownload() {
        if (null != mIDownload) {
            mIDownload.pauseDownload();
        }
    }

    public static DownloadClient getInstance() {
        return mInstance;
    }

    //===================================================================================================
    //for test
    /*高德天气城市配置表文件下载*/
    private void downloadConfigFile() {
        DownloadClient.getInstance().startDownload(ApiContants.AMAP_CITY_CONFIG_FILE_URL, null);
    }
}

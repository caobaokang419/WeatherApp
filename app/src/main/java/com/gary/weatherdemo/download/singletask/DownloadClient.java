package com.gary.weatherdemo.download.singletask;

import com.gary.weatherdemo.network.ApiContants;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadClient {
    private static DownloadClient instance = new DownloadClient();
    private static IDownload iDownload;

    private DownloadClient() {
        init();
    }

    private void init() {
        iDownload = DownloadFactory.createDownloadImpl();
    }

    /**
     * 启动文件下载
     */
    public void startDownload(String url, IDownloadListener iDownloadListener) {
        if (null != iDownload) {
            iDownload.startDownload(url, iDownloadListener);
        }
    }

    /**
     * 取消文件下载
     */
    public void cancelDownload() {
        if (null != iDownload) {
            iDownload.cancelDownload();
        }
    }

    /**
     * 暂停文件下载
     */
    public void pauseDownload() {
        if (null != iDownload) {
            iDownload.pauseDownload();
        }
    }

    public static DownloadClient getInstance() {
        return instance;
    }

    //===================================================================================================
    //for test
    /*高德天气城市配置表文件下载*/
    private void downloadConfigFile() {
        DownloadClient.getInstance().startDownload(ApiContants.AMAP_CITY_CONFIG_FILE_URL, null);
    }
}

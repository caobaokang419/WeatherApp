package com.gary.weatherdemo.download.singletask;

import java.net.URL;

/**
 * Created by GaryCao on 2019/01/12.
 */
public interface IDownload {
    /**启动下载*/
    void startDownload(String url, IDownloadListener iDownloadListener);

    /**暂停下载*/
    void pauseDownload();

    /**取消下载*/
    void cancelDownload();
}

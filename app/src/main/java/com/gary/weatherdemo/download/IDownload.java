package com.gary.weatherdemo.download;

/**
 * Created by GaryCao on 2019/01/12.
 */
public interface IDownload {
    /**启动下载*/
    void startDownload(String url, IDownloadCallback iDownloadCallback);

    /**暂停下载*/
    void pauseDownload();

    /**取消下载*/
    void cancelDownload();
}

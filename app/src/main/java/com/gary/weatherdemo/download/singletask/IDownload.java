package com.gary.weatherdemo.download.singletask;

import java.net.URL;

/**
 * Created by GaryCao on 2019/01/12.
 */
public interface IDownload {
    /**启动下载*/
    void startDownload(URL url, IDownloadListener iDownloadListener);

    /**结束下载*/
    void stopDownload();
}

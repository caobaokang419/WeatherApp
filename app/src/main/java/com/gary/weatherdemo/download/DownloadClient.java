package com.gary.weatherdemo.download;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadClient {
    private static DownloadClient instance = new DownloadClient();
    private static IDownload iDownload;

    private DownloadClient(){
        init();
    }

    private void init(){
        iDownload = DownloadFactory.getDownloadInstant();
    }

    /*启动文件下载*/
    public void startDownload(){
        if(null !=iDownload){
            iDownload.startDownload();
        }
    }

    /*终止文件下载*/
    public void stopDownload(){
        if(null !=iDownload){
            iDownload.stopDownload();
        }
    }

    public static DownloadClient getInstance() {
        return instance ;
    }
}

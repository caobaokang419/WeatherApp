package com.gary.weatherdemo.download;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadUtil {
    private static DownloadUtil instance = new DownloadUtil();
    private static IDownload iDownload;

    private DownloadUtil(){
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

    public static DownloadUtil getInstance() {
        return instance ;
    }
}

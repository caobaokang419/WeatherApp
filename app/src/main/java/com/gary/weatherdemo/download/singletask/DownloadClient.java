package com.gary.weatherdemo.download.singletask;

import com.gary.weatherdemo.network.ApiContants;

import java.net.MalformedURLException;
import java.net.URL;

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
    public void startDownload(URL url, IDownloadListener iDownloadListener){
        if(null !=iDownload){
            iDownload.startDownload(url,iDownloadListener);
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

    //===================================================================================================
    //for test
    /*高德天气城市配置表文件下载：当前天气查询接口:深圳*/
    private void downloadConfigFile() {
        try {
            URL url = new URL(ApiContants.AMAP_CITY_CONFIG_FILE_URL);
            DownloadClient.getInstance().startDownload(url,null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

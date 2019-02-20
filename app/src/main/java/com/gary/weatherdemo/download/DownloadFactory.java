package com.gary.weatherdemo.download;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class DownloadFactory {
    public static int TYPE_DOWNLOAD_XUTILS = 0x01;
    public static int TYPE_DOWNLOAD_ORIGIN = 0x02;

    /*GoF23 静态工厂方法*/
    public static IDownload createDownloadImpl(){
        return createDownloadImpl(TYPE_DOWNLOAD_XUTILS);
    }

    public static IDownload createDownloadImpl(int type){
        if(type == TYPE_DOWNLOAD_XUTILS){
            return new XUtilsDownloadImpl();
        }else{
            return new OriDownloadImpl();
        }
    }
}

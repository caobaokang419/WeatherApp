package com.gary.weatherdemo.download;

/**
 * Created by GaryCao on 2019/01/12.
 * TODO: 用于回调更新状态
 */
public interface IDownloadCallback {
    /**下载开始*/
    void onStart();

    /**下载进度刷新*/
    void onUpdate();

    /**下载成功*/
    void onSuccess();

    /**下载失败*/
    void onFail();

    /**下载被取消*/
    void onCancel();
}

package com.gary.weatherdemo.download;

import android.os.Environment;
import android.util.Log;

import com.gary.weatherdemo.network.ApiContants;
import com.gary.weatherdemo.utils.XutilsFileUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by GaryCao on 2019/01/12.
 */
public class XUtilsDownloadImpl implements IDownload {
    private IDownloadListener mDownloadListener;
    private Callback.Cancelable mCancelable;

    @Override
    public void startDownload(String url, IDownloadListener iDownloadListener) {
        mDownloadListener = iDownloadListener;
        String fileName = XutilsFileUtil.getFileNameByUrl(url);
        String filePath = Environment.getExternalStorageDirectory() + ApiContants.AMAP_CITY_CONFIG_DIRECTIONARY + fileName;
        RequestParams params = new RequestParams(url);
        params.setSaveFilePath(filePath);
        params.setAutoRename(false);
        params.setAutoResume(true);
        params.setCancelFast(true);
        /*需要先动态申请存储权限*/
        mCancelable = x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                mDownloadListener.onSuccess();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mDownloadListener.onFail();
            }

            @Override
            public void onCancelled(CancelledException cex) {

                mDownloadListener.onCancel();
            }

            @Override
            public void onFinished() {

            }

            //网络请求之前回调
            @Override
            public void onWaiting() {
            }

            //网络请求开始的时候回调
            @Override
            public void onStarted() {

                mDownloadListener.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                mDownloadListener.onUpdate();
                Log.i("JAVA", "current：" + current + "，total：" + total);
            }
        });
    }

    @Override
    public void pauseDownload() {
        //TODO 如何實現？？
    }

    @Override
    public void cancelDownload() {
        mCancelable.cancel();
    }
}

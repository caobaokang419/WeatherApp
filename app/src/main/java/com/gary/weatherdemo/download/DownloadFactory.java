package com.gary.weatherdemo.download;

import android.os.Environment;

import com.gary.weatherdemo.http.AmapContants;
import com.gary.weatherdemo.utils.CLog;
import com.gary.weatherdemo.utils.FileUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by GaryCao on 2019/01/12.
 */

public class DownloadFactory {
    enum DownloadType {
        XUTILS, ORIGIN
    }

    /**
     * GoF23 设计模式 1：工厂方法type1（简单工厂）
     */
    public static IDownload createDownloadImpl() {
        return createDownloadImpl(DownloadType.XUTILS);
    }

    public static IDownload createDownloadImpl(DownloadType type) {
        if (type == DownloadType.XUTILS) {
            return new XUtilsDownloadImpl();
        } else {
            return new OriDownloadImpl();
        }
    }

    static class OriDownloadImpl implements IDownload {
        @Override
        public void startDownload(String url, IDownloadCallback iDownloadCallback) {
        }

        @Override
        public void pauseDownload() {

        }

        @Override
        public void cancelDownload() {

        }
    }

    static class XUtilsDownloadImpl implements IDownload {
        private IDownloadCallback downloadCallback;
        private Callback.Cancelable mCancelable;

        /**
         * 注意Callback（鉤子）和Listener（監聽器）的場景區別
         */
        @Override
        public void startDownload(String url, IDownloadCallback downloadCallback) {
            this.downloadCallback = downloadCallback;
            String fileName = FileUtil.getFileNameByUrl(url);
            String filePath = Environment.getExternalStorageDirectory() + AmapContants.AMAP_CITY_CONFIG_DIRECTIONARY + fileName;
            RequestParams params = new RequestParams(url);
            params.setSaveFilePath(filePath);
            params.setAutoRename(false);
            params.setAutoResume(true);
            params.setCancelFast(true);
            params.setCacheMaxAge(1000 * 60 * 3);
            mCancelable = x.http().post(params, new Callback.ProgressCallback<File>() {
                @Override
                public void onSuccess(File result) {
                    XUtilsDownloadImpl.this.downloadCallback.onSuccess();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    XUtilsDownloadImpl.this.downloadCallback.onFail();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    XUtilsDownloadImpl.this.downloadCallback.onCancel();
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
                    XUtilsDownloadImpl.this.downloadCallback.onStart();
                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    XUtilsDownloadImpl.this.downloadCallback.onUpdate();
                    CLog.d("current：" + current + "，total：" + total);
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
}

package com.gary.weatherdemo.mvp;

import android.database.Cursor;
import android.text.TextUtils;

import com.gary.weatherdemo.model.CityBean;

import org.xutils.common.util.LogUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by GaryCao on 2019/03/13.
 * <p>
 * MVP应用框架元素3：Presenter （用于MVVM框架对比&参考）
 */
public class CityWeatherPresenter implements IPresenter{
    private IView iView;

    public CityWeatherPresenter(){

    }

    @Override
    public void onUiReady(IView iview) {
        iView = iview;
    }

    @Override
    public void onUiUnready() {
        iView = null;
    }

    @Override
    public void queryCityWeather(CityBean cityBean) {

    }
}

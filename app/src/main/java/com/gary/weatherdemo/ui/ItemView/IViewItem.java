package com.gary.weatherdemo.ui.ItemView;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.bean.base.BaseItemBean;


/**
 * Created by GaryCao on 2018/12/12.
 */
public interface IViewItem<T extends BaseItemBean> {
    View createView(ViewGroup parent);

    void bindView(@NonNull T data);
}

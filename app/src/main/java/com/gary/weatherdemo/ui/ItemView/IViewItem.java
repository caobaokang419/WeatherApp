package com.gary.weatherdemo.ui.ItemView;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.bean.IViewItemBean;


/**
 * Created by GaryCao on 2018/12/12.
 */
public interface IViewItem<T extends IViewItemBean> {
    View createView(ViewGroup parent);

    void bindView(@NonNull T data);
}

package com.gary.weatherdemo.ui.ItemView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.model.base.BaseItemDataBean;


/**
 * Created by GaryCao on 2018/12/12.
 */
public interface IViewItem<T extends BaseItemDataBean> {
    View createView(ViewGroup parent);

    void bindView(@NonNull T data);
}

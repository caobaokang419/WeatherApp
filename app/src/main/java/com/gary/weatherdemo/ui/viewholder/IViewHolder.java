package com.gary.weatherdemo.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


/**
 * Created by GaryCao on 2018/12/12.
 */
public interface IViewHolder<T> {
    void createViewHolder(ViewGroup parent);
    void bindDataToViewHolder(@NonNull T data);
}

package com.gary.weatherdemo.ui.viewholder.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GaryCao on 2018/10/25.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(ViewGroup parent) {
        super(createViewHolder(parent));
    }

    public abstract View createViewHolder(ViewGroup parent);

    public abstract void bind(@NonNull T data);
}

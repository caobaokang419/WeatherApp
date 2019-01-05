package com.gary.weatherdemo.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gary.weatherdemo.ui.ItemView.IViewItem;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class BaseItemViewHolder extends RecyclerView.ViewHolder {
    private IViewItem iViewItem;
    public BaseItemViewHolder(IViewItem viewItem , ViewGroup parent) {
        super(viewItem.createView(parent));
        iViewItem = viewItem;
    }

    public IViewItem getIViewItem(){
        return iViewItem;
    }
}
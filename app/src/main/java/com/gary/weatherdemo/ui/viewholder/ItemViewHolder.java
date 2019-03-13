package com.gary.weatherdemo.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gary.weatherdemo.ui.ItemView.IViewItem;

/**
 * Created by GaryCao on 2018/12/12.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    private IViewItem mIViewItem;
    public ItemViewHolder(IViewItem viewItem , ViewGroup parent) {
        /*GoF23 设计模式 8：策略模式：viewItem （策略模式的决定权在用户，由外部用户决定具体用哪个算法）*/
        super(viewItem.createView(parent));
        mIViewItem = viewItem;
    }

    public IViewItem getIViewItem(){
        return mIViewItem;
    }
}
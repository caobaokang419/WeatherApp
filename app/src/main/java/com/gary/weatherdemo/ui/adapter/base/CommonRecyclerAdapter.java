package com.gary.weatherdemo.ui.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.ui.viewholder.ItemViewHolder;
import com.gary.weatherdemo.ui.viewholder.ItemViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

public class CommonRecyclerAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<IViewItemBean> mItemDatas = new ArrayList<>();

    public CommonRecyclerAdapter() {
    }

    public void setAdapterData(List<IViewItemBean> datas) {
        mItemDatas.clear();
        mItemDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolderFactory.createViewHolderByType(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.getIViewItem().bindView(mItemDatas.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return (mItemDatas.get(position) == null) ? 0 : mItemDatas.get(position).getViewItemType();
    }

    @Override
    public int getItemCount() {
        return mItemDatas.size();
    }
}

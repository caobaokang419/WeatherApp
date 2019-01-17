package com.gary.weatherdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gary.weatherdemo.model.base.BaseItemBean;
import com.gary.weatherdemo.ui.viewholder.BaseItemViewHolder;
import com.gary.weatherdemo.ui.viewholder.ItemViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

public class CityWeatherRecyclerAdapter extends RecyclerView.Adapter<BaseItemViewHolder> {
    private List<BaseItemBean> itemDataList = new ArrayList<>();

    public CityWeatherRecyclerAdapter() {
    }

    public void setAdapterData(List<BaseItemBean> datas) {
        itemDataList.clear();
        itemDataList = datas;
        notifyDataSetChanged();
    }

    @Override
    public BaseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolderFactory.getViewHolderByType(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder holder, int position) {
        holder.getIViewItem().bindView(itemDataList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return (itemDataList.size() == 0) ? 0 : itemDataList.get(position).getViewItemType();
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }
}

package com.gary.weatherdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gary.weatherdemo.model.base.BaseItemDataBean;
import com.gary.weatherdemo.ui.viewholder.BaseViewItemHolder;
import com.gary.weatherdemo.ui.viewholder.ViewItemHolderFactory;

import java.util.ArrayList;

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<BaseViewItemHolder> {
    private ArrayList<BaseItemDataBean> ItemDataList = new ArrayList<>();

    public ForecastRecyclerAdapter() {
    }

    public void setAdapterData(ArrayList<BaseItemDataBean> datas) {
        ItemDataList.clear();
        ItemDataList = datas;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewItemHolderFactory.getViewHolderByType(parent,viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewItemHolder holder, int position) {
        holder.getIViewItem().bindView(ItemDataList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return ItemDataList.get(position).getViewItemType();
    }

    @Override
    public int getItemCount() {
        return ItemDataList.size();
    }

    /*public class ForecastDayViewHolder extends RecyclerView.ViewHolder {
        private final WeatherForecastDayBinding binding;

        public ForecastDayViewHolder(View itemView) {
            super(itemView);
            binding = WeatherForecastDayBinding.bind(itemView);
        }

        public void bind(@NonNull BaseItemDataBean data) {
            binding.setViewModel(new ForecastDayViewModel(data));
        }
    }*/
}

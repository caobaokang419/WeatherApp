package com.gary.weatherdemo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.databinding.WeatherForecastDayBinding;
import com.gary.weatherdemo.model.AllForecastResult;
import com.gary.weatherdemo.model.DayForecastData;
import com.gary.weatherdemo.viewmodel.ForecastDayViewModel;

import java.util.ArrayList;

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastDayViewHolder> {
    private ArrayList<DayForecastData> forecastDataList = new ArrayList<>();

    public ForecastRecyclerAdapter() {
    }

    public void setAdapterData(AllForecastResult allForecastResult) {
        if (null == allForecastResult) {
            return;
        }
        forecastDataList.clear();
        forecastDataList = (ArrayList<DayForecastData>) allForecastResult.dayForecastDataList;
        notifyDataSetChanged();
    }

    @Override
    public ForecastDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast_day, parent, false);
        return new ForecastDayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastDayViewHolder holder, int position) {
        holder.bind(forecastDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastDataList.size();
    }


    public class ForecastDayViewHolder extends RecyclerView.ViewHolder {
        private final WeatherForecastDayBinding binding;

        public ForecastDayViewHolder(View itemView) {
            super(itemView);
            binding = WeatherForecastDayBinding.bind(itemView);
        }

        public void bind(@NonNull DayForecastData data) {
            binding.setViewModel(new ForecastDayViewModel(data));
        }
    }
}

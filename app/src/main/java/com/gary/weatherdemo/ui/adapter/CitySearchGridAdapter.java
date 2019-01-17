package com.gary.weatherdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.model.CityBean;

import java.util.List;

public class CitySearchGridAdapter extends BaseAdapter {
    private List<CityBean> cityBeanList;
    private LayoutInflater layoutInflater;

    public CitySearchGridAdapter(Context context, List<CityBean> cityBeanList) {
        this.cityBeanList = cityBeanList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cityBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_city_grid_view, null);
            holder = new ViewHolder();
            holder.city_name = (TextView) convertView.findViewById(R.id.city_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CityBean CityBean = cityBeanList.get(position);
        if (CityBean != null) {
            holder.city_name.setText(CityBean.adrName);
        }
        return convertView;
    }

    class ViewHolder {
        TextView city_name;
    }

}

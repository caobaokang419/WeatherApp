package com.gary.weatherdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.bean.CityBean;

import java.util.List;

public class CityGridAdapter  extends BaseAdapter {
    private List<CityBean> CityBeanList;
    private LayoutInflater layoutInflater;

    public CityGridAdapter(Context context, List<CityBean> CityBeanList) {
        this.CityBeanList = CityBeanList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return CityBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return CityBeanList.get(position);
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
        CityBean CityBean = CityBeanList.get(position);
        if (CityBean != null) {
            holder.city_name.setText(CityBean.adrName);
        }
        return convertView;
    }

    class ViewHolder {
        TextView city_name;
    }

}

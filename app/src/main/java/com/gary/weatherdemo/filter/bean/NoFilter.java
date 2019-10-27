package com.gary.weatherdemo.filter.bean;

import com.gary.weatherdemo.bean.IViewItemBean;

import java.util.List;

public class NoFilter implements IFilter {

    @Override
    public FilterType getFilterType(){
        return FilterType.FILTER_TYPE_NO_FILTER;
    }

    @Override
    public List<IViewItemBean> doFilter(List<IViewItemBean> datas) {
        return datas;
    }
}

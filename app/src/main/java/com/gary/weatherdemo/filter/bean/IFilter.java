package com.gary.weatherdemo.filter.bean;

import com.gary.weatherdemo.bean.IViewItemBean;

import java.util.List;

public interface IFilter {
    enum FilterType {
        FILTER_TYPE_NO_FILTER,
        FILTER_TYPE_SEARCH_KEY_WORD,
        FILTER_TYPE_SKIP_FIXED_ITEM
    }

    FilterType getFilterType();
    List<IViewItemBean> doFilter(List<IViewItemBean> datas);
}

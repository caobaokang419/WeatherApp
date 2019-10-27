package com.gary.weatherdemo.filter;

import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.filter.bean.IFilter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by GaryCao on 2019/04/04.
 *
 * 过滤器：GoF23 设计模式 11：责任链模式
 */
public class FilterChain {
    public List<IFilter> mFilters = new CopyOnWriteArrayList<>();

    public void addFilter(IFilter filter) {
        mFilters.add(filter);
    }

    public void removeFilter(IFilter filter) {
        mFilters.remove(filter);
    }

    public List<IViewItemBean> doFilter(List<IViewItemBean> datas) {
        List<IViewItemBean> tempDatas = datas;
        for (IFilter filter : mFilters) {
            tempDatas = filter.doFilter(tempDatas);
        }
        return tempDatas;
    }
}

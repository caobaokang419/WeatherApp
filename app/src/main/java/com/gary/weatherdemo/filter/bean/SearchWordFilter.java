package com.gary.weatherdemo.filter.bean;

import com.gary.weatherdemo.bean.CityItemBean;
import com.gary.weatherdemo.bean.IViewItemBean;

import java.util.ArrayList;
import java.util.List;

public class SearchWordFilter implements IFilter {
    private String mKeyWord;

    public SearchWordFilter(String keyWord){
        mKeyWord = keyWord;
    }

    @Override
    public FilterType getFilterType(){
        return FilterType.FILTER_TYPE_SEARCH_KEY_WORD;
    }

    @Override
    public List<IViewItemBean> doFilter(List<IViewItemBean> datas) {
        List<IViewItemBean> tempDatas = new ArrayList<>();
        for (IViewItemBean item : datas) {
            if (item instanceof CityItemBean) {
                CityItemBean itemBean = (CityItemBean) item;
                if (itemBean.isSearched(mKeyWord)) {
                    tempDatas.add(item);
                }
            }
        }
        return tempDatas;
    }
}

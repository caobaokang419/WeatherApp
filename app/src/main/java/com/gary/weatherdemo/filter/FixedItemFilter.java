package com.gary.weatherdemo.filter;

import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.bean.CityItemBean;
import com.gary.weatherdemo.bean.IViewItemBean;
import com.gary.weatherdemo.cache.memcache.CacheClient;

import java.util.ArrayList;
import java.util.List;

public class FixedItemFilter implements Filter {

    @Override
    public FilterType getFilterType() {
        return FilterType.FILTER_TYPE_SKIP_FIXED_ITEM;
    }

    @Override
    public List<IViewItemBean> doFilter(List<IViewItemBean> datas) {
        List<IViewItemBean> tempDatas = new ArrayList<>();
        for (IViewItemBean item : datas) {
            if (item instanceof CityItemBean) {
                CityItemBean itemBean = (CityItemBean) item;
                if (!isFixedItem(itemBean)) {
                    tempDatas.add(item);
                }
            }
        }
        return tempDatas;
    }

    private boolean isFixedItem(CityItemBean itemBean) {
        List<CityBean> fixedItems = CacheClient.getInstance().getFixedCityBeans();
        for (CityBean cityBean : fixedItems) {
            if (cityBean.cityCode.equals(itemBean.getCityBean().cityCode)) {
                return true;
            }
        }
        return false;
    }
}

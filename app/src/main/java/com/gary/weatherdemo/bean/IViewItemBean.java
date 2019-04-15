package com.gary.weatherdemo.bean;

/**
 * Created by GaryCao on 2018/12/12.
 */
public interface IViewItemBean {
    enum ItemViewType {
        RV_CURRENT_WEATHER_ITEM_TYPE(0),
        RV_FORECAST_DAY_WEATHER_ITEM_TYPE(1),
        RV_SEARCH_CITY_ITEM_TYPE(2);

        private int itemValue;

        ItemViewType(int value) {
            itemValue = value;
        }

        public int value() {
            return itemValue;
        }
    }

    ItemViewType getViewItemType();
}

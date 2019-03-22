package com.gary.weatherdemo.constant;

import com.gary.weatherdemo.model.CityBean;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    // work manager types
    public static final String PERIODIC_UPATE_WORK_NAME = "periodic_update_work";
    public static final String LOAD_ADCODES_CONFIG_WORK_NAME = "load_adcodes_config_work";
    public static final String QUERY_CURRENT_WEATHER_WORK_NAME = "query_current_weather_work";
    public static final String QUERY_FORECAST_WORK_NAME = "query_forecast_work";

    // amap adcode config file name
    public static final String AMAP_ADCODE_CONFIG_FILE_NAME = "city.conf";

    // um push
    public static final String UM_PUSH_APP_KEY = "5c4e7057b465f50f6300171f";
    public static final String UM_PUSH_MESSAGE_SECRET_KEY = "57bdbf9232a34e1d0bc882ae4759b64d";
    public static final String UM_PUSH_CHANNEL_NAME = "umeng_amap";


    public static final List<CityBean> COMMON_CITY_BEANS = new ArrayList<>();
    static {
        COMMON_CITY_BEANS.add(new CityBean("深圳", "440300"));
        COMMON_CITY_BEANS.add(new CityBean("西安", "610100"));
        COMMON_CITY_BEANS.add(new CityBean("合肥", "340100"));
        COMMON_CITY_BEANS.add(new CityBean("巢湖", "340181"));
        COMMON_CITY_BEANS.add(new CityBean("武汉", "420100"));
        COMMON_CITY_BEANS.add(new CityBean("北京", "340181"));
    }
}

package com.gary.weatherdemo.http;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class ApiContants {
    public static final String AMAP_BASE_URL = "https://restapi.amap.com/";
    public static final String AMAP_WEATHER_GET = "v3/weather/weatherInfo?";

    public static final String AMAP_USER_KEY_VALUE = "3b6729d0c40f23fde7c55ae90ee0921d";
    public static final String AMAP_USER_EXTENSION_VALUE_BASE = "base";
    public static final String AMAP_USER_EXTENSION_VALUE_ALL = "all";
    public static final String AMAP_USER_OUTPUT_VALUE = "JSON"; //JSON or xml

    //高德城市配置表下载地址
    public static final String AMAP_CITY_CONFIG_FILE_URL = "http://a.amap.com/lbs/static/file/AMap_adcode_citycode.xlsx.zip";

    //高德城市配置表本地存储目录
    public static final String AMAP_CITY_CONFIG_DIRECTIONARY = "/";//"/amap_weather/";
}

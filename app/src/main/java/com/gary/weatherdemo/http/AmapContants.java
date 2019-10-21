package com.gary.weatherdemo.http;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class AmapContants {
    public static final String AMAP_BASE_URL = "https://restapi.amap.com/";
    public static final String AMAP_WEATHER_GET = "v3/weather/weatherInfo?";

    public static final String AMAP_USER_KEY_VALUE = "3b6729d0c40f23fde7c55ae90ee0921d";

    public interface AmapUserExt {
        String BASE = "base";
        String ALL = "all";

        @Retention(RetentionPolicy.SOURCE)
        @StringDef({BASE, ALL})
        @interface type {
        }
    }

    public interface AmapUserOutput {
        String JSON = "base";
        String XML = "all";

        @Retention(RetentionPolicy.SOURCE)
        @StringDef({JSON, XML})
        @interface type {
        }
    }

    //高德城市配置表下载地址
    public static final String AMAP_CITY_CONFIG_FILE_URL = "http://a.amap.com/lbs/static/file/AMap_adcode_citycode.xlsx.zip";

    //高德城市配置表本地存储目录
    public static final String AMAP_CITY_CONFIG_DIRECTIONARY = "/";//"/amap_weather/";
}

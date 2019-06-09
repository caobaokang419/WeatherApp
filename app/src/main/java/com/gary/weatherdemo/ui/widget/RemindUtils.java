package com.gary.weatherdemo.ui.widget;

import com.gary.weatherdemo.bean.WeatherTypeBean;
import com.gary.weatherdemo.bean.WeatherTypeBean.WeatherType;

import java.util.HashMap;
import java.util.Map;

public class RemindUtils {
    enum RemindType {
        Sunny(0),
        Cloudy(1),
        Rain(3),
        Snow(4);

        private int type;

        RemindType(int type) {
            this.type = type;
        }

        public int getRemindType() {
            return type;
        }
    }

    private static final Map<WeatherType, RemindType> remindTypeMap;

    static {
        remindTypeMap = new HashMap<>();
        remindTypeMap.put(WeatherType.Sunny, RemindType.Sunny);
        remindTypeMap.put(WeatherType.Cloudy, RemindType.Cloudy);
        remindTypeMap.put(WeatherType.RainSmall, RemindType.Rain);
        remindTypeMap.put(WeatherType.RainMedium, RemindType.Rain);
        remindTypeMap.put(WeatherType.RainBig, RemindType.Rain);
        remindTypeMap.put(WeatherType.SnowSmall, RemindType.Snow);
        remindTypeMap.put(WeatherType.SnowMedium, RemindType.Snow);
        remindTypeMap.put(WeatherType.SnowSmall, RemindType.Snow);
    }

    private static final RemindUtils ourInstance = new RemindUtils();

    public static RemindUtils getInstance() {
        return ourInstance;
    }


    private RemindUtils() {

    }

    public String getRemindByWeatherType(WeatherTypeBean.WeatherType type) {
        RemindType remindType = remindTypeMap.get(type);
        return getRemindByRemindType(remindType);
    }

    private String getRemindByRemindType(RemindType remindType){


        return null;
    }
}

package com.gary.weatherdemo.ui.widget;

import com.gary.weatherdemo.bean.WeatherTypeBean;
import com.gary.weatherdemo.bean.WeatherTypeBean.WeatherType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RemindUtils {
    public enum RemindType {
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

    private Map<WeatherType, RemindType> remindTypeMap;
    private Map<RemindType, ArrayList<String>> remindDataMap;

    private static final ArrayList<String> remindSunny;
    static {
        remindSunny = new ArrayList<>();
        remindSunny.add("你若安好，便是晴天");
        remindSunny.add("晴带雨伞，饱带干粮");
    }

    private static final ArrayList<String> remindRain;
    static {
        remindRain = new ArrayList<>();
        remindRain.add("下雨了，记得带伞");
        remindRain.add("下雨了，不用上班了么");
    }

    private static final ArrayList<String> remindCloud;

    static {
        remindCloud = new ArrayList<>();
        remindCloud.add("阴天，保持好心情");
        remindCloud.add("多云了，晴天还会远么");
    }

    private static final ArrayList<String> remindSnow;

    static {
        remindSnow = new ArrayList<>();
        remindSnow.add("下雪了，记得收衣服");
    }

    private static final RemindUtils ourInstance = new RemindUtils();

    public static RemindUtils getInstance() {
        return ourInstance;
    }


    private RemindUtils() {
        initRemindData();
    }

    private void initRemindData(){
        remindTypeMap = new HashMap<>();
        remindTypeMap.put(WeatherType.Sunny, RemindType.Sunny);
        remindTypeMap.put(WeatherType.Cloudy, RemindType.Cloudy);
        remindTypeMap.put(WeatherType.RainSmall, RemindType.Rain);
        remindTypeMap.put(WeatherType.RainMedium, RemindType.Rain);
        remindTypeMap.put(WeatherType.RainBig, RemindType.Rain);
        remindTypeMap.put(WeatherType.SnowSmall, RemindType.Snow);
        remindTypeMap.put(WeatherType.SnowMedium, RemindType.Snow);
        remindTypeMap.put(WeatherType.SnowSmall, RemindType.Snow);

        remindDataMap = new HashMap<>();
        remindDataMap.put(RemindType.Sunny,remindSunny);
        remindDataMap.put(RemindType.Cloudy,remindCloud);
        remindDataMap.put(RemindType.Rain,remindRain);
        remindDataMap.put(RemindType.Snow,remindSnow);
    }

    public String getRemindByWeatherType(WeatherTypeBean.WeatherType type) {
        RemindType remindType = remindTypeMap.get(type);
        ArrayList<String> remindDatas = remindDataMap.get(remindType);
        return getRandomRemindString(remindDatas);
    }

    private String getRandomRemindString(ArrayList<String> strings) {
        if (strings == null || strings.size() <= 0) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(strings.size());
        return strings.get(index);
    }
}

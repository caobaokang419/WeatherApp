package com.gary.weatherdemo.bean;

public class WeatherTypeBean {
    public enum WeatherType {
        Sunny(0),
        Cloudy(1),
        RainSmall(3),
        RainMedium(4),
        RainBig(5),
        SnowSmall(6),
        SnowMedium(7),
        SnowBig(8);

        private int type;

        WeatherType(int type) {
            this.type = type;
        }

        public int getWeatherType() {
            return type;
        }
    }
    private WeatherType weatherType;

    private String weatherData;

    public WeatherTypeBean(WeatherType type, String weatherData) {
        this.weatherType = type;
        this.weatherData = weatherData;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public String getWeatherData() {
        return weatherData;
    }
}

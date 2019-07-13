package com.gary.weatherdemo.bean;

public class WeatherTypeBean {
    public enum WeatherType {
        SUNNY(0),
        CLOUDY(1),
        RAIN_SMALL(3),
        RAIN_MEDIUM(4),
        RAIN_BIG(5),
        SNOW_SMALL(6),
        SNOW_MEIDUM(7),
        SNOW_BIG(8);

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

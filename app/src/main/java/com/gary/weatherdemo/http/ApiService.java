package com.gary.weatherdemo.http;

import com.gary.weatherdemo.http.response.AllForecastResponseData;
import com.gary.weatherdemo.http.response.LiveWeatherResponseData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    //https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=3b6729d0c40f23fde7c55ae90ee0921d&extensions=base&output=JSON
    @FormUrlEncoded
    @POST(ApiContants.AMAP_WEATHER_GET)
    Observable<LiveWeatherResponseData> livesweatherPost(
            @Field("city") String city,
            @Field("key") String key,
            @Field("extensions") String extensions,
            @Field("output") String output
    );

    @FormUrlEncoded
    @POST(ApiContants.AMAP_WEATHER_GET)
    Observable<AllForecastResponseData> allweatherPost(
            @Field("city") String city,
            @Field("key") String key,
            @Field("extensions") String extensions,
            @Field("output") String output
    );

    /*@GET
    Observable<AllForecastResponseData> weatherGet(
            @Url String url);*/

    @GET(ApiContants.AMAP_WEATHER_GET)
    Observable<LiveWeatherResponseData> livesweatherGet(
            @Query("city") String city,
            @Query("key") String key,
            @Query("extensions") String extensions,
            @Query("output") String output
    );

    @GET(ApiContants.AMAP_WEATHER_GET)
    Observable<AllForecastResponseData> allweatherGet(
            @Query("city") String city,
            @Query("key") String key,
            @Query("extensions") String extensions,
            @Query("output") String output
    );
}

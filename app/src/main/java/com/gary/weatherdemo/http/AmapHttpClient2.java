package com.gary.weatherdemo.http;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * https://github.com/lhdboy/DeliberateWeather
 */
public class AmapHttpClient2 {
    private static Gson jsonConvert = new Gson();
    private static OkHttpClient okHttpClient = new OkHttpClient();
    public static <T> void getAmapWeather(String adcode, String type, final Class<T> resultClass
            , final IHttpCallback callback) {
        //demo url:
        //https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=3b6729d0c40f23fde7c55ae90ee0921d&extensions=base&output=JSON
        String parameters = "key=" + AmapContants.AMAP_USER_KEY_VALUE
                + "&city=" + adcode
                + "&extensions=" + type
                + "&output=JSON";
        Request request = new Request.Builder()
                .url(AmapContants.AMAP_HOST_URL + AmapContants.AMAP_WEATHER_PATH + parameters)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onSuccess(null, false);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                callback.onSuccess(jsonConvert.fromJson(response.body().string(), resultClass),
                        true);
            }
        });
    }

    public interface IHttpCallback {
        <T> void onSuccess(T result, boolean isSuccess);
    }
}

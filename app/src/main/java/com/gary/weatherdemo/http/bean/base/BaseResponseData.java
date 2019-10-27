package com.gary.weatherdemo.http.bean.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class BaseResponseData {
    @SerializedName("status")
    @Expose
    private final String status = null;

    @SerializedName("count")
    @Expose
    private final String count = null;

    @SerializedName("info")
    @Expose
    private final String info = null;

    @SerializedName("infocode")
    @Expose
    private final String infocode = null;

    public boolean isSuccessful() {
        return status != null && status.equals("1");
    }

    public boolean isResponseDataValid() {
        return infocode != null && infocode.equals("1000");
    }
}

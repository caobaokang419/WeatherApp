package com.gary.weatherdemo.provider.sp;

import android.net.Uri;
import android.os.Bundle;

import com.gary.weatherdemo.WtApplication;

/**
 * Created by GaryCao on 2018/10/25.
 * <p>
 * sp configs:across-process global access entries
 */
public interface SpProviderManager {
    int getSharePrefInt(String key);

    void setSharePrefInt(String key, int value);

    boolean getSharePrefBoolean(String key);

    void setSharePrefBoolean(String key, boolean value);

    String getSharePrefString(String key);

    void setSharePrefString(String key, String value);
}
package com.gary.weatherdemo.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gary.weatherdemo.R;
import com.gary.weatherdemo.base.MyApplication;

import retrofit2.http.Url;

/**
 * Created by GaryCao on 2019/01/05.
 */
public class GlideUtil {
    public static void loadImageUrl(Context context, Url imageUrl, ImageView imageView){
        Glide.with(context).load(imageUrl).into(imageView);
    }

    public static void loadImageUrl(Context context, Url imageUrl, ImageView imageView, int defResId){
        Glide.with(context).load(imageUrl).placeholder(defResId).into(imageView);
    }
}

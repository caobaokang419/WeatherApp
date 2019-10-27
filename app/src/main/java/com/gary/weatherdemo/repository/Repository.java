package com.gary.weatherdemo.repository;

import com.gary.weatherdemo.http.IAmapHttpClient;
import com.gary.weatherdemo.http.AmapHttpClient;
import com.gary.weatherdemo.provider.db.IDbProviderClient;
import com.gary.weatherdemo.provider.db.DbProviderClient;
import com.gary.weatherdemo.provider.sp.ISpProviderClient;
import com.gary.weatherdemo.provider.sp.SpProviderClient;


/**
 * Created by GaryCao on 2019/03/30.
 * GoF23 设计模式 10：外观模式: 1. sp providers apis  2. db providers apis 3. http apis
 */
public final class Repository {
    public static IAmapHttpClient http() {
        if (Ext.mIAmapHttpClient == null) {
            AmapHttpClient.registerInstance();
        }
        return Ext.mIAmapHttpClient;
    }

    public static ISpProviderClient sp() {
        if (Ext.mISpProviderClient == null) {
            SpProviderClient.registerInstance();
        }
        return Ext.mISpProviderClient;
    }

    public static IDbProviderClient db() {
        if (Ext.mIDbProviderClient == null) {
            DbProviderClient.registerInstance();
        }
        return Ext.mIDbProviderClient;
    }


    public static class Ext {
        private static IAmapHttpClient mIAmapHttpClient;
        private static ISpProviderClient mISpProviderClient;
        private static IDbProviderClient mIDbProviderClient;

        public static void setAmapHttpManager(IAmapHttpClient httpManager) {
            Repository.Ext.mIAmapHttpClient = httpManager;
        }

        public static void setSpProviderManager(ISpProviderClient ISpProviderClient) {
            Repository.Ext.mISpProviderClient = ISpProviderClient;
        }

        public static void setDbProviderManager(IDbProviderClient IDbProviderClient) {
            Repository.Ext.mIDbProviderClient = IDbProviderClient;
        }
    }
}

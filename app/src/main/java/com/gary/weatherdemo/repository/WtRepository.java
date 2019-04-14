package com.gary.weatherdemo.repository;

import com.gary.weatherdemo.http.AmapHttpManager;
import com.gary.weatherdemo.http.AmapHttpManagerImpl;
import com.gary.weatherdemo.provider.db.DbProviderManager;
import com.gary.weatherdemo.provider.db.DbProviderManagerImpl;
import com.gary.weatherdemo.provider.sp.SpProviderManager;
import com.gary.weatherdemo.provider.sp.SpProviderManagerImpl;


/**
 * Created by GaryCao on 2019/03/30.
 * GoF23 设计模式 10：外观模式: 1. sp providers apis  2. db providers apis 3. http apis
 */
public final class WtRepository {
    public static AmapHttpManager http() {
        if (Ext.mAmapHttpManager == null) {
            AmapHttpManagerImpl.registerInstance();
        }
        return Ext.mAmapHttpManager;
    }

    public static SpProviderManager sp() {
        if (Ext.mSpProviderManager == null) {
            SpProviderManagerImpl.registerInstance();
        }
        return Ext.mSpProviderManager;
    }

    public static DbProviderManager db() {
        if (Ext.mDbProviderManager == null) {
            DbProviderManagerImpl.registerInstance();
        }
        return Ext.mDbProviderManager;
    }


    public static class Ext {
        private static AmapHttpManager mAmapHttpManager;
        private static SpProviderManager mSpProviderManager;
        private static DbProviderManager mDbProviderManager;

        public static void setAmapHttpManager(AmapHttpManager httpManager) {
            WtRepository.Ext.mAmapHttpManager = httpManager;
        }

        public static void setSpProviderManager(SpProviderManager spProviderManager) {
            WtRepository.Ext.mSpProviderManager = spProviderManager;
        }

        public static void setDbProviderManager(DbProviderManager dbProviderManager) {
            WtRepository.Ext.mDbProviderManager = dbProviderManager;
        }
    }
}

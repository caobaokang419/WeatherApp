package com.gary.weatherdemo.provider.db;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.bean.CityBean;
import com.gary.weatherdemo.repository.WtRepository;
import com.gary.weatherdemo.room.WtDatabase;

/**
 * Created by GaryCao on 2019/01/05.
 * <p>
 * db data: across-process global access entries
 */
public class DbProviderManagerImpl implements DbProviderManager {

    private static DbProviderManagerImpl dbProviderManagerImpl;

    private DbProviderManagerImpl() {
    }

    public synchronized static void registerInstance() {
        if (dbProviderManagerImpl == null) {
            dbProviderManagerImpl = new DbProviderManagerImpl();
        }

        WtRepository.Ext.setDbProviderManager(dbProviderManagerImpl);
    }

    @Override
    public void insertCityBean(CityBean cityBean) {
        /*WtDatabase.getInstance(WtApplication.getContext()).cityInfoDAO().insert(entity);*/
    }
}

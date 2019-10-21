package com.gary.weatherdemo.cache.memcache;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import com.gary.weatherdemo.provider.db.DbProvider;

public class DbContentObserver  extends ContentObserver {
    public DbContentObserver(Handler handler) {
        super(handler);
    }

    public void registerContentObserver(Context context) {
        /**para2: notifyForDescendants need set true here*/
        context.getContentResolver().registerContentObserver(
                DbProvider.DB_CONTENT_URI, true, this);
    }

    public void unRegisterContentObserver(Context context) {
        context.getContentResolver().unregisterContentObserver(this);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
    }
}
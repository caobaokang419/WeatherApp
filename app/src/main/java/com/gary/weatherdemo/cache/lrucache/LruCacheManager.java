package com.gary.weatherdemo.cache.lrucache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;


/**
 * Created by GaryCao on 2019/05/15.
 *
 * 图片缓存实现
 *
 * 优点：借助Android LruCache，实现图片内存缓存
 */
public class LruCacheManager {
    private static final String TAG = "LruCacheManager";
    private static LruCacheManager mInstance;
    private LruCache<String, Bitmap> mBitmapCache;

    private LruCacheManager() {
        int maxMemory = (int) (Runtime.getRuntime().totalMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mBitmapCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    // 把Bitmap对象加入到缓存中
    public void addBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null) {
            mBitmapCache.put(key, bitmap);
        }
    }

    // 从缓存中得到Bitmap对象
    public Bitmap getBitmapFromCache(String key) {
        Log.i(TAG, "mBitmapCache size: " + mBitmapCache.size());
        return mBitmapCache.get(key);
    }

    // 从缓存中删除指定的Bitmap
    public void removeBitmapFromCache(String key) {
        mBitmapCache.remove(key);
    }


    // 清空缓存
    public void cleanupCache(){
        if (mBitmapCache != null) {
            mBitmapCache.evictAll();
        }
    }

    public synchronized LruCacheManager getInstance() {
        if (mInstance == null) {
            mInstance = new LruCacheManager();
        }
        return mInstance;
    }
}

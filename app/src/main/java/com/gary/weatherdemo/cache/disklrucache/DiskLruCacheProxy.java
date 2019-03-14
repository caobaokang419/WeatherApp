package com.gary.weatherdemo.cache.disklrucache;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.gary.weatherdemo.cache.disklrucache.origin.DiskLruCache;
import com.gary.weatherdemo.utils.WtUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by GaryCao on 2019/01/26.
 * 磁盘缓存代理类
 */
public class DiskLruCacheProxy {
    private static final String TAG = "DiskLruCacheProxy";
    private static final String DIR_NAME = "diskCache";
    private static final int MAX_COUNT = 5 * 1024 * 1024;
    private static final int DEFAULT_APP_VERSION = 1;

    /**
     * The default valueCount when open DiskLruCache.
     */
    private static final int DEFAULT_VALUE_COUNT = 1;

    private DiskLruCache mDiskLruCache;

    public DiskLruCacheProxy(Context context) {
        mDiskLruCache = generateCache(context, DIR_NAME, MAX_COUNT);
    }

    public DiskLruCacheProxy(Context context, String dirName) {
        mDiskLruCache = generateCache(context, dirName, MAX_COUNT);
    }

    public DiskLruCacheProxy(Context context, String dirName, int maxCount) {
        mDiskLruCache = generateCache(context, dirName, maxCount);
    }

    //custom cache dir
    public DiskLruCacheProxy(Context context, File dir) {
        mDiskLruCache = generateCache(context, dir, MAX_COUNT);
    }

    public DiskLruCacheProxy(Context context, File dir, int maxCount) {
        mDiskLruCache = generateCache(context, dir, maxCount);
    }

    private DiskLruCache generateCache(Context context, String dirName, int maxCount) {
        return generateCache(context, getDiskCacheDir(context, dirName), maxCount);
    }


    private DiskLruCache generateCache(Context context, File dir, int maxCount) {
        /**
         * 构造方法不建议throw exception
         */
        if (!dir.exists() || !dir.isDirectory()) {
            /*throw new IllegalArgumentException(
                    dir + " is not a directory or does not exists. ");*/

            return null;
        }

        int appVersion = context == null ? DEFAULT_APP_VERSION : WtUtil.getAppVersion(context);
        try {
            return DiskLruCache.open(
                    dir,
                    appVersion,
                    DEFAULT_VALUE_COUNT,
                    maxCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取缓存文件路径
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    /**
     * 获取DiskLruCache.Editor
     */
    public DiskLruCache.Editor editor(String key) {
        try {
            DiskLruCache.Editor edit = mDiskLruCache.edit(key);
            if (edit == null) {
                Log.w(TAG, "the entry spcified key:" + key + " is editing by other . ");
            }
            return edit;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取DiskLruCache.Snapshot
     */
    public InputStream get(String key) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot == null) {
                Log.e(TAG, "not find entry , or entry.readable = false");
                return null;
            }
            //write READ
            return snapshot.getInputStream(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 移除缓存项
     */
    public boolean remove(String key) {
        try {
            return mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 关闭缓存
     */
    public void close() {
        try {
            mDiskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除缓存
     */
    public void delete() {
        try {
            mDiskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新缓存
     */
    public void flush() throws IOException {
        mDiskLruCache.flush();
    }

    /**
     * 判断缓存是否关闭
     */
    public boolean isClosed() {
        return mDiskLruCache.isClosed();
    }

    /**
     * 获取缓存大小
     */
    public long size() {
        return mDiskLruCache.size();
    }

    /**
     * 设置缓存的最大容量
     */
    public void setMaxSize(long maxSize) {
        mDiskLruCache.setMaxSize(maxSize);
    }

    /**
     * 获取缓存的最大容量
     */
    public long getMaxSize() {
        return mDiskLruCache.getMaxSize();
    }

    /**
     * 设置缓存的目录
     */
    public File getDirectory() {
        return mDiskLruCache.getDirectory();
    }
}




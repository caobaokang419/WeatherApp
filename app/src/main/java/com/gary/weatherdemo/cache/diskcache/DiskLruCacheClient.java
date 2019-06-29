package com.gary.weatherdemo.cache.diskcache;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.gary.weatherdemo.WtApplication;
import com.gary.weatherdemo.cache.diskcache.origin.CacheUtil;
import com.gary.weatherdemo.cache.diskcache.origin.DiskLruCache;
import com.gary.weatherdemo.utils.ConvertUtil;
import com.gary.weatherdemo.utils.IOUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

/**
 * Created by GaryCao on 2019/01/26.
 * 磁盘缓存工具类
 */
public final class DiskLruCacheClient {
    private static final String TAG = "DiskLruCacheClient";
    private DiskLruCacheProxy mDiskLruCacheProxy;
    public static DiskLruCacheClient mInstance;

    private DiskLruCacheClient() {
        mDiskLruCacheProxy = new DiskLruCacheProxy(WtApplication.getContext());
    }

    /**
     * Singleton Instant，唯一入口
     */
    public synchronized static DiskLruCacheClient getInstance() {
        if (mInstance == null) {
            mInstance = new DiskLruCacheClient();
        }
        return mInstance;
    }

    /**
     * 保存 String数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的数据
     */
    public void putKeyAndStringValue(String key, String value) {
        if (mDiskLruCacheProxy == null) {
            return;
        }

        DiskLruCache.Editor edit = null;
        OutputStream os = null;
        BufferedWriter bw = null;
        try {
            edit = mDiskLruCacheProxy.editor(key);
            if (edit == null) return;
            os = edit.newOutputStream(0);
            bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(value);
            edit.commit();//write CLEAN
        } catch (IOException e) {
            e.printStackTrace();
            try {
                //s
                edit.abort();//write REMOVE
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            IOUtil.closeQuietly(os);
            IOUtil.closeQuietly(bw);
        }
    }

    /**
     * 读取缓存中 String数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public String getStringValueByKey(String key) {
        if (mDiskLruCacheProxy == null) {
            return null;
        }

        InputStream inputStream = null;
        inputStream = mDiskLruCacheProxy.get(key);
        if (inputStream == null) return null;
        String str = null;
        try {
            str = CacheUtil.readFully(new InputStreamReader(inputStream, CacheUtil.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return str;
    }


    /**
     * 保存 byte[]数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的数据
     */
    public void putKeyAndByteBuffer(String key, byte[] value) {
        if (mDiskLruCacheProxy == null) {
            return;
        }

        OutputStream out = null;
        DiskLruCache.Editor editor = null;
        try {
            editor = mDiskLruCacheProxy.editor(key);
            if (editor == null) {
                return;
            }
            out = editor.newOutputStream(0);
            out.write(value);
            out.flush();
            editor.commit();//write CLEAN
        } catch (Exception e) {
            e.printStackTrace();
            try {
                editor.abort();//write REMOVE
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取缓存中 byte[]数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public byte[] getByteBufferByKey(String key) {
        if (mDiskLruCacheProxy == null) {
            return null;
        }

        byte[] res = null;
        InputStream is = mDiskLruCacheProxy.get(key);
        if (is == null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] buf = new byte[256];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            res = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key       保存的key
     * @param jsonArray 保存的数据
     */
    public void putKeyAndJSONArray(String key, JSONArray jsonArray) {
        putKeyAndStringValue(key, jsonArray.toString());
    }


    /**
     * 读取缓存中 JSONObject数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public JSONArray getJSONArrayByKey(String key) {
        if (mDiskLruCacheProxy == null) {
            return null;
        }

        String JSONString = getStringValueByKey(key);
        try {
            JSONArray obj = new JSONArray(JSONString);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存 JSONObject数据 到 缓存中
     *
     * @param key        保存的key
     * @param jsonObject 保存的数据
     */
    public void putKeyAndJSONObject(String key, JSONObject jsonObject) {
        putKeyAndStringValue(key, jsonObject.toString());
    }


    /**
     * 读取缓存中 JSONObject数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public JSONObject getAsJson(String key) {
        if (mDiskLruCacheProxy == null) {
            return null;
        }

        String val = getStringValueByKey(key);
        try {
            if (val != null)
                return new JSONObject(val);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 保存 Bitmap数据 到 缓存中
     *
     * @param key    保存的key
     * @param bitmap 保存的数据
     */
    public void putKeyAndBitmap(String key, Bitmap bitmap) {
        putKeyAndByteBuffer(key, ConvertUtil.bitmap2Bytes(bitmap));
    }


    /**
     * 读取缓存中 Bitmap数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public Bitmap getBitmapByKey(String key) {
        byte[] bytes = getByteBufferByKey(key);
        if (bytes == null) {
            return null;
        }
        return ConvertUtil.bytes2Bitmap(bytes);
    }

    /**
     * 读取缓存中 Drawable数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public void putKeyAndDrawable(String key, Drawable value) {
        putKeyAndBitmap(key, ConvertUtil.drawable2Bitmap(value));
    }

    /**
     * 读取缓存中 Drawable数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public Drawable getDrawableByKey(String key) {
        byte[] bytes = getByteBufferByKey(key);
        if (bytes == null) {
            return null;
        }
        return ConvertUtil.bitmap2Drawable(ConvertUtil.bytes2Bitmap(bytes));
    }

    /**
     * 保存 Serializable数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的数据
     */
    public void putKeyAndSerializableValue(String key, Serializable value) {
        if (mDiskLruCacheProxy == null) {
            return;
        }

        DiskLruCache.Editor editor = mDiskLruCacheProxy.editor(key);
        ObjectOutputStream oos = null;
        if (editor == null) return;
        try {
            OutputStream os = editor.newOutputStream(0);
            oos = new ObjectOutputStream(os);
            oos.writeObject(value);
            oos.flush();
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                editor.abort();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取缓存中 Serializable数据
     *
     * @param key 缓存数据的key
     * @return
     */
    public <T> T getSerializableValueByKey(String key) {
        if (mDiskLruCacheProxy == null) {
            return null;
        }

        T t = null;
        InputStream is = mDiskLruCacheProxy.get(key);
        ObjectInputStream ois = null;
        if (is == null) return null;
        try {
            ois = new ObjectInputStream(is);
            t = (T) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * 移除缓存项
     */
    public boolean remove(String key) {
        return mDiskLruCacheProxy == null ? false : mDiskLruCacheProxy.remove(key);
    }

    /**
     * 关闭缓存
     */
    public void close() {
        if (mDiskLruCacheProxy != null) {
            mDiskLruCacheProxy.close();
        }
    }

    /**
     * 删除缓存
     */
    public void delete() {
        if (mDiskLruCacheProxy != null) {
            mDiskLruCacheProxy.delete();
        }
    }

    //===================================================================================================
    //for test
    /*用戶ID缓存*/
    public void saveCacheUserId(String key, String userid) {
        DiskLruCacheClient.getInstance().putKeyAndStringValue(key, userid);
    }

    public String getCacheUserId(String key) {
        return DiskLruCacheClient.getInstance().getStringValueByKey(key);
    }

    /*用戶头像缓存*/
    public void saveCacheUserPhoto(String key, Drawable photo) {
        DiskLruCacheClient.getInstance().putKeyAndDrawable(key, photo);
    }

    public Drawable getCacheUserPhoto(String key) {
        return DiskLruCacheClient.getInstance().getDrawableByKey(key);
    }
}




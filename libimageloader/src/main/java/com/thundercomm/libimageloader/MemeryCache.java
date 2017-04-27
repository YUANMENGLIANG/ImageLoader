package com.thundercomm.libimageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by yuanml0715 on 2017/4/24.
 */

public class MemeryCache implements ImageCache {
    private LruCache<String,Bitmap> mMemerCache;
    public MemeryCache(){
        initImageCache();
    }

    @Override
    public Bitmap get(String url) {
        return mMemerCache.get(url);
    }
    private void initImageCache()
    {
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory/4;
        mMemerCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    @Override
    public void put(String url, Bitmap bmp) {
        mMemerCache.put(url,bmp);
    }
}

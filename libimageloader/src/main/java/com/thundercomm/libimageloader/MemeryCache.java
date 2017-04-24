package com.thundercomm.libimageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by yuanml0715 on 2017/4/24.
 */

public class MemeryCache implements ImageCache {
    private LruCache<String,Bitmap> mMemerCache;
    public MemeryCache(){}

    @Override
    public Bitmap get(String url) {
        return mMemerCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mMemerCache.put(url,bmp);
    }
}

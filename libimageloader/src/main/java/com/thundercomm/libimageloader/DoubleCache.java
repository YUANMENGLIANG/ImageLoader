package com.thundercomm.libimageloader;

import android.graphics.Bitmap;

/**
 * Created by yuanml0715 on 2017/4/24.
 */

public class DoubleCache implements ImageCache {
    ImageCache mMemeryCache = new MemeryCache();
    ImageCache mDiskCache = new DiskCache();
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemeryCache.get(url);
        if(bitmap==null)
            bitmap = mDiskCache.get(url);
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mDiskCache.put(url,bmp);
        mMemeryCache.put(url,bmp);
    }
}

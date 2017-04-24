package com.thundercomm.libimageloader;

import android.graphics.Bitmap;

/**
 * Created by yuanml0715 on 2017/4/24.
 */

public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url,Bitmap bmp);
}

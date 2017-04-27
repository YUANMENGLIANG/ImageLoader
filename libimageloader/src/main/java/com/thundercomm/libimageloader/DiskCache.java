package com.thundercomm.libimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by yuanml0715 on 2017/4/24.
 */

public class DiskCache implements ImageCache {
    static String cacheDir = "sdcard/cache/";
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir+url);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }
}

package com.thundercomm.libimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yuanml0715 on 2017/4/24.
 */

public class ImageLoader {
    ImageCache mImageCache = new MemeryCache();
    ExecutorService mExecutorService = Executors.newFixedThreadPool
            (Runtime.getRuntime().availableProcessors());
    private static  ImageLoader _instance = null;
    public static ImageLoader GetInstance()
    {
        if(_instance == null)
        {
            _instance = new ImageLoader();
        }
        return _instance;
    }

    public  void setImageCache(ImageCache cache)
    {
        mImageCache = cache;
    }
    public void displayImage(String url, ImageView imageView)
    {
        Bitmap bitmap = mImageCache.get(url);
        if(null!=bitmap)
        {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没有缓存，提交到线程池中下载图片
        submitLoadRequest(url,imageView);
    }

    private void submitLoadRequest(final String imageUrl,final  ImageView imageView)
    {
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imageUrl);
                if(bitmap==null)
                {
                    return;
                }
                if(imageView.getTag().equals(imageUrl))
                {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(imageUrl,bitmap);
            }
        });
    }
    public Bitmap downloadImage(String imageUrl)
    {
        Bitmap bitmap = null;
        try{
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  bitmap;
    }
}

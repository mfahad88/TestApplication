package com.example.testapplication;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.StrictMode;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;


public class MyApp extends Application {

    /*static {
        System.loadLibrary("native-lib");
    }*/
    private static MyApp instance;

    public static MyApp getInstance() {

        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        Picasso picasso = new Picasso.Builder(this)
                .memoryCache(new LruCache(100000000))
                .downloader(new OkHttp3Downloader(this,100000000))
                .defaultBitmapConfig(Bitmap.Config.ALPHA_8)
                .build();
        picasso.setIndicatorsEnabled(false);

        picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        instance = this;

        super.onCreate();
    }


}
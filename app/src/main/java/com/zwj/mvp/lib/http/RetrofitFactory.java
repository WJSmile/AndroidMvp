package com.zwj.mvp.lib.http;

import android.util.Log;

import com.zwj.mvp.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "http://api.baidu.com/";

    private static final long TIMEOUT = 30;


    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create())
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(genericClient())
            .build()
            .create(RetrofitService.class);

    public static RetrofitService getInstance() {
        return retrofitService;
    }


    public static OkHttpClient genericClient() {

        //缓存路径
        File cacheFile = new File(MyApplication.getInstall().getContext().getCacheDir().getAbsolutePath(), "HttpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);//缓存文件为10MB
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        int maxAge = 60*60; // 有网络时 设置缓存超时时间1个小时
                        int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                        Request request = chain.request();
                        if (MyApplication.isNetworkAvalible(MyApplication.getInstall().getContext())) {
                            request= request.newBuilder()
                                   // .addHeader("apikey", "2ffc3e48c7086e0e1faa003d781c0e69")
                                    .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                                    .build();
                        }else {
                            request= request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
                                    .build();
                        }
                        Response response = chain.proceed(request);
                        if (MyApplication.isNetworkAvalible(MyApplication.getInstall().getContext())) {
                            response= response.newBuilder()
                                    .removeHeader("Pragma")
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .build();
                        } else {
                            response= response.newBuilder()
                                    .removeHeader("Pragma")
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }
                        return response;
                    }

                })
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("message",message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }
}


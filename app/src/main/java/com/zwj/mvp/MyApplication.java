package com.zwj.mvp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zwj.mvp.lib.mvp.Mvp;


public class MyApplication extends Application {
    public static MyApplication myApplication =null;
    public MyApplication(){

    }
    public static MyApplication getInstall(){
        if (myApplication==null){
            myApplication =  new MyApplication();
            return myApplication;
        }else {
            return myApplication;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Mvp.getInstance().init(this);
        myApplication = this;
    }
    public static boolean isNetworkAvalible(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Context getContext(){
        return getApplicationContext();
    }
}

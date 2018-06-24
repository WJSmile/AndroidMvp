package com.zwj.mvp.model;


import com.zwj.mvp.contract.LoginContract;
import com.zwj.mvp.lib.http.RetrofitFactory;
import com.zwj.mvp.lib.http.TestBase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wulei
 * Data: 2016/3/30.
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<TestBase> login() {
        return RetrofitFactory.getInstance()
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }

}

package com.zwj.mvp.presenter;

import com.orhanobut.logger.Logger;
import com.zwj.mvp.contract.LoginContract;
import com.zwj.mvp.lib.http.TestBase;
import com.zwj.mvp.lib.mvp.MvpPresenter;
import com.zwj.mvp.model.LoginModel;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LoginPresenter extends MvpPresenter<LoginModel, LoginContract.View> {

    public void checkParameter() {
        mModel.login().subscribe(new Observer<TestBase>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TestBase testBase) {
                mModel.login();
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage()+"hello");
            }

            @Override
            public void onComplete() {

            }
        });
    }

}

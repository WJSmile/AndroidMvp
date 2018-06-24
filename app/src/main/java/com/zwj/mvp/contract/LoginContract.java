package com.zwj.mvp.contract;


import com.zwj.mvp.lib.base.BaseView;
import com.zwj.mvp.lib.http.TestBase;
import com.zwj.mvp.lib.mvp.MvpModel;
import com.zwj.mvp.presenter.LoginPresenter;

import io.reactivex.Observable;

/**
 * Created by wulei
 * Data: 2016/8/4.
 */
public class LoginContract {
    public interface View extends BaseView {
        void loginSuccess();
        void loginError(String errorMsg);
    }

    public interface Model extends MvpModel {
        Observable<TestBase> login();
    }

}

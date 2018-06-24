package com.zwj.mvp.contract;


import com.zwj.mvp.lib.base.BaseView;
import com.zwj.mvp.lib.mvp.MvpModel;

/**
 * Created by wulei
 * Data: 2016/8/4.
 */
public class LoginContract {
    public interface View extends BaseView {
        String getAccount();
        String getPassword();
        void loginSuccess();
        void loginError(String errorMsg);
    }

    public interface Model extends MvpModel {
        String  login();
    }
}

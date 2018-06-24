package com.zwj.mvp.presenter;

import android.text.TextUtils;

import com.zwj.mvp.R;
import com.zwj.mvp.contract.LoginContract;
import com.zwj.mvp.lib.mvp.MvpPresenter;
import com.zwj.mvp.model.LoginModel;


/**
 * Created by wulei
 * Data: 2016/3/30.
 */
public class LoginPresenter extends MvpPresenter<LoginModel, LoginContract.View> {
    private static final String TAG = "LoginPresenter";

    /**
     * 登录参数校验
     *
     * @return
     */
    public boolean checkParameter() {
        getIView().loginError(mModel.login());
        String account = getIView().getAccount();
        String password = getIView().getPassword();

        //模拟登陆成功
        getIView().loginSuccess();

        try {
            if (TextUtils.isEmpty(getIView().getAccount())) {
                getIView().loginError(mContext.getString(R.string.toast_account_empty));
                return false;
            }

            if (TextUtils.isEmpty(getIView().getPassword())) {
                getIView().loginError(mContext.getString(R.string.toast_password_empty));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

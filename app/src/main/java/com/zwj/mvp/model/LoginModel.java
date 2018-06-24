package com.zwj.mvp.model;


import com.zwj.mvp.contract.LoginContract;

/**
 * Created by wulei
 * Data: 2016/3/30.
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public String login() {
     return "hello";
    }

}

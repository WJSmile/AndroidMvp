package com.zwj.mvp.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.zwj.mvp.R;
import com.zwj.mvp.contract.LoginContract;
import com.zwj.mvp.lib.base.BaseView;
import com.zwj.mvp.lib.mvp.MvpActivity;
import com.zwj.mvp.presenter.LoginPresenter;

import butterknife.BindView;

public class MainActivity extends MvpActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.name)
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.checkParameter();
    }


    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError(String errorMsg) {
        name.setText(errorMsg);
    }

    @Override
    public BaseView getBaseView() {
        return this;
    }

}

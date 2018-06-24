package com.zwj.mvp.lib.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwj.mvp.lib.base.BaseView;

import butterknife.ButterKnife;


/**
 * Created by wulei
 * Data: 2016/8/4.
 */
public abstract class MvpFragment<P extends MvpPresenter> extends Fragment implements MvpView {
    public P mPresenter;
    private int layoutResID = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.layoutResID = getLayoutResID();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initMvp();
        if (layoutResID!=0){
            View view = inflater.inflate(layoutResID,container,false);
            ButterKnife.bind(this,view);
            return view;
         }else {
            return super.onCreateView(inflater, container, savedInstanceState);
         }
    }

    public void initMvp() {
        Mvp mvp = Mvp.getInstance();
        mvp.registerView(this.getClass(), this);
        mPresenter = (P) mvp.getPresenter(Mvp.getGenericType(this, 0));
        mPresenter.initPresenter(getBaseView());
    }

    public abstract BaseView getBaseView();
    public abstract int getLayoutResID();
    @Override
    public void onDestroy() {
        super.onDestroy();

        Mvp.getInstance().unRegister(this.getClass());
        mPresenter.destory();
    }


}

package com.zwj.mvp.lib.mvp;

import android.content.Context;


import com.zwj.mvp.lib.base.BaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;


/**
 * Created by wulei
 * Data: 2016/8/4.
 */
public class MvpPresenter <M extends MvpModel, V extends BaseView> {
    public Context mContext;
    public Reference<V> mViewRef;
    public M mModel;

    public void initPresenter(V view){
        mModel = (M) Mvp.getInstance().getModel(Mvp.getGenericType(this, 0));

        mViewRef = new WeakReference<V>(view);
        mContext = Mvp.getInstance().getApplictionContext();
    }

    public V getIView(){
        return mViewRef.get();
    }

    public void destory(){
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}

package com.cuihai.login;

import android.content.Context;

import com.cuihai.base.entity.LoginEntity;

import com.cuihai.base.mvp.BasePresenter;
import com.cuihai.http.MyCallBack;

public class LoginPresenter extends BasePresenter<LoginContact.View, LoginModel> implements LoginContact.Presenter {


    public LoginPresenter(LoginContact.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getLogin(String user, String pwd) {
        if (getView() == null) {
            return;
        }
        mModel.sendLogin(user, pwd, new MyCallBack() {
            @Override
            public void onNext(Object o) {
                LoginEntity entity = (LoginEntity) o;
                mView.onLogin(entity);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
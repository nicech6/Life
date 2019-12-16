package com.life.app.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.life.app.R;
import com.life.base.constant.Path;
import com.life.base.mvp.BaseMvpFragment;
import com.life.base.mvp.BasePresenter;

/**
 * @author: cuihai
 * @description: 类描述
 * @date: 2019/12/16
 * @email: nicech6@163.com
 */
@Route(path = Path.HOME)
public class HomeFragment extends BaseMvpFragment {

    @Override
    protected void initView() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}

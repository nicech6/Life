package com.cuihai.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cuihai.base.constant.Path;
import com.cuihai.base.mvvm.BaseMVVMFragment;

/**
 * @author: cuihai
 * @description: 类描述
 * @date: 2019/12/20
 * @email: nicech6@163.com
 */
@Route(path = Path.Home.TEMP)
public class TempFragment extends BaseMVVMFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_temp;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}

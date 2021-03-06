package com.cuihai.home.live;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cuihai.base.constant.Path;
import com.cuihai.base.entity.home.HomeLiveEntity;
import com.cuihai.base.entity.home.LiveMultiItemEntity;
import com.cuihai.base.mvvm.BaseMVVMFragment;
import com.cuihai.home.R;
import com.cuihai.home.databinding.FragmentLiveBinding;
import com.cuihai.http.CommonBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: cuihai
 * @description: 类描述
 * @date: 2019/12/19
 * @email: nicech6@163.com
 */
@Route(path = Path.Home.LIVE)
public class LiveFragment extends BaseMVVMFragment<LiveViewModel, FragmentLiveBinding> {
    private LiveAdapter mLiveAdapter;
    private List<LiveMultiItemEntity> mEntities = new ArrayList<>();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_live;
    }

    @Override
    protected void initData() {
        mLiveAdapter = new LiveAdapter(mEntities);
        mBindingView.rv.setAdapter(mLiveAdapter);
        mBindingView.rv.setLayoutManager(new LinearLayoutManager(mContext));
        mViewModel.getData().observe(this, new Observer<CommonBean<HomeLiveEntity>>() {
            @Override
            public void onChanged(CommonBean<HomeLiveEntity> entityCommonBean) {
                setData(entityCommonBean, false);
            }
        });
    }

    private void setData(CommonBean<HomeLiveEntity> entityCommonBean, boolean loadMore) {
        if (!loadMore) {
            mEntities.clear();
            mBindingView.srl.finishRefresh();
        } else {
            mBindingView.srl.finishLoadMore();
        }
        LiveMultiItemEntity entityBannner = new LiveMultiItemEntity(LiveMultiItemEntity.BANNER);
        entityBannner.mBannerBeans = entityCommonBean.data.getBanner();
        mEntities.add(entityBannner);

        for (int i = 0; i < entityCommonBean.data.getPartitions().size(); i++) {
            LiveMultiItemEntity entityList = new LiveMultiItemEntity(LiveMultiItemEntity.LIST);
            entityList.mPartitionsBean = entityCommonBean.data.getPartitions().get(i);
            mEntities.add(entityList);
        }
        mLiveAdapter.notifyDataSetChanged();
    }

    @Override
    protected void bindEvent() {
        mBindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mViewModel.getData().observe(LiveFragment.this, new Observer<CommonBean<HomeLiveEntity>>() {
                    @Override
                    public void onChanged(CommonBean<HomeLiveEntity> entityCommonBean) {
                        setData(entityCommonBean, false);
                    }
                });
            }
        });
        mBindingView.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.getData().observe(LiveFragment.this, new Observer<CommonBean<HomeLiveEntity>>() {
                    @Override
                    public void onChanged(CommonBean<HomeLiveEntity> entityCommonBean) {
                        setData(entityCommonBean, true);
                    }
                });
            }
        });
    }
}

package com.hong_world.bmodle.view.wanandroid;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hong_world.bmodle.BR;
import com.hong_world.bmodle.R;
import com.hong_world.bmodle.bean.FeedArticleData;
import com.hong_world.bmodle.bean.FeedArticleListData;
import com.hong_world.bmodle.contract.MainPagerContract;
import com.hong_world.bmodle.databinding.FragmentMainPagerBinding;
import com.hong_world.bmodle.presenter.MainPagerPresenter;
import com.hong_world.common.adapters.SingleDataBindingUseAdapter;
import com.hong_world.common.base.BaseFragment;
import com.hong_world.common.utils.StatusBarUtil;
import com.hong_world.routerlibrary.provider.IBProvider;

/**
 * Date: 2018/8/13. 11:26
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IBProvider.B_FRG_MAIN_PAGER)
public class MainPagerFragment extends BaseFragment<MainPagerPresenter, FragmentMainPagerBinding> implements MainPagerContract.View<MainPagerPresenter> {

    private SingleDataBindingUseAdapter mAdapter;
    private int mCurrentPage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    public MainPagerPresenter createPresenter() {
        return new MainPagerPresenter(this);
    }

    @Override
    public String title() {
        return "首页";
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        StatusBarUtil.darkMode(this.getActivity());
        StatusBarUtil.setPaddingSmart(getContext(), baseLayoutBinding.toolbar);
        getSmartRefreshLayout().setEnableOverScrollDrag(true);
        mBinding.setPresenter(mPresenter);
        mAdapter = new SingleDataBindingUseAdapter(R.layout.item_search_pager, mPresenter);
        mBinding.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mBinding.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getSmartRefreshLayout().setEnableRefresh(false);
                mPresenter.getPageList(mCurrentPage, false);
            }
        }, mBinding.rv);
        mPresenter.getPageList(mCurrentPage, true);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mCurrentPage = 0;
        mAdapter.setEnableLoadMore(false);
        mPresenter.getPageList(mCurrentPage, true);
    }

    @Override
    public void onDataNotAvailable(String type, String msg) {
        super.onDataNotAvailable(type, msg);
        mAdapter.setEnableLoadMore(true);
        getSmartRefreshLayout().setEnableRefresh(true);
    }

    @Override
    public void getPageListSuccess(FeedArticleListData data, boolean isRefresh) {
        mCurrentPage++;
        if (isRefresh) {
            mAdapter.replaceData(data.getDatas());
            mAdapter.setEnableLoadMore(true);
        } else {
            if (!data.isOver()) {
                mAdapter.loadMoreComplete();
            } else {
                mAdapter.loadMoreEnd();
            }
            mAdapter.addData(data.getDatas());
            getSmartRefreshLayout().setEnableRefresh(true);
        }
    }

}

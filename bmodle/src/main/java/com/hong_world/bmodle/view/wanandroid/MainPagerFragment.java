package com.hong_world.bmodle.view.wanandroid;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hong_world.bmodle.R;
import com.hong_world.bmodle.bean.FeedArticleData;
import com.hong_world.bmodle.bean.FeedArticleListData;
import com.hong_world.bmodle.contract.MainPagerContract;
import com.hong_world.bmodle.databinding.FragmentMainPagerBinding;
import com.hong_world.bmodle.presenter.MainPagerPresenter;
import com.hong_world.common.adapters.SingleDataBindingUseAdapter;
import com.hong_world.common.base.BaseListFragment;
import com.hong_world.common.utils.StatusBarUtil;
import com.hong_world.routerlibrary.provider.IBProvider;
import com.hong_world.routerlibrary.provider.IHomeProvider;

/**
 * Date: 2018/8/13. 11:26
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IBProvider.B_FRG_MAIN_PAGER)
public class MainPagerFragment extends BaseListFragment<MainPagerPresenter, FragmentMainPagerBinding> implements MainPagerContract.View<MainPagerPresenter> {


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
        return "首页-java";
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.colorPrimaryDark), 50);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.colorPrimaryDark), 50);
        getSmartRefreshLayout().setEnableOverScrollDrag(true);
        mBinding.setPresenter(mPresenter);
        mAdapter = new SingleDataBindingUseAdapter<FeedArticleData,MainPagerPresenter>(R.layout.item_search_pager, mPresenter);
        mBinding.rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mBinding.rv.setAdapter(mAdapter);
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
        mPresenter.getPageList(mCurrentPage, true);
    }


    @Override
    public void getPageListSuccess(FeedArticleListData data, boolean isRefresh) {
        setPageList(data.getDatas(),data.isOver(),isRefresh);
    }

    @Override
    public void onItemClick(FeedArticleData data) {
        ARouter.getInstance().build(IHomeProvider.HOME_ACT_WEB).withString("urls", data.getLink()).navigation();
    }

}

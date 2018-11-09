package com.hong_world.common.base;

import android.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hong_world.library.base.BasePresenter;

import java.util.List;

/**
 * Date: 2018/10/25. 14:50
 * Author: hong_world
 * Description:
 * Version:
 */
public abstract class BaseListFragment<P extends BasePresenter, V extends ViewDataBinding> extends BaseFragment<P, V> {
    protected int mCurrentPage;
    protected BaseQuickAdapter mAdapter;

    @Override
    public void onRefresh() {
        super.onRefresh();
        mCurrentPage = 0;
        if (mAdapter != null) {
            mAdapter.loadMoreEnd();
            mAdapter.setEnableLoadMore(false);
        }
    }

    @Override
    public void onDataNotAvailable(String type, String msg) {
        super.onDataNotAvailable(type, msg);
        if (mAdapter != null) {
            mAdapter.setEnableLoadMore(true);
        }
        setSmartRefreshEnableRefresh(true);
    }

    public void setPageList(List data, boolean isOver, boolean isRefresh) {
        if (mAdapter == null) return;
        mCurrentPage++;
        if (isRefresh) {
            mAdapter.replaceData(data);
            mAdapter.setEnableLoadMore(true);
        } else {
            if (!isOver) {
                mAdapter.loadMoreComplete();
            } else {
                mAdapter.loadMoreEnd();
            }
            mAdapter.addData(data);
            setSmartRefreshEnableRefresh(true);
        }
    }
}

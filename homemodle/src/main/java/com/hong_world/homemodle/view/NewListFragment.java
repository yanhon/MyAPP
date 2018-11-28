package com.hong_world.homemodle.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.hong_world.common.ProviderManager;
import com.hong_world.common.adapters.SingleDataBindingUseAdapter;
import com.hong_world.common.base.BaseFragment;
import com.hong_world.common.utils.StatusBarUtil;
import com.hong_world.common.widget.banner.AutoPlayRecyclerView;
import com.hong_world.common.widget.banner.OrientationHelper;
import com.hong_world.common.widget.banner.ScaleLayoutManager;
import com.hong_world.common.widget.banner.ViewPagerLayoutManager;
import com.hong_world.homemodle.BR;
import com.hong_world.homemodle.R;
import com.hong_world.homemodle.contract.NewListContract;
import com.hong_world.homemodle.databinding.FragmentNewListBinding;
import com.hong_world.homemodle.modle.bean.BeanItem;
import com.hong_world.homemodle.modle.bean.Level0Item;
import com.hong_world.homemodle.modle.bean.Level1Item;
import com.hong_world.homemodle.modle.bean.MultipleItem;
import com.hong_world.homemodle.presenter.NewListPresenter;
import com.hong_world.library.base.BaseSupportFragment;
import com.hong_world.routerlibrary.provider.IHomeProvider;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Date: 2018/7/31. 10:15
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IHomeProvider.HOME_FRG_NEW_LIST)
public class NewListFragment extends BaseFragment<NewListPresenter, FragmentNewListBinding> implements NewListContract.View<NewListPresenter> {

    private MultipleItemQuickAdapter2 mAdapter;

    public static NewListFragment getInstance() {
        return new NewListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_list;
    }

    @Override
    public NewListPresenter createPresenter() {
        return new NewListPresenter(this);
    }

    @Override
    public String title() {
        return "新闻列表";
    }

    List<Level0Item> list;

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarUtil.setDarkMode(_mActivity);
        StatusBarUtil.setFullScrren(_mActivity);
        StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.shimmer_color));
//        mBinding.banner.start();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
//        mBinding.banner.pause();
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.shimmer_color));
        getSmartRefreshLayout().setEnableOverScrollDrag(true);
        getSmartRefreshLayout().setEnableNestedScroll(true);
//        getSmartRefreshLayout().setEnableLoadMore(true);
        mBinding.setView(this);
        mBinding.setPresenter(mPresenter);
//        DataBindingUseAdapter mAdapter = new DataBindingUseAdapter(R.layout.item_new_list_view, genData());
//        MultipleItemQuickAdapter mAdapter = new MultipleItemQuickAdapter(this.getContext(), genData2());
        mAdapter = new MultipleItemQuickAdapter2();
//        final ExpandableItemAdapter mAdapter = new ExpandableItemAdapter();
        mBinding.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mBinding.setAdapter(mAdapter);
//        mAdapter.setEnableLoadMore(true);
//        mAdapter.addData(list = genData3());
        mAdapter.addData(genData2());
//        mAdapter.loadMoreComplete();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                mAdapter.addData(genData3());
//                mAdapter.loadMoreComplete();
                mAdapter.loadMoreEnd();
            }
        }, mBinding.rv);
        mAdapter.disableLoadMoreIfNotFullPage();
//        mAdapter.loadMoreComplete();
//        AppCompatDelegate.setDefaultNightMode(
//                AppCompatDelegate.MODE_NIGHT_YES);
        initBanner();
    }

    public void initBanner() {
        ScaleLayoutManager mLayoutManager = new ScaleLayoutManager.Builder(getContext(), 30)
                .setMinScale(0.8f)
                .setMoveSpeed(1.0f)
                .setOrientation(OrientationHelper.HORIZONTAL)
                .build();
        mLayoutManager.setInfinite(true);
        mBinding.banner.setLayoutManager(mLayoutManager);
//        new CenterSnapHelper().attachToRecyclerView(mBinding.banner);
        DataBindingUseAdapter mAdapter = new DataBindingUseAdapter(R.layout.item_new_list_view, genData());
        mBinding.banner.setAdapter(mAdapter);
        mLayoutManager.setOnPageChangeListener(new ViewPagerLayoutManager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Logger.i("onPageSelected:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Logger.i("onPageScrollStateChanged:" + state);
            }
        });
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
        mAdapter.loadMoreEnd();
        mAdapter.setEnableLoadMore(false);
        mAdapter.setNewData(genData2());
        smartRefreshFinishRefresh();
    }

    private List<Level0Item> genData3() {

        int lv0Count = 12;
        int lv1Count = 3;
        ArrayList<Level0Item> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item(MultipleItem.TEXT, i + "");
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item(MultipleItem.IMG_TEXT, i + "");
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }

    private List<BeanItem> genData() {
        List<BeanItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new BeanItem(i + ""));
        }
        return list;
    }

    private List<MultipleItem> genData2() {
        List<MultipleItem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0)
                list.add(new MultipleItem(i + "", MultipleItem.TEXT));
            else
                list.add(new MultipleItem(i + "", MultipleItem.IMG_TEXT, genData()));
        }
        return list;
    }

    @Override
    public void onItemClick(MultipleItem data) {
        ((BaseSupportFragment) getParentFragment()).start((ISupportFragment) ProviderManager.getInstance().getHomeProvider().getFragment(IHomeProvider.HOME_FRG_IMAGE, null));
    }

    public class DataBindingUseAdapter extends BaseQuickAdapter<BeanItem, MovieViewHolder> {


        public DataBindingUseAdapter(int layoutResId, @Nullable List<BeanItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(MovieViewHolder helper, BeanItem item) {
            ViewDataBinding binding = helper.getBinding();
            binding.setVariable(BR.date, item);
            binding.setVariable(BR.presenter, mPresenter);
//            binding.executePendingBindings();
        }

        @Override
        protected View getItemView(int layoutResId, ViewGroup parent) {
            ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
            if (binding == null) {
                return super.getItemView(layoutResId, parent);
            }
            View view = binding.getRoot();
            view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
            return view;
        }

    }

    private class MyOnScrollListener extends RecyclerView.OnScrollListener {

        private LinearLayoutManager mLayoutManager;
        private MultipleItem mEntity;
        private int mItemWidth;
        private int mItemMargin;

        public MyOnScrollListener(MultipleItem shopItem, LinearLayoutManager layoutManager) {
            mLayoutManager = layoutManager;
            mEntity = shopItem;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:

                    int offset = recyclerView.computeHorizontalScrollOffset();
                    Logger.i("offset = " + offset);
                    mEntity.scrollPosition = mLayoutManager.findFirstVisibleItemPosition() < 0 ? mEntity.scrollPosition : mLayoutManager.findFirstVisibleItemPosition() + 1;
                    Logger.i("1、mItemWidth = " + mItemWidth + " ,scrollPosition = " + mEntity.scrollPosition);
                    if (mItemWidth <= 0) {
                        View item = mLayoutManager.findViewByPosition(mEntity.scrollPosition);
                        if (item != null) {
                            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) item.getLayoutParams();
                            mItemWidth = item.getWidth();
                            mItemMargin = layoutParams.rightMargin;
                        }
                    }
                    Logger.i("2、mItemWidth = " + mItemWidth);
                    Logger.i("mItemMargin = " + mItemMargin + " ,offset = " + offset);
                    if (offset > 0 && mItemWidth > 0) {
                        //offset % mItemWidth：得到当前position的滑动距离
                        //mEntity.scrollPosition * mItemMargin：得到（0至position）的所有item的margin
                        //用当前item的宽度-所有margin-当前position的滑动距离，就得到offset。
                        mEntity.scrollOffset = mItemWidth - offset % mItemWidth + mEntity.scrollPosition * mItemMargin;
                    } else {
                        mEntity.scrollOffset = 0;
                    }
                    Logger.i("scrollOffset = " + mEntity.scrollOffset);

                    break;
            }
        }
    }

    public static class MovieViewHolder extends BaseViewHolder {

        public MovieViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }

//    public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
//
//
//        public MultipleItemQuickAdapter(Context context, List data) {
//            super(data);
//            //Step.1
////            setMultiTypeDelegate(new MultiTypeDelegate<Entity>() {
////                @Override
////                protected int getItemType(Entity entity) {
////                    //infer the type by entity
////                    return entity.type;
////                }
////            });
////            //Step.2
////            getMultiTypeDelegate()
////                    .registerItemType(Entity.TEXT, R.layout.item_text_view)
////                    .registerItemType(Entity.IMG, R.layout.item_image_view);
//            addItemType(MultipleItem.TEXT, R.layout.item_new_list_view_1);
//            addItemType(MultipleItem.IMG_TEXT, R.layout.item_new_list_view_2);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, MultipleItem item) {
//            ViewDataBinding binding = (ViewDataBinding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
//            switch (helper.getItemViewType()) {
//                case MultipleItem.TEXT:
//                    binding.setVariable(BR.date, item);
//                    binding.setVariable(BR.presenter, mPresenter);
//                    break;
//                case MultipleItem.IMG_TEXT:
//                    binding.setVariable(BR.date, item);
//                    binding.setVariable(BR.presenter, mPresenter);
//                    break;
//            }
//        }
//
//        @Override
//        protected View getItemView(int layoutResId, ViewGroup parent) {
//            ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
//            if (binding == null) {
//                return super.getItemView(layoutResId, parent);
//            }
//            View view = binding.getRoot();
//            view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
//            return view;
//        }
//    }

    public class MultipleItemQuickAdapter2 extends SingleDataBindingUseAdapter<MultipleItem, NewListPresenter> {


        public MultipleItemQuickAdapter2() {
            super(mPresenter);
            //Step.1
            setMultiTypeDelegate(new MultiTypeDelegate<MultipleItem>() {
                @Override
                protected int getItemType(MultipleItem entity) {
                    //infer the type by entity
                    return entity.getItemType();
                }
            });
//            //Step.2
            getMultiTypeDelegate()
                    .registerItemType(MultipleItem.TEXT, R.layout.item_new_list_view_1)
                    .registerItemType(MultipleItem.IMG_TEXT, R.layout.item_new_list_and_list_view);
//            addItemType(MultipleItem.TEXT, R.layout.item_new_list_view_1);
//            addItemType(MultipleItem.IMG_TEXT, R.layout.item_new_list_and_list_view);
        }

        @Override
        protected void convert(BaseViewHolder helper, MultipleItem item) {
            ViewDataBinding binding = (ViewDataBinding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
            switch (helper.getItemViewType()) {
                case MultipleItem.TEXT:
                    binding.setVariable(BR.date, item);
                    binding.setVariable(BR.presenter, mPresenter);
                    break;
                case MultipleItem.IMG_TEXT:
                    DataBindingUseAdapter mAdapter = new DataBindingUseAdapter(R.layout.item_new_list_view, item.getBeanItemList());
//                    mAdapter.addData(item.getBeanItemList());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewListFragment.this.getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    ScaleLayoutManager mLayoutManager = new ScaleLayoutManager.Builder(getContext(), 10)
                            .setMinScale(0.8f)
                            .setMoveSpeed(1.0f)
                            .setOrientation(OrientationHelper.HORIZONTAL)
                            .build();
                    mLayoutManager.setInfinite(item.getBeanItemList().size() > 0);
                    binding.setVariable(BR.layoutManager, mLayoutManager);
                    binding.setVariable(BR.adapter, mAdapter);
                    binding.setVariable(BR.date, item);
                    binding.setVariable(BR.presenter, mPresenter);
                    SnapHelper snapHelper = new PagerSnapHelper();
                    AutoPlayRecyclerView recyclerView = (AutoPlayRecyclerView) binding.getRoot().findViewById(R.id.rv);
//                    recyclerView.start();
//                    getLifecycle().addObserver(recyclerView);
//                    recyclerView.setOnFlingListener(null);
//                    snapHelper.attachToRecyclerView(recyclerView);
                    Logger.i("scrollPosition= " + item.scrollPosition + " ,scrollOffset= " + item.scrollOffset + " ,item position = " + helper.getAdapterPosition());
                    if (item.scrollPosition > 0) {
                        linearLayoutManager.scrollToPositionWithOffset(item.scrollPosition - 1, item.scrollOffset);
//                        linearLayoutManager.scrollToPosition(item.scrollPosition);
                    }
//                    recyclerView.addOnScrollListener(new MyOnScrollListener(item, linearLayoutManager));
                    mLayoutManager.setOnPageChangeListener(new ViewPagerLayoutManager.OnPageChangeListener() {
                        @Override
                        public void onPageSelected(int position) {
                            Logger.i("onPageSelected2:" + position);
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            Logger.i("onPageScrollStateChanged2:" + state);
                        }
                    });
                    break;
            }
        }

        @Override
        protected View getItemView(int layoutResId, ViewGroup parent) {
            ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
            if (binding == null) {
                return super.getItemView(layoutResId, parent);
            }
            View view = binding.getRoot();
            view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
            return view;
        }
    }

    public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {


        public ExpandableItemAdapter() {
            super(null);
            addItemType(MultipleItem.TEXT, R.layout.item_new_list_view_3);
            addItemType(MultipleItem.IMG_TEXT, R.layout.item_new_list_view_4);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
            ViewDataBinding binding = (ViewDataBinding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
            switch (helper.getItemViewType()) {
                case MultipleItem.TEXT:
                    final Level0Item lv0 = (Level0Item) item;
                    binding.setVariable(BR.date, lv0);
                    binding.setVariable(BR.presenter, mPresenter);
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = helper.getAdapterPosition();
                            Log.d(TAG, "Level 0 item pos: " + pos);
                            if (lv0.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                            }
                        }
                    });
                    break;
                case MultipleItem.IMG_TEXT:
                    final Level1Item lv1 = (Level1Item) item;
                    Level0Item lv00 = null;
                    for (Level0Item level0Item : list) {
                        if (level0Item.contains(lv1)) {
                            lv00 = level0Item;
                            break;
                        }

                    }
                    binding.setVariable(BR.date, lv1);
                    binding.setVariable(BR.presenter, mPresenter);
                    final Level0Item finalLv0 = lv00;
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lv1.setSelect(!lv1.isSelect());

                            if (finalLv0 == null) return;
                            int i = 0;
                            for (Level1Item level1Item : finalLv0.getSubItems()) {
                                if (!level1Item.isSelect()) {
                                    i = 1;
                                    break;
                                }
                            }
                            finalLv0.setSelect(i == 0);
                        }
                    });
                    break;
            }
        }

        @Override
        protected View getItemView(int layoutResId, ViewGroup parent) {
            ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
            if (binding == null) {
                return super.getItemView(layoutResId, parent);
            }
            View view = binding.getRoot();
            view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
            return view;
        }
    }
}

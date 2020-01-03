package com.lzy.basemodule.base;

import android.view.View;

import com.lzy.basemodule.mvp.BasePresenterImpl;
import com.lzy.basemodule.mvp.BaseView;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @Auther: liziyang
 * @datetime: 2019-11-23
 * @desc:
 */

public abstract class BaseSmartLayoutActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends BaseActivity<V, T> implements View.OnClickListener, BaseView {
    protected abstract String getToolBarTitle();

    protected abstract void clearDatas();

    protected abstract boolean isRefresh();

    @Override
    protected void initView() {
        initToolbar(getToolBarTitle());
        initRecy();
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        initSmartLayout(isRefresh(), false);
        initImgNodata();
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        if (isRefresh())
            initDatas();
    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        clearDatas();
        initDatas();
    }


    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }
}

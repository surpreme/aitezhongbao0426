package com.lzy.basemodule.mvp3;

import android.content.Intent;
import com.lzy.basemodule.base.BaseActivity;

public abstract class BaseMVPActivity3<P extends IPresenter> extends BaseActivity implements IView{

    protected P mPresenter;

    protected abstract int getLayoutResId();

    @Override
    protected void initDatas() {
        mPresenter = createPresenter();
        // presenter 绑定 view
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        requestNetwork();
    }

    protected abstract P createPresenter();

    protected abstract void requestNetwork();

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Activity 销毁时取消所有的订阅
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    private boolean isShow;

    @Override
    protected void onStart() {
        super.onStart();
        isShow = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isShow = false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void useNightMode(boolean isNightMode) {
//        setEnableNightMode(isNightMode);
    }

    @Override
    public boolean isShow() {
        return isShow;
    }

    @Override
    public void showEmpty() {

    }
}

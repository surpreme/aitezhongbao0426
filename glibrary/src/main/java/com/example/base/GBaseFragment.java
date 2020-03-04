package com.example.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Utils.ProgressDialog;
import com.lzy.basemodule.bean.ContentValue;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import butterknife.ButterKnife;

/**
 * 创建时间 2019/12/23 11:54
 * 描述:
 */
public abstract class GBaseFragment<T extends GBasePresenter> extends Fragment implements BaseView {

    protected T mPresenter;


    private View mLayoutView = null;

    protected Context mContext;

    protected Activity mActivity;


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(setLayoutId(), null);
        ButterKnife.bind(this, mLayoutView);
        mContext = getContext();
        mActivity = getActivity();
        initOthers();
        return mLayoutView;
    }

    /**
     * 设置contentview
     */
    public abstract int setLayoutId();


    /**
     * @bref 初始化其他工具
     */
    public abstract void initOthers();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    public static void log(String toast) {
        Log.i("TODO:==", toast);
    }

    public void showLoading() {
        ProgressDialog.getInstance().show(mContext);
    }

    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }


    /**
     * 创建fragment的静态方法，方便传递参数
     *
     * @param args 传递的参数
     * @return
     */
    public static <T extends Fragment> T newInstance(Class clazz, Bundle args) {
        T mFragment = null;
        try {
            mFragment = (T) clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mFragment.setArguments(args);
        return mFragment;
    }


    /**
     * 绑定生命周期
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
    }

    /**
     * 跳转页面
     *
     * @param cls 所跳转的目的Activity类
     */
    protected void startActivityWithCls(Class cls, int requestCode, ContentValue... values) {
        Intent intent = new Intent(mContext, cls);

        if (values != null && values.length > 0) {
            for (ContentValue value : values) {
                value.fillIntent(intent);
            }
        }

        if (requestCode > 0) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
    }
    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void toActivity(Class<?> clz, String tag, String extra) {
        Intent intent = new Intent(mContext, clz);
        intent.putExtra(tag, extra);
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void toActivity(Class<?> clz, String tag1, String extra1, String tag2, String extra2) {
        Intent intent = new Intent(mContext, clz);
        intent.putExtra(tag1, extra1);
        intent.putExtra(tag2, extra2);
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void toActivity(Class<?> clz) {
        Intent intent = new Intent(mContext, clz);
        startActivity(intent);
    }

}

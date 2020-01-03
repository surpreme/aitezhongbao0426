package com.example.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Utils.ProgressDialog;
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
public  abstract class GBaseFragment<T extends GBasePresenter>  extends Fragment implements BaseView {

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

    public void showLoading(){
        ProgressDialog.getInstance().show(mContext);
    }

    public void hideLoading(){
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

}

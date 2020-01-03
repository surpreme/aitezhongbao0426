package com.lzy.basemodule.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.activitylife.ActivityManager;
import com.lzy.basemodule.base.androidlife.AppManager;
import com.lzy.basemodule.bean.BaseDataEtras;
import com.lzy.basemodule.bean.BeanConvertor;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.R;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.BasePresenterImpl;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.basemodule.util.SystemUtil;
import com.lzy.basemodule.util.toast.ToastTopUtils;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView, T extends BasePresenterImpl<V>> extends mBaseFragment<V, T> implements View.OnClickListener, BaseView {

    protected abstract void initModel();

    protected abstract void initViews();

    protected abstract int getLayoutResId();

    private SmartRefreshLayout smartRefreshLayout;

    protected float screenwidth = 0;
    protected boolean hasMore = false;


    protected RecyclerView mBaserecyclerView;

    private Unbinder unbinder;

    public View getMview() {
        return mview;
    }

    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mview = view;
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (isUseMvp()) {
                mPresenter = getInstance(this, 1);
                mPresenter.attachView((V) this);
            }

        } catch (Exception e) {
            LogUtils.e("mvp错误" + e);
        }
        if (screenwidth == 0) {
            screenwidth = context.getResources().getDisplayMetrics().widthPixels;
        }

    }

    @Override
    protected boolean isUseMvp() {
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initModel();

        LogUtils.d(this.getClass() + "       onViewCreated");
    }

    public float getScreenwidth() {
        return screenwidth;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void showLoading() {
        PopwindowUtils.getmInstance().showloaddingPopupWindow(getActivity());
    }

    @Override
    public void dimissLoading() {
        PopwindowUtils.getmInstance().dismissPopWindow();


    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showEmpty() {

    }

    protected void stopNoData() {
        nodata_lottieAnimationView.setVisibility(View.GONE);
        showMoreRecy();
        stopLoadingAnim();
    }

    protected void initMoreRecy() {
        mBaserecyclerView = mview.findViewById(R.id.recycler_view);
    }


    protected void showMoreRecy() {
        mBaserecyclerView.setVisibility(View.VISIBLE);
    }

    protected void initNodata() {
        stopLoadingAnim();
        if (nodata_lottieAnimationView != null) {
            nodata_lottieAnimationView.setVisibility(View.VISIBLE);
            nodata_lottieAnimationView.playAnimation();
        }


    }


    //初始化动画
    protected void initLoadingAnima() {
        lottieAnimationView = mview.findViewById(R.id.lottieAnimationView);
        nodata_lottieAnimationView = mview.findViewById(R.id.nodata_lottieAnimationView);
        lottieAnimationView.playAnimation();   //播放
        smartRefreshLayout.setVisibility(View.GONE);
        lottieAnimationView.setVisibility(View.VISIBLE);
        mBaserecyclerView.setVisibility(View.GONE);

        nodata_lottieAnimationView.setVisibility(View.GONE);
        if (!SystemUtil.isNetworkConnected()) {
            stopLoadingAnim();
            initNodata();
            ToastTopUtils toastTopUtils = new ToastTopUtils();
            toastTopUtils.Short(context, "请检查网络").setGravity(Gravity.TOP).show();
        }

    }

    protected void stopLoadingAnim() {
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();  //取消
            lottieAnimationView.setVisibility(View.GONE);
        }
        smartRefreshLayout.setVisibility(View.VISIBLE);


    }

    /**
     * 初始化刷新控件
     * 是否可以上拉加载
     *
     * @param isRefresh
     */
    public void initSmartLayout(boolean isRefresh) {
        try {
            smartRefreshLayout = mview.findViewById(R.id.smartlayout);
            smartRefreshLayout.setEnableAutoLoadMore(isRefresh);
            smartRefreshLayout.setRefreshHeader(new WaterDropHeader(context));
            mCurrentPage = 1;
            smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    mCurrentPage = 1;
                    onSmartRefresh();
                    smartRefreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败

                }
            });
            smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    LogUtils.d("mCurrentPage" + mCurrentPage);
                    if (hasMore) {
                        mCurrentPage++;
                        smartRefreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
                        onSmartLoadMore();
                    } else {
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();

                    }

                }
            });
        } catch (Exception e) {
            LogUtils.e("initSmartLayout-fail" + e + e.getClass());
        }

    }

    protected String replaceString(String msg, int start, int end) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < msg.toCharArray().length; i++) {
            if (i > start && i < end) {
                b.append("*");
            } else {
                b.append(msg.toCharArray()[i]);
            }
        }
        return b.toString();
    }

    @Override
    protected void onSmartRefresh() {

    }

    @Override
    protected void onSmartLoadMore() {

    }


    @Override
    public void showError(String msg) {
        showToast(msg);
        LogUtils.d("服务器错误信息+++++++" + msg);
        if (msg.equals("查询不到Token数据") || msg.equals("请登录")) {
            PopwindowUtils.getmInstance().showDialogPopupWindow(
                    ActivityManager.getInstance().getCurrentActivity(), "下线通知",
                    "您的账号在另一台手机登录了该账号，如非本人操作，则密码可能已经泄露，建议修改密码",
                    "重新登录",
                    "退出",
                    v -> {
                        againLogIn();
                        PopwindowUtils.getmInstance().dismissPopWindow();

                    }
                    , v -> {
                        PopwindowUtils.getmInstance().dismissPopWindow();
                        Intent intent = new Intent();
                        intent.setAction("com.aite.aitezhongbao.app.activity.login.LoginActivity");
                        AppManager.getInstance().killAllActivity();
                        startActivity(intent);
                    }
            );
        }
    }

    /**
     * 重新登录
     */
    private void againLogIn() {
        if (AppConstant.LOGINUSERNUMBER == null) return;
        if (AppConstant.USERPASSWORD == null) return;
        HttpParams params = new HttpParams();
        params.put("username", AppConstant.LOGINUSERNUMBER);
        params.put("password", AppConstant.USERPASSWORD);
        params.put("client", AppConstant.CLIENT);
        OkGo.<BaseDataEtras<BaseLogInBean>>post(AppConstant.LOGINURL)
                .tag(BaseApp.getContext())
                .params(params)
                .execute(new AbsCallback<BaseDataEtras<BaseLogInBean>>() {
                    @Override
                    public BaseDataEtras<BaseLogInBean> convertResponse(okhttp3.Response response) throws Throwable {
                        LogUtils.d(response.request());
                        return null;
                    }

                    @Override
                    public void onStart(Request<BaseDataEtras<BaseLogInBean>, ? extends Request> request) {
                        LogUtils.d("onStart");

                    }

                    @Override
                    public void onSuccess(Response<BaseDataEtras<BaseLogInBean>> response) {
                        LogUtils.d("onSuccess");

                    }
                });
    }

    protected void showToast(String msg) {
        ToastUtils.showToast(context, msg);
    }

    protected void showToast(String msg, int gravity) {
        ToastUtils.showToast(context, msg, gravity);
    }

    protected void onBackPressed() {
        if (getActivity() != null)
            getActivity().onBackPressed();
        else {
            LogUtils.d("未得到上下文");
            showToast("处理返回键错误 ");
        }
    }

    protected HttpParams initListHttpParams(boolean isusekey, ContentValue... values) {
        HttpParams httpParams = new HttpParams();
        if (isusekey) {
            httpParams.put("key", AppConstant.KEY);
        }
        if (values != null && values.length > 0) {
            for (ContentValue value : values) {
                value.fillHttpParams(httpParams);
            }
        }
        return httpParams;
    }

    protected HttpParams initKeyParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        return params;
    }

    protected boolean isStringEmpty(String s) {
        return s == null || s.isEmpty();
    }
}

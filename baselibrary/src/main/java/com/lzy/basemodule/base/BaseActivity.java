package com.lzy.basemodule.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.activitylife.ActivityManager;
import com.lzy.basemodule.bean.BaseDataEtras;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.R;
import com.lzy.basemodule.base.androidlife.AppManager;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.BasePresenterImpl;
import com.lzy.basemodule.mvp.BaseView;
import com.lzy.basemodule.util.KeyBoardUtils;
import com.lzy.basemodule.util.SharePreferencesHelper;
import com.lzy.basemodule.util.SystemUtil;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.lzy.basemodule.util.toast.ToastTopUtils;
import com.lzy.basemodule.view.StatusBarUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: liziyang
 * @datetime: 2019-11-23
 * @desc:
 */

public abstract class BaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends mBaseActivity<V, T> implements View.OnClickListener, BaseView {

    protected static final String TAG = "TODO";


    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void initDatas();

    protected abstract void initResume();

    protected abstract void initReStart();


    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    private Bundle savedInstanceState;


    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
//        if (isCanSavedInstanceState)
        if (savedInstanceState != null && !savedInstanceState.isEmpty())
            this.savedInstanceState = savedInstanceState;
        try {
            if (isUseMvp()) {
                //mvp
                mPresenter = getInstance(this, 1);
                mPresenter.attachView((V) this);
            }
        } catch (Exception e) {
            LogUtils.e("mvp（可能未使用mvp格式）" + "/n" + e.getClass() + e);
        }
        context = this;
        unbinder = ButterKnife.bind((Activity) context);
        AppManager.getInstance().addActivity((Activity) context);
//        StatusBarUtils.setTransparent(context);
        StatusBarUtils.setColor(context, getResources().getColor(R.color.white));
        initView();
        initDatas();

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected boolean isUseMvp() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initResume();
        if (!SystemUtil.isNetworkConnected()) {
//            ToastUtils.showToast(context, "请检查网络设置");
            LogUtils.d("当前无网络");
        }


    }

    protected HttpParams initKeyParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        return params;
    }


    public void initBottomBtn(String txt, View.OnClickListener listener) {
        try {
            Button bottom_btn = this.findViewById(R.id.bottom_btn);
            bottom_btn.setOnClickListener(this);
            bottom_btn.setText(txt);
            bottom_btn.setOnClickListener(listener);
        } catch (Exception e) {
            LogUtils.e("initBottomBtn-fail" + e);
        }

    }

    public void initBottomBtn(String txt, float aplah, View.OnClickListener listener) {
        try {
            Button bottom_btn = this.findViewById(R.id.bottom_btn);
            bottom_btn.setOnClickListener(this);
            bottom_btn.setText(txt);
            bottom_btn.setOnClickListener(listener);
            bottom_btn.setAlpha(aplah);
        } catch (Exception e) {
            LogUtils.e("initBottomBtn-fail" + e);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(context, "PERMISSION_OK");
        if (!sharePreferencesHelper.contain("PERMISSION_OK") && !Boolean.valueOf(String.valueOf(sharePreferencesHelper.getSharePreference("PERMISSION_OK", false))))
            applypermission();
    }

    @Override
    protected void onSmartLoadMore() {


    }

    protected boolean isFileHas(File file) {
        return file.exists();
    }

    @Override
    protected void onSmartRefresh() {
        if (!SystemUtil.isNetworkConnected())
            initNodata();
        mCurrentPage = 1;


    }


    protected String getUrlKey(String url, String key) {
        return Uri.parse(url).getQueryParameter(key);
    }

    protected int getScreenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    protected int getScreenHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    protected void showTopToasts(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastTopUtils toastUtil = new ToastTopUtils();
                toastUtil.Short(context, msg).setGravity(Gravity.TOP).setToastBackground(Color.WHITE, R.drawable.toast_radius).show();


            }
        });
    }

    protected void showToast(final String msg, final int gravity) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastTopUtils toastUtil = new ToastTopUtils();
                toastUtil.Short(context, msg).setGravity(gravity).show();


            }
        });
    }

    protected void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(context, msg);

            }
        });
    }

    protected String getBaseUrl() {
        return getResources().getString(R.string.baseUrl);
    }

    protected void killThisActvity() {
        AppManager.getInstance().killActivity((Activity) context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showNormal() {

    }

    @Override
    protected void applyperssionbody() {
    }

    @Override
    public void showLoading() {
        PopwindowUtils.getmInstance().showloaddingPopupWindow(this);
    }

    @Override
    public void dimissLoading() {
        PopwindowUtils.getmInstance().dismissPopWindow();
    }

    @Override
    public void showError(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(context, msg);
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
                LogUtils.e("服务器返回错误信息-----------" + msg);
            }
        });
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

    /**
     * 判断是否有虚拟系统键
     *
     * @param activity
     * @return
     */
    @SuppressLint("NewApi")
    protected boolean checkDeviceHasNavigationBar(Context activity) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    /**
     * 得到系统键的高度
     *
     * @param activity
     * @return
     */
    protected int getNavigationBarHeight(Context activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    protected void hideSoftWare() {
        KeyBoardUtils.hideKeyboard(getWindow().getDecorView());
    }
}

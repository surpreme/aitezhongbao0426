package com.example.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.base.GBaseActivity;
import com.example.base.GBasePresenter;
import com.example.glibrary.R;
import com.example.im.IMUtils;
import com.lzy.basemodule.base.BaseApp;
import com.lzy.basemodule.logcat.LogUtils;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-15
 * @desc:
 */
public class BaseGIMActivity<T extends GBasePresenter> extends GBaseActivity<T> {
    private static final String TAG = BaseGIMActivity.class.getSimpleName();

    // 监听做成静态可以让每个子类重写时都注册相同的一份。
    private static IMEventListener mIMEventListener = new IMEventListener() {
        @Override
        public void onForceOffline() {
            ToastUtil.toastLongMessage("您的帐号已在其它终端登录");
            IMUtils.logoutIM();
        }
    };

    @Override
    public int setLayoutId() {
        return 0;
    }

    @Override
    public void initOthers() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_color));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigation_bar_color));
            int vis = getWindow().getDecorView().getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            getWindow().getDecorView().setSystemUiVisibility(vis);
        }

        TUIKit.addIMEventListener(mIMEventListener);


    }

    @Override
    public void onError(Throwable throwable) {

    }
}

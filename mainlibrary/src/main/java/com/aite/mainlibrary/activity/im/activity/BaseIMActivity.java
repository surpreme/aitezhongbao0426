package com.aite.mainlibrary.activity.im.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.im.util.IMAccountManager;
import com.lzy.basemodule.base.BaseApp;
import com.lzy.basemodule.logcat.LogUtils;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

/**
 * 登录状态的Activity都要集成该类，来完成被踢下线等监听处理。
 */
public class BaseIMActivity extends Activity {

    private static final String TAG = BaseIMActivity.class.getSimpleName();

    // 监听做成静态可以让每个子类重写时都注册相同的一份。
    private static IMEventListener mIMEventListener = new IMEventListener() {
        @Override
        public void onForceOffline() {
            ToastUtil.toastLongMessage("您的帐号已在其它终端登录");
            logout(BaseApp.getContext(), false);
        }
    };

    public static void logout(Context context, boolean autoLogin) {
        LogUtils.i(TAG, "logout账号退出");
//        SharedPreferences shareInfo = context.getSharedPreferences(PrimaryConstants.USERINFO, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = shareInfo.edit();
//        editor.putBoolean(PrimaryConstants.AUTO_LOGIN, autoLogin);
//        editor.commit();

//        IMAccountManager.getInstance().requestLoginAccount();
//        Intent intent = new Intent(context, LoginForDevActivity.class);
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(PrimaryConstants.LOGOUT, true);
//        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

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
    protected void onStart() {
        LogUtils.i(TAG, "onStart");
        super.onStart();
//        SharedPreferences shareInfo = getSharedPreferences(PrimaryConstants.USERINFO, Context.MODE_PRIVATE);
//        boolean login = shareInfo.getBoolean(PrimaryConstants.AUTO_LOGIN, false);
//        if (!login) {
//            BaseIMActivity.logout(BaseApp.getContext(), false);
//        }
    }

    @Override
    protected void onResume() {
        LogUtils.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtils.i(TAG, "onDestroy");
        super.onDestroy();
    }

}

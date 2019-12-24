package com.aite.aitezhongbao.activity.login;


import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.aite.aitezhongbao.MainActivity;
import com.aite.aitezhongbao.R;
import com.aite.aitezhongbao.activity.findkey.FindKeyActivity;
import com.aite.aitezhongbao.activity.newuser.NewUserActivity;
import com.aite.aitezhongbao.activity.newusermsg.NewusermsgActivity;
import com.aite.aitezhongbao.activity.usertype.UserTypeActivity;
import com.aite.aitezhongbao.bean.LogInBean;
import com.aite.alipaylibrary.wechat.wxapi.WechatUtils;
import com.aite.mainlibrary.Mainbean.ThreeSuccessCodeBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.activity.allnews.newsinformation.NewsInformationActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.PopwindowUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.util.SystemUtil;
import com.lzy.okgo.model.HttpParams;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends BaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.new_user_tv)
    TextView new_user_tv;
    @BindView(R.id.find_key_tv)
    TextView find_key_tv;
    @BindView(R.id.number_get_edit)
    TextInputEditText numberGetEdit;
    @BindView(R.id.key_get_edit)
    TextInputEditText keyGetEdit;
    @BindView(R.id.log_wechat)
    ImageView logWechat;
    private String UNIONID = "";
    private String NICKNAME = "";
    private String HEADIMGURL = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.login_layout;
    }

    //WXEntryActivity2
    @OnClick({R.id.login_btn, R.id.new_user_tv, R.id.find_key_tv, R.id.log_wechat})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (isEditTextEmpty(numberGetEdit) || isEditTextEmpty(keyGetEdit)) {
                    showToast("请检查账号密码");
                    return;
                }
                if (!SystemUtil.isNetworkConnected()) {
                    showToast("请检查网络", Gravity.TOP);
                    return;
                }
                mPresenter.login(isStringEmpty(UNIONID) ? initParams() : initwechatbinderuserParams(UNIONID));
                showLoading();
                break;
            case R.id.new_user_tv:
                startActivity(NewUserActivity.class);
                break;
            case R.id.find_key_tv:
                startActivity(FindKeyActivity.class);
                break;
            case R.id.log_wechat:
//                startActivity(WXEntryActivity2.class);
                weiXinLogin();
                break;

        }
    }

    public void weiXinLogin() {
        WechatUtils.authorization(SHARE_MEDIA.WEIXIN, this, new WechatUtils.WechatInformationInterface() {
            @Override
            public void ongetMsg(Map<String, String> map) {
                mPresenter.weChatLogin(initWeChatLoginParams(map.get("unionid")));
                UNIONID = map.get("unionid");
                NICKNAME = map.get("name");
                HEADIMGURL = map.get("iconurl");
            }
        });
    }

    private HttpParams initwechatbinderuserParams(String unionid) {
        HttpParams params = new HttpParams();
        params.put("username", getEditString(numberGetEdit));
        params.put("password", getEditString(keyGetEdit));
        params.put("client", AppConstant.CLIENT);
        params.put("unionid", unionid);
        params.put("type", 1);
        return params;
    }

    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("username", getEditString(numberGetEdit));
        params.put("password", getEditString(keyGetEdit));
        params.put("client", AppConstant.CLIENT);
        return params;
    }

    private HttpParams initWecahtnoewloginParams() {
        HttpParams params = new HttpParams();
        params.put("unionid", UNIONID);
        params.put("nickname", NICKNAME);
        params.put("headimgurl", HEADIMGURL);
        params.put("client", AppConstant.CLIENT);
        return params;
    }

    private HttpParams initWeChatLoginParams(String unionid) {
        HttpParams params = new HttpParams();
        params.put("unionid", unionid);
        return params;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void logInSuccess(Object msg) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
        AppConstant.KEY = ((LogInBean) msg).getDatas().getKey();
        AppConstant.USERNAME = ((LogInBean) msg).getDatas().getUsername();
        AppConstant.FRIEND_VALID = ((LogInBean) msg).getDatas().getConfig().getFriend_valid();
        AppConstant.MEMBER_ID = ((LogInBean) msg).getDatas().getConfig().getMember_id();
        dimissLoading();
        startActivity(MainActivity.class);
        finish();
//            }
//        });


    }

    @Override
    public void showError(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dimissLoading();
            }
        });
        super.showError(msg);

    }

    @Override
    public void logInFail(String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dimissLoading();
                showTopToasts(error);
            }
        });


    }

    @Override
    public void logInNeedMoreMsg(String number, String key) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dimissLoading();
                if (number.equals("2"))
                    startActivity(NewusermsgActivity.class, "key", key);
                if (number.equals("3"))
                    startActivity(UserTypeActivity.class, "key", key);
            }
        });


    }

    @Override
    public void weChatLoginSuccess(Object msg) {
        ThreeSuccessCodeBean threeSuccessCodeBean = (ThreeSuccessCodeBean) msg;
        if (threeSuccessCodeBean.getResult().equals("0")) {
            PopwindowUtils.getmInstance().showwechatPopupWindow(context, new PopwindowUtils.OnWechatChoiceBackInterface() {
                @Override
                public void onGetWay(String way) {
                    if (way.equals("BINDEROLDER")) {
                        login_btn.setText("绑定并登录");

                    } else if (way.equals("NEWSUSERS")) {
                        mPresenter.weChatnowloginLogin(initWecahtnoewloginParams());
                    }

                }
            });
        } else if (threeSuccessCodeBean.getResult().equals("1")) {
            AppConstant.KEY = threeSuccessCodeBean.getKey();
            dimissLoading();
            startActivity(MainActivity.class);
            finish();
        }

    }

    @Override
    public void weChatnowloginSuccess(Object msg) {
        ThreeSuccessCodeBean threeSuccessCodeBean = (ThreeSuccessCodeBean) msg;
        if (threeSuccessCodeBean != null) {
            startActivity(NewusermsgActivity.class, "key", threeSuccessCodeBean.getKey());
        }

    }


}

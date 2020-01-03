package com.aite.aitezhongbao.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aite.a.utils.SPUtils;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.activitylife.ActivityManager;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-26
 * @desc:
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = WXAPIFactory.createWXAPI(this, BaseConstant.WECAHT.APP_ID);
        api.handleIntent(getIntent(), this);
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq arg0) {

    }

    /**
     * 0 成功 -1 配置错误 -2用户取消
     * 0	成功	展示成功页面
     * -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     * -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
     *
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            View.OnClickListener dismissPopOnClickListener = v -> {
                PopwindowUtils.getmInstance().dismissPopWindow();
            };
            if (resp.errCode == 0) {
                // TODO: 2019/7/8 0008  支付成功 微信通知
//                Toast.makeText(this, "微信-支付成功", Toast.LENGTH_LONG).show();
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(ActivityManager.getInstance().getCurrentActivity(), "支付成功", dismissPopOnClickListener);
            } else if (resp.errCode == -2) {
                // TODO: 2019/7/8 0008    支付用户取消
//                Toast.makeText(this, "微信-用户取消支付", Toast.LENGTH_LONG).show();
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(ActivityManager.getInstance().getCurrentActivity(), "用户取消支付", dismissPopOnClickListener);
            } else {
                // TODO: 2019/7/8 0008    支付失败
//                Toast.makeText(this, "微信-支付失败", Toast.LENGTH_LONG).show();
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(ActivityManager.getInstance().getCurrentActivity(), "支付失败", dismissPopOnClickListener);
            }
            finish();

        }
    }

}

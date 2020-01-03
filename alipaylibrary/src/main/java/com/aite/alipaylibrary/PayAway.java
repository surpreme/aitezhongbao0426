package com.aite.alipaylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.aite.alipaylibrary.alppay.PayResult;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.alipay.sdk.app.PayTask;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.activitylife.ActivityManager;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.logcat.LogUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import butterknife.internal.Constants;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-26
 * @desc:
 */
public class PayAway {
    private volatile static Toast toast;

    /**
     * 支付宝支付
     *
     * @param order_id
     * @param activity 钓起来支付的activity
     * @param cls      支付完成跳转的页面
     */
    public static void Alipay(final String order_id, final Activity activity, final Class cls) {
        new Thread(() -> {
            PayTask alipay = new PayTask(activity);
            final Map<String, String> result = alipay.payV2(order_id, true);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    paraseResult(result, activity, cls);
                }
            });
        }).start();
    }

    /**
     * 微信支付
     * **
     * *  返回字段	类型	说明 后台返回
     * *  datas.payinfo	字符串	支付字符串 支付宝
     * *  datas.appid	字符串	appid 微信
     * *  datas.nonce_str	字符串	随机字符串 微信
     * *  datas.prepay_id	字符串	预支付交易会话id 微信
     * *  datas.startTimeStamp	字符串	时间戳(毫秒) 微信
     * *  datas.sign	字符串	签名 微信
     * *  datas.trade_type	字符串	请求方式 微信
     * *  datas.wxkey	字符串	微信key 微信
     * *  error	字符串	错误信息 code=200 正确 其他编码错误
     * *  mch_id 商户号
     * **
     *
     *
     *
     * <p> 微信平台需要app提供的参数
     * <p>
     * <p>
     * 字段名	变量名	类型	必填	示例值	描述
     * 应用ID	appid	String(32)	是	wx8888888888888888	微信开放平台审核通过的应用APPID
     * 商户号	partnerid	String(32)	是	1900000109	微信支付分配的商户号
     * 预支付交易会话ID	prepayid	String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
     * 扩展字段	package	String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
     * 随机字符串	noncestr	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
     * 时间戳	timestamp	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
     * 签名	sign	String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法注意：签名方式一定要与统一下单接口使用的一致
     *
     * @param wechatPayBean 后台返回
     */

    public static void WchatPay(WeChatPayBackBean wechatPayBean, Context mContext) {
        Map<String, String> params = new HashMap<>();
        params.put("appid", wechatPayBean.getAppid());
        params.put("partnerid", wechatPayBean.getMch_id());
        params.put("prepayid", wechatPayBean.getPrepay_id());
        params.put("package", "Sign=WXPay");
        params.put("noncestr", wechatPayBean.getNonce_str());
        params.put("timestamp", String.format(Locale.CANADA, "%d", System.currentTimeMillis() / 1000));
        String sign2 = "";
        try {
            sign2 = PayUtil.Sign(params, wechatPayBean.getWxkey());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, null);
        // 将该app注册到微信
        msgApi.registerApp(wechatPayBean.getAppid());
        PayReq request = new PayReq();
        request.appId = wechatPayBean.getAppid();
        request.partnerId = wechatPayBean.getMch_id();
        request.prepayId = wechatPayBean.getPrepay_id();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = wechatPayBean.getNonce_str();
        request.timeStamp = String.format(Locale.CANADA, "%d", System.currentTimeMillis() / 1000);
        request.sign = sign2;
        boolean a = request.checkArgs();
        msgApi.sendReq(request);
    }

    private static void paraseResult(Map<String, String> map, Activity activity, Class cls) {
        PayResult payResult = new PayResult(map);
        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        @SuppressWarnings("unused")
        String resultInfo = payResult.getResult();
        String resultStatus = payResult.getResultStatus();
        View.OnClickListener dismissPopOnClickListener = v -> {
            PopwindowUtils.getmInstance().dismissPopWindow();
        };
        if (toast == null)
            toast = Toast.makeText(activity, "", Toast.LENGTH_SHORT);
        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        if (TextUtils.equals(resultStatus, "9000")) {
            toast.setText("支付成功");
            toast.show();
            PopwindowUtils.getmInstance().showdiadlogPopupWindow(ActivityManager.getInstance().getCurrentActivity(), "支付宝-支付成功", dismissPopOnClickListener);
            Intent intent2 = new Intent(activity, cls);
            activity.startActivity(intent2);
            activity.finish();
        } else {
            // 判断resultStatus 为非“9000”则代表可能支付失败
            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
            if (TextUtils.equals(resultStatus, "8000")) {
                toast.setText("等待支付结果确认,最终交易是否成功以界面显示为准");
                toast.show();
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(ActivityManager.getInstance().getCurrentActivity(), "支付宝-等待支付结果确认,最终交易是否成功以界面显示为准", dismissPopOnClickListener);

            } else {
                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                toast.setText("支付失败");
                toast.show();
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(ActivityManager.getInstance().getCurrentActivity(), "支付宝-支付失败", dismissPopOnClickListener);

            }
        }
    }

}
//        // TODO: 2019/7/11 0011 注意 : 一定要加 类型
////        SPUtils.put(mContext, "PayType", Constants.payType3);
//
//
//        IWXAPI api = WXAPIFactory.createWXAPI(mContext, null);
//        api.registerApp(wechatPayBean.getAppid());
//        PayReq req = new PayReq();
//        req.appId = wechatPayBean.getAppid();//你的微信appid
//        req.partnerId = wechatPayBean.getMch_id();//商户号
//        req.prepayId = wechatPayBean.getPrepay_id();//预支付交易会话ID
//        req.nonceStr = wechatPayBean.getNonce_str();//随机字符串
//        req.timeStamp = wechatPayBean.getStartTimeStamp().substring(0,wechatPayBean.getStartTimeStamp().length() - 3);//时间戳
////        req.timeStamp = "1577345344";//时间戳
//        req.packageValue = "Sign=WXPay";
//        req.sign = wechatPayBean.getSign();//签名
//        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//        api.sendReq(req);
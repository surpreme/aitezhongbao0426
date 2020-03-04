package com.aite.mainlibrary.activity.allmain.buydaytogether;


import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.MainActivity;
import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.BuySencondBean;
import com.aite.mainlibrary.Mainbean.PayHelpServiceSuccessBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BuyDayTogetherActivity extends BaseActivity<BuyDayTogetherContract.View, BuyDayTogetherPresenter> implements BuyDayTogetherContract.View {

    @BindView(R2.id.iphone_number_edit)
    EditText iphoneNumberEdit;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.book_price)
    TextView bookPrice;
    @BindView(R2.id.bottom_btn)
    Button bottomBtn;
    @BindView(R2.id.icon_img)
    ImageView iconImg;
    @BindView(R2.id.title_tv)
    TextView titleTv;
    @BindView(R2.id.price_tv)
    TextView priceTv;
    //订单号
    private String ORDER_ID = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.buy_day_together;
    }

    @Override
    protected void initView() {
        initToolbar("购买");
        initBottomBtn("购买", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(PaySettingActivity.class, "goods_id", !isStringEmpty(getIntent().getStringExtra("goods_id")) ? getIntent().getStringExtra("goods_id") : "");
                if (isEditTextEmpty(iphoneNumberEdit)) {
                    showToast("请输入手机号码", Gravity.TOP);
                    return;
                }
                mPresenter.buyService(initPostAllParams());
            }
        });
    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("goods_id",
                !isStringEmpty(getIntent().getStringExtra("goods_id")) ? getIntent().getStringExtra("goods_id") : "");
        httpParams.put("quantity", 1);
        return httpParams;
    }

    private HttpParams initPostAllParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("buyer_phone", getEditString(iphoneNumberEdit));
        httpParams.put("quantity", 1);
        httpParams.put("goods_id", !isStringEmpty(getIntent().getStringExtra("goods_id")) ? getIntent().getStringExtra("goods_id") : "");
        return httpParams;
    }

    /**
     * key	get	字符串	必须			会员登录key
     * pay_sn	get	字符串	必须			订单交易编号
     *
     * @return
     */
    private HttpParams initCollectParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        if (!isStringEmpty(ORDER_ID))
            params.put("order_id", ORDER_ID);
        return params;
    }

    @Override
    protected void initDatas() {
        mPresenter.getInformation(initParams());
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onBuyThingSuccesss(Object msg) {
        if (((PayHelpServiceSuccessBean) msg).getOrder_id() != null) {
            ORDER_ID = isStringEmpty(((PayHelpServiceSuccessBean) msg).getOrder_id()) ? "" : ((PayHelpServiceSuccessBean) msg).getOrder_id();
            mPresenter.getPayList(initKeyParams());
        }
//            startActivity(SureUnFactShopBookActivity.class, "order_id", isStringEmpty(((PayHelpServiceSuccessBean) msg).getOrder_id()) ? "" : ((PayHelpServiceSuccessBean) msg).getOrder_id());
        else
            showToast("订单生成失败", Gravity.TOP);
    }

    /**
     * 返回字段	类型	说明
     * goods_info.goods_id	整型	商品id
     * goods_info.goods_name	字符串	商品名称
     * goods_info.goods_promotion_price	字符串	商品名单价
     * goods_info.goods_image_url	字符串	商品图片
     * goods_info.goods_addtime	字符串	发布时间
     * goods_info.quantity	字符串	购买数量
     * goods_info.goods_total	字符串	应付总额
     * error	字符串	错误信息 error_code=0 正确 其他编码错误
     *
     * @param msg
     */
    @Override
    public void onGetInformationSuceess(Object msg) {
        Glide.with(context).load(((BuySencondBean) msg).getGoods_info().getGoods_image_url()).into(iconImg);
        titleTv.setText(((BuySencondBean) msg).getGoods_info().getGoods_name());
        priceTv.setText(String.format("￥%s", ((BuySencondBean) msg).getGoods_info().getGoods_price()));
        bookPrice.setText(String.format("￥%s", ((BuySencondBean) msg).getGoods_info().getGoods_total()));
    }

    private List<PayListBean.DatasBean> paylist = new ArrayList<>();

    /**
     * 1  支付宝 alipay
     * 3  微信   app_wxpay
     * 99 钱包
     *
     * @param msg
     */
    @Override
    public void onPayListSuccess(Object msg) {
        paylist = ((PayListBean) msg).getDatas();
        if (paylist == null || paylist.isEmpty()) return;
        PayRadioGroupRecyAdapter payRadioGroupRecyAdapter = new PayRadioGroupRecyAdapter(context, paylist);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        payRadioGroupRecyAdapter.setClickInterface(postion -> {
            LogUtils.d(postion);
            PopwindowUtils.getmInstance().dismissPopWindow();
            if (postion == 5) {
                mPresenter.PayCollect(initCollectParams());
            } else {
                if (postion == 1) {
                    mPresenter.PayThreeElse(initListHttpParams(
                            true,
                            new ContentValue("order_id", isStringEmpty(ORDER_ID) ? "" : ORDER_ID),
                            new ContentValue("payment_code", "alipay")), "alipay");
                } else if (postion == 3) {
                    mPresenter.PayThreeElse(initListHttpParams(
                            true,
                            new ContentValue("order_id", isStringEmpty(ORDER_ID) ? "" : ORDER_ID),
                            new ContentValue("payment_code", "app_wxpay")), "app_wxpay");
                }


            }

        });
        PopwindowUtils.getmInstance().showPayRecyPopupWindow(context, Gravity.BOTTOM, payRadioGroupRecyAdapter, manager);


    }

    @Override
    public void onPayCollectSuccess(Object msg) {
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("支付成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg(), Gravity.TOP);
            onBackPressed();
        } else {
            showToast("支付失败", Gravity.TOP);
        }

    }

    /**
     * [
     * {
     * "payment_id": "1",
     * "payment_code": "alipay",
     * "payment_name": "支付宝",
     * "payment_image": "http://zhongbyi.aitecc.com/mall/templates/default/images/payment/alipay_logo.gif"
     * },
     * {
     * "payment_id": "3",
     * "payment_code": "app_wxpay",
     * "payment_name": "微信外支付",
     * "payment_image": "http://zhongbyi.aitecc.com/mall/templates/default/images/payment/app_wxpay_logo.gif"
     * },
     * {
     * "payment_id": 99,
     * "payment_code": "predeposit",
     * "payment_name": "钱包",
     * "payment_image": "http://zhongbyi.aitecc.com/mall/templates/default/images/payment/predeposit_logo.gif"
     * }
     * ]
     *
     * @param msg
     */
    @Override
    public void onPayThreeElseSuccess(Object msg, String payAway) {
        if (msg != null) {
            if (payAway.equals("alipay")) {
                AlipayOrderIdBean alipayOrderIdBean = (AlipayOrderIdBean) msg;
                LogUtils.d(alipayOrderIdBean.getPayinfo());
                PayAway.Alipay(alipayOrderIdBean.getPayinfo(), this, MainActivity.class);
            } else if (payAway.equals("app_wxpay")) {
                WeChatPayBackBean weChatPayBackBean = (WeChatPayBackBean) msg;
                PayAway.WchatPay(weChatPayBackBean, this);
            }
        }
    }

}

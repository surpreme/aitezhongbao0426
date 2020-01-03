package com.aite.mainlibrary.activity.allshopcard.sureunfactshopbook;


import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.a.activity.MainActivity;
import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.Mainbean.UnFactSureBookBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.CallUtils;
import com.lzy.basemodule.util.TextEmptyUtils;
import com.lzy.basemodule.util.TimeUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SureUnFactShopBookActivity extends BaseActivity<SureUnFactShopBookContract.View, SureUnFactShopBookPresenter> implements SureUnFactShopBookContract.View {


    @BindView(R2.id.book_state_tv)
    TextView bookStateTv;
    @BindView(R2.id.book_information_tv)
    TextView bookInformationTv;
    @BindView(R2.id.icon_iv)
    ImageView iconIv;
    @BindView(R2.id.title_tv)
    TextView titleTv;
    @BindView(R2.id.price_tv)
    TextView priceTv;
    @BindView(R2.id.hide_book_go_tv)
    TextView hideBookGoTv;
    @BindView(R2.id.all_price_tv)
    TextView allPriceTv;
    @BindView(R2.id.book_number_tv)
    TextView bookNumberTv;
    @BindView(R2.id.pay_number_tv)
    TextView payNumberTv;
    @BindView(R2.id.time_create_tv)
    TextView timeCreateTv;
    @BindView(R2.id.message_ll)
    LinearLayout messageLl;
    @BindView(R2.id.call_ll)
    LinearLayout callLl;
    private String STOTERPHONENUMBER = "";
    //订单号
    private String ORDER_ID = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.book_informationlayout;
    }

    @Override
    protected void initView() {
        initToolbar("订单详情");
        initRecy();

    }

    @Override
    protected void initDatas() {
        mPresenter.getInformation(initParams());

    }

    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("order_id", getIntent().getStringExtra("order_id"));
        return params;
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
    protected void initResume() {

    }


    @Override
    protected void initReStart() {

    }

    @OnClick({R2.id.call_ll, R2.id.hide_book_go_tv})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.call_ll) {
            if (isStringEmpty(STOTERPHONENUMBER)) {
                showToast("店铺暂无联系电话");
            } else
                CallUtils.callkeyboredPhone(STOTERPHONENUMBER, this);
        } else if (v.getId() == R.id.hide_book_go_tv) {

            mPresenter.getPayList(initKeyParams());
        }

    }

    /**
     * page_total	整型	总页数
     * datas->order_info[]	数组	订单记录
     * datas->order_info[]->order_id	字符串	订单id
     * datas->order_info[]->order_amount	字符串	订单价格
     * datas->order_info[]->order_shipping_fee	字符串	订单配送费
     * datas->order_info[]->order_state_text	字符串	订单状态文字
     * datas->order_info[]->buyer_name	字符串	姓名
     * datas->order_info[]->buyer_phone	字符串	手机号码
     * datas->order_info[]->goods_id	字符串	商品id
     * datas->order_info[]->goods_name	字符串	商品名称
     * datas->order_info[]->goods_price	字符串	商品价格
     * datas->order_info[]->goods_image_url	字符串	商品图片
     * datas->order_info[]->order_sn	字符串	订单编号
     * datas->order_info[]->payment_name	字符串	支付方式名称
     * datas->order_info[]->trade_no	字符串	第三方交易号
     * datas->order_info[]->add_time	字符串	下单时间
     * datas->order_info[]->store_qq	字符串	服务商QQ
     * datas->order_info[]->store_phone	字符串	服务商电话
     * datas->order_info[]->meal_info[]	字符串	早、午餐信息
     * datas->order_info[]->meal_info[]->meal_time	字符串	用餐时间
     * datas->order_info[]->meal_info[]->meal_address	字符串	用餐地址
     * datas->order_info[]->meal_info[]->type_text	字符串	订餐方式文字
     * datas->order_info[]->is_delete	字符串	是否可以删除 1是
     *
     * @param msg
     */
    @Override
    public void onGetInformationSuccess(Object msg) {
        UnFactSureBookBean unFactSureBookBean = (UnFactSureBookBean) msg;
        if (unFactSureBookBean == null) return;
        bookStateTv.setText(unFactSureBookBean.getOrder_info().getOrder_state_text());
        bookInformationTv.setText(String.format("%s - %s", unFactSureBookBean.getOrder_info().getBuyer_name(), unFactSureBookBean.getOrder_info().getBuyer_phone()));
        Glide.with(context).load(unFactSureBookBean.getOrder_info().getGoods_image_url()).into(iconIv);
        titleTv.setText(String.format("%sx%s", unFactSureBookBean.getOrder_info().getGoods_name(), unFactSureBookBean.getOrder_info().getGoods_num()));
        priceTv.setText(String.format("￥ %s", unFactSureBookBean.getOrder_info().getGoods_price()));
        allPriceTv.setText(String.format("实付款：￥%s", unFactSureBookBean.getOrder_info().getGoods_price()));
        bookNumberTv.setText(String.format("订单编号：%s", unFactSureBookBean.getOrder_info().getOrder_sn()));
        if (unFactSureBookBean.getOrder_info().getPayment_name() != null || !isStringEmpty(unFactSureBookBean.getOrder_info().getPayment_name())) {
            payNumberTv.setText(String.format("%s%s", unFactSureBookBean.getOrder_info().getPayment_name(), String.format(" ：%s", unFactSureBookBean.getOrder_info().getOrder_sn())));
        } else {
            payNumberTv.setText("暂无支付信息");
        }
        try {
            STOTERPHONENUMBER = TextEmptyUtils.getText(String.valueOf(unFactSureBookBean.getOrder_info().getStore_phone()));
            timeCreateTv.setText(String.format("创建时间:%s", TimeUtils.stampToDatemm2(Long.parseLong(unFactSureBookBean.getOrder_info().getAdd_time()))));
        } catch (Exception e) {
            LogUtils.d(e);
        }
        ORDER_ID = unFactSureBookBean.getOrder_info().getOrder_id();
        hideBookGoTv.setVisibility(unFactSureBookBean.getOrder_info().getOrder_state_text().equals("待付款") ? View.VISIBLE : View.GONE);
    }

    private List<PayListBean.DatasBean> paylist = new ArrayList<>();

    @Override
    public void onPayListSuccess(Object msg) {
        paylist = ((PayListBean) msg).getDatas();
        if (paylist == null || paylist.isEmpty()) return;
        PayRadioGroupRecyAdapter payRadioGroupRecyAdapter = new PayRadioGroupRecyAdapter(context, paylist);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        payRadioGroupRecyAdapter.setClickInterface(position -> {
            LogUtils.d(position);
            PopwindowUtils.getmInstance().dismissPopWindow();
            PopwindowUtils.getmInstance().dismissPopWindow();
            if (position == 99) {
                mPresenter.PayCollect(initCollectParams());
            } else {
                if (position == 1) {
                    mPresenter.PayThreeElse(initListHttpParams(
                            true,
                            new ContentValue("order_id", isStringEmpty(ORDER_ID) ? "" : ORDER_ID),
                            new ContentValue("payment_code", "alipay")), "alipay");
                } else if (position == 3) {
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

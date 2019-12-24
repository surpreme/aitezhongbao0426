package com.aite.mainlibrary.activity.allshopcard.bookinformation;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.MorningNoonBookInformationBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.util.CallUtils;
import com.lzy.basemodule.util.TextEmptyUtils;
import com.lzy.basemodule.util.TextUtil;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BookinformationActivity extends BaseActivity<BookinformationContract.View, BookinformationPresenter> implements BookinformationContract.View {
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
    @BindView(R2.id.all_price_tv)
    TextView allPriceTv;
    private String ORDER_ID = "";
    private String STOTERPHONENUMBER = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.book_informationlayout;
    }

    @Override
    protected void initView() {
        initToolbar("订单详情");
        ORDER_ID = getIntent().getStringExtra("ORDER_ID");
    }

    @OnClick({R2.id.call_ll})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.call_ll) {
            if (isStringEmpty(STOTERPHONENUMBER)) {
                showToast("店铺暂无联系电话");
            } else
                CallUtils.callkeyboredPhone(STOTERPHONENUMBER, this);
        }

    }

    @Override
    protected void initDatas() {
        mPresenter.GetBookInformation(initParams());

    }

    protected HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("order_id", ORDER_ID);
        return params;
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    /**
     * 返回字段	类型	说明
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onGetBookInformationSuccess(Object msg) {
        MorningNoonBookInformationBean morningNoonBookInformationBean = ((MorningNoonBookInformationBean) msg);
        if (morningNoonBookInformationBean == null) return;
        timeCreateTv.setText(TextEmptyUtils.getText(morningNoonBookInformationBean.getOrder_info().getAdd_time()));
        titleTv.setText(TextEmptyUtils.getText(morningNoonBookInformationBean.getOrder_info().getGoods_name()));
        bookInformationTv.setText(String.format("%s  %s", TextEmptyUtils.getText(morningNoonBookInformationBean.getOrder_info().getBuyer_name()), TextEmptyUtils.getText(morningNoonBookInformationBean.getOrder_info().getBuyer_phone())));
        if (morningNoonBookInformationBean.getOrder_info().getGoods_image_url() != null)
            Glide.with(context).load(morningNoonBookInformationBean.getOrder_info().getGoods_image_url()).into(iconIv);
        if (morningNoonBookInformationBean.getOrder_info().getPayment_name() != null) {
            payNumberTv.setText(String.format("支付方式： %s ", morningNoonBookInformationBean.getOrder_info().getPayment_name()));
            if (morningNoonBookInformationBean.getOrder_info().getTrade_no() != null) {
                payNumberTv.setText(morningNoonBookInformationBean.getOrder_info().getPayment_name() + "编号 " + morningNoonBookInformationBean.getOrder_info().getTrade_no());

            }
        }
        try {
            if (TextUtil.isNumber(morningNoonBookInformationBean.getOrder_info().getGoods_price()) && TextUtil.isNumber(morningNoonBookInformationBean.getOrder_info().getOrder_shipping_fee()))
                allPriceTv.setText("实付款：￥" + haveTwoDouble(Double.valueOf(morningNoonBookInformationBean.getOrder_info().getGoods_price()) + Double.valueOf(morningNoonBookInformationBean.getOrder_info().getOrder_shipping_fee())));
            else allPriceTv.setText("实付款计算出错");
        } catch (Exception e) {

        }
        bookNumberTv.setText("订单编号：" + TextEmptyUtils.getNumber(String.valueOf(morningNoonBookInformationBean.getOrder_info().getTrade_no())));
        bookStateTv.setText(TextEmptyUtils.getText(morningNoonBookInformationBean.getOrder_info().getOrder_state_text()));
        timeCreateTv.setText(TextEmptyUtils.getText(morningNoonBookInformationBean.getOrder_info().getMeal_info().getMeal_time()));
        priceTv.setText("￥ " + TextEmptyUtils.getText(morningNoonBookInformationBean.getOrder_info().getGoods_price()));
        hideBookGoTv.setVisibility(morningNoonBookInformationBean.getOrder_info().getOrder_state_text().equals("未支付") ? View.VISIBLE : View.GONE);
        if (morningNoonBookInformationBean.getOrder_info().getStore_phone() != null) {
            showToast(TextEmptyUtils.getText(String.valueOf(morningNoonBookInformationBean.getOrder_info().getStore_phone())));
            STOTERPHONENUMBER = TextEmptyUtils.getText(String.valueOf(morningNoonBookInformationBean.getOrder_info().getStore_phone()));
        }
    }


}

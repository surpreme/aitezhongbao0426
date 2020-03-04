package com.aite.mainlibrary.activity.allshopcard.bookchoiceeatinformation;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.ChioceEatBookinformationBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.addbookdispute.AddBookDisputeActivity;
import com.aite.mainlibrary.adapter.ChoiceEatsBookInformationRecyAdapter;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.CallUtils;
import com.lzy.basemodule.util.TextEmptyUtils;
import com.lzy.basemodule.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BookChoiceEatinformationActivity extends BaseActivity<BookChoiceEatinformationContract.View, BookChoiceEatinformationPresenter> implements BookChoiceEatinformationContract.View {

    @BindView(R2.id.book_state_tv)
    TextView bookStateTv;
    @BindView(R2.id.book_information_tv)
    TextView bookInformationTv;
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
    @BindView(R2.id.book_dispute_tv)
    TextView bookDisputeTv;
    @BindView(R2.id.book_eat_time_tv)
    TextView bookEatTimeTv;
    @BindView(R2.id.pay_away_tv)
    TextView payAwayTv;
    @BindView(R2.id.delete_order_tv)
    TextView deleteOrderTv;
    @BindView(R2.id.pay_order_tv)
    TextView payOrderTv;
    private String mOrderId = "";
    private ChoiceEatsBookInformationRecyAdapter choiceEatsBookInformationRecyAdapter;
    private List<ChioceEatBookinformationBean.OrderInfoBean.ExtendOrderGoodsBean> extendOrderGoodsBeans = new ArrayList<>();
    private String STOTERPHONENUMBER = "";
    private ChioceEatBookinformationBean chioceEatBookinformationBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.book_choiceeat_informationlayout;
    }

    @Override
    protected void initView() {
        initToolbar("菜品订单详情");
        initRecy();
        mBaserecyclerView.setAdapter(choiceEatsBookInformationRecyAdapter = new ChoiceEatsBookInformationRecyAdapter(context, extendOrderGoodsBeans));
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        choiceEatsBookInformationRecyAdapter.setOnItemRecyClickInterface(position -> {
            startActivityWithCls(AddBookDisputeActivity.class, 0,
                    new ContentValue("order_id", extendOrderGoodsBeans.get(position).getOrder_id()),
                    new ContentValue("goods_id", extendOrderGoodsBeans.get(position).getGoods_id()));
        });
    }

    @Override
    protected void initDatas() {
        mPresenter.getInformations(initListHttpParams(
                true,
                new ContentValue("order_id", getIntent().getStringExtra("order_id"))));

    }

    @OnClick({R2.id.call_ll, R2.id.book_dispute_tv})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.call_ll) {
            if (isStringEmpty(STOTERPHONENUMBER)) {
                showToast("店铺暂无联系电话");
            } else
                CallUtils.callkeyboredPhone(STOTERPHONENUMBER, this);
        } else if (v.getId() == R.id.book_dispute_tv) {

        }

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    /**
     * 返回字段	类型	说明
     * order_info[].order_id	整型	订单ID
     * order_info[].order_sn	字符串	订单号
     * order_info[].pay_sn	字符串	支付单号
     * order_info[].store_id	整型	店铺编号
     * order_info[].store_name	字符串	店铺名称
     * order_info[].buyer_id	整型	买家会员编号
     * order_info[].buyer_name	字符串	买家会员名
     * order_info[].buyer_email	字符串	买家邮箱
     * order_info[].add_time_text	字符串	下单时间
     * order_info[].payment_code	字符串	支付方式
     * order_info[].payment_time	整型	支付时间
     * order_info[].finnshed_time	整型	完成时间
     * order_info[].goods_amount	浮点型	商品总额
     * order_info[].order_amount	浮点型	订单总额
     * order_info[].shipping_fee	整型	运费
     * order_info[].order_state	整型	订单状态
     * order_info[].state_desc	整型	订单状态描述
     * order_info[].payment_name	字符串	支付方式名称
     * order_info[].pay_trade_no	字符串	第三方交易号
     * order_info[].tableware_num	整型	餐具份数
     * order_info[].dining_time	字符串	用餐时间
     * order_info[].extend_order_common[].reciver_name	字符串	收货人姓名
     * order_info[].extend_order_common[].reciver_info[].phone	字符串	收货人联系电话
     * order_info[].extend_order_common[].reciver_info[].address	字符串	收货地址
     * order_info[].extend_order_goods	数组	订单商品列表
     * order_info[].extend_order_goods[].goods_name	字符串	商品名称
     * order_info[].extend_order_goods[].goods_price	字符串	商品价格
     * order_info[].extend_order_goods[].goods_num	字符串	购买数量
     * order_info[].if_cancel	整型	显示取消订单
     * order_info[].if_refund_cancel	整型	显示退款取消订单
     * order_info[].if_complain	整型	显示投诉
     * order_info[].if_receive	整型	显示收货
     * order_info[].if_lock	整型	显示锁定中
     * order_info[].if_deliver	整型	显示物流跟踪
     * order_info[].if_evaluation	整型	显示评价
     * order_info[].if_delete	整型	显示删除订单(放入回收站)
     * order_info[].if_drop	整型	显示永久删除
     * order_info[].if_restore	整型	显示还原订单
     * order_info[].if_address	整型	显示修改地址
     * extend_order_goods[].order_lock	整型	订单锁定类型:1为不用锁定,2为需要锁定,默认为1
     * order_info[].goods_state	整型	物流状态:1为待发货,2为待收货,3为未收到,4为已收货,默认为1
     *
     * @param msg
     */
    @Override
    public void onGetInformationSuccess(Object msg) {
        chioceEatBookinformationBean = (ChioceEatBookinformationBean) msg;
        if (chioceEatBookinformationBean == null) return;
        if (chioceEatBookinformationBean.getOrder_info().getOrder_id() != null)
            mOrderId = chioceEatBookinformationBean.getOrder_info().getOrder_id();
        if (chioceEatBookinformationBean.getOrder_info().getState_desc() != null)
            bookStateTv.setText(TextEmptyUtils.getText(chioceEatBookinformationBean.getOrder_info().getState_desc()));
        if (chioceEatBookinformationBean.getOrder_info().getOrder_amount() != null)
            allPriceTv.setText(String.format("合计：￥%s", chioceEatBookinformationBean.getOrder_info().getOrder_amount()));
        if (chioceEatBookinformationBean.getOrder_info().getOrder_sn() != null)
            bookNumberTv.setText(String.format("订单编号:%s", chioceEatBookinformationBean.getOrder_info().getOrder_sn()));
        if (chioceEatBookinformationBean.getOrder_info().getPayment_name() != null || chioceEatBookinformationBean.getOrder_info().getPay_sn() != null)
            payNumberTv.setText(String.format("第三方交易号:%s",
                    chioceEatBookinformationBean.getOrder_info().getPay_sn()));
        try {
            if (chioceEatBookinformationBean.getOrder_info().getPayment_name() != null)
                payAwayTv.setText(chioceEatBookinformationBean.getOrder_info().getPayment_name());
            timeCreateTv.setText(String.format("支付时间：%s", TimeUtils.stampToDatemm2(Long.parseLong(chioceEatBookinformationBean.getOrder_info().getPayment_time()))));
            bookEatTimeTv.setText(String.format("用餐时间：%s", TimeUtils.stampToDatemm2(Long.parseLong(chioceEatBookinformationBean.getOrder_info().getAdd_time()))));
        } catch (Exception e) {
            LogUtils.d(e);
        }
        if (chioceEatBookinformationBean.getOrder_info().getExtend_order_goods() != null) {
            extendOrderGoodsBeans.addAll(chioceEatBookinformationBean.getOrder_info().getExtend_order_goods());
            choiceEatsBookInformationRecyAdapter.notifyDataSetChanged();
        }
        if (chioceEatBookinformationBean.getOrder_info().getBuyer_name() != null || chioceEatBookinformationBean.getOrder_info().getExtend_order_common().getReciver_info() != null) {
            STOTERPHONENUMBER = TextEmptyUtils.getText(String.valueOf(chioceEatBookinformationBean.getOrder_info().getStore_phone()));
            bookInformationTv.setText(String.format("%s  %s",
                    chioceEatBookinformationBean.getOrder_info().getBuyer_name(),
                    chioceEatBookinformationBean.getOrder_info().getExtend_order_common().getReciver_info().getMob_phone()));
        }
    }

    @Override
    public void onCancleOrderSuccess(Object msg) {
        showToast("取消成功");
        onBackPressed();


    }


    @OnClick({R2.id.delete_order_tv, R2.id.pay_order_tv})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.delete_order_tv) {
            PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "取消后不可恢复", v -> {
                mPresenter.cancleOrder(initListHttpParams(true, new ContentValue("order_id", mOrderId)));
                PopwindowUtils.getmInstance().dismissPopWindow();

            });
        } else if (id == R.id.pay_order_tv) {

        }
    }
}

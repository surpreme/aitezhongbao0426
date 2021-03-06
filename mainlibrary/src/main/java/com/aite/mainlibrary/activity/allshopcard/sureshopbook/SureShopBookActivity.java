package com.aite.mainlibrary.activity.allshopcard.sureshopbook;


import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.a.activity.MainActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.BookInfprmationMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.MoreAdressInormationBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.SureSendMoneyBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.adressfix.AdressFixActivity;
import com.aite.mainlibrary.activity.allshopcard.shopcard.ShopCardActivity;
import com.aite.mainlibrary.adapter.EatShopBookRecyAdapter;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TimeUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import chat.utils.TimeUtil;


public class SureShopBookActivity extends BaseActivity<SureShopBookContract.View, SureShopBookPresenter> implements SureShopBookContract.View {
    @BindView(R2.id.sure_buy_btn)
    Button sureBuyBtn;
    @BindView(R2.id.name_phonenumber_tv)
    TextView namePhonenumberTv;
    @BindView(R2.id.address_tv)
    TextView addressTv;
    @BindView(R2.id.user_information_ll)
    LinearLayout userInformationLl;
    @BindView(R2.id.icon)
    ImageView icon;
    @BindView(R2.id.title_thing_tv)
    TextView title_thing_tv;
    @BindView(R2.id.information_tv)
    TextView informationTv;
    @BindView(R2.id.thing_price_tv)
    TextView thingPriceTv;
    @BindView(R2.id.send_price_tv)
    TextView sendPriceTv;
    @BindView(R2.id.all_price_tv)
    TextView allPriceTv;
    @BindView(R2.id.all)
    TextView all;
    @BindView(R2.id.price_ll)
    LinearLayout priceLl;
    @BindView(R2.id.eat_fix_number_edit)
    EditText eatFixNumberEdit;
    @BindView(R2.id.eat_another_information_edit)
    EditText eatAnotherInformationEdit;
    @BindView(R2.id.card_all_price_tv)
    TextView cardAllPriceTv;
    @BindView(R2.id.card_send_price_tv)
    TextView cardSendPriceTv;
    @BindView(R2.id.year_tv)
    TextView yearTv;
    @BindView(R2.id.oclock_tv)
    TextView oclockTv;
    private BookInfprmationMorningNoonEatBean bookInfprmationMorningNoonEatBean;
    private EatShopBookRecyAdapter eatShopBookRecyAdapter;
    private List<BookInfprmationMorningNoonEatBean.GoodsListBean> goodsListBeans = new ArrayList<>();
    private MoreAdressInormationBean moreAdressInormationBean;
    private String ADDRESS_ID = "";
    private SureSendMoneyBean sureSendMoneyBean;
    //订单号
    private String PAY_SN = "";
    private String EAT_YEAR_TIME = "";
    private String EAT_HOUR_TIME = "";


    @Override
    protected int getLayoutResId() {
        return R.layout.sureshop;
    }

    @Override
    protected void initView() {
        initToolbar("确认订单");
        initRecy();
        EAT_YEAR_TIME = TimeUtils.getCurrentYYMMDD();
        EAT_HOUR_TIME = TimeUtils.getCurrentHHMMSS();
        yearTv.setText(String.format("用餐日期 %s", TimeUtils.getCurrentYYMMDD()));
        oclockTv.setText(String.format("用餐时间 %s", TimeUtils.getCurrentHHMMSS()));
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBaserecyclerView.setAdapter(eatShopBookRecyAdapter = new EatShopBookRecyAdapter(context, goodsListBeans));
        eatShopBookRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {

            }
        });

    }

    @Override
    protected void initDatas() {
        mPresenter.getInformation(initParams());

    }

    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("cart_id", getIntent().getStringExtra("cart_id"));
        params.put("ifcart", "1");
        return params;
    }

    /**
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * key	post	字符串	必须			会员登录key
     * cart_id	post	字符串	必须			购买参数 立即购买：第一个数字为商品编号，第二个数字为购买数量，用竖线分割。例：232|1 购物车购买：第一个数字为购物车编号，第二个数字为购买数量，用竖线分割。多组用半角逗号分割，例：232|1,110|2 232商品购买1个，110商品购买2个
     * ifcart	post	字符串	必须			是否来源于购买车下单 1:是 其他否
     * address_id	post	整型	必须			收货地址编号
     * vat_hash	post	字符串	必须			发票信息hash，第一步接口提供
     * offpay_hash	post	字符串	必须			是否支持货到付款hash，通过更换收货地址接口获得
     * offpay_hash_batch	post	字符串	必须			店铺是否支持货到付款hash，通过更换收货地址接口获得
     * dining_time	post	字符串	可选			用餐时间 格式:YYYY-mm-dd H:i:s
     * tableware_num	post	整型	可选			餐具份数
     * pay_message	post	字符串	可选			买家留言
     *
     * @return
     */
    private HttpParams initPayParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("cart_id", getIntent().getStringExtra("cart_id"));
        params.put("ifcart", "1");
        params.put("tableware_num", isEditTextEmpty(eatFixNumberEdit) ? "1" : getEditString(eatFixNumberEdit));
        params.put("dining_time", String.format("%s %s", EAT_YEAR_TIME, EAT_HOUR_TIME));
        params.put("pay_message", isEditTextEmpty(eatAnotherInformationEdit) ? "1" : getEditString(eatAnotherInformationEdit));
        params.put("address_id", !isStringEmpty(ADDRESS_ID) ? ADDRESS_ID : bookInfprmationMorningNoonEatBean.getAddress_info().getAddress_id());
        params.put("vat_hash", bookInfprmationMorningNoonEatBean.getVat_hash());
        if (sureSendMoneyBean != null && sureSendMoneyBean.getOffpay_hash() != null && sureSendMoneyBean.getOffpay_hash_batch() != null) {
            params.put("offpay_hash", sureSendMoneyBean.getOffpay_hash());
            params.put("offpay_hash_batch", sureSendMoneyBean.getOffpay_hash_batch());
        }

        return params;
    }

    /**
     * address_id	post	整型	必须			地址编号
     * key	post	字符串	必须			会员登陆key
     *
     * @param ADDRESS_ID
     * @return
     */
    private HttpParams initParams(String ADDRESS_ID) {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("address_id", ADDRESS_ID);
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
        if (!isStringEmpty(PAY_SN))
            params.put("pay_sn", PAY_SN);
        return params;
    }

    private HttpParams initParams(String ADDRESS_ID, String FREIGHT_HASH) {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("address_id", ADDRESS_ID);
        params.put("freight_hash", FREIGHT_HASH);
        return params;
    }

    @Override
    protected void initResume() {

    }


    @Override
    protected void initReStart() {

    }

    @OnClick({R2.id.sure_buy_btn, R2.id.user_information_ll, R2.id.year_tv, R2.id.oclock_tv})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sure_buy_btn) {
            mPresenter.MakePay(initPayParams());
        } else if (v.getId() == R.id.user_information_ll) {
            startActivityWithCls(AdressFixActivity.class,
                    BaseConstant.ACTIVITY_RESULT_CODE.REQUEST_CODE_ACTIVITY_RESULT,
                    new ContentValue("JUMP_TYPE", "CHOICE_ADDRESS"));
        } else if (v.getId() == R.id.year_tv) {
            initChoiceTimer((date, v1) -> {
                yearTv.setText(String.format("用餐日期 %s", TimeUtils.stampToDate(date.getTime())));
                EAT_YEAR_TIME = TimeUtils.stampToDate(date.getTime());
            }, "用餐日期", false);
            pvTime.show();
        } else if (v.getId() == R.id.oclock_tv) {
            initChoiceHMTimer((date, v12) -> {
                long time = date.getTime();
                oclockTv.setText(String.format("用餐时间 %s:%s", TimeUtils.timestampToDateStrHH(time), TimeUtils.timestampToDateStrMM(time)));
                EAT_HOUR_TIME = String.format("%s:%s:00", TimeUtils.timestampToDateStrHH(time), TimeUtils.timestampToDateStrMM(time));

            }, "用餐时间", false);
            pvTime.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.ACTIVITY_RESULT_CODE.REQUEST_CODE_ACTIVITY_RESULT && resultCode == RESULT_OK) {
            if (data != null) {
                mPresenter.getAddress(initParams(ADDRESS_ID = data.getStringExtra("address_id")));

            }
        }
    }


    @Override
    public void onGetInformationSuccess(Object msg) {
        bookInfprmationMorningNoonEatBean = ((BookInfprmationMorningNoonEatBean) msg);
        goodsListBeans.addAll(bookInfprmationMorningNoonEatBean.getGoods_list());
        eatShopBookRecyAdapter.notifyDataSetChanged();
        addressTv.setText(bookInfprmationMorningNoonEatBean.getAddress_info().getAddress());
        namePhonenumberTv.setText(String.format("%s%s", bookInfprmationMorningNoonEatBean.getAddress_info().getTrue_name(), bookInfprmationMorningNoonEatBean.getAddress_info().getMob_phone()));
        sendPriceTv.setText(String.format("￥ %s", bookInfprmationMorningNoonEatBean.getTotal_shipping_fee()));
        allPriceTv.setText(String.format("￥ %s", statistics(bookInfprmationMorningNoonEatBean.getTotal_shipping_fee())));
        mPresenter.getAdressMoneny(initParams(ADDRESS_ID = bookInfprmationMorningNoonEatBean.getAddress_info().getAddress_id(), bookInfprmationMorningNoonEatBean.getFreight_hash()));
        cardSendPriceTv.setText(String.format("￥ %s", bookInfprmationMorningNoonEatBean.getTotal_shipping_fee()));
        cardAllPriceTv.setText(String.format("￥ %s", statistics(bookInfprmationMorningNoonEatBean.getTotal_shipping_fee())));
    }

    public String statistics(Double price) {
        for (int i = 0; i < bookInfprmationMorningNoonEatBean.getGoods_list().size(); i++) {
            price += Double.valueOf(bookInfprmationMorningNoonEatBean.getGoods_list().get(i).getGoods_price())
                    * Double.valueOf(bookInfprmationMorningNoonEatBean.getGoods_list().get(i).getGoods_num());
        }
        return haveTwoDouble(price);
    }
/**
 *         bookInfprmationMorningNoonEatBean = ((BookInfprmationMorningNoonEatBean) msg);
 *         namePhonenumberTv.setText(String.format("%s  %s", bookInfprmationMorningNoonEatBean.getOrder_info().getBuyer_name(), bookInfprmationMorningNoonEatBean.getOrder_info().getBuyer_phone()));
 *         addressTv.setText(bookInfprmationMorningNoonEatBean.getOrder_info().getMeal_info().getMeal_address());
 *         allPriceTv.setText(String.format("￥%s", bookInfprmationMorningNoonEatBean.getOrder_info().getOrder_amount()));
 *         sendPriceTv.setText(String.format("￥%s", bookInfprmationMorningNoonEatBean.getOrder_info().getOrder_shipping_fee()));
 *         thingPriceTv.setText(String.format("￥%s", bookInfprmationMorningNoonEatBean.getOrder_info().getGoods_price()));
 *         title_thing_tv.setText(bookInfprmationMorningNoonEatBean.getOrder_info().getGoods_name());
 * //        Glide.with(context).load(bookInfprmationMorningNoonEatBean.getOrder_info().getGoods_image()).into(icon);
 *         informationTv.setText(bookInfprmationMorningNoonEatBean.getOrder_info().getMeal_info().getType_text());
 *         yearTimeTv.setText(new StringBuilder().append("用餐日期： ").append(bookInfprmationMorningNoonEatBean.getOrder_info().getMeal_info().getMeal_time().substring(0, 11)).toString());
 *         oclockTv.setText(new StringBuilder().append("用餐时间：").append(bookInfprmationMorningNoonEatBean.getOrder_info().getMeal_info().getMeal_time().substring(11)).toString());
 *         cardAllPriceTv.setText(String.format("￥%s", bookInfprmationMorningNoonEatBean.getOrder_info().getOrder_amount()));
 *         cardSendPriceTv.setText(String.format("配送费%s", bookInfprmationMorningNoonEatBean.getOrder_info().getOrder_shipping_fee()));
 *         storeNameTv.setText(String.format("店铺名字%s", bookInfprmationMorningNoonEatBean.getOrder_info().getStore_name()));
 */
    /**
     * 返回字段	类型	说明
     * address_info[]	数组	地址列表
     * address_info[].address_id	整型	地址id
     * address_info[].true_name	字符串	收货人姓名
     * address_info[].province_id	整型	省级id
     * address_info[].city_id	整型	市级id
     * address_info[].area_id	整型	区域id
     * address_info[].area_info	字符串	省市区
     * address_info[].address	字符串	详细地址
     * address_info[].mob_phone	字符串	手机号码
     * address_info[].is_default	整型	是否默认
     *
     * @param msg
     */
    @Override
    public void onGetAddressSuccess(Object msg) {
        moreAdressInormationBean = ((MoreAdressInormationBean) msg);
        if (moreAdressInormationBean == null) return;
        addressTv.setText(moreAdressInormationBean.getAddress_info().getIs_default().equals("1") ? "默认地址：" : "地址：" + String.format("%s%s", moreAdressInormationBean.getAddress_info().getArea_info(), moreAdressInormationBean.getAddress_info().getAddress()));
        namePhonenumberTv.setText(String.format("%s  %s", moreAdressInormationBean.getAddress_info().getTrue_name(), moreAdressInormationBean.getAddress_info().getMob_phone()));
        mPresenter.getAdressMoneny(initParams(moreAdressInormationBean.getAddress_info().getAddress_id(), "" + bookInfprmationMorningNoonEatBean.getFreight_hash())
        );
    }

    private List<PayListBean.DatasBean> paylist = new ArrayList<>();

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
                mPresenter.PayFactCollect(initCollectParams());
            } else {
                if (postion == 1) {
                    mPresenter.PayFactThreeElse(initListHttpParams(
                            true,
                            new ContentValue("pay_sn", isStringEmpty(PAY_SN) ? "" : PAY_SN),
                            new ContentValue("payment_code", "alipay")), "alipay");
                } else if (postion == 3) {
                    mPresenter.PayFactThreeElse(initListHttpParams(
                            true,
                            new ContentValue("pay_sn", isStringEmpty(PAY_SN) ? "" : PAY_SN),
                            new ContentValue("payment_code", "app_wxpay")), "app_wxpay");
                }

            }
        });
        PopwindowUtils.getmInstance().showPayRecyPopupWindow(context, Gravity.BOTTOM, payRadioGroupRecyAdapter, manager);
    }

    @Override
    public void onPaySuccess(Object msg) {
        PAY_SN = msg.toString();
        mPresenter.getPayList(initKeyParams());


    }

    @Override
    public void onAdressMonenySuccess(Object msg) {
        sureSendMoneyBean = ((SureSendMoneyBean) msg);

    }

    @Override
    public void onFactPayCollectSuccess(Object msg) {
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("支付成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg(), Gravity.TOP);
            onBackPressed();
        } else {
            showToast("支付失败", Gravity.TOP);
        }


    }

    @Override
    public void onFactPayThreeElseSuccess(Object msg, String payAway) {
        if (msg != null) {
            if (payAway.equals("alipay")) {
                AlipayOrderIdBean alipayOrderIdBean = (AlipayOrderIdBean) msg;
                LogUtils.d(alipayOrderIdBean.getPayinfo());
                PayAway.Alipay(alipayOrderIdBean.getPayinfo(), this, ShopCardActivity.class);
            } else if (payAway.equals("app_wxpay")) {
                WeChatPayBackBean weChatPayBackBean = (WeChatPayBackBean) msg;
                PayAway.WchatPay(weChatPayBackBean, this);
            }
        }
    }
}

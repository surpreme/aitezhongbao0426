package com.aite.mainlibrary.activity.allshopcard.remembershopbook;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.mainlibrary.Mainbean.MoreAdressInormationBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.RememberFoodInformationBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.adressfix.AdressFixActivity;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.google.android.material.textfield.TextInputEditText;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.PopwindowUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.IImgBaseBean;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.KeyBoardUtils;
import com.lzy.basemodule.util.TimeUtils;
import com.lzy.okgo.model.HttpParams;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RememberShopBookActivity extends BaseActivity<RememberShopBookContract.View, RememberShopBookPresenter> implements RememberShopBookContract.View, OnBannerListener {

    @BindView(R2.id.bottom_btn)
    Button bottomBtn;
    @BindView(R2.id.banner)
    Banner banner;
    @BindView(R2.id.eat_away_checkbox)
    CheckBox eatAwayCheckbox;
    @BindView(R2.id.getshop_tv)
    TextView getshopTv;
    @BindView(R2.id.send_user_tv)
    TextView sendUserTv;
    @BindView(R2.id.choice_time_ll)
    LinearLayout choiceTimeLl;
    @BindView(R2.id.time_tv)
    TextView timeTv;
    @BindView(R2.id.oclock_tv)
    TextView oclockTv;
    @BindView(R2.id.choice_oclock_ll)
    LinearLayout choiceOclockLl;
    @BindView(R2.id.phone_edit)
    TextInputEditText phoneEdit;
    @BindView(R2.id.address_tv)
    TextView addressTv;
    @BindView(R2.id.address_ll)
    LinearLayout addressLl;
    private String EATAWAYTYPE = "1";
    private String mDate = "";
    private boolean isYearCheck = false;
    private boolean isOclockCheck = false;

    //banner datalist
    private List<String> list_img = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private String ORDER_ID = "";


    @Override
    protected int getLayoutResId() {
        return R.layout.remembershop;
    }

    @Override
    protected void initView() {
        initToolbar("预约订餐");
        //初始化banner
        initBanner(banner);
        banner.setIndicatorGravity(BannerConfig.RIGHT)
                .setOnBannerListener(this);
        eatAwayCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sendUserTv.setBackground(
                    getResources().getDrawable(isChecked ? R.drawable.round_background_yellow : R.drawable.round_background_white));
            getshopTv.setBackground(
                    getResources().getDrawable(isChecked ? R.drawable.round_background_white : R.drawable.round_background_yellow));

            getshopTv.setTextColor(getResources().getColor(isChecked ? R.color.black : R.color.white));
            sendUserTv.setTextColor(getResources().getColor(isChecked ? R.color.white : R.color.black));
            EATAWAYTYPE = isChecked ? "2" : "1";

        });


    }

    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("goods_id", getIntent().getStringExtra("goods_id"));
        return params;
    }

    private HttpParams initAllInformationParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("goods_id", getIntent().getStringExtra("goods_id"));
        params.put("buyer_phone", getEditString(phoneEdit));
        params.put("type", EATAWAYTYPE);
        params.put("meal_time", isStringEmpty(mDate) ? "" : mDate);
//        params.put("meal_address", "南山科技园福安大厦");

        return params;
    }

    @Override
    protected void initDatas() {
        mPresenter.getFoodInformation(initParams());

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @OnClick({R2.id.bottom_btn, R2.id.choice_time_ll, R2.id.choice_oclock_ll, R2.id.address_ll})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bottom_btn) {
            if (!isOclockCheck && !isYearCheck) {
                showToast("请检查用餐日期");
                return;
            }
            // 产生订单
            mPresenter.postAllInformation(initAllInformationParams());
        } else if (v.getId() == R.id.choice_time_ll) {
            hideSoftWare();
            initChoiceTimer((date, v1) -> {
                long time = date.getTime();
                String tim = TimeUtils.stampToDate(time);
                timeTv.setText(tim);
                LogUtils.e(String.valueOf(TimeUtils.getTime(date.getTime())) + "--" + tim);
                mDate = TimeUtils.stampToDate(time);
                isYearCheck = true;
            }, "选择用餐日期", false);
            pvTime.show();
        } else if (v.getId() == R.id.choice_oclock_ll) {
            hideSoftWare();
            initChoiceHMTimer(new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    long time = date.getTime();
                    String tim = TimeUtils.stampToDate(time);
                    oclockTv.setText(String.format("%s:%s", TimeUtils.timestampToDateStrHH(time), TimeUtils.timestampToDateStrMM(time)));
                    LogUtils.e(String.valueOf(TimeUtils.getTime(date.getTime())) + "--" + tim);
                    mDate = mDate + String.format(" %s:%s:%s", TimeUtils.timestampToDateStrHH(time), TimeUtils.timestampToDateStrMM(time), TimeUtils.timestampToDateStrSS(time));
                    isOclockCheck = true;
                }
            }, "选择用餐时间", false);
            pvTime.show();
        } else if (v.getId() == R.id.address_ll) {
            startActivityWithCls(AdressFixActivity.class, BaseConstant.ACTIVITY_RESULT_CODE.REQUEST_CODE_ACTIVITY_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.ACTIVITY_RESULT_CODE.REQUEST_CODE_ACTIVITY_RESULT && resultCode == RESULT_OK) {
            if (data != null) {
//                ADDRESS_ID = data.getStringExtra("address_id");
                mPresenter.getAddress(initParams(data.getStringExtra("address_id")));

            }
        } else {
            switch (resultCode) {
                case 10010:
//                        Intent intent2 = new Intent(RememberShopBookActivity.this, );
//                        startActivity(intent2);
//                        finish();
                    break;

            }
        }
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

    @Override
    public void onGetFoodInformationSuccess(Object msg) {
        if (((RememberFoodInformationBean) msg).getAdv_list() != null || ((RememberFoodInformationBean) msg).getAdv_list().size() > 0) {
            for (RememberFoodInformationBean.AdvListBean advListBean : ((RememberFoodInformationBean) msg).getAdv_list()) {
                list_img.add(advListBean.getAdv_content().getAdv_pic());
                list_title.add(advListBean.getAdv_title());
                LogUtils.d(advListBean.getAdv_title());

            }
            startBanner(banner, list_img, list_title);
        }

    }

    @Override
    public void postAllInformationSuccess(Object msg) {
        if ((String) msg != null || !isStringEmpty((String) msg)) {
            LogUtils.d(msg.toString());
            showToast("订单生成成功");
            ORDER_ID = msg.toString();
            mPresenter.getPayList(initKeyParams());

            //订单id
//            startActivity(SureShopBookActivity.class, "order_id", msg.toString());

        }


    }

    @Override
    public void onGetAddressSuccess(Object msg) {
        MoreAdressInormationBean moreAdressInormationBean = ((MoreAdressInormationBean) msg);
        if (moreAdressInormationBean == null) return;
        addressTv.setText(String.format("%s%s", moreAdressInormationBean.getAddress_info().getArea_info(), moreAdressInormationBean.getAddress_info().getAddress()));


    }

    private List<PayListBean.DatasBean> paylist = new ArrayList<>();

    @Override
    public void onPayListSuccess(Object msg) {
        paylist = ((PayListBean) msg).getDatas();
        if (paylist == null || paylist.isEmpty()) return;
        PayRadioGroupRecyAdapter payRadioGroupRecyAdapter = new PayRadioGroupRecyAdapter(context, paylist);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        payRadioGroupRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                LogUtils.d(postion);
                PopwindowUtils.getmInstance().dismissPopWindow();
                PopwindowUtils.getmInstance().dismissPopWindow();
                if (postion == 99) {
                    mPresenter.PayCollect(initCollectParams());

                } else if (postion==1) {
                    PayAway.Alipay("fgydfgsxdfgscfgdf", RememberShopBookActivity.this, ChoiceActivity.class);

                }

            }
        });
        PopwindowUtils.getmInstance().showPayRecyPopupWindow(context, Gravity.BOTTOM, payRadioGroupRecyAdapter, manager);


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
    public void onPayCollectSuccess(Object msg) {
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("支付成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg(), Gravity.TOP);
            onBackPressed();
        } else {
            showToast("支付失败", Gravity.TOP);
        }


    }


    @Override
    public void OnBannerClick(int position) {

    }


}

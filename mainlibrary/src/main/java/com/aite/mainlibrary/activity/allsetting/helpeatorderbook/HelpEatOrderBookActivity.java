package com.aite.mainlibrary.activity.allsetting.helpeatorderbook;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.a.activity.MainActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.ChoiceOrderBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.QrBookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allqr.qrcode.QrCodeActivity;
import com.aite.mainlibrary.activity.allshopcard.bookinformation.BookinformationActivity;
import com.aite.mainlibrary.activity.allshopcard.chatoutbook.ChatOutBookActivity;
import com.aite.mainlibrary.adapter.MineHelpEatRecyAdapter;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.aite.mainlibrary.adapter.RadioGroupRecyAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.MVPBaseActivity;
import com.lzy.basemodule.util.FileUtils;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.lzy.basemodule.view.StatusBarUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HelpEatOrderBookActivity extends BaseActivity<HelpEatOrderBookContract.View, HelpEatOrderBookPresenter> implements HelpEatOrderBookContract.View {
    @BindView(R2.id.all_tv)
    TextView allTv;
    @BindView(R2.id.all_iv)
    ImageView allIv;
    @BindView(R2.id.all_ll)
    LinearLayout allLl;
    @BindView(R2.id.unpay_tv)
    TextView unpayTv;
    @BindView(R2.id.unqr_tv)
    TextView unqrTv;
    @BindView(R2.id.overed_tv)
    TextView overedTv;
    @BindView(R2.id.talk_tv)
    TextView talkTv;
    @BindView(R2.id.cancel_tv)
    TextView cancelTv;
    @BindView(R2.id.tablayout)
    LinearLayout tablayout;
    private List<ChoiceOrderBean> choiceOrderBeans = new ArrayList<>();
    private RadioGroupRecyAdapter radioGroupRecyAdapter;
    private MineHelpEatRecyAdapter mineHelpEatRecyAdapter;
    private List<BookMorningNoonEatBean.OrderListBean> orderListBeans = new ArrayList<>();
    private String ORDER_ID = "";
    private int PAGE_TYPE = 0;
    private int STATE = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_service_order_book;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        initToolbar("助餐订单", "核销", v -> {
            startActivity(QrCodeActivity.class, "type", "unfactbook");
        });
        initRecy();
        initImgNodata();
        initSmartLayout(true, false);
        allTv.setTextColor(getColor(R.color.blue));
        String eat_choice_json = FileUtils.getFromAssets(context, "choiceeatpop.json");
        Gson gson = new Gson();
        choiceOrderBeans = gson.fromJson(eat_choice_json, new TypeToken<List<ChoiceOrderBean>>() {
        }.getType());
        if (choiceOrderBeans != null && choiceOrderBeans.get(0) != null)
            choiceOrderBeans.get(0).setChecked(true);
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mineHelpEatRecyAdapter = new MineHelpEatRecyAdapter(context, orderListBeans);
        mBaserecyclerView.setAdapter(mineHelpEatRecyAdapter);
        mineHelpEatRecyAdapter.setClickInterface(postion ->
                startActivity(BookinformationActivity.class,
                        "ORDER_ID", String.valueOf(orderListBeans.get(postion).getOrder_id())));
        mineHelpEatRecyAdapter.setOnInformationInteface(new MineHelpEatRecyAdapter.OnInformationInteface() {
            @Override
            public void pay(int position) {
                ORDER_ID = String.valueOf(orderListBeans.get(position).getOrder_id());
                mPresenter.getPayList(initKeyParams());
            }

            @Override
            public void lookInformation(int position) {
                startActivity(BookinformationActivity.class, "ORDER_ID", String.valueOf(orderListBeans.get(position).getOrder_id()));

            }

            @Override
            public void talkTv(int position) {
                ORDER_ID = String.valueOf(orderListBeans.get(position).getOrder_id());
                Intent intent = new Intent(context, ChatOutBookActivity.class);
                intent.putExtra("order_id", orderListBeans.get(position).getOrder_id());
                context.startActivity(intent);
            }

            @Override
            public void cancelTv(int position) {
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "您确定要取消" + orderListBeans.get(position).getGoods_name() + "吗", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.Cancelinformation(initParams(orderListBeans.get(position).getOrder_id()));
                        PopwindowUtils.getmInstance().dismissPopWindow();
                    }
                });

            }
        });

    }

    private HttpParams initParams(String order_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("order_id", order_id);
        return httpParams;
    }

    //状态 0全部 1待付款 2已付款 3待核销 4评价 5已取消
    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R2.id.all_ll, R2.id.unpay_tv, R2.id.unqr_tv, R2.id.overed_tv, R2.id.talk_tv, R2.id.cancel_tv})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.all_ll) {
            clearSelected();
            allTv.setTextColor(getColor(R.color.blue));
            initChoiceType();
        } else if (v.getId() == R.id.unpay_tv) {
            clearSelected();
            unpayTv.setTextColor(getColor(R.color.blue));
            STATE = 1;
            initDatas();
        } else if (v.getId() == R.id.unqr_tv) {
            clearSelected();
            unqrTv.setTextColor(getColor(R.color.blue));
            STATE = 2;
            initDatas();
        } else if (v.getId() == R.id.overed_tv) {
            clearSelected();
            overedTv.setTextColor(getColor(R.color.blue));
            STATE = 3;
            initDatas();
        } else if (v.getId() == R.id.talk_tv) {
            clearSelected();
            talkTv.setTextColor(getColor(R.color.blue));
            STATE = 4;
            initDatas();
        } else if (v.getId() == R.id.cancel_tv) {
            clearSelected();
            cancelTv.setTextColor(getColor(R.color.blue));
            STATE = 5;
            initDatas();
        }
    }

    private void initChoiceType() {
        radioGroupRecyAdapter = new RadioGroupRecyAdapter(context, choiceOrderBeans);
        radioGroupRecyAdapter.setClickInterface(position -> {
            PAGE_TYPE = position;
            STATE = 0;
            allTv.setText(choiceOrderBeans.get(position).getNasme());
            clearRecyDatas();
            initDatas();
            PopwindowUtils.getmInstance().dismissPopWindow();

        });
        // - StatusBarUtils.getHeight(context)
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        PopwindowUtils.getmInstance().showRecyPopupWindow(
                context,
                getScreenHeight() - tablayout.getBottom() + StatusBarUtils.getHeight(context),
                radioGroupRecyAdapter,
                manager,
                tablayout,
                new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {


                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void clearSelected() {
        allTv.setTextColor(getColor(R.color.black));
        unpayTv.setTextColor(getColor(R.color.black));
        unqrTv.setTextColor(getColor(R.color.black));
        overedTv.setTextColor(getColor(R.color.black));
        talkTv.setTextColor(getColor(R.color.black));
        cancelTv.setTextColor(getColor(R.color.black));
        clearRecyDatas();
        mCurrentPage = 1;

    }

    private void clearRecyDatas() {
        if (orderListBeans != null && !orderListBeans.isEmpty()) {
            orderListBeans.clear();
            mineHelpEatRecyAdapter.notifyDataSetChanged();
        } else if (orderListBeans == null) {
            orderListBeans = new ArrayList<>();
        }
    }


    @Override
    protected void initDatas() {
        mPresenter.getinformation(initEatParams());

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        clearRecyDatas();
        initDatas();
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        initDatas();

    }

    /**
     * * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * * key	get	字符串	必须			会员登录key
     * * curpage	get	字符串	必须	1		当前页码
     * * state	get	整型	必须	0		状态 0全部 1待付款 2待核销 3已完成 4评价 5已取消
     * * page_type	get	整型	必须	1		页面类型 1早餐 2午餐 0全部
     * *
     *
     * @return
     */
    private HttpParams initEatParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        httpParams.put("state", STATE);
        httpParams.put("page_type", PAGE_TYPE);
        return httpParams;
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetinformationSuccess(Object msg) {
        BookMorningNoonEatBean bookMorningNoonEatBean = (BookMorningNoonEatBean) msg;
        if (bookMorningNoonEatBean != null) {
            hasMore = !bookMorningNoonEatBean.getOrder_list().isEmpty();
            if (!hasMore && mCurrentPage == 1) {
                showImgNoData();
            } else {
                stopImgNodata();
            }
            if (bookMorningNoonEatBean.getOrder_list() != null && !bookMorningNoonEatBean.getOrder_list().isEmpty()) {
                orderListBeans.addAll(bookMorningNoonEatBean.getOrder_list());
                mineHelpEatRecyAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onGetStartEatQrinformationSuccess(Object msg, int position) {
        if (((QrBookMorningNoonEatBean) msg).getQrcodeimg() != null) {
            Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(
                    orderListBeans.get(position).getVr_code(),
                    BGAQRCodeUtil.dp2px(context, 150),
                    Color.BLACK, Color.WHITE,
                    BitmapFactory.decodeResource(context.getResources(), com.lzy.basemodule.R.drawable.logo));
            PopwindowUtils.getmInstance().showQrPopupWindow(context, bitmap, orderListBeans.get(position).getGoods_name() + "   " + orderListBeans.get(position).getAdd_time());

        }
//        PopwindowUtils.getmInstance().showImgPopupWindow(context, ((QrBookMorningNoonEatBean) msg).getQrcodeimg(), orderListBeans.get(postion).getGoods_name() + "   " + orderListBeans.get(postion).getAdd_time());

    }

    @Override
    public void onCancelinformationSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean.getResult().equals("1")) {
            ToastUtils.showToast(context, twoSuccessCodeBean.getMsg(), Gravity.TOP);
            onSmartRefresh();

        }

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
            PopwindowUtils.getmInstance().dismissPopWindow();
            if (postion == 99) {
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
    public void onPayThreeElseSuccess(Object msg, String payAway) {
        if (msg != null) {
            if (payAway.equals("alipay")) {
                AlipayOrderIdBean alipayOrderIdBean = (AlipayOrderIdBean) msg;
                LogUtils.d(alipayOrderIdBean.getPayinfo());
                PayAway.Alipay(alipayOrderIdBean.getPayinfo(), this, MainActivity.class);
            } else if (payAway.equals("app_wxpay")) {
                WeChatPayBackBean weChatPayBackBean = (WeChatPayBackBean) msg;
                PayAway.WchatPay(weChatPayBackBean, getContext());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}

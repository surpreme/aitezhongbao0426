package com.aite.mainlibrary.fragment.daybookchridren.daybooklist;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.a.activity.MainActivity;
import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.QrBookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allshopcard.bookinformation.BookinformationActivity;
import com.aite.mainlibrary.activity.allshopcard.chatoutbook.ChatOutBookActivity;
import com.aite.mainlibrary.adapter.MineHelpEatRecyAdapter;
import com.aite.mainlibrary.adapter.MineLessBodybookRecyAdapter;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.blankj.rxbus.RxBus;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.base.BaseLazyFragment;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DaybooklistFragment extends BaseLazyFragment<DaybooklistContract.View, DaybooklistPresenter> implements DaybooklistContract.View {
    private MineHelpEatRecyAdapter mineHelpEatRecyAdapter;
    private List<BookMorningNoonEatBean.OrderListBean> orderListBeans = new ArrayList<>();
    private String ORDER_ID = "";
    private String PAGE_TYPE = "0";


    @Override
    protected void initModel() {


    }


    @Override
    protected void initViews() {
        initMoreRecy();
        RxBus.getDefault().subscribe(context, "eat", new RxBus.Callback<String>() {
            @Override
            public void onEvent(String o) {
                PAGE_TYPE = o;
                onSmartRefresh();

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mBaserecyclerView.setLayoutManager(linearLayoutManager);
        mineHelpEatRecyAdapter = new MineHelpEatRecyAdapter(context, orderListBeans);
        mineHelpEatRecyAdapter.setClickInterface(postion ->
                startActivity(BookinformationActivity.class, "ORDER_ID", String.valueOf(orderListBeans.get(postion).getOrder_id())));
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

        mineHelpEatRecyAdapter.setOnStartEatcodeInterface(new MineLessBodybookRecyAdapter.OnStartEatcodeInterface() {
            @Override
            public void getStartqrPosition(int position) {
                mPresenter.getStartEatQrinformation(initParams(orderListBeans.get(position).getOrder_id()), position);

            }
        });
        mBaserecyclerView.setAdapter(mineHelpEatRecyAdapter);
        //smartlayout
        initSmartLayout(true);
        //初始化加载
        initLoadingAnima();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.smartlayout_recy_layout;
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        mPresenter.getinformation(initEatParams());

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (orderListBeans != null) {
            orderListBeans.clear();
            mineHelpEatRecyAdapter.notifyDataSetChanged();
        }

        mPresenter.getinformation(initEatParams());

    }


    /**
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * key	get	字符串	必须			会员登录key
     * curpage	get	字符串	必须	1		当前页码
     * state	get	整型	必须	0		状态 0全部 1待付款 2待核销 3已完成 4评价 5已取消
     * page_type	get	整型	必须	1		页面类型 1早餐 2午餐
     *
     * @return getArguments().getString(" page_type ").equals(" morning ") ? 1 : 2
     * 页面类型 0全部 1早餐 2午餐
     * if (postion == 0)
     * startActivity(ChiendThingsbookActivity.class, "type", "all");
     * if (postion == 1)
     * startActivity(ChiendThingsbookActivity.class, "type", "morning");
     * if (postion == 2)
     * startActivity(ChiendThingsbookActivity.class, "type", "noon");
     * if (postion == 3)
     * startActivity(ChiendThingsbookActivity.class, "type", "choice");
     * <p>
     * if (getArguments() != null && getArguments().getString("type") != null) {
     * switch (getArguments().getString("type")) {
     * case "all":
     * break;
     * case "morning":
     * httpParams.put("page_type", 1);
     * break;
     * case "noon":
     * httpParams.put("page_type", 2);
     * break;
     * default:
     * httpParams.put("page_type", 0);
     * break;
     * }
     * } else {
     * httpParams.put("page_type", 0);
     * <p>
     * }
     */
    private HttpParams initEatParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        httpParams.put("state", getArguments().getString("state"));
        httpParams.put("page_type", PAGE_TYPE);
        return httpParams;
    }

    private HttpParams initParams(String order_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("order_id", order_id);
        return httpParams;
    }

    @Override
    public void loadData() {
        mPresenter.getinformation(initEatParams());

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetinformationSuccess(Object msg) {
        if (((BookMorningNoonEatBean) msg).getOrder_list().isEmpty()) {
            initNodata();
        } else {
            stopNoData();
            orderListBeans.addAll(((BookMorningNoonEatBean) msg).getOrder_list());
            mineHelpEatRecyAdapter.notifyDataSetChanged();
            hasMore = ((BookMorningNoonEatBean) msg).getIs_nextpage() > 0;
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
            if (!orderListBeans.isEmpty()) {
                orderListBeans.clear();
                mCurrentPage = 1;
                loadData();

            }

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
                PayAway.Alipay(alipayOrderIdBean.getPayinfo(), getActivity(), MainActivity.class);
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

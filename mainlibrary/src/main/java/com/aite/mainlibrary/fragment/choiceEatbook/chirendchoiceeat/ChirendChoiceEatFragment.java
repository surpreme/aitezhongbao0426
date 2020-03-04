package com.aite.mainlibrary.fragment.choiceEatbook.chirendchoiceeat;


import android.view.Gravity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.alipaylibrary.PayAway;
import com.aite.alipaylibrary.bean.WeChatPayBackBean;
import com.aite.mainlibrary.Mainbean.AlipayOrderIdBean;
import com.aite.mainlibrary.Mainbean.ChioceEatBookListBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allshopcard.bookchoiceeatinformation.BookChoiceEatinformationActivity;
import com.aite.mainlibrary.activity.allshopcard.shopcard.ShopCardActivity;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.aite.mainlibrary.adapter.moreitem.MoreItemRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseLazyFragment;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

public class ChirendChoiceEatFragment extends BaseLazyFragment<ChirendChoiceEatContract.View, ChirendChoiceEatPresenter> implements ChirendChoiceEatContract.View {

    private MoreItemRecyAdapter moreItemRecyAdapter;
    private String PAY_SN = "";
    private List<ChioceEatBookListBean.DatasBean.OrderGroupListBean.OrderListBean> orderListBeans = new ArrayList<>();

    @Override
    public void loadData() {

    }

    /**
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * key	get	字符串	必须			会员登录key
     * 32097a981064694f14abb1b98e80ef9c
     * curpage	get	整型	可选	1		当前页码
     * 1
     * pagesize	get	整型	可选	10		每页记录条数
     * type	get	整型	可选	0		订单状态1:待付款 2:待发货 3:待收货 4:待评价
     * 1
     * order_module	get	整型	可选	0		订单状态类型 0系统默认 1助餐服务-菜品
     *
     * @return
     */
    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("curpage", mCurrentPage);
        params.put("order_module", 1);
        try {
            int ste = Integer.valueOf(getArguments().getString("state"));
            params.put("type", ste);

        } catch (Exception e) {
            LogUtils.e("转换出错" + e);
        }
        return params;
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        mPresenter.getBookList(initParams());

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (orderListBeans != null) {
            orderListBeans.clear();
            moreItemRecyAdapter.notifyDataSetChanged();
        }
        mCurrentPage = 1;
        mPresenter.getBookList(initParams());

    }

    @Override
    protected void initModel() {
        mPresenter.getBookList(initParams());

    }

    @Override
    protected void initViews() {
        initMoreRecy();
        mBaserecyclerView.setAdapter(moreItemRecyAdapter = new MoreItemRecyAdapter(context, orderListBeans));
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        moreItemRecyAdapter.setOnclickInterface(position ->
                startActivity(BookChoiceEatinformationActivity.class, "order_id", orderListBeans.get(position).getOrder_id()));
        moreItemRecyAdapter.setOnThingClickInterface(msg -> {
            PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "取消后不可恢复", v -> {
                mPresenter.cancleOrder(initListHttpParams(true, new ContentValue("order_id", msg)));
                PopwindowUtils.getmInstance().dismissPopWindow();

            });

        });
        moreItemRecyAdapter.setOnPayclickInterface(postion -> {
            PAY_SN = orderListBeans.get(postion).getPay_sn();
//            startActivity(BookChoiceEatinformationActivity.class, "order_id", orderListBeans.get(postion).getOrder_id());
            mPresenter.getPayList(initKeyParams());

        });
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
    public void onGetBookListSuccess(Object msg) {

        if ((ChioceEatBookListBean) msg != null) {
            if (((ChioceEatBookListBean) msg).getDatas().getOrder_group_list().isEmpty()) {
                initNodata();
            } else {
                stopLoadingAnim();
                showMoreRecy();
                stopNoData();
                ChioceEatBookListBean chioceEatBookListBean = (ChioceEatBookListBean) msg;
                if (chioceEatBookListBean.getDatas().getOrder_group_list() != null) {
                    List<ChioceEatBookListBean.DatasBean.OrderGroupListBean.OrderListBean> list = new ArrayList<>();
                    for (ChioceEatBookListBean.DatasBean.OrderGroupListBean orderGroupListBean : chioceEatBookListBean.getDatas().getOrder_group_list()) {
                        list.addAll(orderGroupListBean.getOrder_list());
                    }

                    orderListBeans.addAll(list);
                    moreItemRecyAdapter.notifyDataSetChanged();
                }
                hasMore = ((ChioceEatBookListBean) msg).getPage_total() > 0;
            }


        }

    }

    @Override
    public void onCancleOrderSuccess(Object msg) {
        if (msg.toString().equals("1")) {
            showToast("取消成功", Gravity.TOP);
            onSmartRefresh();
        }

    }
    @Override
    public void onPayOrderSuccess(Object msg) {

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
    public void onFactPayCollectSuccess(Object msg) {
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("支付成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg(), Gravity.TOP);
            onBackPressed();
        } else {
            showToast("支付失败", Gravity.TOP);
        }
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

    @Override
    public void onFactPayThreeElseSuccess(Object msg, String payAway) {
        if (msg != null) {
            if (payAway.equals("alipay")) {
                AlipayOrderIdBean alipayOrderIdBean = (AlipayOrderIdBean) msg;
                LogUtils.d(alipayOrderIdBean.getPayinfo());
                PayAway.Alipay(alipayOrderIdBean.getPayinfo(), getActivity(), ShopCardActivity.class);
            } else if (payAway.equals("app_wxpay")) {
                WeChatPayBackBean weChatPayBackBean = (WeChatPayBackBean) msg;
                PayAway.WchatPay(weChatPayBackBean, getActivity());
            }
        }
    }
}

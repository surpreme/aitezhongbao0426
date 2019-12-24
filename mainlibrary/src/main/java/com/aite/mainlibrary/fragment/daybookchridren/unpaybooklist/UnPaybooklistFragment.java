package com.aite.mainlibrary.fragment.daybookchridren.unpaybooklist;


import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.alipaylibrary.PayAway;
import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.Mainbean.PayListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allshopcard.bookinformation.BookinformationActivity;
import com.aite.mainlibrary.activity.allshopcard.chatoutbook.ChatOutBookActivity;
import com.aite.mainlibrary.adapter.MineHelpEatRecyAdapter;
import com.aite.mainlibrary.adapter.PayRadioGroupRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.PopwindowUtils;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.base.BaseLazyFragment;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.MVPBaseFragment;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UnPaybooklistFragment extends BaseLazyFragment<UnPaybooklistContract.View, UnPaybooklistPresenter> implements UnPaybooklistContract.View {

    private MineHelpEatRecyAdapter mineHelpEatRecyAdapter;
    private List<BookMorningNoonEatBean.OrderListBean> orderListBeans = new ArrayList<>();
    private String ORDER_ID = "";


    @Override
    protected void initModel() {


    }

    @Override
    protected void initViews() {
        initMoreRecy();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mBaserecyclerView.setLayoutManager(linearLayoutManager);
        mineHelpEatRecyAdapter = new MineHelpEatRecyAdapter(context, orderListBeans);
        mBaserecyclerView.setAdapter(mineHelpEatRecyAdapter);
        mineHelpEatRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                startActivity(BookinformationActivity.class, "ORDER_ID", String.valueOf(orderListBeans.get(postion).getOrder_id()));
            }
        });
        mineHelpEatRecyAdapter.setOnInformationInteface(new MineHelpEatRecyAdapter.OnInformationInteface() {
            @Override
            public void pay(int position) {
                ORDER_ID = String.valueOf(orderListBeans.get(position).getOrder_id());
                mPresenter.getPayList(initKeyParams());

            }

            @Override
            public void lookInformation(int position) {

            }

            @Override
            public void talkTv(int position) {
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
     * getArguments().getString("page_type").equals("morning") ? 1 : 2
     *
     * @return
     */
    private HttpParams initEatParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        httpParams.put("state", 1);
        if (getArguments() != null && getArguments().getString("type") != null) {
            switch (getArguments().getString("type")) {
                case "all":
                    httpParams.put("page_type", 0);
                    break;
                case "morning":
                    httpParams.put("page_type", 1);
                    break;
                case "noon":
                    httpParams.put("page_type", 2);
                    break;
                default:
                    httpParams.put("page_type", 0);
                    break;
            }
        } else {
            httpParams.put("page_type", 0);
        }

        return httpParams;
    }

    @Override
    public void loadData() {
        mPresenter.getinformation(initEatParams());

    }

    @Override
    public void onClick(View v) {

    }

    private HttpParams initParams(String order_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("order_id", order_id);
        return httpParams;
    }

    @Override
    public void onGetinformationSuccess(Object msg) {
        if (((BookMorningNoonEatBean) msg).getOrder_list().isEmpty()) {
            initNodata();
        } else {
            stopLoadingAnim();
            showMoreRecy();
            stopNoData();
            orderListBeans.addAll(((BookMorningNoonEatBean) msg).getOrder_list());
            mineHelpEatRecyAdapter.notifyDataSetChanged();
            hasMore = ((BookMorningNoonEatBean) msg).getIs_nextpage() > 0;
        }


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

            } else if (postion == 1) {
                PayAway.Alipay("fgydfgsxdfgscfgdf", getActivity(), ChoiceActivity.class);

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
    public void onDestroy() {
        super.onDestroy();
        System.gc();

    }
}

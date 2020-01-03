package com.aite.mainlibrary.fragment.daybookchridren.overedbooklist;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.BookMorningNoonEatBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allshopcard.bookinformation.BookinformationActivity;
import com.aite.mainlibrary.activity.allshopcard.chatoutbook.ChatOutBookActivity;
import com.aite.mainlibrary.adapter.MineHelpEatRecyAdapter;
import com.blankj.rxbus.RxBus;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.base.BaseLazyFragment;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.mvp.MVPBaseFragment;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OveredbooklistFragment extends BaseLazyFragment<OveredbooklistContract.View, OveredbooklistPresenter> implements OveredbooklistContract.View {
    private MineHelpEatRecyAdapter mineHelpEatRecyAdapter;
    private List<BookMorningNoonEatBean.OrderListBean> orderListBeans = new ArrayList<>();
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
            }

            @Override
            public void lookInformation(int position) {
                startActivity(BookinformationActivity.class, "ORDER_ID", String.valueOf(orderListBeans.get(position).getOrder_id()));

            }

            @Override
            public void talkTv(int position) {
                Intent intent = new Intent(context, ChatOutBookActivity.class);
                intent.putExtra("order_id", orderListBeans.get(position).getOrder_id());
                context.startActivity(intent);
            }

            @Override
            public void cancelTv(int position) {


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
     * getArguments().getString("page_type").equals("morning")?1:2
     *
     * @return
     */
    private HttpParams initEatParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        httpParams.put("state", 3);
        httpParams.put("page_type", PAGE_TYPE);
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
            stopLoadingAnim();
            showMoreRecy();
            stopNoData();
            orderListBeans.addAll(((BookMorningNoonEatBean) msg).getOrder_list());
            mineHelpEatRecyAdapter.notifyDataSetChanged();
            hasMore = ((BookMorningNoonEatBean) msg).getIs_nextpage() > 0;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();

    }
}
package com.aite.mainlibrary.fragment.choiceEatbook.chirendchoiceeat;


import androidx.recyclerview.widget.LinearLayoutManager;
import com.aite.mainlibrary.Mainbean.ChioceEatBookListBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allshopcard.bookchoiceeatinformation.BookChoiceEatinformationActivity;
import com.aite.mainlibrary.adapter.moreitem.MoreItemRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseLazyFragment;
import com.lzy.okgo.model.HttpParams;
import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChirendChoiceEatFragment extends BaseLazyFragment<ChirendChoiceEatContract.View, ChirendChoiceEatPresenter> implements ChirendChoiceEatContract.View {

    private MoreItemRecyAdapter moreItemRecyAdapter;
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
        params.put("type", getArguments().getString("state"));
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
}

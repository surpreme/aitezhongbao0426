package com.aite.mainlibrary.fragment.lessBodyfragment.lessbodyunpaybooklist;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.BookLessBodyFamilyBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allshopcard.dayinformation.DayInformationActivity;
import com.aite.mainlibrary.activity.allshopcard.sureunfactshopbook.SureUnFactShopBookActivity;
import com.aite.mainlibrary.adapter.MineLessBodybookRecyAdapter;
import com.blankj.rxbus.RxBus;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.MVPBaseFragment;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LessbodyunpaybooklistFragment extends BaseFragment<LessbodyunpaybooklistContract.View, LessbodyunpaybooklistPresenter> implements LessbodyunpaybooklistContract.View {
    private MineLessBodybookRecyAdapter mineLessBodybookRecyAdapter;
    private List<BookLessBodyFamilyBean.OrderListBean> orderListBeans = new ArrayList<>();
    private String PAGE_TYPE = "1";


    @Override
    protected void initModel() {
        mPresenter.getinformation(initParams());


    }

    @Override
    protected void initViews() {
        PAGE_TYPE = getArguments().getString("page_type");
        initMoreRecy();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mBaserecyclerView.setLayoutManager(linearLayoutManager);
        mineLessBodybookRecyAdapter = new MineLessBodybookRecyAdapter(context, orderListBeans);
        mBaserecyclerView.setAdapter(mineLessBodybookRecyAdapter);
        mineLessBodybookRecyAdapter.setOnInformationInteface(new MineLessBodybookRecyAdapter.OnInformationInteface() {
            @Override
            public void pay(int position) {

            }

            //datas->order_list[]->vr_code	字符串	核销码
            @Override
            public void lookInformation(int position) {
                LogUtils.d(position);
                Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(
                        orderListBeans.get(position).getVr_code(),
                        BGAQRCodeUtil.dp2px(context, 150),
                        Color.BLACK, Color.WHITE,
                        BitmapFactory.decodeResource(context.getResources(), com.lzy.basemodule.R.drawable.logo));
                PopwindowUtils.getmInstance().showQrPopupWindow(context, bitmap, orderListBeans.get(position).getGoods_name() + "   " + orderListBeans.get(position).getAdd_time());

            }

            @Override
            public void talkTv(int position) {

            }

            @Override
            public void cancelTv(int position) {
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "您确定要取消" + orderListBeans.get(position).getGoods_name() + "吗", v -> {
                    mPresenter.Cancelinformation(initCancelParams(orderListBeans.get(position).getOrder_id()));
                    PopwindowUtils.getmInstance().dismissPopWindow();
                });

            }
        });
        mineLessBodybookRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                startActivity(SureUnFactShopBookActivity.class, "order_id", orderListBeans.get(postion).getOrder_id());
            }
        });
        //smartlayout
        initSmartLayout(true);
        //初始化加载
        initLoadingAnima();
    }

    private HttpParams initCancelParams(String ORDER_ID) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("order_id", ORDER_ID);
        return httpParams;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.smartlayout_recy_layout;
    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        mPresenter.getinformation(initParams());

    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (orderListBeans != null) {
            orderListBeans.clear();
            mineLessBodybookRecyAdapter.notifyDataSetChanged();
        }

        mPresenter.getinformation(initParams());

    }

    /**
     * key	get	字符串	必须			会员登录key
     * curpage	get	字符串	必须	1		当前页码
     * state	get	整型	必须	0		状态 0全部 1待付款 2已付款 3已完成 4评价 5已取消
     * page_type	get	整型	必须	1		页面类型 1日托 2培训 3就业 4助残活动 5其他服务
     *
     * @return
     */
    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        httpParams.put("state", 1);
        httpParams.put("page_type", PAGE_TYPE);
        return httpParams;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetinformationSuccess(Object msg) {
        if (((BookLessBodyFamilyBean) msg).getOrder_list().isEmpty()) {
            initNodata();
        } else {
            stopLoadingAnim();
            showMoreRecy();
            stopNoData();
            orderListBeans.addAll(((BookLessBodyFamilyBean) msg).getOrder_list());
            mineLessBodybookRecyAdapter.notifyDataSetChanged();
            hasMore = ((BookLessBodyFamilyBean) msg).getIs_nextpage() > 0;
        }


    }

    @Override
    public void onCancelinformationSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean.getResult().equals("1") && twoSuccessCodeBean.getMsg() != null) {
            showToast(twoSuccessCodeBean.getMsg());
            onSmartRefresh();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}

package com.aite.mainlibrary.activity.allsetting.serviceorderbook;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.BookLessBodyFamilyBean;
import com.aite.mainlibrary.Mainbean.ChoiceOrderBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allqr.qrcode.QrCodeActivity;
import com.aite.mainlibrary.activity.allshopcard.chatoutbook.ChatOutBookActivity;
import com.aite.mainlibrary.activity.allshopcard.sureunfactshopbook.SureUnFactShopBookActivity;
import com.aite.mainlibrary.activity.allshopcard.talkbook.TalkBookActivity;
import com.aite.mainlibrary.adapter.MineLessBodybookRecyAdapter;
import com.aite.mainlibrary.adapter.RadioGroupRecyAdapter;
import com.blankj.rxbus.RxBus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.FileUtils;
import com.lzy.basemodule.view.StatusBarUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ServiceOrderBookActivity extends BaseActivity<ServiceOrderBookContract.View, ServiceOrderBookPresenter> implements ServiceOrderBookContract.View {
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
    private MineLessBodybookRecyAdapter mineLessBodybookRecyAdapter;
    private List<BookLessBodyFamilyBean.OrderListBean> orderListBeans = new ArrayList<>();
    private List<ChoiceOrderBean> choiceOrderBeans = new ArrayList<>();
    private RadioGroupRecyAdapter radioGroupRecyAdapter;
    private int PAGE_TYPE = 0;
    private int STATE = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_service_order_book;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        initToolbar("服务订单", "核销", v -> {
            startActivity(QrCodeActivity.class, "type", "unfactbook");
        });
        initRecy();
        initImgNodata();
        initSmartLayout(true, false);
        mBaserecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBaserecyclerView.setAdapter(mineLessBodybookRecyAdapter = new MineLessBodybookRecyAdapter(context, orderListBeans));
        allTv.setTextColor(getColor(R.color.blue));
        String eat_choice_json = FileUtils.getFromAssets(context, "choiceservicepop.json");
        Gson gson = new Gson();
        choiceOrderBeans = gson.fromJson(eat_choice_json, new TypeToken<List<ChoiceOrderBean>>() {
        }.getType());
        if (choiceOrderBeans != null && choiceOrderBeans.get(0) != null)
            choiceOrderBeans.get(0).setChecked(true);

        mineLessBodybookRecyAdapter.setClickInterface(position -> {
            startActivity(SureUnFactShopBookActivity.class, "order_id", orderListBeans.get(position).getOrder_id());

        });
        mineLessBodybookRecyAdapter.setOnInformationInteface(new MineLessBodybookRecyAdapter.OnInformationInteface() {
            @Override
            public void pay(int position) {
                startActivity(SureUnFactShopBookActivity.class, "order_id", orderListBeans.get(position).getOrder_id());

            }

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
                startActivity(ChatOutBookActivity.class, "order_id", orderListBeans.get(position).getOrder_id());

            }

            @Override
            public void cancelTv(int position) {
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "您确定要取消" + orderListBeans.get(position).getGoods_name() + "吗", v -> {
                    mPresenter.Cancelinformation(initSizeParams(orderListBeans.get(position).getOrder_id()));
                    PopwindowUtils.getmInstance().dismissPopWindow();
                });
            }
        });
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

    private void clearRecyDatas() {
        if (orderListBeans != null && !orderListBeans.isEmpty()) {
            orderListBeans.clear();
            mineLessBodybookRecyAdapter.notifyDataSetChanged();
        } else if (orderListBeans == null) {
            orderListBeans = new ArrayList<>();
        }
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

    @Override
    protected void initDatas() {
        mPresenter.onGetOrderBookList(initParams());

    }

    /**
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * key	get	字符串	必须			会员登录key
     * curpage	get	字符串	必须	1		当前页码
     * state	get	整型	必须	0		状态 0全部 1待付款 2已付款 3已完成 4评价 5已取消
     * page_type	get	整型	必须	0		页面类型 1日托 2培训 3就业 4助残活动 5其他服务 0 全部
     *
     * @return
     */
    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        httpParams.put("state", STATE);
        httpParams.put("page_type", PAGE_TYPE);
        return httpParams;
    }

    private HttpParams initSizeParams(String ORDER_ID) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("order_id", ORDER_ID);
        return httpParams;
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetOrderBookListSuccess(Object msg) {
        BookLessBodyFamilyBean bookLessBodyFamilyBean = (BookLessBodyFamilyBean) msg;
        if (bookLessBodyFamilyBean != null) {
            if (bookLessBodyFamilyBean.getOrder_list() != null) {
                hasMore = !bookLessBodyFamilyBean.getOrder_list().isEmpty();
                if (!hasMore && mCurrentPage == 1) {
                    showImgNoData();
                } else {
                    stopImgNodata();
                }
                if (!bookLessBodyFamilyBean.getOrder_list().isEmpty()) {
                    orderListBeans.addAll(bookLessBodyFamilyBean.getOrder_list());
                    mineLessBodybookRecyAdapter.notifyDataSetChanged();
                }
            }
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


}

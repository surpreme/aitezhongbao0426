package com.aite.mainlibrary.activity.allshopcard.dayinformation;


import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.LessBodyInformationBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCode2Bean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allmain.buydaytogether.BuyDayTogetherActivity;
import com.aite.mainlibrary.activity.allshopcard.orderdiscuss.OrderDiscussActivity;
import com.aite.mainlibrary.adapter.LessBodyGoodListRecyAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TextEmptyUtils;
import com.lzy.basemodule.webJs.JsInterface;
import com.lzy.basemodule.webJs.RerashWebView;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DayInformationActivity extends BaseActivity<DayInformationContract.View, DayInformationPresenter> implements DayInformationContract.View {
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.goods_iv)
    ImageView goodsIv;
    @BindView(R2.id.goods_price_tv)
    TextView goodsPriceTv;
    @BindView(R2.id.goods_information_tv)
    TextView goodsInformationTv;
    @BindView(R2.id.collect_iv)
    ImageView collectIv;
    @BindView(R2.id.buy_service_tv)
    TextView buy_service_tv;
    @BindView(R2.id.talk_all_tv)
    TextView talkAllTv;
    @BindView(R2.id.talk_good_tv)
    TextView talkGoodTv;
    @BindView(R2.id.talk_norm_tv)
    TextView talkNormTv;
    @BindView(R2.id.talk_bad_tv)
    TextView talkBadTv;
    @BindView(R2.id.talk_good_number_tv)
    TextView talkGoodNumberTv;
    @BindView(R2.id.talk_norm_number_tv)
    TextView talkNormNumberTv;
    @BindView(R2.id.talk_bad_number_tv)
    TextView talkBadNumberTv;
    @BindView(R2.id.talk_all_number_tv)
    TextView talkAllNumberTv;
    @BindView(R2.id.goods_recy)
    RecyclerView goodsRecy;
    @BindView(R2.id.tablayout)
    TabLayout tablayout;
    @BindView(R2.id.nested_scrollview)
    NestedScrollView nestedScrollview;
    @BindView(R2.id.information_ll)
    LinearLayout informationLl;
    @BindView(R2.id.talk_ll)
    LinearLayout talkLl;
    @BindView(R2.id.all_information_ll)
    LinearLayout allInformationLl;
    @BindView(R2.id.all_information_webview)
    WebView allInformationWebview;
    @BindView(R2.id.talk_user_icon)
    ImageView talkUserIcon;
    @BindView(R2.id.talk_name_tv)
    TextView talkNameTv;
    @BindView(R2.id.talk_state_tv)
    TextView talkStateTv;
    @BindView(R2.id.talk_information_tv)
    TextView talkInformationTv;
    @BindView(R2.id.talkout_ll)
    LinearLayout talkoutLl;
    @BindView(R2.id.all_progressBar)
    ProgressBar allProgressBar;
    @BindView(R2.id.center_progressBar)
    ProgressBar centerProgressBar;
    @BindView(R2.id.bad_progressBar)
    ProgressBar badProgressBar;
    @BindView(R2.id.orderDiscuss_ll)
    LinearLayout orderDiscussLl;
    @BindView(R2.id.good_nice_iv)
    ImageView goodNiceIv;
    @BindView(R2.id.collect_ll)
    LinearLayout collectLl;
    @BindView(R2.id.goods_ll)
    LinearLayout goodsLl;
    private LessBodyGoodListRecyAdapter lessBodyGoodListRecyAdapter;
    private List<LessBodyInformationBean.GoodsCommendListBean> goodsCommendListBeans = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.day_togther_information;
    }

    private int curY = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        talkoutLl.setVisibility(View.GONE);
        badProgressBar.setMax(100);
        centerProgressBar.setMax(100);
        allProgressBar.setMax(100);

        nestedScrollview.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            curY = scrollY;
            Log.e("TAG", scrollY + "  " + oldScrollY + "  " + scrollX + "   " + talkLl.getTop());
            //informationLl.getBottom() <= scrollY &&
            if (scrollY < allInformationLl.getTop() && tablayout.getSelectedTabPosition() != 0) {
                tablayout.selectTab(tablayout.getTabAt(0));
                //allInformationLl.getBottom() <= scrollY &&
            } else if (scrollY >= allInformationLl.getTop() && scrollY < talkLl.getTop() && tablayout.getSelectedTabPosition() != 1) {
                tablayout.selectTab(tablayout.getTabAt(1));
                //talkLl.getBottom() <= scrollY&&
            } else if (scrollY >= talkLl.getTop() && tablayout.getSelectedTabPosition() != 2) {
                tablayout.selectTab(tablayout.getTabAt(2));
            } else {

            }

        });
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    //顶部
                    if (curY >= allInformationLl.getTop()) {
                        nestedScrollview.fullScroll(NestedScrollView.FOCUS_UP);
                    }

                } else if (tab.getPosition() == 1) {
                    if (curY < allInformationLl.getTop() || curY >= talkLl.getTop()) {
                        nestedScrollview.scrollTo(0, allInformationLl.getTop());
                    }

                } else if (tab.getPosition() == 2) {
                    if (curY <= talkLl.getTop())
                        nestedScrollview.scrollTo(0, talkLl.getTop());

//                    //底部
//                    nestedScrollview.fullScroll(NestedScrollView.FOCUS_DOWN);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        goodsRecy.setLayoutManager(new GridLayoutManager(context, 2));
        goodsRecy.setAdapter(lessBodyGoodListRecyAdapter = new LessBodyGoodListRecyAdapter(context, goodsCommendListBeans));
        lessBodyGoodListRecyAdapter.setLstenerInterface(position -> {
            startActivity(DayInformationActivity.class, "goods_id", goodsCommendListBeans.get(position).getGoods_id());
        });

    }

    @OnClick({R2.id.buy_service_tv, R2.id.iv_back, R2.id.collect_ll, R2.id.orderDiscuss_ll, R2.id.talkout_ll, R2.id.goods_ll})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.buy_service_tv) {
            LogUtils.d("goods_id", !isStringEmpty(getIntent().getStringExtra("goods_id")) ? getIntent().getStringExtra("goods_id") : "");
            startActivity(BuyDayTogetherActivity.class, "goods_id",
                    !isStringEmpty(getIntent().getStringExtra("goods_id")) ? getIntent().getStringExtra("goods_id") : "");
        } else if (v.getId() == R.id.collect_ll) {
            mPresenter.onCollect(initParams());
        } else if (v.getId() == R.id.orderDiscuss_ll || v.getId() == R.id.talkout_ll) {
            startActivityWithCls(OrderDiscussActivity.class, 0, new ContentValue("goods_id", getIntent().getStringExtra("goods_id")));
        } else if (v.getId() == R.id.goods_ll) {
            mPresenter.onFavourite(initParams());
        }
    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("goods_id",
                !isStringEmpty(getIntent().getStringExtra("goods_id")) ? getIntent().getStringExtra("goods_id") : "");
        return httpParams;
    }

    @Override
    protected void initDatas() {
        mPresenter.onGetInformation(initParams());
        mPresenter.onGetGoodAllInformation(initParams());

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    @Override
    public void onGetInformationSuccess(Object msg) {
        LessBodyInformationBean lessBodyInformationBean = ((LessBodyInformationBean) msg);
        collectIv.setImageDrawable(getDrawable((lessBodyInformationBean.getIsFavorites() == 1 ? R.drawable.ic_collect_full : R.drawable.collect_doctor_red)));
        goodNiceIv.setImageDrawable(getDrawable((lessBodyInformationBean.getIsLike() == 1 ? R.drawable.good_nice : R.drawable.good_gray)));
        Glide.with(context).load(lessBodyInformationBean.getGoods_info().getGoods_image_url()).into(goodsIv);
        goodsInformationTv.setText(lessBodyInformationBean.getGoods_info().getGoods_name());
        goodsPriceTv.setText(String.format("￥ %s", lessBodyInformationBean.getGoods_info().getGoods_price()));
        talkAllTv.setText(String.format("全部评价(%d)", lessBodyInformationBean.getEvaluate_info().getAll()));
        talkGoodTv.setText(String.format("好评(%d)", lessBodyInformationBean.getEvaluate_info().getAll()));
        talkNormTv.setText(String.format("中评(%d)", lessBodyInformationBean.getEvaluate_info().getAll()));
        talkBadTv.setText(String.format("差评(%d)", lessBodyInformationBean.getEvaluate_info().getAll()));

        allProgressBar.setProgress(lessBodyInformationBean.getEvaluate_info().getGood_percent());
        centerProgressBar.setProgress(lessBodyInformationBean.getEvaluate_info().getNormal_percent());
        badProgressBar.setProgress(lessBodyInformationBean.getEvaluate_info().getBad_percent());
        talkAllNumberTv.setText(String.format("%s%%", String.format("共有(%d)", lessBodyInformationBean.getEvaluate_info().getGood_star())));
        talkBadNumberTv.setText(String.format("%s%%", String.format("差评(%d)", lessBodyInformationBean.getEvaluate_info().getBad_percent())));
        talkNormNumberTv.setText(String.format("%s%%", String.format("中评(%d)", lessBodyInformationBean.getEvaluate_info().getNormal_percent())));
        talkGoodNumberTv.setText(String.format("%s%%", String.format("好评(%d)", lessBodyInformationBean.getEvaluate_info().getGood_percent())));
        if (!lessBodyInformationBean.getGoods_commend_list().isEmpty()) {
            goodsCommendListBeans.addAll(lessBodyInformationBean.getGoods_commend_list());
            lessBodyGoodListRecyAdapter.notifyDataSetChanged();
        }
        /**
         * geval_scores_text : 中评
         * geval_scores : 3
         * geval_content : 地方
         * geval_frommembername : 18614079738
         * geval_member_avatar : http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg
         * geval_addtime : 2019-12-26
         */
        if (lessBodyInformationBean.getGoods_evaluate_list() != null && !lessBodyInformationBean.getGoods_evaluate_list().isEmpty()) {
            talkoutLl.setVisibility(View.VISIBLE);
            talkNameTv.setText(TextEmptyUtils.getText(lessBodyInformationBean.getGoods_evaluate_list().get(0).getGeval_frommembername()));
            talkStateTv.setText(TextEmptyUtils.getText(lessBodyInformationBean.getGoods_evaluate_list().get(0).getGeval_scores_text()));
            talkInformationTv.setText(TextEmptyUtils.getText(lessBodyInformationBean.getGoods_evaluate_list().get(0).getGeval_content()));
            Glide.with(context).load(lessBodyInformationBean.getGoods_evaluate_list().get(0).getGeval_member_avatar())
                    .apply(RequestOptions.circleCropTransform()).into(talkUserIcon);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCollectSuccess(Object msg) {
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("收藏成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg());
            collectIv.setImageDrawable(getDrawable(R.drawable.ic_collect_full));
        }
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("取消收藏成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg());
            collectIv.setImageDrawable(getDrawable(R.drawable.collect_doctor_red));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onFavouriteSuccess(Object msg) {
        LogUtils.d(((TwoSuccessCodeBean) msg).getMsg());
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("点赞成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg());
            goodNiceIv.setImageDrawable(getDrawable(R.drawable.good_nice));
        }
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("取消点赞成功")) {
            showToast(((TwoSuccessCodeBean) msg).getMsg());
            goodNiceIv.setImageDrawable(getDrawable(R.drawable.good_gray));
        }
    }

    @Override
    public void onGetGoodAllInformationSuccess(Object msg) {
        TwoSuccessCode2Bean twoSuccessCodeBean = (TwoSuccessCode2Bean) msg;
        if (twoSuccessCodeBean != null && twoSuccessCodeBean.getUrl() != null) {
            allInformationWebview.loadUrl(twoSuccessCodeBean.getUrl());
            allInformationWebview.setWebViewClient(new WebViewClient());
            RerashWebView.initWebView(allInformationWebview);
            allInformationWebview.addJavascriptInterface(new JsInterface(allInformationWebview, this), "AndroidWebView");
        }

    }

}

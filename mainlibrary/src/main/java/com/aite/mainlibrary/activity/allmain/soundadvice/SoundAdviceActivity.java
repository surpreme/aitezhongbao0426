package com.aite.mainlibrary.activity.allmain.soundadvice;


import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.BaseFirstResultBean;
import com.aite.mainlibrary.Mainbean.SoundAdviceInformationTitleBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SoundAdviceActivity extends BaseActivity<SoundAdviceContract.View, SoundAdvicePresenter> implements SoundAdviceContract.View {

    @BindView(R2.id.title_tv)
    TextView titleTv;
    @BindView(R2.id.time_tv)
    TextView timeTv;
    @BindView(R2.id.content_webview)
    WebView contentWebview;

    @Override
    protected int getLayoutResId() {
        return R.layout.acticity_soundadvice_web;
    }

    @Override
    protected void initView() {
        initToolbar("公告详情");

    }

    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("article_id", getIntent().getStringExtra("article_id"));
        return params;
    }

    @Override
    protected void initDatas() {
        mPresenter.getUriContentSuccess(initParams());
        mPresenter.getUiTitleSuccess(initParams());

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetUiTitleSuccess(Object msg) {
        SoundAdviceInformationTitleBean soundAdviceInformationTitleBean = (SoundAdviceInformationTitleBean) msg;
        if (soundAdviceInformationTitleBean == null) return;
        if (soundAdviceInformationTitleBean.getArticle().getArticle_title() != null)
            titleTv.setText(soundAdviceInformationTitleBean.getArticle().getArticle_title());
        if (soundAdviceInformationTitleBean.getArticle().getArticle_time() != null)
            timeTv.setText(soundAdviceInformationTitleBean.getArticle().getArticle_time());


    }

    @Override
    public void onGetUriContentSuccess(Object msg) {
        BaseFirstResultBean baseFirstResultBean = (BaseFirstResultBean) msg;
        if (baseFirstResultBean == null) return;
        if (baseFirstResultBean.getUrl() == null) return;
        contentWebview.loadUrl(baseFirstResultBean.getUrl());
        contentWebview.setWebViewClient(new WebViewClient());

    }

}

package com.lzy.basemodule.base;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.lzy.basemodule.R;
import com.lzy.basemodule.webJs.JsInterface;
import com.lzy.basemodule.webJs.RerashWebView;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-04
 * @desc:
 *       @ Such as Down of Simple ，u can used start this activity for bundle or putExtra,and u can extends this activity to make your thinkers
 *         Intent intent = new Intent();
 *         intent.putExtra("title", mMineRuralPushListBean.get(position).getTheme_name());
 *         intent.putExtra("webViewUrl", String.format("%scomefrom=app", mMineRuralPushListBean.get(position).getH5_url()));
 *         intent.putExtra("isHideToolBar", "false");
 *         intent.setAction("com.lzy.base.BaseWebViewActivity");
 *         startActivity(intent);
 * <p>
 * <p>
 * ** @title
 * @ u need made isHideToolBar to false ,that it's title of toolbar
 * ** @webViewUrl
 * @ it's your want to show web pages's url
 * ** @isHideToolBar
 * @ it's because most web page possible add web toolbar ,there given a choice to used ,and don't put title with some things,because it's more things
 * <p>
 * <p>
 * !!!!!!!!!please add this Activity to your app or module of AndroidManifest.xml !!!!!!!!!!
 * if u want to extends this activity ,u can don't add this Activity name to file of your AndroidManifest.xml
 */
public class BaseWebViewActivity extends BaseActivity {
    protected WebView webView;
    protected RelativeLayout toolbar_ll;

    @Override
    protected int getLayoutResId() {
        return R.layout.base_web_activity;
    }

    @Override
    protected void initView() {
        initWebView();
        if (getIntent().getStringExtra("title") != null)
            initToolbar(getIntent().getStringExtra("title"));
        else {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                if (bundle.getString("title") != null) {
                    if (isStringEmpty(bundle.getString("title"))) initToolbar("网页详情");
                    else
                        initToolbar(bundle.getString("title"));
                }
            } else {
                initToolbar("网页详情");
            }
        }
        if (getIntent().getStringExtra("webViewUrl") != null) {
            webView.loadUrl(getIntent().getStringExtra("webViewUrl"));
            webView.setWebViewClient(new WebViewClient());
            RerashWebView.initWebView(webView);
            webView.addJavascriptInterface(new JsInterface(webView, this), "AndroidWebView");
        } else {
            Bundle webviewUrlBundle = getIntent().getExtras();
            if (webviewUrlBundle != null) {
                if (webviewUrlBundle.getString("webViewUrl") != null) {
                    webView.loadUrl(getIntent().getStringExtra("webViewUrl"));
                    webView.setWebViewClient(new WebViewClient());
                    RerashWebView.initWebView(webView);
                    webView.addJavascriptInterface(new JsInterface(webView, this), "AndroidWebView");
                }
            }
        }
        if (getIntent().getStringExtra("isHideToolBar") != null) {
            if (getIntent().getStringExtra("isHideToolBar").equals("true")) {
                toolbar_ll.setVisibility(View.GONE);
            } else if (getIntent().getStringExtra("isHideToolBar").equals("false")) {
                toolbar_ll.setVisibility(View.VISIBLE);
            } else {
                toolbar_ll.setVisibility(View.VISIBLE);
            }
        } else {
            Bundle isShowToolBarBundle = getIntent().getExtras();
            if (isShowToolBarBundle != null) {
                if (isShowToolBarBundle.getString("isHideToolBar").equals("true")) {
                    toolbar_ll.setVisibility(View.GONE);

                } else if (isShowToolBarBundle.getString("isHideToolBar").equals("false")) {
                    toolbar_ll.setVisibility(View.VISIBLE);

                } else {
                    toolbar_ll.setVisibility(View.VISIBLE);
                }
            } else {
                toolbar_ll.setVisibility(View.VISIBLE);
            }
        }


    }

    private void initWebView() {
        webView = findViewById(R.id.webview);
        toolbar_ll = findViewById(R.id.toolbar_ll);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }
}

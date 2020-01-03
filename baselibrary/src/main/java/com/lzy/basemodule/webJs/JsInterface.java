package com.lzy.basemodule.webJs;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-25
 * @desc:
 */
public class JsInterface {
    private WebView webView;
    private Activity activity;

    public JsInterface(WebView webView, Activity activity) {
        this.webView = webView;
        this.activity = activity;
    }

    /**
     * 返回
     */
    @JavascriptInterface
    public void AppGoBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            activity.finish();
        }
    }
}


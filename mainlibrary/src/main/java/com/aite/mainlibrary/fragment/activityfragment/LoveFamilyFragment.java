package com.aite.mainlibrary.fragment.activityfragment;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.viewpager.widget.ViewPager;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.fragmentAdpter.BackgroundViewPagerApdapter;
import com.blankj.rxbus.RxBus;
import com.google.android.material.tabs.TabLayout;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseApp;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.webJs.JsInterface;
import com.lzy.basemodule.webJs.RerashWebView;

import butterknife.BindView;

public class LoveFamilyFragment extends BaseFragment {
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    @BindView(R2.id.thingsfix_tabMode)
    TabLayout tabLayout;
    @BindView(R2.id.webView)
    WebView webView;
    private View[] views;
    private BackgroundViewPagerApdapter viewPagerAdapter;


    @Override
    protected void initModel() {

    }

    public boolean WebonBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return false;
        } else {
            return true;
        }

    }

    @Override
    protected boolean isUseMvp() {
        return false;
    }

    private void initFragment() {
        views = new View[4];
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        views[0] = layoutInflater.inflate(R.layout.news_main_fragment, null);
        views[1] = layoutInflater.inflate(R.layout.maindoctor_information, null);
        views[2] = layoutInflater.inflate(R.layout.recy_layout, null);
        views[3] = layoutInflater.inflate(R.layout.recy_layout, null);
        viewPagerAdapter = new BackgroundViewPagerApdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void initViews() {
        webView.loadUrl(AppConstant.ZHONGBAOLOVEFAMILYSURL + AppConstant.KEY);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.startsWith("http://zhongbyi.aitecc.com/wap/index.php?act=circle&comefrom=APP") || url.startsWith("http://zhongbyi.aitecc.com/wap/index.php?act=news&comefrom=APP")) {
                    RxBus.getDefault().post("show", "ISSHOWHIDETABLL");
                } else {
                    RxBus.getDefault().post("hide", "ISSHOWHIDETABLL");
                }
            }
        });
        RerashWebView.initWebView(webView);
        webView.addJavascriptInterface(new JsInterface(webView, getActivity()), "AndroidWebView");
        initFragment();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_around_fragment;
    }

    @Override
    public void onClick(View v) {

    }
}

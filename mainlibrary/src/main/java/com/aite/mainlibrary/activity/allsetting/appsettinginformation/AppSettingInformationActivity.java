package com.aite.mainlibrary.activity.allsetting.appsettinginformation;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.aboutus.AboutUsActivity;
import com.aite.mainlibrary.activity.allsetting.feedback.FeedbackActivity;
import com.lzy.basemodule.activitylife.SystemFileUtil;
import com.lzy.basemodule.adpter.BaseTextViewRecyAdapter;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.base.BaseApp;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.util.VersionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AppSettingInformationActivity extends BaseActivity<AppsettinginformationContract.View, AppsettinginformationPresenter> implements AppsettinginformationContract.View {

    @BindView(R2.id.app_version_tv)
    TextView appVersionTv;
    @BindView(R2.id.clear_cache_ll)
    LinearLayout clearCacheLl;
    @BindView(R2.id.cache_tv)
    TextView cacheTv;
    @BindView(R2.id.feedback_ll)
    LinearLayout feedbackLl;
    @BindView(R2.id.about_us_ll)
    LinearLayout aboutUsLl;
    @BindView(R2.id.update_ll)
    LinearLayout updateLl;
    private BaseTextViewRecyAdapter baseTextViewRecyAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.appsetting_information;
    }

    @Override
    protected void initView() {
        initToolbar("设置");
        try {
            cacheTv.setText(SystemFileUtil.getTotalCacheSize(context));
            appVersionTv.setText(String.format("当前版本号:%s", VersionUtils.getAppVersionName(context)));
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    @OnClick({R2.id.clear_cache_ll, R2.id.feedback_ll, R2.id.about_us_ll, R2.id.update_ll})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.clear_cache_ll) {
            List<String> messages = new ArrayList<>();
            messages.add("确认清除缓存");
            baseTextViewRecyAdapter = new BaseTextViewRecyAdapter(this, messages);
            baseTextViewRecyAdapter.setClickInterface(position -> {
                SystemFileUtil.clearAllCache(BaseApp.getContext());
                PopwindowUtils.getmInstance().dismissPopWindow();
                try {
                    if (SystemFileUtil.getTotalCacheSize(this).equals("0") || SystemFileUtil.getTotalCacheSize(this).equals("0M") || SystemFileUtil.getTotalCacheSize(this).equals("0.00K"))
                        cacheTv.setText("0.00K");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            PopwindowUtils.getmInstance().showBottomRecyAndCancelPopupWindow(this, baseTextViewRecyAdapter, new LinearLayoutManager(BaseApp.getContext(), LinearLayoutManager.VERTICAL, false));

        } else if (v.getId() == R.id.feedback_ll) {
            Intent intent = new Intent(this, FeedbackActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.about_us_ll) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.update_ll) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            intent.putExtra("isShowUpdate", "true");
            startActivity(intent);
        }
    }

}

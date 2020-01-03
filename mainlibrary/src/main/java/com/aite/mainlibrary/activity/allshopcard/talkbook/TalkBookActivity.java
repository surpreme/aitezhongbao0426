package com.aite.mainlibrary.activity.allshopcard.talkbook;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TalkBookActivity extends BaseActivity<TalkBookContract.View, TalkBookPresenter> implements TalkBookContract.View {
    @BindView(R2.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R2.id.talk_edit)
    EditText talkEdit;
    @BindView(R2.id.post_img_ll)
    RelativeLayout postImgLl;
    private String TALKHIGHT = "1";

    @Override
    protected int getLayoutResId() {
        return R.layout.chat_outbook;
    }

    @Override
    protected void initView() {
        initToolbar("评价");
        initBottomBtn("提交", v -> {
            mPresenter.onTalk(initParams());

        });
        postImgLl.setVisibility(View.GONE);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton1) {
                TALKHIGHT = "1";
            } else if (checkedId == R.id.radioButton2) {
                TALKHIGHT = "2";
            } else if (checkedId == R.id.radioButton3) {
                TALKHIGHT = "3";

            }

        });
//        LogUtils.d(getIntent().getStringExtra("id"));

    }

    /**
     * key	post	字符串	必须			登录key值
     * id	post	整型	必须			服务id
     * score	post	整型	必须	1		服务满意度 1差 2良 3优
     * content	post	字符串	可选			评语
     *
     * @return
     */
    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("id", getIntent().getStringExtra("id"));
        params.put("content", getEditString(talkEdit));
        params.put("score", TALKHIGHT);
        return params;
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

    @Override
    public void onTalkSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean == null) return;
        if (twoSuccessCodeBean.getResult().equals("1")) {
            showToast(twoSuccessCodeBean.getMsg());
            onBackPressed();
        } else {
            showToast("评价失败");
            onBackPressed();
        }
    }

}

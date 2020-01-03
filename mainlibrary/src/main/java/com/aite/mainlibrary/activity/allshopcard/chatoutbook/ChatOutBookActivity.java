package com.aite.mainlibrary.activity.allshopcard.chatoutbook;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.util.FileUtils;
import com.lzy.basemodule.util.ImageUtils;
import com.lzy.okgo.model.HttpParams;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChatOutBookActivity extends BaseActivity<ChatOutBookContract.View, ChatOutBookPresenter> implements ChatOutBookContract.View {
    @BindView(R2.id.radioButton1)
    RadioButton radioButton;
    @BindView(R2.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R2.id.radioButton3)
    RadioButton radioButton3;
    @BindView(R2.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R2.id.add_image_tv)
    TextView addImageTv;
    @BindView(R2.id.post_img)
    ImageView postImg;
    @BindView(R2.id.post_two_img)
    ImageView postTwoImg;
    @BindView(R2.id.post_three_img)
    ImageView postThreeImg;
    @BindView(R2.id.talk_edit)
    EditText talkEdit;
    private String TALKHIGHT = "";
    private List<Uri> mSelected = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.chat_outbook;
    }

    @Override
    protected void initView() {
        initToolbar("评价");
        initBottomBtn("提交", v -> {
            mPresenter.postEditBookInformation(initParams());

        });
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton1) {
                TALKHIGHT = "1";
            } else if (checkedId == R.id.radioButton2) {
                TALKHIGHT = "2";
            } else if (checkedId == R.id.radioButton3) {
                TALKHIGHT = "3";

            }

        });
    }

    /**
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * key	post	字符串	必须			会员登录key
     * order_id	post	整型	必须			订单id
     * score	post	整型	必须	1		评分 1优 2良 3差
     * comment	post	字符串	必须			评语
     * evaluate_image	post	字符串	必须			图片集合，多个图片用英文逗号隔开
     *
     * @return
     */
    private HttpParams initParams() {
        HttpParams params = new HttpParams();
        params.put("key", AppConstant.KEY);
        params.put("order_id", getIntent().getStringExtra("order_id"));
        params.put("comment", getEditString(talkEdit));
        params.put("score", TALKHIGHT);
        if (mSelected != null && !mSelected.isEmpty()) {
            for (int i = 0; i < mSelected.size(); i++) {
                File file = FileUtils.getFileByUri(context, mSelected.get(i));
                if (file != null && file.exists()) {
                    params.put("evaluate_image[" + i + "]", file);
                }
            }
        }
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
    public void onPostEditBookInformationSuccess(Object msg) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            if (mSelected.size() == 1) {
                Glide.with(context).load(FileUtils.getFileByUri(context, mSelected.get(0))).into(postImg);
            } else if (mSelected.size() == 2) {
                Glide.with(context).load(FileUtils.getFileByUri(context, mSelected.get(0))).into(postImg);
                Glide.with(context).load(FileUtils.getFileByUri(context, mSelected.get(1))).into(postTwoImg);
            } else if (mSelected.size() == 3) {
                Glide.with(context).load(FileUtils.getFileByUri(context, mSelected.get(0))).into(postImg);
                Glide.with(context).load(FileUtils.getFileByUri(context, mSelected.get(1))).into(postTwoImg);
                Glide.with(context).load(FileUtils.getFileByUri(context, mSelected.get(2))).into(postThreeImg);
            }
        }
    }


    @OnClick(R2.id.add_image_tv)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_image_tv) {
            openImg(this, 3, BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE);
        }
    }

}

package com.aite.mainlibrary.activity.allsetting.addhealthbook;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.util.FileUtils;
import com.lzy.basemodule.util.TimeUtils;
import com.lzy.okgo.model.HttpParams;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddHealthBookActivity extends BaseActivity<AddHealthBookContract.View, AddHealthBookPresenter> implements AddHealthBookContract.View {

    @BindView(R2.id.add_image_tv)
    TextView addImageTv;
    @BindView(R2.id.post_img)
    ImageView postImg;
    @BindView(R2.id.sick_name_edit)
    EditText sickNameEdit;
    @BindView(R2.id.sick_information_edit)
    EditText sickInformationEdit;
    @BindView(R2.id.sick_time_tv)
    TextView sickTimeTv;
    @BindView(R2.id.choice_sick_time_ll)
    LinearLayout choiceSickTimeLl;
    @BindView(R2.id.post_two_img)
    ImageView postTwoImg;
    @BindView(R2.id.post_three_img)
    ImageView postThreeImg;
    private List<Uri> mSelected = new ArrayList<>();
    private String SICKTIMER = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.actviity_add_health;
    }

    @Override
    protected void initView() {
        initToolbar("添加", "提交", v -> {
            if (isStringEmpty(getEditString(sickNameEdit)) || isStringEmpty(getEditString(sickInformationEdit))) {
                showToast("请检查信息", Gravity.TOP);
                return;
            }
            mPresenter.PostInformation(initParams());


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
//            ImageUtils.getmInstance().photoClip(this, mSelected.get(0));
            if (mSelected.isEmpty()) return;
            Glide.with(context).load(mSelected.get(0)).into(postImg);
            if (mSelected.size() == 2) {
                Glide.with(context).load(mSelected.get(0)).into(postImg);
                Glide.with(context).load(mSelected.get(1)).into(postTwoImg);

            }
            if (mSelected.size() == 3) {
                Glide.with(context).load(mSelected.get(0)).into(postImg);
                Glide.with(context).load(mSelected.get(1)).into(postTwoImg);
                Glide.with(context).load(mSelected.get(2)).into(postThreeImg);
            }

        }

    }

    @OnClick({R2.id.add_image_tv, R2.id.choice_sick_time_ll})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_image_tv) {
            openImg(this, 3, BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE);


        } else if (v.getId() == R.id.choice_sick_time_ll) {
            hideSoftWare();
            initChoiceTimer((date, v1) -> {
                sickTimeTv.setText(TimeUtils.stampToDate(date.getTime()));
                SICKTIMER = TimeUtils.stampToDate(date.getTime());
            }, "选择开始时间", 1999, true);
            pvTime.show();


        }
    }

    /**
     * 多个文件上传
     *
     * @return
     */
    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("type", getIntent().getStringExtra("type"));
        httpParams.put("name", getEditString(sickNameEdit));
        httpParams.put("time", isStringEmpty(SICKTIMER) ? "" : SICKTIMER);
        httpParams.put("description", getEditString(sickInformationEdit));
        if (mSelected != null && !mSelected.isEmpty()) {
            for (int i = 0; i < mSelected.size(); i++) {
                File file = FileUtils.getFileByUri(context, mSelected.get(i));
                if (file != null && file.exists()) {
                    httpParams.put("imgs[" + i + "]", file);
                }
            }
        }
        return httpParams;
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
    public void onPostInformationSuccess(Object msg) {
        showToast((String) msg, Gravity.TOP);
        onBackPressed();

    }


}

package com.aite.mainlibrary.activity.allsetting.addbookdispute;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.DisputeTypeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.PopwindowUtils;
import com.lzy.basemodule.adpter.TextWithIdBaseRecyAdapter;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.FileUtils;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddBookDisputeActivity extends BaseActivity<AddBookDisputeContract.View, AddBookDisputePresenter> implements AddBookDisputeContract.View {

    @BindView(R2.id.bottom_btn)
    Button bottomBtn;
    @BindView(R2.id.choice_iv_ll)
    LinearLayout choiceIvLl;
    @BindView(R2.id.src_iv)
    ImageView srcIv;
    @BindView(R2.id.dispute_type_ll)
    LinearLayout disputeTypeLl;
    @BindView(R2.id.type_tv)
    TextView typeTv;
    @BindView(R2.id.season_edit)
    EditText seasonEdit;
    private List<Uri> mSelected = new ArrayList<>();
    private TextWithIdBaseRecyAdapter textWithIdBaseRecyAdapter;
    private List<DisputeTypeBean.SubjectListBean> subjectListBeans = new ArrayList<>();
    private int POSITION = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.addbook_dispute;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        initToolbar("交易投诉");
        bottomBtn.setBackground(getDrawable(R.drawable.round_background_yellow));
        bottomBtn.setText("提交申请");
        bottomBtn.setOnClickListener(v -> {
            if (mSelected.size() == 1) {
                mPresenter.postAllDispute(initListHttpParams(true,
                        new ContentValue("input_order_id", getIntent().getStringExtra("order_id")),
                        new ContentValue("input_goods_id", getIntent().getStringExtra("goods_id")),
                        new ContentValue("input_complain_content", getEditString(seasonEdit)),
                        new ContentValue("input_complain_subject", subjectListBeans.get(POSITION).getId() + "," + subjectListBeans.get(POSITION).getNasme()),
                        new ContentValue("complain_pic1", FileUtils.getFileByUri(context, mSelected.get(0)))
                        )
                );

            } else if (mSelected.size() == 2) {
                mPresenter.postAllDispute(initListHttpParams(true,
                        new ContentValue("input_order_id", getIntent().getStringExtra("order_id")),
                        new ContentValue("input_goods_id", getIntent().getStringExtra("goods_id")),
                        new ContentValue("input_complain_content", getEditString(seasonEdit)),
                        new ContentValue("input_complain_subject", subjectListBeans.get(POSITION).getId() + "," + subjectListBeans.get(POSITION).getNasme()),
                        new ContentValue("complain_pic1", FileUtils.getFileByUri(context, mSelected.get(0))),
                        new ContentValue("complain_pic2", FileUtils.getFileByUri(context, mSelected.get(1))))
                );

            } else if (mSelected.size() == 3) {
                mPresenter.postAllDispute(initListHttpParams(true,
                        new ContentValue("input_order_id", getIntent().getStringExtra("order_id")),
                        new ContentValue("input_goods_id", getIntent().getStringExtra("goods_id")),
                        new ContentValue("input_complain_content", getEditString(seasonEdit)),
                        new ContentValue("input_complain_subject", subjectListBeans.get(POSITION).getId()),
                        new ContentValue("complain_pic1", FileUtils.getFileByUri(context, mSelected.get(0))),
                        new ContentValue("complain_pic2", FileUtils.getFileByUri(context, mSelected.get(1))),
                        new ContentValue("complain_pic3", FileUtils.getFileByUri(context, mSelected.get(2)))
                        )
                );

            } else {
                mPresenter.postAllDispute(initListHttpParams(true,
                        new ContentValue("input_order_id", getIntent().getStringExtra("order_id")),
                        new ContentValue("input_goods_id", getIntent().getStringExtra("goods_id")),
                        new ContentValue("input_complain_content", getEditString(seasonEdit)),
                        new ContentValue("input_complain_subject", subjectListBeans.get(POSITION).getId() + "," + subjectListBeans.get(POSITION).getNasme())
                        )
                );

            }


        });

    }

    @OnClick({R2.id.choice_iv_ll, R2.id.dispute_type_ll})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.choice_iv_ll)
            openImg(this, 3, BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE);
        if (v.getId() == R.id.dispute_type_ll) {
            if (subjectListBeans.isEmpty()) {
                showToast("数据错误");
                return;
            }
            textWithIdBaseRecyAdapter = new TextWithIdBaseRecyAdapter(context, subjectListBeans);
            textWithIdBaseRecyAdapter.setClickInterface(position -> {
                typeTv.setText(subjectListBeans.get(POSITION = position).getNasme());
                PopwindowUtils.getmInstance().dismissPopWindow();
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            PopwindowUtils.getmInstance().showRecyPopupWindow(context, textWithIdBaseRecyAdapter, linearLayoutManager, disputeTypeLl);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
//            ImageUtils.getmInstance().photoClip(this, mSelected.get(0));
            if (mSelected.isEmpty()) return;
            Glide.with(context).load(mSelected.get(0)).into(srcIv);
        }

    }

    /**
     * startActivityWithCls(AddBookDisputeActivity.class, 0,
     * new ContentValue("order_id", extendOrderGoodsBeans.get(position).getOrder_id()),
     * new ContentValue("goods_id", extendOrderGoodsBeans.get(position).getGoods_id()));
     */
    @Override
    protected void initDatas() {
        if (getIntent().getStringExtra("order_id") != null && getIntent().getStringExtra("goods_id") != null)
            mPresenter.getDisputeType(initListHttpParams(true,
                    new ContentValue("order_id", getIntent().getStringExtra("order_id")),
                    new ContentValue("goods_id", getIntent().getStringExtra("goods_id"))
            ));

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetDisputeTypeSuccess(Object msg) {
        subjectListBeans = ((DisputeTypeBean) msg).getSubject_list();


    }

    @Override
    public void onPostAllDisputeSuccess(Object msg) {

        if (msg.equals("0")) {
            showToast("投诉失败哦");
        } else if (msg.equals("200")) {
            showToast("投诉成功 可去详情查看投诉进度");
        }
        onBackPressed();
    }

}
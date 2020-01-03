package com.example.ui.activity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.view.RatingBarView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间 2019/12/20 16:05
 * 描述:
 */
public class EvaluateActivity extends BaseActivity {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R2.id.tv_type)
    TextView mTvType;
    @BindView(R2.id.tv_Lv)
    TextView mTvLV;
    @BindView(R2.id.starView)
    RatingBarView mStarView;
    @BindView(R2.id.et_evaluate)
    EditText mEtEvaluate;
    @BindView(R2.id.but_submit)
    Button mButSubmit;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initView() {
        initToolbar("评价");
        mTvTitleRight.setVisibility(View.GONE);

        //设置星级
        mStarView.setStar(4);
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


    @OnClick({R2.id.et_evaluate, R2.id.but_submit})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.but_submit) {

        }
    }
}

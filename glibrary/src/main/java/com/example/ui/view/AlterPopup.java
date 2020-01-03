package com.example.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxj.xpopup.core.CenterPopupView;
import com.lzy.basemodule.R;

import androidx.annotation.NonNull;

/**
 * 创建时间 2019/12/20 18:56
 * 描述: 修改咨询内容
 */
public class AlterPopup extends CenterPopupView {
    private Context mContext;

    private onSubmitClickListener listener;
    private EditText mEtTime;
    private EditText mEtPric;


    public void setListener(onSubmitClickListener listener) {
        this.listener = listener;
    }

    public interface onSubmitClickListener {
        void onSubmitClick(String tiem, String price);
    }

    public AlterPopup(@NonNull Context context) {
        super(context);
        mContext = context;
    }


    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.alter_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        initView();
    }

    private void initView() {
        ImageView mIvClose = (ImageView) findViewById(R.id.iv_close);

        TextView mTvSubmit = (TextView) findViewById(R.id.tv_submit);


        mEtTime = (EditText) findViewById(R.id.et_time);

        mEtPric = (EditText) findViewById(R.id.et_price);

        mIvClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });

        mTvSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = mEtTime.getText().toString();
                String pric = mEtPric.getText().toString();
                if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(pric)) {
                    listener.onSubmitClick(time, pric);
                    dismiss(); // 关闭弹窗
                }
            }
        });


    }

}

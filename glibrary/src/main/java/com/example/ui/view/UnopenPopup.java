package com.example.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxj.xpopup.core.CenterPopupView;
import com.lzy.basemodule.R;

import androidx.annotation.NonNull;

/**
 * 创建时间 2019/12/16 16:50
 * 描述: 未开放弹窗
 */
public class UnopenPopup extends CenterPopupView {

    ImageView mIvClose;
    TextView mTvContent;


    private String mContent; //内容

    public UnopenPopup(@NonNull Context context, String content) {
        super(context);
        mContent = content;
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.unopen_popup;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        initView();
    }

    private void initView() {
         mIvClose = (ImageView) findViewById(R.id.iv_close);
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvContent.setText(mContent);
    }
}

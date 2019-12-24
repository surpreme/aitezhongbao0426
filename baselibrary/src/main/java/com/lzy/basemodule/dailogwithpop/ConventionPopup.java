package com.lzy.basemodule.dailogwithpop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.lzy.basemodule.R;

/**
 * 创建时间 2019/12/16 17:52
 * 描述: 常规的弹窗
 */
public class ConventionPopup extends CenterPopupView implements View.OnClickListener {

    private Context mContext;

    private String mContent;

    private String mButText1;

    private String mButText2;


    ImageView mIvClose;
    TextView mTvContent, mBut1, mBut2;


    private onBut1ClickListener mOnBut1ClickListener;

    private onBut2ClickListener mOnBut2ClickListener;

    public void setOnBut1ClickListener(onBut1ClickListener onBut1ClickListener) {
        mOnBut1ClickListener = onBut1ClickListener;
    }

    public void setOnBut2ClickListener(onBut2ClickListener onBut2ClickListener) {
        mOnBut2ClickListener = onBut2ClickListener;
    }

    public ConventionPopup(@NonNull Context context, String content, String butText1, String butText2) {
        super(context);
        mContext = context;
        mContent = content;
        mButText1 = butText1;
        mButText2 = butText2;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.convention_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mBut1 = (TextView) findViewById(R.id.but_1);
        mBut2 = (TextView) findViewById(R.id.but_2);
        mIvClose = (ImageView) findViewById(R.id.iv_close);

        mTvContent.setText(mContent);
        mBut1.setText(mButText1);
        mBut2.setText(mButText2);

        mBut1.setOnClickListener(this);
        mBut2.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_close) {
            dismiss(); // 关闭弹窗
        } else if (v.getId() == R.id.but_1) {
            if (mOnBut1ClickListener != null) {
                mOnBut1ClickListener.onBut1ClickListener();
            }
        } else if (v.getId() == R.id.but_2) {
            if (mOnBut2ClickListener != null) {
                mOnBut2ClickListener.onBut2ClickListener();
            }
        }
    }


    /**
     * 按键1
     */
    public interface onBut1ClickListener {
        void onBut1ClickListener();
    }

    /**
     * 按键2
     */
    public interface onBut2ClickListener {
        void onBut2ClickListener();
    }

}

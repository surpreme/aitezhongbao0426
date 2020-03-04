package com.example.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.Utils.TextUtil;
import com.example.app.Constants;
import com.example.bean.LabelBean;
import com.example.glibrary.R;
import com.example.glibrary.R2;
import com.lxj.xpopup.impl.PartShadowPopupView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建时间 2019/12/31 11:48
 * 描述:
 */
public class ScreenPopup extends PartShadowPopupView {

    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.flow_Layout)
    FlowLayout mFlowLayout;
    @BindView(R2.id.tv_reset)
    TextView mTvReset;
    @BindView(R2.id.tv_complete)
    TextView mTvComplete;
    private Context mContext;

    private List<LabelBean> mData;


    //选中字段
    private List<String> isSelected = new ArrayList<>();


    private onCompleteListener mOnCompleteListener;

    public onCompleteListener getOnCompleteListener() {
        return mOnCompleteListener;
    }

    public void setOnCompleteListener(onCompleteListener onCompleteListener) {
        mOnCompleteListener = onCompleteListener;
    }


    public ScreenPopup(@NonNull Context context, List<LabelBean> data) {
        super(context);
        mContext = context;
        mData = data;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_screen;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTvTitle.setText("医生级别");
        initFlowLayout(mData);


    }

    private void initFlowLayout(List<LabelBean> mData) {
        for (int i = 0; i < mData.size(); i++) {
            //添加到集合中
            TextView textView = new TextView(mContext);
            textView.setText(mData.get(i).getText());
            textView.setSelected(mData.get(i).getSelected());
            textView.setBackgroundResource(R.drawable.label_selector);
            textView.setGravity(Gravity.CENTER);
            int padding4 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_4);
            int padding8 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_8);
            textView.setPadding(padding8, padding4, padding8, padding4);
            textView.setTextColor(mContext.getResources().getColor(R.color.classify_text_selector));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.get(finalI).setSelected(!mData.get(finalI).getSelected());
                    textView.setSelected(mData.get(finalI).getSelected());
                    //添加集合
                    if (textView.isSelected()) {
                        isSelected.add(isSelected.contains(mData.get(finalI).getText()) ? null : mData.get(finalI).getText());
                    } else {
                        isSelected.remove(isSelected.contains(mData.get(finalI).getText()) ? mData.get(finalI).getText() : null);
                    }
                }
            });
            mFlowLayout.addView(textView);
        }
    }

    @OnClick({R2.id.tv_reset, R2.id.tv_complete})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_reset) {
            //重置
            for (int i = 0; i < mData.size(); i++) {
                mData.get(i).setSelected(false);
            }
            mFlowLayout.removeAllViewsInLayout();
            initFlowLayout(mData);
        } else if (id == R.id.tv_complete) {
            String s1 = "";
            for (int i = 0; i < mData.size(); i++) {
                String s = mData.get(i).getText();
                if (mData.get(i).getSelected()) {
                    if (Constants.DoctorType1.equals(s)) {
                        s1 += 0 + ",";
                    } else if (Constants.DoctorType2.equals(s)) {
                        s1 += 1 + ",";
                    } else if (Constants.DoctorType3.equals(s)) {
                        s1 += 2 + ",";
                    } else if (Constants.DoctorType4.equals(s)) {
                        s1 += 3 + ",";
                    }
                }
            }
            mOnCompleteListener.getData(s1);
        }
    }


    public interface onCompleteListener {
        void getData(String data);
    }

}

package com.lzy.basemodule.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.lzy.basemodule.R;
import com.lzy.basemodule.util.SystemUtil;

/**
 * 创建时间 2019/12/20 11:08
 * 描述: 全部订单 / 续费价格
 */
public class MorePopWindow extends PopupWindow {

    private OnPopWindowItemClickListener listener;
    private View contentView;
    private Context mContext;

    public interface OnPopWindowItemClickListener {
        void onOrderClick();

        void onRenewClick();


        void addDoctrInfo();

    }

    @SuppressLint("InflateParams")
    public MorePopWindow(final Context context, OnPopWindowItemClickListener listener) {
        this.listener = listener;
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.order_popup, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0000000000);
//        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
//        this.setBackgroundDrawable(dw);

        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationMainTitleMore);
        contentView.findViewById(R.id.tv_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onOrderClick();
                }
                dismiss();
            }
        });
        contentView.findViewById(R.id.tv_renew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRenewClick();
                }
                dismiss();
            }
        });

        contentView.findViewById(R.id.tv_add_doctr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.addDoctrInfo();
                }
                dismiss();
            }
        });


    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, 0, SystemUtil.dp2px(mContext, 19));
        } else {
            this.dismiss();
        }
    }
}

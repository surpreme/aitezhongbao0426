package com.lzy.basemodule.screen;

import android.content.Context;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Auther: valy
 * @datetime: 2019-12-21
 * @desc:
 */
public class ScreenUtils {
    public static void setBackGroundAlpha(float alpha, Context context) {
        WindowManager.LayoutParams layoutParams = ((AppCompatActivity) context).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((AppCompatActivity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((AppCompatActivity) context).getWindow().setAttributes(layoutParams);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}

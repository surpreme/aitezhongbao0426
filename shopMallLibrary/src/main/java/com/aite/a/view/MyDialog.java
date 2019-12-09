package com.aite.a.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/12/2.
 */
public class MyDialog extends Dialog {
	private static int default_width = 320; // 默认宽度
	private static int default_height = 550;// 默认高度

	public MyDialog(Context context, View layout, int style) {
		this(context, default_width, default_height, layout, style);

	}

	public MyDialog(Context context, int width, int height, View layout,
			int style) {
		super(context, style);
		setContentView(layout);
		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.gravity = Gravity.CENTER;
		params.width = width;
		window.setAttributes(params);
	}

}

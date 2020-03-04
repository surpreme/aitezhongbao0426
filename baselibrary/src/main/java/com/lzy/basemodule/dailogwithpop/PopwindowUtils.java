package com.lzy.basemodule.dailogwithpop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.R;
import com.lzy.basemodule.screen.ScreenUtils;
import com.lzy.basemodule.view.PickerView;

import java.util.List;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class PopwindowUtils {
    private static PopwindowUtils mInstance;
    private PopupWindow popupWindow;
    private static final int loaddinglayoutid = R.layout.lodding_anim_layout;
    private static final int loaddingdatalayoutid = R.layout.datalodding_anim_layout;
    private static final int newuertogtherinformaionlayoutid = R.layout.together_infomation_pop;
    private static final int errorlayoutid = R.layout.error_toast;
    private static final int recylayoutid = R.layout.recy_layout;
    private static final int withaphlarecylayoutid = R.layout.pop_recy_layout;
    private static final int threeRecylayoutid = R.layout.three_choice;
    private static final int chioceGenderlayoutid = R.layout.choice_gender;
    private static final int bootomrecylayoutid = R.layout.choice_bottom;
    private static final int qrcodelayoutid = R.layout.pop_img;
    private static final int qrcode2layoutid = R.layout.pop_qr;
    private static final int payawylayoutid = R.layout.pop_pay_awy;
    private static final int choicethreerecylayoutid = R.layout.choice_three_recy;
    private static final int popdailoglayoutid = R.layout.popdailog;
    private static final int popchoiceqrtypelayoutid = R.layout.popqrunfact_service;
    private static final int popsuredailoglayoutid = R.layout.popsuredailog;
    private static final int popwechatchoicenewslayoutid = R.layout.wechat_pop_newsuser;
    private static final int bottom_recyandcanceLayout = R.layout.base_bottom_recyandcancel_pop;
    private static final int lookImagelayoutid = R.layout.base_look_image_pop;

    public static PopwindowUtils getmInstance() {
        if (mInstance == null) {
            synchronized (PopwindowUtils.class) {
                if (mInstance == null) {
                    mInstance = new PopwindowUtils();
                }
            }
        }
        return mInstance;
    }


    public void showloaddingPopupWindow(final Activity context) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(loaddinglayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, 1000, 700, false);
        final LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
//        lottieAnimationView.playAnimation();   //播放

        popupWindow.setOutsideTouchable(false);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                lottieAnimationView.cancelAnimation();  //取消
            }
        });

    }

    public void showLookImagePopupWindow(final Context context, Bitmap bitmap) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(lookImagelayoutid, null);
        ScreenUtils.setBackGroundAlpha(0.5f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        ImageView show_iv = view.findViewById(R.id.show_iv);
        ImageView close_iv = view.findViewById(R.id.close_iv);
        show_iv.setImageBitmap(bitmap);
        close_iv.setOnClickListener(v -> popupWindow.dismiss());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> ScreenUtils.setBackGroundAlpha(1.0f, context));

    }

    public void showLookImagePopupWindow(final Context context, OnClickLstenerInterface.OnThingClickInterfaces onClickListener) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(popchoiceqrtypelayoutid, null);
        ScreenUtils.setBackGroundAlpha(0.5f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        TextView timebank_tv = view.findViewById(R.id.timebank_tv);
        TextView helpdoctor_tv = view.findViewById(R.id.helpdoctor_tv);
        TextView breath_tv = view.findViewById(R.id.breath_tv);
        timebank_tv.setOnClickListener(v -> {
            onClickListener.getPosition(1);

        });
        helpdoctor_tv.setOnClickListener(v -> {
            onClickListener.getPosition(2);

        });
        breath_tv.setOnClickListener(v -> {
            onClickListener.getPosition(3);

        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> ScreenUtils.setBackGroundAlpha(1.0f, context));

    }

    public void showQrPopupWindow(final Context context, Bitmap bitmap, String information) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(qrcodelayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, 800, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        ImageView qrcode_bar = view.findViewById(R.id.qrcode_iv);
        qrcode_bar.setImageBitmap(bitmap);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        TextView information_tv = view.findViewById(R.id.information_tv);
        information_tv.setText(information);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });

    }

    public void showImgPopupWindow(final Context context, String url, String information) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(qrcodelayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, 800, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        ImageView qrcode_iv = view.findViewById(R.id.qrcode_iv);
        Glide.with(context).load(url).into(qrcode_iv);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        TextView information_tv = view.findViewById(R.id.information_tv);
        information_tv.setText(information);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });

    }

    public void showImgPopupWindow(final Context context, String url, String information, PopupWindow.OnDismissListener onDismissListener) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(qrcodelayoutid, null);
        popupWindow = new PopupWindow(view, 800, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        ImageView qrcode_iv = view.findViewById(R.id.qrcode_iv);
        Glide.with(context).load(url).into(qrcode_iv);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        TextView information_tv = view.findViewById(R.id.information_tv);
        information_tv.setText(information);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(onDismissListener);

    }

    public interface OnWechatChoiceBackInterface {
        void onGetWay(String way);
    }

    public void showwechatPopupWindow(final Context context, OnWechatChoiceBackInterface onWechatChoiceBackInterface) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(popwechatchoicenewslayoutid, null);
        setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        TextView binderOlderTv = view.findViewById(R.id.binder_older_tv);
        TextView newUserTv = view.findViewById(R.id.new_user_tv);
        TextView cancelTv = view.findViewById(R.id.cancel_tv);
        binderOlderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWechatChoiceBackInterface.onGetWay("BINDEROLDER");
                dismissPopWindow();
            }
        });
        newUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWechatChoiceBackInterface.onGetWay("NEWSUSERS");
                dismissPopWindow();

            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();

            }
        });
        popupWindow.setOutsideTouchable(false);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });

    }

    class oo {
        int a;
        List<bb> b;

        class bb {
            int c;


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showThreeRecyPopupWindow(final Context context, List<String> yearlist, List<String> mondaylist, View ui) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(choicethreerecylayoutid, null);
        setBackGroundAlpha(1.0f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, 330, false);
        PickerView yearPick = view.findViewById(R.id.year_picker);
        PickerView dayPick = view.findViewById(R.id.day_picker);
        yearPick.setDataList(yearlist);
        yearPick.setOnSelectListener(new PickerView.OnSelectListener() {
            @Override
            public void onSelect(View view, String selected) {
                dayPick.setDataList(mondaylist);

            }
        });
        dayPick.setOnSelectListener(new PickerView.OnSelectListener() {
            @Override
            public void onSelect(View view, String selected) {

            }
        });

        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.showAsDropDown(ui, 0, 0, Gravity.CENTER);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });

    }

    public void showChioceGenderPopupWindow(final Context context, int grivaty, float alpah, OnClickLstenerInterface.OnThingClickInterface onThingClickInterface) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(chioceGenderlayoutid, null);
        setBackGroundAlpha(alpah, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        TextView superman_tv = view.findViewById(R.id.superman_tv);
        TextView woman_tv = view.findViewById(R.id.woman_tv);
        TextView cancel_tv = view.findViewById(R.id.cancel_tv);

        superman_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThingClickInterface.getString("superman");
                popupWindow.dismiss();

            }
        });
        woman_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThingClickInterface.getString("woman");
                popupWindow.dismiss();

            }
        });
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, grivaty, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            }
        });

    }

    public void showChioceBottomPopupWindow(final Context context, int grivaty, float alpah, RecyclerView.Adapter adapter) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(bootomrecylayoutid, null);
        setBackGroundAlpha(alpah, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        popupWindow.setFocusable(true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        TextView cancel_tv = view.findViewById(R.id.cancel_tv);
        recyclerView.setLayoutManager(linearLayoutManager);
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });

        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, grivaty, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            }
        });

    }

    public void showRecyPopupWindow(final Context context, RecyclerView.Adapter recyadpater, LinearLayoutManager linearLayoutManager, int grivaty, float alpah) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(recylayoutid, null);
        setBackGroundAlpha(alpah, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        popupWindow.setFocusable(true);

        //图片设置透明度
//        recyclerView.setAlpha(0.4f);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyadpater);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
//        popupWindow.showAsDropDown(ui, 0, 0);
        popupWindow.showAtLocation(view, grivaty, 0, 10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            }
        });

    }

    public void showDialogPopupWindow(final Context context, String titles, String information, String sureStr, String cancelStr, View.OnClickListener listenersure, View.OnClickListener listenercancel) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(popdailoglayoutid, null);
        ScreenUtils.setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        final TextView title_tv = view.findViewById(R.id.title_tv);
        final TextView information_tv = view.findViewById(R.id.information_tv);
        final Button sure_btn = view.findViewById(R.id.sure_btn);
        final Button cancel_btn = view.findViewById(R.id.cancel_btn);
        information_tv.setLines(3);
        view.setAlpha(0.9f);
        if (isStringUnEmpty(titles)) title_tv.setText(titles);
        if (isStringUnEmpty(sureStr)) sure_btn.setText(sureStr);
        if (isStringUnEmpty(cancelStr)) cancel_btn.setText(cancelStr);
        information_tv.setText(information);
        sure_btn.setOnClickListener(listenersure);
        if (listenercancel == null) {
            cancel_btn.setOnClickListener(v -> {
                ScreenUtils.setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            });
        } else {
            cancel_btn.setOnClickListener(listenercancel);
        }

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            ScreenUtils.setBackGroundAlpha(1.0f, context);
            dismissPopWindow();
        });

    }

    public void showSureDialogPopupWindow(final Context context, String titles, String information, String sureStr, View.OnClickListener listenersure) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(popsuredailoglayoutid, null);
        ScreenUtils.setBackGroundAlpha(0.6f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        final TextView title_tv = view.findViewById(R.id.title_tv);
        final TextView information_tv = view.findViewById(R.id.information_tv);
        final Button sure_btn = view.findViewById(R.id.sure_btn);
        information_tv.setLines(3);
        view.setAlpha(0.9f);
        if (isStringUnEmpty(titles)) title_tv.setText(titles);
        if (isStringUnEmpty(sureStr)) sure_btn.setText(sureStr);
        information_tv.setText(information);
        sure_btn.setOnClickListener(listenersure);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            ScreenUtils.setBackGroundAlpha(1.0f, context);
            dismissPopWindow();
        });

    }

    private boolean isStringUnEmpty(String str) {
        return str != null && !str.equals("") && !str.equals("null");
    }

    public void showdiadlogPopupWindow(final Context context, String information, View.OnClickListener listener) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(popdailoglayoutid, null);
        setBackGroundAlpha(0.8f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        final TextView information_tv = view.findViewById(R.id.information_tv);
        final Button sure_btn = view.findViewById(R.id.sure_btn);
        final Button cancel_btn = view.findViewById(R.id.cancel_btn);
        information_tv.setText(information);
        sure_btn.setOnClickListener(listener);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            }
        });

    }

    public void showRecyPopupWindow(final Context context, RecyclerView.Adapter recyadpater, RecyclerView.LayoutManager layoutManager, View ui) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(recylayoutid, null);
        setBackGroundAlpha(1.0f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        popupWindow.setFocusable(true);
        view.setBackgroundColor(context.getResources().getColor(R.color.white));

        //图片设置透明度
//        recyclerView.setAlpha(0.4f);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyadpater);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(ui, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                recyclerView.setAlpha(1.0f);
                dismissPopWindow();
            }
        });

    }

    public void showBottomRecyAndCancelPopupWindow(final Context context, RecyclerView.Adapter adapter, LinearLayoutManager linearLayoutManager) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(bottom_recyandcanceLayout, null);
        ScreenUtils.setBackGroundAlpha(0.5f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        RecyclerView recycler_view = view.findViewById(R.id.recycler_view);
        TextView cancel_tv = view.findViewById(R.id.cancel_tv);
        cancel_tv.setOnClickListener(v -> popupWindow.dismiss());
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(linearLayoutManager);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(() -> ScreenUtils.setBackGroundAlpha(1.0f, context));

    }

    public void showRecyPopupWindow(final Context context, int height, RecyclerView.Adapter recyadpater, LinearLayoutManager linearLayoutManager, View ui, PopupWindow.OnDismissListener onDismissListeners) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(withaphlarecylayoutid, null);
        setBackGroundAlpha(1.0f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, height, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        popupWindow.setFocusable(true);
        View background_layout = view.findViewById(R.id.background_layout);
        background_layout.setAlpha(0.7f);
        recyclerView.setBackgroundColor(context.getResources().getColor(R.color.white));
        //图片设置透明度
//        recyclerView.setAlpha(0.4f);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyadpater);
        popupWindow.setOutsideTouchable(false);
        background_layout.setOnClickListener(v -> {
            dismissPopWindow();
            ScreenUtils.setBackGroundAlpha(1.0f, context);
        });
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(ui, 0, 0, Gravity.BOTTOM);
        }
        popupWindow.setOnDismissListener(onDismissListeners);
    }


    public void showPayRecyPopupWindow(final Context context, int gravity, RecyclerView.Adapter adapter, LinearLayoutManager linearLayoutManager) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(payawylayoutid, null);
//        ButterKnife.bind(this,view);
        setBackGroundAlpha(0.7f, context);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setFocusable(false);
        //设置触摸
        popupWindow.setTouchable(true);
        RecyclerView recycler_view = view.findViewById(R.id.recycler_view);
        ImageView closeiv = view.findViewById(R.id.close_iv);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.setAdapter(adapter);
        closeiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow();
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, gravity, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                dismissPopWindow();
            }
        });

    }

    public void showAsDownPopupWindow(final Activity activity, String errormsg) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(activity).inflate(errorlayoutid, null);
        setBackGroundAlpha(0.6f, activity);
        popupWindow = new PopupWindow(view, 1000, 100, false);
        final TextView errorTv = view.findViewById(R.id.error_tv);
        errorTv.setText(errormsg);
        ImageView background = view.findViewById(R.id.notification_background_img);
        //图片设置透明度
        background.setAlpha(0.4f);
//        lottieAnimationView.playAnimation();   //播放
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, activity);
            }
        });

    }

    @SuppressLint("WrongConstant")
    public void showErrorPopupWindow(final Activity activity, String errormsg) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(activity).inflate(errorlayoutid, null);
        setBackGroundAlpha(0.6f, activity);
        popupWindow = new PopupWindow(view, 1000, 100, false);
        final TextView errorTv = view.findViewById(R.id.error_tv);
        errorTv.setText(errormsg);
        ImageView background = view.findViewById(R.id.notification_background_img);
        //图片设置透明度
        background.setAlpha(0.4f);
//        lottieAnimationView.playAnimation();   //播放
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        //防止PopupWindow被软件盘挡住
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, activity);
            }
        });

    }

    public void showNewUerTogtherInformaionPopupWindow(final Activity context, String url) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(newuertogtherinformaionlayoutid, null);
        setBackGroundAlpha(0.5f, context);
        popupWindow = new PopupWindow(view,
                context.getResources().getDisplayMetrics().widthPixels * 3 / 4,
                context.getResources().getDisplayMetrics().heightPixels * 3 / 4,
                true);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_view);
        WebView webview = view.findViewById(R.id.webview);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) popupWindow.dismiss();
            }
        });
        popupWindow.setOutsideTouchable(false);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
            }
        });

    }

    public void showloaddingDataPopupWindow(final Context context) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(loaddingdatalayoutid, null);
        setBackGroundAlpha(0.5f, context);
        popupWindow = new PopupWindow(view, 1000, 700, false);
        final LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        lottieAnimationView.playAnimation();   //播放

        popupWindow.setOutsideTouchable(false);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                lottieAnimationView.cancelAnimation();  //取消
            }
        });

    }

    public void dismissPopWindow() {
        if (popupWindow != null && popupWindow.isShowing())
            popupWindow.dismiss();
    }


    void setBackGroundAlpha(float alpha, Context context) {
        WindowManager.LayoutParams layoutParams = ((AppCompatActivity) context).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((AppCompatActivity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((AppCompatActivity) context).getWindow().setAttributes(layoutParams);
    }
}
// lottieLike.playAnimation();   //播放
//        lottieLike.pauseAnimation();  //暂停
//        lottieLike.cancelAnimation();  //取消
//        lottieLike.getDuration();   //获取动画时长
//        lottieLike.addAnimatorListener(new Animator.AnimatorListener() {  //添加动画监听
//        @Override
//        public void onAnimationStart(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationEnd(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationCancel(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationRepeat(Animator animation) {
//
//        }
//    });

/**
 * popwindow需要延时操作 未初始化完成不可以显示（view）
 * //
 */
//    @SuppressLint("HandlerLeak")
//    private Handler popupHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
////                    PopwindowUtils.getmInstance().showloaddingDataPopupWindow(context);
//                    break;
//                case 1:
////                    PopwindowUtils.getmInstance().dismissPopWindow();
//                    break;
//
//            }
//        }
//
//    };
//        popupHandler.sendEmptyMessageDelayed(0, 1000);
//        popupHandler.sendEmptyMessageDelayed(1, 2000);
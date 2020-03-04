package com.aite.mainlibrary.activity.allqr.qrcode;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;

import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TextUtil;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.lzy.okgo.model.HttpParams;

import java.net.URI;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;


public class QrCodeActivity extends BaseActivity<QrCodeContract.View, QrCodePresenter> implements QrCodeContract.View, QRCodeView.Delegate, View.OnClickListener {


    @BindView(R2.id.zxingview)
    ZXingView zXingView;
    private static final int REQUEST_CODE = 453876;

    @Override
    protected int getLayoutResId() {
        return R.layout.qrcode_layout;
    }

    @Override
    protected void initView() {
        initToolbar("扫码");
        zXingView.setDelegate(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        zXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
//               getDatas(data);
            }


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
        zXingView.startSpot();
        zXingView.setType(BarcodeType.ALL, null);
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

    private HttpParams initDeviceParams(String IMEI) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("imei", IMEI);
        return httpParams;
    }

    private HttpParams initParams(String ID) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("id", ID);
        httpParams.put("type", getIntent().getStringExtra("type"));
        return httpParams;
    }

    private HttpParams initUnfactBookParams(String VR_CODE) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("vr_code", VR_CODE);
        return httpParams;
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        LogUtils.d(result);
//        Uri uri = Uri.parse(result);
//        String id = uri.getQueryParameter("id");
        showToast("扫码成功", Gravity.TOP);
//        mPresenter.sureBook(initParams(result));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getIntent().getStringExtra("type") != null) {
                    if (getIntent().getStringExtra("type").equals("watch"))
                        mPresenter.BindingDevice(initDeviceParams(getUrlKey(result, "imei")));
                    else if (getIntent().getStringExtra("type").equals("unfactbook"))
                        mPresenter.sureUnfactBook(initUnfactBookParams(result));
                    else if (TextUtil.isNumber(result))
                        mPresenter.sureBook(initParams(result));
                    else mPresenter.sureBook(initParams(getUrlKey(result, "id")));


                }
            }
        });


    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        String tipText = zXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            //在这里加入了根据传感器光线暗的时候自动打开闪光灯
            if (!tipText.contains(ambientBrightnessTip)) {
                zXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
//                zXingView.openFlashlight();
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                zXingView.getScanBoxView().setTipText(tipText);
            }
            try {
                zXingView.closeFlashlight();
            } catch (Exception e) {
                LogUtils.e(e);
            }

        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }


    @Override
    public void onSureSuccess(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PopwindowUtils.getmInstance().showSureDialogPopupWindow(
                        context,
                        null,
                        msg.equals("200") ? "核销成功" : "核销失败",
                        null,
                        v -> {
                            onBackPressed();
                        });
            }
        });


    }

    @Override
    public void onSureUnfactBookSuccess(Object msg) {
        if (((TwoSuccessCodeBean) msg).getResult().equals("1") && ((TwoSuccessCodeBean) msg).getMsg().equals("核销成功")) {
            PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, ((TwoSuccessCodeBean) msg).getMsg(), v -> {
                onBackPressed();
            });
//            LogUtils.d(msg.toString());
//            onBackPressed();
        }

    }

    @Override
    public void onBindingDeviceSuccess(Object msg) {

    }

    /**
     * PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, msg, v -> {
     * onBackPressed();
     * });
     *
     * @param msg
     */
    @Override
    public void showError(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PopwindowUtils.getmInstance().showSureDialogPopupWindow(
                        context,
                        null,
                        msg,
                        null,
                        v -> {
                            onBackPressed();
                        });

            }
        });

    }
}

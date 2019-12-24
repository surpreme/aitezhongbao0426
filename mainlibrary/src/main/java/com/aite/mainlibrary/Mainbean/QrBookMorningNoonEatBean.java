package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class QrBookMorningNoonEatBean extends ErrorBean implements Serializable {


    /**
     * qrcodeimg : http://zhongbyi.aitecc.com/data/upload/shop/qrcode/bbd7f845e91dffea7b1003b5a6ce3563.jpg
     * vr_code : null
     */

    private String qrcodeimg;
    private Object vr_code;

    public String getQrcodeimg() {
        return qrcodeimg;
    }

    public void setQrcodeimg(String qrcodeimg) {
        this.qrcodeimg = qrcodeimg;
    }

    public Object getVr_code() {
        return vr_code;
    }

    public void setVr_code(Object vr_code) {
        this.vr_code = vr_code;
    }
}

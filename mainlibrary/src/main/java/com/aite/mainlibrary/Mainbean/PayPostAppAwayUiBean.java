package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class PayPostAppAwayUiBean extends ErrorBean implements Serializable {


    /**
     * code : 200
     * datas : [{"payment_id":"1","payment_code":"alipay","payment_name":"支付宝","payment_image":"http://zhongbyi.aitecc.com/mall/templates/default/images/payment/alipay_logo.gif"},{"payment_id":"3","payment_code":"app_wxpay","payment_name":"微信外支付","payment_image":"http://zhongbyi.aitecc.com/mall/templates/default/images/payment/app_wxpay_logo.gif"}]
     */

    private int code;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * payment_id : 1
         * payment_code : alipay
         * payment_name : 支付宝
         * payment_image : http://zhongbyi.aitecc.com/mall/templates/default/images/payment/alipay_logo.gif
         */

        private String payment_id;
        private String payment_code;
        private String payment_name;
        private String payment_image;

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public String getPayment_image() {
            return payment_image;
        }

        public void setPayment_image(String payment_image) {
            this.payment_image = payment_image;
        }
    }
}

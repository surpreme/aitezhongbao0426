package com.aite.mainlibrary.Mainbean;

import com.google.gson.annotations.SerializedName;
import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class SureSendMoneyBean extends ErrorBean implements Serializable {

    /**
     * state : success
     * content : {"2":0}
     * allow_offpay : 0
     * allow_offpay_batch : {"2":false}
     * offpay_hash : 8XXnNMTGy-kQv86XveBkeyJZbFpdVrsHoNWEIeB
     * offpay_hash_batch : rd0iYbHlXpC8Qpye3wAjhXkXHVqoeOuy.533DeVuQwPgxOh
     * freight : 0
     */

    private String state;
    private ContentBean content;
    private String allow_offpay;
    private AllowOffpayBatchBean allow_offpay_batch;
    private String offpay_hash;
    private String offpay_hash_batch;
    private int freight;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getAllow_offpay() {
        return allow_offpay;
    }

    public void setAllow_offpay(String allow_offpay) {
        this.allow_offpay = allow_offpay;
    }

    public AllowOffpayBatchBean getAllow_offpay_batch() {
        return allow_offpay_batch;
    }

    public void setAllow_offpay_batch(AllowOffpayBatchBean allow_offpay_batch) {
        this.allow_offpay_batch = allow_offpay_batch;
    }

    public String getOffpay_hash() {
        return offpay_hash;
    }

    public void setOffpay_hash(String offpay_hash) {
        this.offpay_hash = offpay_hash;
    }

    public String getOffpay_hash_batch() {
        return offpay_hash_batch;
    }

    public void setOffpay_hash_batch(String offpay_hash_batch) {
        this.offpay_hash_batch = offpay_hash_batch;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public static class ContentBean {
        /**
         * 2 : 0
         */

        @SerializedName("2")
        private int _$2;

        public int get_$2() {
            return _$2;
        }

        public void set_$2(int _$2) {
            this._$2 = _$2;
        }
    }

    public static class AllowOffpayBatchBean {
        /**
         * 2 : false
         */

        @SerializedName("2")
        private boolean _$2;

        public boolean is_$2() {
            return _$2;
        }

        public void set_$2(boolean _$2) {
            this._$2 = _$2;
        }
    }
}

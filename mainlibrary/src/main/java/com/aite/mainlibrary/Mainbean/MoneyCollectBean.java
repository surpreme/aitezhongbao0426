package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class MoneyCollectBean extends ErrorBean implements Serializable {


    /**
     * predepoit : 98.57
     * new_amount : 0
     * cash_info : {"bank_name":"","pdc_amount":"","pdc_payment_time":""}
     * recharge_info : {"pdr_payment_name":"","pdc_amount":"","pdc_payment_time":""}
     * log_info : {"lg_av_amount":"0.00","lg_add_time":"2019-12-16","lg_desc":"下单，支付预存款，订单号: 230629846535711007"}
     */

    private double predepoit;
    private int new_amount;
    private CashInfoBean cash_info;
    private RechargeInfoBean recharge_info;
    private LogInfoBean log_info;

    public double getPredepoit() {
        return predepoit;
    }

    public void setPredepoit(double predepoit) {
        this.predepoit = predepoit;
    }

    public int getNew_amount() {
        return new_amount;
    }

    public void setNew_amount(int new_amount) {
        this.new_amount = new_amount;
    }

    public CashInfoBean getCash_info() {
        return cash_info;
    }

    public void setCash_info(CashInfoBean cash_info) {
        this.cash_info = cash_info;
    }

    public RechargeInfoBean getRecharge_info() {
        return recharge_info;
    }

    public void setRecharge_info(RechargeInfoBean recharge_info) {
        this.recharge_info = recharge_info;
    }

    public LogInfoBean getLog_info() {
        return log_info;
    }

    public void setLog_info(LogInfoBean log_info) {
        this.log_info = log_info;
    }

    public static class CashInfoBean {
        /**
         * bank_name :
         * pdc_amount :
         * pdc_payment_time :
         */

        private String bank_name;
        private String pdc_amount;
        private String pdc_payment_time;

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getPdc_amount() {
            return pdc_amount;
        }

        public void setPdc_amount(String pdc_amount) {
            this.pdc_amount = pdc_amount;
        }

        public String getPdc_payment_time() {
            return pdc_payment_time;
        }

        public void setPdc_payment_time(String pdc_payment_time) {
            this.pdc_payment_time = pdc_payment_time;
        }
    }

    public static class RechargeInfoBean {
        /**
         * pdr_payment_name :
         * pdc_amount :
         * pdc_payment_time :
         */

        private String pdr_payment_name;
        private String pdc_amount;
        private String pdc_payment_time;

        public String getPdr_payment_name() {
            return pdr_payment_name;
        }

        public void setPdr_payment_name(String pdr_payment_name) {
            this.pdr_payment_name = pdr_payment_name;
        }

        public String getPdc_amount() {
            return pdc_amount;
        }

        public void setPdc_amount(String pdc_amount) {
            this.pdc_amount = pdc_amount;
        }

        public String getPdc_payment_time() {
            return pdc_payment_time;
        }

        public void setPdc_payment_time(String pdc_payment_time) {
            this.pdc_payment_time = pdc_payment_time;
        }
    }

    public static class LogInfoBean {
        /**
         * lg_av_amount : 0.00
         * lg_add_time : 2019-12-16
         * lg_desc : 下单，支付预存款，订单号: 230629846535711007
         */

        private String lg_av_amount;
        private String lg_add_time;
        private String lg_desc;

        public String getLg_av_amount() {
            return lg_av_amount;
        }

        public void setLg_av_amount(String lg_av_amount) {
            this.lg_av_amount = lg_av_amount;
        }

        public String getLg_add_time() {
            return lg_add_time;
        }

        public void setLg_add_time(String lg_add_time) {
            this.lg_add_time = lg_add_time;
        }

        public String getLg_desc() {
            return lg_desc;
        }

        public void setLg_desc(String lg_desc) {
            this.lg_desc = lg_desc;
        }
    }
}

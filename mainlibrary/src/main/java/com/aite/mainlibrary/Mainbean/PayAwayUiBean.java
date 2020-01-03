package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class PayAwayUiBean extends ErrorBean implements Serializable {

    /**
     * predepoit : 2486.61
     * bank_list : [{"id":"28","member_id":"7","bank_name":"国家开发银行","bank_no":"4438884854245","bank_user":"李易峰","branch_name":"挺厉害"}]
     */

    private String predepoit;
    private List<BankListBean> bank_list;

    public String getPredepoit() {
        return predepoit;
    }

    public void setPredepoit(String predepoit) {
        this.predepoit = predepoit;
    }

    public List<BankListBean> getBank_list() {
        return bank_list;
    }

    public void setBank_list(List<BankListBean> bank_list) {
        this.bank_list = bank_list;
    }

    public static class BankListBean extends IBaseBean {


        /**
         * id : 28
         * member_id : 7
         * bank_name : 国家开发银行
         * bank_no : 4438884854245
         * bank_user : 李易峰
         * branch_name : 挺厉害
         */

        private String id;
        private String member_id;
        private String bank_name;
        private String bank_no;
        private String bank_user;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBank_no() {
            return bank_no;
        }

        public void setBank_no(String bank_no) {
            this.bank_no = bank_no;
        }

        public String getBank_user() {
            return bank_user;
        }

        public void setBank_user(String bank_user) {
            this.bank_user = bank_user;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        private String branch_name;

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getNasme() {
            return bank_name;
        }

        @Override
        public boolean isIsCheck() {
            return isChecked;
        }
    }
}

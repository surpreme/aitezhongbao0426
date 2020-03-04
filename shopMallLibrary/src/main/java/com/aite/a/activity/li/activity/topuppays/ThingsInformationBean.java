package com.aite.a.activity.li.activity.topuppays;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2020-02-12
 * @desc:
 */
public class ThingsInformationBean extends ErrorBean implements Serializable {

    /**
     * list : [{"pdr_id":"619","pdr_sn":"460634765647826007","pdr_member_id":"7","pdr_member_name":"18614079738","pdr_amount":"1.00","pdr_payment_code":"","pdr_payment_name":"","pdr_trade_sn":"","pdr_add_time":"1581421647","pdr_payment_state":"0","pdr_payment_time":"0","pdr_admin":""}]
     * member_info : {"available_predeposit":"9975562.87","freeze_predeposit":"1177.00"}
     */

    private MemberInfoBean member_info;
    private List<ListBean> list;

    public MemberInfoBean getMember_info() {
        return member_info;
    }

    public void setMember_info(MemberInfoBean member_info) {
        this.member_info = member_info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class MemberInfoBean {
        /**
         * available_predeposit : 9975562.87
         * freeze_predeposit : 1177.00
         */

        private String available_predeposit;
        private String freeze_predeposit;

        public String getAvailable_predeposit() {
            return available_predeposit;
        }

        public void setAvailable_predeposit(String available_predeposit) {
            this.available_predeposit = available_predeposit;
        }

        public String getFreeze_predeposit() {
            return freeze_predeposit;
        }

        public void setFreeze_predeposit(String freeze_predeposit) {
            this.freeze_predeposit = freeze_predeposit;
        }
    }

    public static class ListBean {
        /**
         * pdr_id : 619
         * pdr_sn : 460634765647826007
         * pdr_member_id : 7
         * pdr_member_name : 18614079738
         * pdr_amount : 1.00
         * pdr_payment_code :
         * pdr_payment_name :
         * pdr_trade_sn :
         * pdr_add_time : 1581421647
         * pdr_payment_state : 0
         * pdr_payment_time : 0
         * pdr_admin :
         */

        private String pdr_id;
        private String pdr_sn;
        private String pdr_member_id;
        private String pdr_member_name;
        private String pdr_amount;
        private String pdr_payment_code;
        private String pdr_payment_name;
        private String pdr_trade_sn;
        private String pdr_add_time;
        private String pdr_payment_state;
        private String pdr_payment_time;
        private String pdr_admin;

        public String getPdr_id() {
            return pdr_id;
        }

        public void setPdr_id(String pdr_id) {
            this.pdr_id = pdr_id;
        }

        public String getPdr_sn() {
            return pdr_sn;
        }

        public void setPdr_sn(String pdr_sn) {
            this.pdr_sn = pdr_sn;
        }

        public String getPdr_member_id() {
            return pdr_member_id;
        }

        public void setPdr_member_id(String pdr_member_id) {
            this.pdr_member_id = pdr_member_id;
        }

        public String getPdr_member_name() {
            return pdr_member_name;
        }

        public void setPdr_member_name(String pdr_member_name) {
            this.pdr_member_name = pdr_member_name;
        }

        public String getPdr_amount() {
            return pdr_amount;
        }

        public void setPdr_amount(String pdr_amount) {
            this.pdr_amount = pdr_amount;
        }

        public String getPdr_payment_code() {
            return pdr_payment_code;
        }

        public void setPdr_payment_code(String pdr_payment_code) {
            this.pdr_payment_code = pdr_payment_code;
        }

        public String getPdr_payment_name() {
            return pdr_payment_name;
        }

        public void setPdr_payment_name(String pdr_payment_name) {
            this.pdr_payment_name = pdr_payment_name;
        }

        public String getPdr_trade_sn() {
            return pdr_trade_sn;
        }

        public void setPdr_trade_sn(String pdr_trade_sn) {
            this.pdr_trade_sn = pdr_trade_sn;
        }

        public String getPdr_add_time() {
            return pdr_add_time;
        }

        public void setPdr_add_time(String pdr_add_time) {
            this.pdr_add_time = pdr_add_time;
        }

        public String getPdr_payment_state() {
            return pdr_payment_state;
        }

        public void setPdr_payment_state(String pdr_payment_state) {
            this.pdr_payment_state = pdr_payment_state;
        }

        public String getPdr_payment_time() {
            return pdr_payment_time;
        }

        public void setPdr_payment_time(String pdr_payment_time) {
            this.pdr_payment_time = pdr_payment_time;
        }

        public String getPdr_admin() {
            return pdr_admin;
        }

        public void setPdr_admin(String pdr_admin) {
            this.pdr_admin = pdr_admin;
        }
    }
}

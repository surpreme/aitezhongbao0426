package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;
import com.lzy.basemodule.bean.IImgBaseBean;
import com.lzy.basemodule.bean.ImBaseBean;

import java.io.Serializable;
import java.util.List;

public class DisputeTypeBean extends ErrorBean implements Serializable {

    /**
     * subject_list : [{"complain_subject_id":"5","complain_subject_content":"态度不好","complain_subject_desc":"态度差","complain_subject_state":"1"},{"complain_subject_id":"4","complain_subject_content":"不是购买商品","complain_subject_desc":"不是购买商品","complain_subject_state":"1"},{"complain_subject_id":"3","complain_subject_content":"售后保障服务","complain_subject_desc":"交易完成后30天内，在使用商品过程中，发现商品有质量问题或无法正常使用，可进行维权。","complain_subject_state":"1"},{"complain_subject_id":"2","complain_subject_content":"未收到货","complain_subject_desc":"交易成功，未收到货，钱已经付给商家，可进行维权。","complain_subject_state":"1"}]
     * goods_id : 13
     */

    private int goods_id;
    private List<SubjectListBean> subject_list;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public List<SubjectListBean> getSubject_list() {
        return subject_list;
    }

    public void setSubject_list(List<SubjectListBean> subject_list) {
        this.subject_list = subject_list;
    }

    public static class SubjectListBean extends ImBaseBean {
        /**
         * complain_subject_id : 5
         * complain_subject_content : 态度不好
         * complain_subject_desc : 态度差
         * complain_subject_state : 1
         */

        private String complain_subject_id;
        private String complain_subject_content;
        private String complain_subject_desc;
        private String complain_subject_state;

        public String getComplain_subject_content() {
            return complain_subject_content;
        }

        public void setComplain_subject_content(String complain_subject_content) {
            this.complain_subject_content = complain_subject_content;
        }


        public String getComplain_subject_state() {
            return complain_subject_state;
        }

        public void setComplain_subject_state(String complain_subject_state) {
            this.complain_subject_state = complain_subject_state;
        }

        @Override
        public String getId() {
            return complain_subject_id;
        }

        @Override
        public String getNasme() {
            return complain_subject_content;
        }

    }
}

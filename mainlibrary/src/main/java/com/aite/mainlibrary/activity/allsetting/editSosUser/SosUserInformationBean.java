package com.aite.mainlibrary.activity.allsetting.editSosUser;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-11
 * @desc:
 */
public class SosUserInformationBean extends ErrorBean implements Serializable {

    /**
     * info : {"id":"35","member_id":"7","avatar":"http://zhongbyi.aitecc.com/data/upload/shop/member/06311056275520840.png","realname":"李","mobile":"18614079738","relation":"5","is_default":"1"}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 35
         * member_id : 7
         * avatar : http://zhongbyi.aitecc.com/data/upload/shop/member/06311056275520840.png
         * realname : 李
         * mobile : 18614079738
         * relation : 5
         * is_default : 1
         */

        private String id;
        private String member_id;
        private String avatar;
        private String realname;
        private String mobile;
        private String relation;
        private String is_default;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}

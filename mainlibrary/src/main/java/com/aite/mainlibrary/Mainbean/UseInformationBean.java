package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class UseInformationBean extends ErrorBean implements Serializable {


    /**
     * member_info : {"member_id":"7","member_name":"18614079738","nickname":"","member_truename":"布雫雫","member_sex":1,"member_birthday":"2017-05-21","member_mobile":"18614079738","member_email":"18614079738@139.com","point":"8417","predepoit":"870.19","member_qq":"","member_ww":"","member_areainfo":"北京天津河北","member_provinceid":"1","member_cityid":"2","member_areaid":"3","member_avatar":"http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg","is_allowtalk":1,"is_member":1,"is_volunteer":1,"is_hugong":1,"is_doctors":0,"is_nursing":0,"current_identity":1}
     */

    private MemberInfoBean member_info;

    public MemberInfoBean getMember_info() {
        return member_info;
    }

    public void setMember_info(MemberInfoBean member_info) {
        this.member_info = member_info;
    }

    public static class MemberInfoBean {
        /**
         * member_id : 7
         * member_name : 18614079738
         * nickname :
         * member_truename : 布雫雫
         * member_sex : 1
         * member_birthday : 2017-05-21
         * member_mobile : 18614079738
         * member_email : 18614079738@139.com
         * point : 8417
         * predepoit : 870.19
         * member_qq :
         * member_ww :
         * member_areainfo : 北京天津河北
         * member_provinceid : 1
         * member_cityid : 2
         * member_areaid : 3
         * member_avatar : http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg
         * is_allowtalk : 1
         * is_member : 1
         * is_volunteer : 1
         * is_hugong : 1
         * is_doctors : 0
         * is_nursing : 0
         * current_identity : 1
         */

        private String member_id;
        private String member_name;
        private String nickname;
        private String member_truename;
        private int member_sex;
        private String member_birthday;
        private String member_mobile;
        private String member_email;
        private String point;
        private String predepoit;
        private String member_qq;
        private String member_ww;
        private String member_areainfo;
        private String member_provinceid;
        private String member_cityid;
        private String member_areaid;
        private String member_avatar;
        private int is_allowtalk;
        private int is_member;
        private int is_volunteer;
        private int is_hugong;
        private int is_doctors;
        private int is_nursing;
        private int current_identity;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMember_truename() {
            return member_truename;
        }

        public void setMember_truename(String member_truename) {
            this.member_truename = member_truename;
        }

        public int getMember_sex() {
            return member_sex;
        }

        public void setMember_sex(int member_sex) {
            this.member_sex = member_sex;
        }

        public String getMember_birthday() {
            return member_birthday;
        }

        public void setMember_birthday(String member_birthday) {
            this.member_birthday = member_birthday;
        }

        public String getMember_mobile() {
            return member_mobile;
        }

        public void setMember_mobile(String member_mobile) {
            this.member_mobile = member_mobile;
        }

        public String getMember_email() {
            return member_email;
        }

        public void setMember_email(String member_email) {
            this.member_email = member_email;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getPredepoit() {
            return predepoit;
        }

        public void setPredepoit(String predepoit) {
            this.predepoit = predepoit;
        }

        public String getMember_qq() {
            return member_qq;
        }

        public void setMember_qq(String member_qq) {
            this.member_qq = member_qq;
        }

        public String getMember_ww() {
            return member_ww;
        }

        public void setMember_ww(String member_ww) {
            this.member_ww = member_ww;
        }

        public String getMember_areainfo() {
            return member_areainfo;
        }

        public void setMember_areainfo(String member_areainfo) {
            this.member_areainfo = member_areainfo;
        }

        public String getMember_provinceid() {
            return member_provinceid;
        }

        public void setMember_provinceid(String member_provinceid) {
            this.member_provinceid = member_provinceid;
        }

        public String getMember_cityid() {
            return member_cityid;
        }

        public void setMember_cityid(String member_cityid) {
            this.member_cityid = member_cityid;
        }

        public String getMember_areaid() {
            return member_areaid;
        }

        public void setMember_areaid(String member_areaid) {
            this.member_areaid = member_areaid;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public int getIs_allowtalk() {
            return is_allowtalk;
        }

        public void setIs_allowtalk(int is_allowtalk) {
            this.is_allowtalk = is_allowtalk;
        }

        public int getIs_member() {
            return is_member;
        }

        public void setIs_member(int is_member) {
            this.is_member = is_member;
        }

        public int getIs_volunteer() {
            return is_volunteer;
        }

        public void setIs_volunteer(int is_volunteer) {
            this.is_volunteer = is_volunteer;
        }

        public int getIs_hugong() {
            return is_hugong;
        }

        public void setIs_hugong(int is_hugong) {
            this.is_hugong = is_hugong;
        }

        public int getIs_doctors() {
            return is_doctors;
        }

        public void setIs_doctors(int is_doctors) {
            this.is_doctors = is_doctors;
        }

        public int getIs_nursing() {
            return is_nursing;
        }

        public void setIs_nursing(int is_nursing) {
            this.is_nursing = is_nursing;
        }

        public int getCurrent_identity() {
            return current_identity;
        }

        public void setCurrent_identity(int current_identity) {
            this.current_identity = current_identity;
        }
    }
}

package com.aite.aitezhongbao.bean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class LogInBean extends ErrorBean implements Serializable {

    /**
     * username : 18614079738
     * key : 3e701231099b3269a58aa50330fc4527
     * config : {"friend_valid":0,"member_id":"7"}
     * is_member : 1
     * is_volunteer : 1
     * is_hugong : 1
     * is_doctors : 0
     * is_nursing : 0
     * current_identity : 1
     */

    private String username;
    private String key;
    private ConfigBean config;
    private String is_member;
    private String is_volunteer;
    private String is_hugong;
    private String is_doctors;
    private String is_nursing;
    private String current_identity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public String getIs_member() {
        return is_member;
    }

    public void setIs_member(String is_member) {
        this.is_member = is_member;
    }

    public String getIs_volunteer() {
        return is_volunteer;
    }

    public void setIs_volunteer(String is_volunteer) {
        this.is_volunteer = is_volunteer;
    }

    public String getIs_hugong() {
        return is_hugong;
    }

    public void setIs_hugong(String is_hugong) {
        this.is_hugong = is_hugong;
    }

    public String getIs_doctors() {
        return is_doctors;
    }

    public void setIs_doctors(String is_doctors) {
        this.is_doctors = is_doctors;
    }

    public String getIs_nursing() {
        return is_nursing;
    }

    public void setIs_nursing(String is_nursing) {
        this.is_nursing = is_nursing;
    }

    public String getCurrent_identity() {
        return current_identity;
    }

    public void setCurrent_identity(String current_identity) {
        this.current_identity = current_identity;
    }

    public static class ConfigBean {
        /**
         * friend_valid : 0
         * member_id : 7
         */

        private String friend_valid;
        private String member_id;

        public String getFriend_valid() {
            return friend_valid;
        }

        public void setFriend_valid(String friend_valid) {
            this.friend_valid = friend_valid;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }
    }
}

package com.example.bean;

import java.util.List;

/**
 * 创建时间 2020/1/2 15:31
 * 描述:
 */
public class DoctorInfoBean {


    /**
     * doctor_id : 7
     * member_id : 7
     * work_address : ["地址地址","多地址多地址"]
     * adept : ["擅长1","擅长2"]
     * introduce : 介绍 介绍
     * professional_types : 2
     * departments : 科室会
     * position : 3
     * consult_count : 0
     * evaluate_score : 0.0
     * member_mark :
     * member_name : 18614079738
     * member_truename : 布雫雫
     * member_avatar : avatar_7.jpg
     * member_sex : 1
     * member_birthday : 2017-05-21
     * member_passwd : e10adc3949ba59abbe56e057f20f883e
     * member_paypwd : null
     * member_email : 18614079738@139.com
     * member_email_bind : 0
     * member_mobile : 18614079738
     * member_mobile_bind : 1
     * member_qq :
     * member_ww :
     * member_login_num : 53
     * member_time : 2019-11-20 21:04:13
     * member_login_time : 2020-01-02 14:57:33
     * member_old_login_time : 1577941835
     * member_login_ip : 119.137.55.198
     * member_old_login_ip : 119.137.55.198
     * member_qqopenid : null
     * member_qqinfo : null
     * member_sinaopenid : null
     * member_sinainfo : null
     * member_points : 8417
     * available_predeposit : 871.19
     * freeze_predeposit : 1057.00
     * available_rc_balance : 0.00
     * freeze_rc_balance : 0.00
     * inform_allow : 1
     * is_buy : 1
     * is_allowtalk : 1
     * member_state : 1
     * member_snsvisitnum : 0
     * member_areaid : 3
     * member_cityid : 2
     * member_provinceid : 1
     * member_areainfo : 北京天津河北
     * member_privacy : null
     * member_quicklink : null
     * member_exppoints : 1067
     * wechat_id :
     * is_subscribe : 0
     * commission : 10100.00
     * nickname :
     * member_rank : 0
     * member_is_auth : 0
     * headimgurl :
     * media_id :
     * member_wxunionid : onFhpv5UtahlsUbAsDLhVZF2gvMc
     * member_wxinfo : null
     * member_ticket_identity : null
     * member_ticket_name : null
     * is_push : 0
     * hx_username : null
     * user_sig : null
     * member_album : null
     * member_volunteer_points : 2880
     * member_address : 秃头
     * member_idcard : 411481199909041030
     * member_age : 0
     * member_card_zthumb : shop/member/register/member_card_zthumb_7.jpg
     * member_card_fthumb : shop/member/register/member_card_fthumb_7.jpg
     * work_permit : null
     * doctor_license_z : null
     * doctor_license_f : null
     * check_status : 1
     * register_step : 3
     * member_height : 156
     * member_weight : 50
     * member_blood_types : A型
     * is_organ_donor : 1
     * is_member : 1
     * is_volunteer : 1
     * is_hugong : 1
     * is_doctors : 0
     * is_nursing : 0
     * nursing_store_id : 4
     * current_identity : 0
     * evaluate_count : 0
     * collection_count : 0
     */

    private String doctor_id;
    private String member_id;
    private String introduce;
    private String professional_types;
    private String departments;
    private String position;
    private String consult_count;
    private String evaluate_score;
    private String member_mark;
    private String member_name;
    private String member_truename;
    private String member_avatar;
    private String member_sex;
    private String member_birthday;
    private String member_passwd;
    private Object member_paypwd;
    private String member_email;
    private String member_email_bind;
    private String member_mobile;
    private String member_mobile_bind;
    private String member_qq;
    private String member_ww;
    private String member_login_num;
    private String member_time;
    private String member_login_time;
    private String member_old_login_time;
    private String member_login_ip;
    private String member_old_login_ip;
    private Object member_qqopenid;
    private Object member_qqinfo;
    private Object member_sinaopenid;
    private Object member_sinainfo;
    private String member_points;
    private String available_predeposit;
    private String freeze_predeposit;
    private String available_rc_balance;
    private String freeze_rc_balance;
    private String inform_allow;
    private String is_buy;
    private String is_allowtalk;
    private String member_state;
    private String member_snsvisitnum;
    private String member_areaid;
    private String member_cityid;
    private String member_provinceid;
    private String member_areainfo;
    private Object member_privacy;
    private Object member_quicklink;
    private String member_exppoints;
    private String wechat_id;
    private String is_subscribe;
    private String commission;
    private String nickname;
    private String member_rank;
    private String member_is_auth;
    private String headimgurl;
    private String media_id;
    private String member_wxunionid;
    private Object member_wxinfo;
    private Object member_ticket_identity;
    private Object member_ticket_name;
    private String is_push;
    private Object hx_username;
    private Object user_sig;
    private Object member_album;
    private String member_volunteer_points;
    private String member_address;
    private String member_idcard;
    private String member_age;
    private String member_card_zthumb;
    private String member_card_fthumb;
    private Object work_permit;
    private Object doctor_license_z;
    private Object doctor_license_f;
    private String check_status;
    private String register_step;
    private String member_height;
    private String member_weight;
    private String member_blood_types;
    private String is_organ_donor;
    private String is_member;
    private String is_volunteer;
    private String is_hugong;
    private String is_doctors;
    private String is_nursing;
    private String nursing_store_id;
    private String current_identity;
    private int evaluate_count;
    private int collection_count;
    private List<String> work_address;
    private List<String> adept;

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getProfessional_types() {
        return professional_types;
    }

    public void setProfessional_types(String professional_types) {
        this.professional_types = professional_types;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getConsult_count() {
        return consult_count;
    }

    public void setConsult_count(String consult_count) {
        this.consult_count = consult_count;
    }

    public String getEvaluate_score() {
        return evaluate_score;
    }

    public void setEvaluate_score(String evaluate_score) {
        this.evaluate_score = evaluate_score;
    }

    public String getMember_mark() {
        return member_mark;
    }

    public void setMember_mark(String member_mark) {
        this.member_mark = member_mark;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_truename() {
        return member_truename;
    }

    public void setMember_truename(String member_truename) {
        this.member_truename = member_truename;
    }

    public String getMember_avatar() {
        return member_avatar;
    }

    public void setMember_avatar(String member_avatar) {
        this.member_avatar = member_avatar;
    }

    public String getMember_sex() {
        return member_sex;
    }

    public void setMember_sex(String member_sex) {
        this.member_sex = member_sex;
    }

    public String getMember_birthday() {
        return member_birthday;
    }

    public void setMember_birthday(String member_birthday) {
        this.member_birthday = member_birthday;
    }

    public String getMember_passwd() {
        return member_passwd;
    }

    public void setMember_passwd(String member_passwd) {
        this.member_passwd = member_passwd;
    }

    public Object getMember_paypwd() {
        return member_paypwd;
    }

    public void setMember_paypwd(Object member_paypwd) {
        this.member_paypwd = member_paypwd;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_email_bind() {
        return member_email_bind;
    }

    public void setMember_email_bind(String member_email_bind) {
        this.member_email_bind = member_email_bind;
    }

    public String getMember_mobile() {
        return member_mobile;
    }

    public void setMember_mobile(String member_mobile) {
        this.member_mobile = member_mobile;
    }

    public String getMember_mobile_bind() {
        return member_mobile_bind;
    }

    public void setMember_mobile_bind(String member_mobile_bind) {
        this.member_mobile_bind = member_mobile_bind;
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

    public String getMember_login_num() {
        return member_login_num;
    }

    public void setMember_login_num(String member_login_num) {
        this.member_login_num = member_login_num;
    }

    public String getMember_time() {
        return member_time;
    }

    public void setMember_time(String member_time) {
        this.member_time = member_time;
    }

    public String getMember_login_time() {
        return member_login_time;
    }

    public void setMember_login_time(String member_login_time) {
        this.member_login_time = member_login_time;
    }

    public String getMember_old_login_time() {
        return member_old_login_time;
    }

    public void setMember_old_login_time(String member_old_login_time) {
        this.member_old_login_time = member_old_login_time;
    }

    public String getMember_login_ip() {
        return member_login_ip;
    }

    public void setMember_login_ip(String member_login_ip) {
        this.member_login_ip = member_login_ip;
    }

    public String getMember_old_login_ip() {
        return member_old_login_ip;
    }

    public void setMember_old_login_ip(String member_old_login_ip) {
        this.member_old_login_ip = member_old_login_ip;
    }

    public Object getMember_qqopenid() {
        return member_qqopenid;
    }

    public void setMember_qqopenid(Object member_qqopenid) {
        this.member_qqopenid = member_qqopenid;
    }

    public Object getMember_qqinfo() {
        return member_qqinfo;
    }

    public void setMember_qqinfo(Object member_qqinfo) {
        this.member_qqinfo = member_qqinfo;
    }

    public Object getMember_sinaopenid() {
        return member_sinaopenid;
    }

    public void setMember_sinaopenid(Object member_sinaopenid) {
        this.member_sinaopenid = member_sinaopenid;
    }

    public Object getMember_sinainfo() {
        return member_sinainfo;
    }

    public void setMember_sinainfo(Object member_sinainfo) {
        this.member_sinainfo = member_sinainfo;
    }

    public String getMember_points() {
        return member_points;
    }

    public void setMember_points(String member_points) {
        this.member_points = member_points;
    }

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

    public String getAvailable_rc_balance() {
        return available_rc_balance;
    }

    public void setAvailable_rc_balance(String available_rc_balance) {
        this.available_rc_balance = available_rc_balance;
    }

    public String getFreeze_rc_balance() {
        return freeze_rc_balance;
    }

    public void setFreeze_rc_balance(String freeze_rc_balance) {
        this.freeze_rc_balance = freeze_rc_balance;
    }

    public String getInform_allow() {
        return inform_allow;
    }

    public void setInform_allow(String inform_allow) {
        this.inform_allow = inform_allow;
    }

    public String getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(String is_buy) {
        this.is_buy = is_buy;
    }

    public String getIs_allowtalk() {
        return is_allowtalk;
    }

    public void setIs_allowtalk(String is_allowtalk) {
        this.is_allowtalk = is_allowtalk;
    }

    public String getMember_state() {
        return member_state;
    }

    public void setMember_state(String member_state) {
        this.member_state = member_state;
    }

    public String getMember_snsvisitnum() {
        return member_snsvisitnum;
    }

    public void setMember_snsvisitnum(String member_snsvisitnum) {
        this.member_snsvisitnum = member_snsvisitnum;
    }

    public String getMember_areaid() {
        return member_areaid;
    }

    public void setMember_areaid(String member_areaid) {
        this.member_areaid = member_areaid;
    }

    public String getMember_cityid() {
        return member_cityid;
    }

    public void setMember_cityid(String member_cityid) {
        this.member_cityid = member_cityid;
    }

    public String getMember_provinceid() {
        return member_provinceid;
    }

    public void setMember_provinceid(String member_provinceid) {
        this.member_provinceid = member_provinceid;
    }

    public String getMember_areainfo() {
        return member_areainfo;
    }

    public void setMember_areainfo(String member_areainfo) {
        this.member_areainfo = member_areainfo;
    }

    public Object getMember_privacy() {
        return member_privacy;
    }

    public void setMember_privacy(Object member_privacy) {
        this.member_privacy = member_privacy;
    }

    public Object getMember_quicklink() {
        return member_quicklink;
    }

    public void setMember_quicklink(Object member_quicklink) {
        this.member_quicklink = member_quicklink;
    }

    public String getMember_exppoints() {
        return member_exppoints;
    }

    public void setMember_exppoints(String member_exppoints) {
        this.member_exppoints = member_exppoints;
    }

    public String getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(String wechat_id) {
        this.wechat_id = wechat_id;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMember_rank() {
        return member_rank;
    }

    public void setMember_rank(String member_rank) {
        this.member_rank = member_rank;
    }

    public String getMember_is_auth() {
        return member_is_auth;
    }

    public void setMember_is_auth(String member_is_auth) {
        this.member_is_auth = member_is_auth;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getMember_wxunionid() {
        return member_wxunionid;
    }

    public void setMember_wxunionid(String member_wxunionid) {
        this.member_wxunionid = member_wxunionid;
    }

    public Object getMember_wxinfo() {
        return member_wxinfo;
    }

    public void setMember_wxinfo(Object member_wxinfo) {
        this.member_wxinfo = member_wxinfo;
    }

    public Object getMember_ticket_identity() {
        return member_ticket_identity;
    }

    public void setMember_ticket_identity(Object member_ticket_identity) {
        this.member_ticket_identity = member_ticket_identity;
    }

    public Object getMember_ticket_name() {
        return member_ticket_name;
    }

    public void setMember_ticket_name(Object member_ticket_name) {
        this.member_ticket_name = member_ticket_name;
    }

    public String getIs_push() {
        return is_push;
    }

    public void setIs_push(String is_push) {
        this.is_push = is_push;
    }

    public Object getHx_username() {
        return hx_username;
    }

    public void setHx_username(Object hx_username) {
        this.hx_username = hx_username;
    }

    public Object getUser_sig() {
        return user_sig;
    }

    public void setUser_sig(Object user_sig) {
        this.user_sig = user_sig;
    }

    public Object getMember_album() {
        return member_album;
    }

    public void setMember_album(Object member_album) {
        this.member_album = member_album;
    }

    public String getMember_volunteer_points() {
        return member_volunteer_points;
    }

    public void setMember_volunteer_points(String member_volunteer_points) {
        this.member_volunteer_points = member_volunteer_points;
    }

    public String getMember_address() {
        return member_address;
    }

    public void setMember_address(String member_address) {
        this.member_address = member_address;
    }

    public String getMember_idcard() {
        return member_idcard;
    }

    public void setMember_idcard(String member_idcard) {
        this.member_idcard = member_idcard;
    }

    public String getMember_age() {
        return member_age;
    }

    public void setMember_age(String member_age) {
        this.member_age = member_age;
    }

    public String getMember_card_zthumb() {
        return member_card_zthumb;
    }

    public void setMember_card_zthumb(String member_card_zthumb) {
        this.member_card_zthumb = member_card_zthumb;
    }

    public String getMember_card_fthumb() {
        return member_card_fthumb;
    }

    public void setMember_card_fthumb(String member_card_fthumb) {
        this.member_card_fthumb = member_card_fthumb;
    }

    public Object getWork_permit() {
        return work_permit;
    }

    public void setWork_permit(Object work_permit) {
        this.work_permit = work_permit;
    }

    public Object getDoctor_license_z() {
        return doctor_license_z;
    }

    public void setDoctor_license_z(Object doctor_license_z) {
        this.doctor_license_z = doctor_license_z;
    }

    public Object getDoctor_license_f() {
        return doctor_license_f;
    }

    public void setDoctor_license_f(Object doctor_license_f) {
        this.doctor_license_f = doctor_license_f;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getRegister_step() {
        return register_step;
    }

    public void setRegister_step(String register_step) {
        this.register_step = register_step;
    }

    public String getMember_height() {
        return member_height;
    }

    public void setMember_height(String member_height) {
        this.member_height = member_height;
    }

    public String getMember_weight() {
        return member_weight;
    }

    public void setMember_weight(String member_weight) {
        this.member_weight = member_weight;
    }

    public String getMember_blood_types() {
        return member_blood_types;
    }

    public void setMember_blood_types(String member_blood_types) {
        this.member_blood_types = member_blood_types;
    }

    public String getIs_organ_donor() {
        return is_organ_donor;
    }

    public void setIs_organ_donor(String is_organ_donor) {
        this.is_organ_donor = is_organ_donor;
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

    public String getNursing_store_id() {
        return nursing_store_id;
    }

    public void setNursing_store_id(String nursing_store_id) {
        this.nursing_store_id = nursing_store_id;
    }

    public String getCurrent_identity() {
        return current_identity;
    }

    public void setCurrent_identity(String current_identity) {
        this.current_identity = current_identity;
    }

    public int getEvaluate_count() {
        return evaluate_count;
    }

    public void setEvaluate_count(int evaluate_count) {
        this.evaluate_count = evaluate_count;
    }

    public int getCollection_count() {
        return collection_count;
    }

    public void setCollection_count(int collection_count) {
        this.collection_count = collection_count;
    }

    public List<String> getWork_address() {
        return work_address;
    }

    public void setWork_address(List<String> work_address) {
        this.work_address = work_address;
    }

    public List<String> getAdept() {
        return adept;
    }

    public void setAdept(List<String> adept) {
        this.adept = adept;
    }

}

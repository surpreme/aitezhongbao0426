package com.example.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-17
 * @desc:
 */
public class DoctorInfoPersonBean implements Serializable {

    /**
     * doctor_id : 10
     * member_id : 7
     * work_address : ["地址1","明明哦李静名字听一下几乎"]
     * adept : ["擅长1","壮士一去兮嘻嘻","哦所以所以一直"]
     * introduce : 介绍一下明明体力会计机构公共管理叽里呱啦
     * professional_types : 1
     * departments : 儿科
     * position : 1
     * consult_count : 248
     * evaluate_score : 0.0
     * member_avatar : http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg
     * member_name : 18614079738
     * member_truename : 布雫雫
     * consult_price : 0.01
     * consult_time : 1
     * p_consult_price : 55.00
     * p_consult_time : 25
     * evaluate_count : 0
     * collection_count : 4
     */

    private String doctor_id;
    private String member_id;
    private String introduce;
    private String professional_types;
    private String departments;
    private String position;
    private String consult_count;
    private String evaluate_score;
    private String member_avatar;
    private String member_name;
    private String member_truename;
    private String consult_price;
    private String consult_time;
    private String p_consult_price;
    private String p_consult_time;
    private int evaluate_count;
    private String collection_count;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    private String longitude;
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

    public String getMember_avatar() {
        return member_avatar;
    }

    public void setMember_avatar(String member_avatar) {
        this.member_avatar = member_avatar;
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

    public String getConsult_price() {
        return consult_price;
    }

    public void setConsult_price(String consult_price) {
        this.consult_price = consult_price;
    }

    public String getConsult_time() {
        return consult_time;
    }

    public void setConsult_time(String consult_time) {
        this.consult_time = consult_time;
    }

    public String getP_consult_price() {
        return p_consult_price;
    }

    public void setP_consult_price(String p_consult_price) {
        this.p_consult_price = p_consult_price;
    }

    public String getP_consult_time() {
        return p_consult_time;
    }

    public void setP_consult_time(String p_consult_time) {
        this.p_consult_time = p_consult_time;
    }

    public int getEvaluate_count() {
        return evaluate_count;
    }

    public void setEvaluate_count(int evaluate_count) {
        this.evaluate_count = evaluate_count;
    }

    public String getCollection_count() {
        return collection_count;
    }

    public void setCollection_count(String collection_count) {
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

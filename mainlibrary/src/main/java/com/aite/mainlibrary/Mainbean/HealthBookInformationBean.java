package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class HealthBookInformationBean extends ErrorBean implements Serializable {

    /**
     * id : 52
     * name : 考虑
     * time : 1999-02-01 00:00:00
     * description : 提前
     * diseases_imgs : ["http://zhongbyi.aitecc.com/data/upload/health_record/2019/12/17/Array","http://zhongbyi.aitecc.com/data/upload/health_record/2019/12/17/Array","http://zhongbyi.aitecc.com/data/upload/health_record/2019/12/17/Array"]
     * member_id : 7
     * type : 1
     */

    private String id;
    private String name;
    private String time;
    private String description;
    private String member_id;
    private String type;
    private List<String> diseases_imgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDiseases_imgs() {
        return diseases_imgs;
    }

    public void setDiseases_imgs(List<String> diseases_imgs) {
        this.diseases_imgs = diseases_imgs;
    }
}

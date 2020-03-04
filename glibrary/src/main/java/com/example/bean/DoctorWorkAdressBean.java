package com.example.bean;

import java.io.Serializable;

/**
 * @Auther: liziyang
 * @datetime: 2020-02-11
 * @desc:
 */
public class DoctorWorkAdressBean implements Serializable {

    /**
     * title : 万德莱大厦
     * location : 22.537953,113.948279
     */

    private String title;
    private String location;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

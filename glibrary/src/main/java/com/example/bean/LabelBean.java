package com.example.bean;

/**
 * 创建时间 2019/12/31 12:30
 * 描述:
 */
public class LabelBean {


    public LabelBean(){}

    public LabelBean(String text) {
        this.text = text;
    }

    private String text;

    private Boolean isSelected =false;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}

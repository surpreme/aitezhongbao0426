package com.lzy.basemodule.bean;

import java.io.Serializable;

public class BankNameListBean implements Serializable {

    /**
     * value : CDB
     * text : 国家开发银行
     */

    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

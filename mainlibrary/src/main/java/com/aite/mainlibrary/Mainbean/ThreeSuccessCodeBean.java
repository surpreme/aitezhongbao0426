package com.aite.mainlibrary.Mainbean;

import com.google.gson.annotations.SerializedName;
import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class ThreeSuccessCodeBean extends ErrorBean implements Serializable {

    /**
     * "result":1,
     * "msg":"申请成功"
     */
    @SerializedName(value = "result", alternate = {"state"})
    private String result;
    @SerializedName(value = "msg", alternate = {"username"})
    private String msg;
    private String key;
    private String register_step;

    public String getRegister_step() {
        return register_step;
    }

    public void setRegister_step(String register_step) {
        this.register_step = register_step;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

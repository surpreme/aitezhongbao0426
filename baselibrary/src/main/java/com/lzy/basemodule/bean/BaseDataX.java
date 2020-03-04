package com.lzy.basemodule.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * @author:liziyang
 * @Date: 2019/4/15
 * @description:
 */
public class BaseDataX<T extends ErrorBean> implements Serializable {


    /**
     * error_code : 10003
     * message : 用户名密码错误
     * datas :
     */
    @SerializedName(value = "code", alternate = {"error_code"})
    private Object code;
    private T datas;
    private boolean isSuccessed;
    private String errorMsg;

    public boolean isSuccessed() {
        return !getCode().toString().equals("0");
    }

    public void setSuccessed(boolean successed) {
        isSuccessed = successed;
    }

    public void setErrorMsg(String msg) {
        this.errorMsg = msg;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }


}

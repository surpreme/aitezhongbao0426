package com.lzy.basemodule.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/**
 * @author:TQX
 * @Date: 2019/4/15
 * @description:
 */
public class BaseData2<T> implements Serializable {


    /**
     * error_code : 10003
     * message : 用户名密码错误
     * datas :
     */
    @SerializedName(value = "code", alternate = {"error_code"})
    private Object code;
    private String errorMsg;
    private List<T> datas;
    private boolean isSuccessed;


    public boolean isSuccessed() {
        return getCode().toString().equals("200");
    }

    public boolean isFailed() {
        return !getCode().toString().equals("200");
    }

    public void setSuccessed(boolean successed) {
        isSuccessed = successed;
    }


    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
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


}

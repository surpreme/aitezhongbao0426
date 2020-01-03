package com.example.bean;

/**
 * 创建时间 2019/12/30 10:41
 * 描述:
 */
public class BaseBean<T> {

    private int code;

    private String message;

    private T datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}

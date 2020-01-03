package com.lzy.basemodule.baseRetrofit.http;

public class ApiException extends RuntimeException {

    private String errcode;
    private String errmsg;

    public ApiException() {

    }

    public ApiException(String code, String msg) {
        this.errcode = code;
        this.errmsg = msg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
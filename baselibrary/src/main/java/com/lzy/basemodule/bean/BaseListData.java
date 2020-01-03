package com.lzy.basemodule.bean;

import java.io.Serializable;


/**
 * @author:TQX
 * @Date: 2019/4/15
 * @description:
 */
public class BaseListData<T extends ErrorBean> implements Serializable {


    /**
     * error_code : 10003
     * message : 用户名密码错误
     * datas :
     */

    private Object code;
    private T datas;
    private boolean isSuccessed;
    private String message;
    private String page_total;
    private boolean hasmore;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPage_total() {
        return page_total;
    }

    public void setPage_total(String page_total) {
        this.page_total = page_total;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


    public boolean isSuccessed() {
        return getCode().toString().equals("200");
    }

    public void setSuccessed(boolean successed) {
        isSuccessed = successed;
    }


    private String errorMsg;

//    public String getErrorMsg() {
//        if (!isSuccessed()) {
//            ErrorBean code = BeanConvertor.convertBean(datas, ErrorBean.class);
//            return code.getError();
//        }
//        return null;
//    }

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

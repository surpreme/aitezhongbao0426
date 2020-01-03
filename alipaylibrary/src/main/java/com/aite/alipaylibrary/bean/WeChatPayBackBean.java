package com.aite.alipaylibrary.bean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

/**
 * @Auther: liziyang
 * @datetime: 2019-11-26
 * @desc:
 */
public class WeChatPayBackBean extends ErrorBean implements Serializable {
    /**
     *  返回字段	类型	说明
     *  datas.payinfo	字符串	支付字符串 支付宝
     *  datas.appid	字符串	appid 微信
     *  datas.nonce_str	字符串	随机字符串 微信
     *  datas.prepay_id	字符串	预支付交易会话id 微信
     *  datas.startTimeStamp	字符串	时间戳(毫秒) 微信
     *  datas.sign	字符串	签名 微信
     *  datas.trade_type	字符串	请求方式 微信
     *  datas.wxkey	字符串	微信key 微信
     *  error	字符串	错误信息 code=200 正确 其他编码错误
     *  mch_id 商户号
     */

    /**
     * appid : wx41f275ef24e19565
     * mch_id : 1530313581
     * nonce_str : awzl4umSbn67Js9g
     * prepay_id : wx26155449406910473624908a1164424600
     * result_code : SUCCESS
     * return_code : SUCCESS
     * return_msg : OK
     * sign : ED58C7F65D52DF6733FD9DEB67E5573A
     * trade_type : APP
     * startTimeStamp : 1577346889334
     * wxkey : SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
     */

    private String appid;
    private String mch_id;
    private String nonce_str;
    private String prepay_id;
    private String result_code;
    private String return_code;
    private String return_msg;
    private String sign;
    private String trade_type;
    private String startTimeStamp;
    private String wxkey;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(String startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public String getWxkey() {
        return wxkey;
    }

    public void setWxkey(String wxkey) {
        this.wxkey = wxkey;
    }


}

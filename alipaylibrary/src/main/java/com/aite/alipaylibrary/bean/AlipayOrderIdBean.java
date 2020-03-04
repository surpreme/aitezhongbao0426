package com.aite.alipaylibrary.bean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class AlipayOrderIdBean extends ErrorBean implements Serializable {

    /**
     * payinfo : alipay_sdk=alipay-sdk-php-20160411&app_id=2016022701168596&biz_content=%7B%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%E6%80%BB%E8%AE%A1%E6%94%AF%E4%BB%98%E8%B4%B9%E7%94%A81.00%E5%85%83%21%22%2C%22subject%22%3A%22%E9%A2%84%E5%AD%98%E6%AC%BE%E5%85%85%E5%80%BC_130630673162991007%22%2C%22out_trade_no%22%3A%22130630673162991007%22%2C%22total_amount%22%3A%221.00%22%2C%22timeout_express%22%3A%221m%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fzhongbyi.aitecc.com%2Fwap%2Fapi%2Fpayment%2Falipay%2Fnotify_url_pd_order.php&sign_type=RSA2×tamp=2019-12-26+10%3A59%3A23&version=1.0&sign=OzJ1Dz%2B9fCtFWC7%2BmmJhUVYDM1moDywjFZsYGJxTeKyymWB6wo8eyZwl6%2Fc%2FgGTrVpoH%2BA9TCdeCem4MFuq2zUC28zQZxD%2FW6dbQixoFMPyl%2FltTaosUtYTjoNys4mErH%2BFEJbiPTwhrpuzufjmxjWCgsp52BLfsYAasIU4XspfhqHhC7t7opIPKHpGXvSlLqQJxlWYWhzucHmAXjsVNjx70I9ifjH2%2BAojDgn0vlTqT9%2B5TJK3gDd%2BF1dMvghh1Z4iZxMKFCsXE6rWNePVWyPkp%2BNB3VK7KsItdv8Dnbjmmec7sg1zVw%2BjbLCfeTWA7QVGm1NVVy1sLmNLjFBlwEw%3D%3D
     */
    private String payinfo;

    public String getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(String payinfo) {
        this.payinfo = payinfo;
    }
}

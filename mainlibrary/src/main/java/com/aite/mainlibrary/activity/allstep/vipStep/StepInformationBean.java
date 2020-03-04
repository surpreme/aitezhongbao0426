package com.aite.mainlibrary.activity.allstep.vipStep;

import com.lzy.basemodule.bean.ErrorBean;

import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-13
 * @desc:
 */
public class StepInformationBean extends ErrorBean {


    /**
     * signCount : 1
     * continuousCount : 1
     * thisMonthInfo : ["13"]
     * app_thisMonthInfo : [{"day":"13"}]
     * dayCount : 31
     */

    private String signCount;
    private String continuousCount;
    private String dayCount;
    private List<String> thisMonthInfo;
    private List<AppThisMonthInfoBean> app_thisMonthInfo;

    public String getSignCount() {
        return signCount;
    }

    public void setSignCount(String signCount) {
        this.signCount = signCount;
    }

    public String getContinuousCount() {
        return continuousCount;
    }

    public void setContinuousCount(String continuousCount) {
        this.continuousCount = continuousCount;
    }

    public String getDayCount() {
        return dayCount;
    }

    public void setDayCount(String dayCount) {
        this.dayCount = dayCount;
    }

    public List<String> getThisMonthInfo() {
        return thisMonthInfo;
    }

    public void setThisMonthInfo(List<String> thisMonthInfo) {
        this.thisMonthInfo = thisMonthInfo;
    }

    public List<AppThisMonthInfoBean> getApp_thisMonthInfo() {
        return app_thisMonthInfo;
    }

    public void setApp_thisMonthInfo(List<AppThisMonthInfoBean> app_thisMonthInfo) {
        this.app_thisMonthInfo = app_thisMonthInfo;
    }

    public static class AppThisMonthInfoBean {
        /**
         * day : 13
         */

        private String day;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }
    }
}

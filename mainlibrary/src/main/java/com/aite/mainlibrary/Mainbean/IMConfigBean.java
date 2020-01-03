package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

/**
 * @Auther: liziyang
 * @datetime: 2019-11-25
 * @desc:
 */
public class IMConfigBean extends ErrorBean implements Serializable {


    /**
     * userSig : eJxNjN1OgzAARt*l18b0h1Li3SAbbrIYhS0bISGVFm3YugLFDY3vbkO26O053-m*QZak97yqToO2pR2NBA8AgrsJKyG1VbWSnYPFQDEKisEXjBVDIAi9rrgxSpTclqQT-*JeNOWkHEMehIhin6KrlBejOlny2k7fiFKKIbyln7Lr1Uk7gV2GMIHwT1p1lFPCmM88hG*XvXp3eD3fR8uXaCRh2psZSpKDl3AS4ibKlWmquK1X4yvfrYPzTh*OrN2flx*z1aamOoqHVi5siPK39MvD2yQbtzzXftDkrXiq4*y5etxcwM8vJU9bxQ__
     * identifier : wangwang
     * sdkAppID : 1400301991
     * identifierNick : 天涯海角
     * headurl : http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_3.jpg
     * gender : Gender_Type_Male
     */

    private String userSig;
    private String identifier;
    private String sdkAppID;
    private String identifierNick;
    private String headurl;
    private String gender;

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSdkAppID() {
        return sdkAppID;
    }

    public void setSdkAppID(String sdkAppID) {
        this.sdkAppID = sdkAppID;
    }

    public String getIdentifierNick() {
        return identifierNick;
    }

    public void setIdentifierNick(String identifierNick) {
        this.identifierNick = identifierNick;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

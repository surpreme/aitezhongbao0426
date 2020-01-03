package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2019-11-25
 * @desc:
 */
public class MallChaterBean extends ErrorBean implements Serializable {


    private List<PlatformCallcenterBean> platform_callcenter;

    public List<PlatformCallcenterBean> getPlatform_callcenter() {
        return platform_callcenter;
    }

    public void setPlatform_callcenter(List<PlatformCallcenterBean> platform_callcenter) {
        this.platform_callcenter = platform_callcenter;
    }

    public static class PlatformCallcenterBean implements Serializable {
        /**
         * name : 平台客服1
         * type : im
         * num : 3
         * img : http://zhongbyi.aitecc.com/data/resource/img/type3.png
         */

        private String name;
        private String type;
        private String num;
        private String img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}

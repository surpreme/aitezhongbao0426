package com.aite.mainlibrary.Mainbean;

import java.io.Serializable;
import java.util.List;

public class PlatformListBean implements Serializable {

    private String type_name;

    private List<Services> callcenter_list;

    public List<Services> getCallcenter_list() {
        return callcenter_list;
    }

    public void setCallcenter_list(List<Services> callcenter_list) {
        this.callcenter_list = callcenter_list;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public static class Services implements Serializable {

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

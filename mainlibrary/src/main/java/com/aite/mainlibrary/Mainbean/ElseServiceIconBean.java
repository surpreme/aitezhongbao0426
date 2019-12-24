package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class ElseServiceIconBean extends ErrorBean implements Serializable {


    private List<ListClassBean> list_class;

    public List<ListClassBean> getList_class() {
        return list_class;
    }

    public void setList_class(List<ListClassBean> list_class) {
        this.list_class = list_class;
    }

    public static class ListClassBean {
        /**
         * gc_id : 17
         * gc_name : 中西专区
         * pic : http://zhongbyi.aitecc.com/data/upload/shop/common/category-pic-17.jpg
         */

        private String gc_id;
        private String gc_name;
        private String pic;

        public String getGc_id() {
            return gc_id;
        }

        public void setGc_id(String gc_id) {
            this.gc_id = gc_id;
        }

        public String getGc_name() {
            return gc_name;
        }

        public void setGc_name(String gc_name) {
            this.gc_name = gc_name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}

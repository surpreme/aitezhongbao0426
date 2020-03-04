package com.aite.mainlibrary.activity.allsetting.minerural;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class MineRuralPushListBean extends ErrorBean implements Serializable {


    /**
     * list_total : 1
     * is_nextpage : 0
     * list : [{"theme_id":"2","theme_name":"阿鲁特起来咯图","member_id":"7","member_name":"18614079738","theme_content":"浏览器","theme_addtime":"1578118477","theme_browsecount":"0","if_like":0,"member_avatar":"http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg","h5_url":"http://zhongbyi.aitecc.com/wap/index.php?act=circle&op=circle_theme_arc&theme_id=2"}]
     */

    private String list_total;
    private int is_nextpage;
    private List<ListBean> list;

    public String getList_total() {
        return list_total;
    }

    public void setList_total(String list_total) {
        this.list_total = list_total;
    }

    public int getIs_nextpage() {
        return is_nextpage;
    }

    public void setIs_nextpage(int is_nextpage) {
        this.is_nextpage = is_nextpage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * theme_id : 2
         * theme_name : 阿鲁特起来咯图
         * member_id : 7
         * member_name : 18614079738
         * theme_content : 浏览器
         * theme_addtime : 1578118477
         * theme_browsecount : 0
         * if_like : 0
         * member_avatar : http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg
         * h5_url : http://zhongbyi.aitecc.com/wap/index.php?act=circle&op=circle_theme_arc&theme_id=2
         */

        private String theme_id;
        private String theme_name;
        private String member_id;
        private String member_name;
        private String theme_content;
        private String theme_addtime;
        private String theme_browsecount;
        private int if_like;
        private String member_avatar;
        private String h5_url;

        public String getTheme_id() {
            return theme_id;
        }

        public void setTheme_id(String theme_id) {
            this.theme_id = theme_id;
        }

        public String getTheme_name() {
            return theme_name;
        }

        public void setTheme_name(String theme_name) {
            this.theme_name = theme_name;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getTheme_content() {
            return theme_content;
        }

        public void setTheme_content(String theme_content) {
            this.theme_content = theme_content;
        }

        public String getTheme_addtime() {
            return theme_addtime;
        }

        public void setTheme_addtime(String theme_addtime) {
            this.theme_addtime = theme_addtime;
        }

        public String getTheme_browsecount() {
            return theme_browsecount;
        }

        public void setTheme_browsecount(String theme_browsecount) {
            this.theme_browsecount = theme_browsecount;
        }

        public int getIf_like() {
            return if_like;
        }

        public void setIf_like(int if_like) {
            this.if_like = if_like;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getH5_url() {
            return h5_url;
        }

        public void setH5_url(String h5_url) {
            this.h5_url = h5_url;
        }
    }
}

package com.lzy.basemodule.bean;

import java.io.Serializable;
import java.util.List;

public class OrderDiscussBean extends ErrorBean implements Serializable {

    /**
     * goods_evaluate_info : {"good":0,"normal":1,"bad":0,"all":1,"good_percent":0,"normal_percent":100,"bad_percent":0,"good_star":0,"star_average":3,"pj_url":"http://zhongbyi.aitecc.com/wap/index.php?act=member_order&op=index","cl_url":"http://zhongbyi.aitecc.com/wap/index.php?act=goods&op=consulting_list&goods_id=19","comments_list_url":"STORE_SITE_URL/index.php?act=goods&op=comments_list&goods_id=19"}
     * list_total : 1
     * is_nextpage : 0
     * goodsevallist : [{"geval_id":"2","geval_orderid":"94","geval_orderno":"510628720152960007","geval_ordergoodsid":"94","geval_goodsid":"19","geval_goodscommonid":"19","geval_goodsname":"很好看","geval_goodsprice":"455.00","geval_goodsimage":"2019/11/26/2_06281104227287619.png","geval_scores":"3","geval_content":"地方","geval_isanonymous":"0","geval_addtime":"2019-12-26","geval_storeid":"2","geval_storename":"艾特技术","geval_frommemberid":"7","geval_frommembername":"18614079738","geval_state":"0","geval_remark":null,"geval_explain":null,"geval_image":[],"geval_frommemberavatar":"http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg"}]
     */

    private GoodsEvaluateInfoBean goods_evaluate_info;
    private int list_total;
    private int is_nextpage;
    private List<GoodsevallistBean> goodsevallist;

    public GoodsEvaluateInfoBean getGoods_evaluate_info() {
        return goods_evaluate_info;
    }

    public void setGoods_evaluate_info(GoodsEvaluateInfoBean goods_evaluate_info) {
        this.goods_evaluate_info = goods_evaluate_info;
    }

    public int getList_total() {
        return list_total;
    }

    public void setList_total(int list_total) {
        this.list_total = list_total;
    }

    public int getIs_nextpage() {
        return is_nextpage;
    }

    public void setIs_nextpage(int is_nextpage) {
        this.is_nextpage = is_nextpage;
    }

    public List<GoodsevallistBean> getGoodsevallist() {
        return goodsevallist;
    }

    public void setGoodsevallist(List<GoodsevallistBean> goodsevallist) {
        this.goodsevallist = goodsevallist;
    }

    public static class GoodsEvaluateInfoBean {
        /**
         * good : 0
         * normal : 1
         * bad : 0
         * all : 1
         * good_percent : 0
         * normal_percent : 100
         * bad_percent : 0
         * good_star : 0
         * star_average : 3
         * pj_url : http://zhongbyi.aitecc.com/wap/index.php?act=member_order&op=index
         * cl_url : http://zhongbyi.aitecc.com/wap/index.php?act=goods&op=consulting_list&goods_id=19
         * comments_list_url : STORE_SITE_URL/index.php?act=goods&op=comments_list&goods_id=19
         */

        private int good;
        private int normal;
        private int bad;
        private int all;
        private int good_percent;
        private int normal_percent;
        private int bad_percent;
        private int good_star;
        private int star_average;
        private String pj_url;
        private String cl_url;
        private String comments_list_url;

        public int getGood() {
            return good;
        }

        public void setGood(int good) {
            this.good = good;
        }

        public int getNormal() {
            return normal;
        }

        public void setNormal(int normal) {
            this.normal = normal;
        }

        public int getBad() {
            return bad;
        }

        public void setBad(int bad) {
            this.bad = bad;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getGood_percent() {
            return good_percent;
        }

        public void setGood_percent(int good_percent) {
            this.good_percent = good_percent;
        }

        public int getNormal_percent() {
            return normal_percent;
        }

        public void setNormal_percent(int normal_percent) {
            this.normal_percent = normal_percent;
        }

        public int getBad_percent() {
            return bad_percent;
        }

        public void setBad_percent(int bad_percent) {
            this.bad_percent = bad_percent;
        }

        public int getGood_star() {
            return good_star;
        }

        public void setGood_star(int good_star) {
            this.good_star = good_star;
        }

        public int getStar_average() {
            return star_average;
        }

        public void setStar_average(int star_average) {
            this.star_average = star_average;
        }

        public String getPj_url() {
            return pj_url;
        }

        public void setPj_url(String pj_url) {
            this.pj_url = pj_url;
        }

        public String getCl_url() {
            return cl_url;
        }

        public void setCl_url(String cl_url) {
            this.cl_url = cl_url;
        }

        public String getComments_list_url() {
            return comments_list_url;
        }

        public void setComments_list_url(String comments_list_url) {
            this.comments_list_url = comments_list_url;
        }
    }

    public static class GoodsevallistBean {
        /**
         * geval_id : 2
         * geval_orderid : 94
         * geval_orderno : 510628720152960007
         * geval_ordergoodsid : 94
         * geval_goodsid : 19
         * geval_goodscommonid : 19
         * geval_goodsname : 很好看
         * geval_goodsprice : 455.00
         * geval_goodsimage : 2019/11/26/2_06281104227287619.png
         * geval_scores : 3
         * geval_content : 地方
         * geval_isanonymous : 0
         * geval_addtime : 2019-12-26
         * geval_storeid : 2
         * geval_storename : 艾特技术
         * geval_frommemberid : 7
         * geval_frommembername : 18614079738
         * geval_state : 0
         * geval_remark : null
         * geval_explain : null
         * geval_image : []
         * geval_frommemberavatar : http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg
         */

        private String geval_id;
        private String geval_orderid;
        private String geval_orderno;
        private String geval_ordergoodsid;
        private String geval_goodsid;
        private String geval_goodscommonid;
        private String geval_goodsname;
        private String geval_goodsprice;
        private String geval_goodsimage;
        private String geval_scores;
        private String geval_content;
        private String geval_isanonymous;
        private String geval_addtime;
        private String geval_storeid;
        private String geval_storename;
        private String geval_frommemberid;
        private String geval_frommembername;
        private String geval_state;
        private Object geval_remark;
        private Object geval_explain;
        private String geval_frommemberavatar;
        private List<?> geval_image;

        public String getGeval_id() {
            return geval_id;
        }

        public void setGeval_id(String geval_id) {
            this.geval_id = geval_id;
        }

        public String getGeval_orderid() {
            return geval_orderid;
        }

        public void setGeval_orderid(String geval_orderid) {
            this.geval_orderid = geval_orderid;
        }

        public String getGeval_orderno() {
            return geval_orderno;
        }

        public void setGeval_orderno(String geval_orderno) {
            this.geval_orderno = geval_orderno;
        }

        public String getGeval_ordergoodsid() {
            return geval_ordergoodsid;
        }

        public void setGeval_ordergoodsid(String geval_ordergoodsid) {
            this.geval_ordergoodsid = geval_ordergoodsid;
        }

        public String getGeval_goodsid() {
            return geval_goodsid;
        }

        public void setGeval_goodsid(String geval_goodsid) {
            this.geval_goodsid = geval_goodsid;
        }

        public String getGeval_goodscommonid() {
            return geval_goodscommonid;
        }

        public void setGeval_goodscommonid(String geval_goodscommonid) {
            this.geval_goodscommonid = geval_goodscommonid;
        }

        public String getGeval_goodsname() {
            return geval_goodsname;
        }

        public void setGeval_goodsname(String geval_goodsname) {
            this.geval_goodsname = geval_goodsname;
        }

        public String getGeval_goodsprice() {
            return geval_goodsprice;
        }

        public void setGeval_goodsprice(String geval_goodsprice) {
            this.geval_goodsprice = geval_goodsprice;
        }

        public String getGeval_goodsimage() {
            return geval_goodsimage;
        }

        public void setGeval_goodsimage(String geval_goodsimage) {
            this.geval_goodsimage = geval_goodsimage;
        }

        public String getGeval_scores() {
            return geval_scores;
        }

        public void setGeval_scores(String geval_scores) {
            this.geval_scores = geval_scores;
        }

        public String getGeval_content() {
            return geval_content;
        }

        public void setGeval_content(String geval_content) {
            this.geval_content = geval_content;
        }

        public String getGeval_isanonymous() {
            return geval_isanonymous;
        }

        public void setGeval_isanonymous(String geval_isanonymous) {
            this.geval_isanonymous = geval_isanonymous;
        }

        public String getGeval_addtime() {
            return geval_addtime;
        }

        public void setGeval_addtime(String geval_addtime) {
            this.geval_addtime = geval_addtime;
        }

        public String getGeval_storeid() {
            return geval_storeid;
        }

        public void setGeval_storeid(String geval_storeid) {
            this.geval_storeid = geval_storeid;
        }

        public String getGeval_storename() {
            return geval_storename;
        }

        public void setGeval_storename(String geval_storename) {
            this.geval_storename = geval_storename;
        }

        public String getGeval_frommemberid() {
            return geval_frommemberid;
        }

        public void setGeval_frommemberid(String geval_frommemberid) {
            this.geval_frommemberid = geval_frommemberid;
        }

        public String getGeval_frommembername() {
            return geval_frommembername;
        }

        public void setGeval_frommembername(String geval_frommembername) {
            this.geval_frommembername = geval_frommembername;
        }

        public String getGeval_state() {
            return geval_state;
        }

        public void setGeval_state(String geval_state) {
            this.geval_state = geval_state;
        }

        public Object getGeval_remark() {
            return geval_remark;
        }

        public void setGeval_remark(Object geval_remark) {
            this.geval_remark = geval_remark;
        }

        public Object getGeval_explain() {
            return geval_explain;
        }

        public void setGeval_explain(Object geval_explain) {
            this.geval_explain = geval_explain;
        }

        public String getGeval_frommemberavatar() {
            return geval_frommemberavatar;
        }

        public void setGeval_frommemberavatar(String geval_frommemberavatar) {
            this.geval_frommemberavatar = geval_frommemberavatar;
        }

        public List<?> getGeval_image() {
            return geval_image;
        }

        public void setGeval_image(List<?> geval_image) {
            this.geval_image = geval_image;
        }
    }
}

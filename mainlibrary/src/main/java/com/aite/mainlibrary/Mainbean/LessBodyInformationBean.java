package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-01
 * @desc:
 */
public class LessBodyInformationBean extends ErrorBean implements Serializable {


    /**
     * goods_info : {"goods_id":"19","goods_name":"很好看","goods_price":"455.00","is_virtual":"1","goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281104227287619_240.png"}
     * isFavorites : 1
     * isLike : 0
     * goods_commend_list : [{"page_type":5,"goods_id":"19","goods_name":"很好看","goods_price":"455.00","goods_marketprice":"475278.00","goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281104227287619_240.png"},{"page_type":5,"goods_id":"28","goods_name":"专注滴滴打人30年","goods_price":"5000.00","goods_marketprice":"10000.00","goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/12/05/2_06288600197391356_240.png"},{"page_type":5,"goods_id":"25","goods_name":"给点钱吧，不给就面对疾风吧","goods_price":"23423.00","goods_marketprice":"2464324.00","goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/12/02/2_06286185934053928_240.jpg"}]
     * evaluate_info : {"good":0,"normal":1,"bad":0,"all":1,"good_percent":0,"normal_percent":100,"bad_percent":0,"good_star":0,"star_average":3}
     * goods_evaluate_list : [{"geval_scores_text":"中评","geval_scores":"3","geval_content":"地方","geval_frommembername":"18614079738","geval_member_avatar":"http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg","geval_addtime":"2019-12-26"}]
     */

    private GoodsInfoBean goods_info;
    private int isFavorites;
    private int isLike;
    private EvaluateInfoBean evaluate_info;
    private List<GoodsCommendListBean> goods_commend_list;
    private List<GoodsEvaluateListBean> goods_evaluate_list;

    public GoodsInfoBean getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(GoodsInfoBean goods_info) {
        this.goods_info = goods_info;
    }

    public int getIsFavorites() {
        return isFavorites;
    }

    public void setIsFavorites(int isFavorites) {
        this.isFavorites = isFavorites;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public EvaluateInfoBean getEvaluate_info() {
        return evaluate_info;
    }

    public void setEvaluate_info(EvaluateInfoBean evaluate_info) {
        this.evaluate_info = evaluate_info;
    }

    public List<GoodsCommendListBean> getGoods_commend_list() {
        return goods_commend_list;
    }

    public void setGoods_commend_list(List<GoodsCommendListBean> goods_commend_list) {
        this.goods_commend_list = goods_commend_list;
    }

    public List<GoodsEvaluateListBean> getGoods_evaluate_list() {
        return goods_evaluate_list;
    }

    public void setGoods_evaluate_list(List<GoodsEvaluateListBean> goods_evaluate_list) {
        this.goods_evaluate_list = goods_evaluate_list;
    }

    public static class GoodsInfoBean {
        /**
         * goods_id : 19
         * goods_name : 很好看
         * goods_price : 455.00
         * is_virtual : 1
         * goods_image_url : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281104227287619_240.png
         */

        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String is_virtual;
        private String goods_image_url;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getIs_virtual() {
            return is_virtual;
        }

        public void setIs_virtual(String is_virtual) {
            this.is_virtual = is_virtual;
        }

        public String getGoods_image_url() {
            return goods_image_url;
        }

        public void setGoods_image_url(String goods_image_url) {
            this.goods_image_url = goods_image_url;
        }
    }

    public static class EvaluateInfoBean {
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
    }

    public static class GoodsCommendListBean {
        /**
         * page_type : 5
         * goods_id : 19
         * goods_name : 很好看
         * goods_price : 455.00
         * goods_marketprice : 475278.00
         * goods_image_url : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281104227287619_240.png
         */

        private int page_type;
        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String goods_marketprice;
        private String goods_image_url;

        public int getPage_type() {
            return page_type;
        }

        public void setPage_type(int page_type) {
            this.page_type = page_type;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_marketprice() {
            return goods_marketprice;
        }

        public void setGoods_marketprice(String goods_marketprice) {
            this.goods_marketprice = goods_marketprice;
        }

        public String getGoods_image_url() {
            return goods_image_url;
        }

        public void setGoods_image_url(String goods_image_url) {
            this.goods_image_url = goods_image_url;
        }
    }

    public static class GoodsEvaluateListBean {
        /**
         * geval_scores_text : 中评
         * geval_scores : 3
         * geval_content : 地方
         * geval_frommembername : 18614079738
         * geval_member_avatar : http://zhongbyi.aitecc.com/data/upload/shop/avatar/avatar_7.jpg
         * geval_addtime : 2019-12-26
         */

        private String geval_scores_text;
        private String geval_scores;
        private String geval_content;
        private String geval_frommembername;
        private String geval_member_avatar;
        private String geval_addtime;

        public String getGeval_scores_text() {
            return geval_scores_text;
        }

        public void setGeval_scores_text(String geval_scores_text) {
            this.geval_scores_text = geval_scores_text;
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

        public String getGeval_frommembername() {
            return geval_frommembername;
        }

        public void setGeval_frommembername(String geval_frommembername) {
            this.geval_frommembername = geval_frommembername;
        }

        public String getGeval_member_avatar() {
            return geval_member_avatar;
        }

        public void setGeval_member_avatar(String geval_member_avatar) {
            this.geval_member_avatar = geval_member_avatar;
        }

        public String getGeval_addtime() {
            return geval_addtime;
        }

        public void setGeval_addtime(String geval_addtime) {
            this.geval_addtime = geval_addtime;
        }
    }
}

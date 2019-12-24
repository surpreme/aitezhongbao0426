package com.aite.a.activity.li.bean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class ZhongbaoCollectBean extends ErrorBean implements Serializable {

    /**
     * code : 200
     * hasmore : false
     * page_total : 1
     * datas : {"favorites_list":[{"goods_id":"2","goods_name":"日托服务商品","goods_price":"0.10","goods_image":"2019/10/31/2_06258484919002918.jpg","store_id":"2","is_own_shop":"0","goods_salenum":"12","gc_id_2":"5","gc_id_3":"13","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"bargain_info":[],"goods_url":"STORE_SITE_URL/index.php?act=goods&op=index&goods_id=2","goods_promotion_price":null,"fav_id":"2","page_type":1,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg"},{"goods_id":"15","goods_name":"高级日托服务","goods_price":"1000.00","goods_image":"2019/11/26/2_06281098417000730.png","store_id":"2","is_own_shop":"0","goods_salenum":"2","gc_id_2":"5","gc_id_3":"13","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"bargain_info":[],"goods_url":"STORE_SITE_URL/index.php?act=goods&op=index&goods_id=15","goods_promotion_price":null,"fav_id":"15","page_type":1,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281098417000730_240.png"},{"goods_id":"19","goods_name":"很好看","goods_price":"455.00","goods_image":"2019/11/26/2_06281104227287619.png","store_id":"2","is_own_shop":"0","goods_salenum":"1","gc_id_2":"9","gc_id_3":"17","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"bargain_info":[],"goods_url":"STORE_SITE_URL/index.php?act=goods&op=index&goods_id=19","goods_promotion_price":null,"fav_id":"19","page_type":5,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281104227287619_240.png"},{"goods_id":"25","goods_name":"给点钱吧，不给就面对疾风吧","goods_price":"23423.00","goods_image":"2019/12/02/2_06286185934053928.jpg","store_id":"2","is_own_shop":"0","goods_salenum":"1","gc_id_2":"9","gc_id_3":"17","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"bargain_info":[],"goods_url":"STORE_SITE_URL/index.php?act=goods&op=index&goods_id=25","goods_promotion_price":null,"fav_id":"25","page_type":5,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/12/02/2_06286185934053928_240.jpg"},{"goods_id":"26","goods_name":"培训专家，1天上王者","goods_price":"1000.00","goods_image":"2019/11/26/2_06281104227287619.png","store_id":"2","is_own_shop":"0","goods_salenum":"3","gc_id_2":"6","gc_id_3":"25","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"bargain_info":[],"goods_url":"STORE_SITE_URL/index.php?act=goods&op=index&goods_id=26","goods_promotion_price":null,"fav_id":"26","page_type":2,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281104227287619_240.png"},{"goods_id":"28","goods_name":"专注滴滴打人30年","goods_price":"5000.00","goods_image":"2019/12/05/2_06288600197391356.png","store_id":"2","is_own_shop":"0","goods_salenum":"0","gc_id_2":"9","gc_id_3":"28","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"bargain_info":[],"goods_url":"STORE_SITE_URL/index.php?act=goods&op=index&goods_id=28","goods_promotion_price":null,"fav_id":"28","page_type":5,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/12/05/2_06288600197391356_240.png"}]}
     */

    private int code;
    private boolean hasmore;
    private int page_total;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private List<FavoritesListBean> favorites_list;

        public List<FavoritesListBean> getFavorites_list() {
            return favorites_list;
        }

        public void setFavorites_list(List<FavoritesListBean> favorites_list) {
            this.favorites_list = favorites_list;
        }

        public static class FavoritesListBean {
            /**
             * goods_id : 2
             * goods_name : 日托服务商品
             * goods_price : 0.10
             * goods_image : 2019/10/31/2_06258484919002918.jpg
             * store_id : 2
             * is_own_shop : 0
             * goods_salenum : 12
             * gc_id_2 : 5
             * gc_id_3 : 13
             * groupbuy_info : null
             * xianshi_info : null
             * miaosha_info : []
             * spellgroup_info : []
             * bargain_info : []
             * goods_url : STORE_SITE_URL/index.php?act=goods&op=index&goods_id=2
             * goods_promotion_price : null
             * fav_id : 2
             * page_type : 1
             * goods_image_url : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg
             */

            private String goods_id;
            private String goods_name;
            private String goods_price;
            private String goods_image;
            private String store_id;
            private String is_own_shop;
            private String goods_salenum;
            private String gc_id_2;
            private String gc_id_3;
            private Object groupbuy_info;
            private Object xianshi_info;
            private String goods_url;
            private Object goods_promotion_price;
            private String fav_id;
            private int page_type;
            private String goods_image_url;
            private List<?> miaosha_info;
            private List<?> spellgroup_info;
            private List<?> bargain_info;

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

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getIs_own_shop() {
                return is_own_shop;
            }

            public void setIs_own_shop(String is_own_shop) {
                this.is_own_shop = is_own_shop;
            }

            public String getGoods_salenum() {
                return goods_salenum;
            }

            public void setGoods_salenum(String goods_salenum) {
                this.goods_salenum = goods_salenum;
            }

            public String getGc_id_2() {
                return gc_id_2;
            }

            public void setGc_id_2(String gc_id_2) {
                this.gc_id_2 = gc_id_2;
            }

            public String getGc_id_3() {
                return gc_id_3;
            }

            public void setGc_id_3(String gc_id_3) {
                this.gc_id_3 = gc_id_3;
            }

            public Object getGroupbuy_info() {
                return groupbuy_info;
            }

            public void setGroupbuy_info(Object groupbuy_info) {
                this.groupbuy_info = groupbuy_info;
            }

            public Object getXianshi_info() {
                return xianshi_info;
            }

            public void setXianshi_info(Object xianshi_info) {
                this.xianshi_info = xianshi_info;
            }

            public String getGoods_url() {
                return goods_url;
            }

            public void setGoods_url(String goods_url) {
                this.goods_url = goods_url;
            }

            public Object getGoods_promotion_price() {
                return goods_promotion_price;
            }

            public void setGoods_promotion_price(Object goods_promotion_price) {
                this.goods_promotion_price = goods_promotion_price;
            }

            public String getFav_id() {
                return fav_id;
            }

            public void setFav_id(String fav_id) {
                this.fav_id = fav_id;
            }

            public int getPage_type() {
                return page_type;
            }

            public void setPage_type(int page_type) {
                this.page_type = page_type;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public List<?> getMiaosha_info() {
                return miaosha_info;
            }

            public void setMiaosha_info(List<?> miaosha_info) {
                this.miaosha_info = miaosha_info;
            }

            public List<?> getSpellgroup_info() {
                return spellgroup_info;
            }

            public void setSpellgroup_info(List<?> spellgroup_info) {
                this.spellgroup_info = spellgroup_info;
            }

            public List<?> getBargain_info() {
                return bargain_info;
            }

            public void setBargain_info(List<?> bargain_info) {
                this.bargain_info = bargain_info;
            }
        }
    }
}

package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class BookInfprmationMorningNoonEatBean extends ErrorBean implements Serializable {

    /**
     * goods_list : [{"cart_id":"56","buyer_id":"7","store_id":"2","store_name":"艾特技术","goods_id":"13","goods_name":"助餐商品","goods_price":"0.01","goods_num":"10","goods_image":"2019/11/22/2_06277498241227640.jpg","bl_id":"0","cart_type":"1","state":true,"storage_state":true,"goods_commonid":"13","gc_id":"16","goods_shipping_fee":"0.00","goods_costprice":"0.00","transport_id":"0","goods_freight":"0.00","is_vat":"0","goods_vat":"0","goods_storage":"100","goods_storage_alarm":"0","is_fcode":"0","have_gift":"0","is_more_discount":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"wholesale_price":"","goods_points_offset":0,"is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"goods_total":"0.10","goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/22/2_06277498241227640_240.jpg","goods_costtotal":"0.00"},{"cart_id":"57","buyer_id":"7","store_id":"2","store_name":"艾特技术","goods_id":"12","goods_name":"测试助餐套餐","goods_price":"0.01","goods_num":"1","goods_image":"2019/10/31/2_06258484919002918.jpg","bl_id":"0","cart_type":"1","state":true,"storage_state":true,"goods_commonid":"12","gc_id":"16","goods_shipping_fee":"0.10","goods_costprice":"0.00","transport_id":"0","goods_freight":"0.00","is_vat":"0","goods_vat":"0","goods_storage":"100","goods_storage_alarm":"0","is_fcode":"0","have_gift":"0","is_more_discount":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"wholesale_price":"","goods_points_offset":0,"is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"goods_total":"0.01","goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg","goods_costtotal":"0.00"}]
     * goods_total : 0.21000000000000002
     * total_shipping_fee : 0.1
     * freight_hash : -4KY9W2X93_pubw6MqjsFbvVOXvPKXMC98kbN3v57OaaTFZfuLLEh1ccmEK6dlMYPuKaDQEac6vcM5K3_CDrzKIZmeLJYQAsc7MZrcFY_m9XnpNnQf4opze
     * address_info : {"address_id":"10","member_id":"7","true_name":"南京","area_id":"2134","city_id":"175","area_info":"浙江 杭州市 上城区","address":"数据库","tel_phone":"120","mob_phone":"18614079728","is_default":"0","dlyp_id":"0","province_id":"11"}
     * ifshow_offpay : true
     * is_vat_deny : true
     * vat_hash : syoKlDFziq1-d8ozjyNxrKU50OnCHUIy1mt
     * inv_info : {"content":"不需要发票"}
     * available_predeposit : null
     * available_rc_balance : null
     * member_paypwd : false
     */

    private double goods_total;
    private double total_shipping_fee;
    private String freight_hash;
    private AddressInfoBean address_info;
    private boolean ifshow_offpay;
    private boolean is_vat_deny;
    private String vat_hash;
    private InvInfoBean inv_info;
    private Object available_predeposit;
    private Object available_rc_balance;
    private boolean member_paypwd;
    private List<GoodsListBean> goods_list;

    public double getGoods_total() {
        return goods_total;
    }

    public void setGoods_total(double goods_total) {
        this.goods_total = goods_total;
    }

    public double getTotal_shipping_fee() {
        return total_shipping_fee;
    }

    public void setTotal_shipping_fee(double total_shipping_fee) {
        this.total_shipping_fee = total_shipping_fee;
    }

    public String getFreight_hash() {
        return freight_hash;
    }

    public void setFreight_hash(String freight_hash) {
        this.freight_hash = freight_hash;
    }

    public AddressInfoBean getAddress_info() {
        return address_info;
    }

    public void setAddress_info(AddressInfoBean address_info) {
        this.address_info = address_info;
    }

    public boolean isIfshow_offpay() {
        return ifshow_offpay;
    }

    public void setIfshow_offpay(boolean ifshow_offpay) {
        this.ifshow_offpay = ifshow_offpay;
    }

    public boolean isIs_vat_deny() {
        return is_vat_deny;
    }

    public void setIs_vat_deny(boolean is_vat_deny) {
        this.is_vat_deny = is_vat_deny;
    }

    public String getVat_hash() {
        return vat_hash;
    }

    public void setVat_hash(String vat_hash) {
        this.vat_hash = vat_hash;
    }

    public InvInfoBean getInv_info() {
        return inv_info;
    }

    public void setInv_info(InvInfoBean inv_info) {
        this.inv_info = inv_info;
    }

    public Object getAvailable_predeposit() {
        return available_predeposit;
    }

    public void setAvailable_predeposit(Object available_predeposit) {
        this.available_predeposit = available_predeposit;
    }

    public Object getAvailable_rc_balance() {
        return available_rc_balance;
    }

    public void setAvailable_rc_balance(Object available_rc_balance) {
        this.available_rc_balance = available_rc_balance;
    }

    public boolean isMember_paypwd() {
        return member_paypwd;
    }

    public void setMember_paypwd(boolean member_paypwd) {
        this.member_paypwd = member_paypwd;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class AddressInfoBean {
        /**
         * address_id : 10
         * member_id : 7
         * true_name : 南京
         * area_id : 2134
         * city_id : 175
         * area_info : 浙江 杭州市 上城区
         * address : 数据库
         * tel_phone : 120
         * mob_phone : 18614079728
         * is_default : 0
         * dlyp_id : 0
         * province_id : 11
         */

        private String address_id;
        private String member_id;
        private String true_name;
        private String area_id;
        private String city_id;
        private String area_info;
        private String address;
        private String tel_phone;
        private String mob_phone;
        private String is_default;
        private String dlyp_id;
        private String province_id;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel_phone() {
            return tel_phone;
        }

        public void setTel_phone(String tel_phone) {
            this.tel_phone = tel_phone;
        }

        public String getMob_phone() {
            return mob_phone;
        }

        public void setMob_phone(String mob_phone) {
            this.mob_phone = mob_phone;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getDlyp_id() {
            return dlyp_id;
        }

        public void setDlyp_id(String dlyp_id) {
            this.dlyp_id = dlyp_id;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }
    }

    public static class InvInfoBean {
        /**
         * content : 不需要发票
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class GoodsListBean {
        /**
         * cart_id : 56
         * buyer_id : 7
         * store_id : 2
         * store_name : 艾特技术
         * goods_id : 13
         * goods_name : 助餐商品
         * goods_price : 0.01
         * goods_num : 10
         * goods_image : 2019/11/22/2_06277498241227640.jpg
         * bl_id : 0
         * cart_type : 1
         * state : true
         * storage_state : true
         * goods_commonid : 13
         * gc_id : 16
         * goods_shipping_fee : 0.00
         * goods_costprice : 0.00
         * transport_id : 0
         * goods_freight : 0.00
         * is_vat : 0
         * goods_vat : 0
         * goods_storage : 100
         * goods_storage_alarm : 0
         * is_fcode : 0
         * have_gift : 0
         * is_more_discount : 0
         * groupbuy_info : null
         * xianshi_info : null
         * miaosha_info : []
         * wholesale_price :
         * goods_points_offset : 0
         * is_visit_comm : 0
         * is_Independent_comm : 0
         * comm_rule : null
         * goods_total : 0.10
         * goods_image_url : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/22/2_06277498241227640_240.jpg
         * goods_costtotal : 0.00
         */

        private String cart_id;
        private String buyer_id;
        private String store_id;
        private String store_name;
        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String goods_num;
        private String goods_image;
        private String bl_id;
        private String cart_type;
        private boolean state;
        private boolean storage_state;
        private String goods_commonid;
        private String gc_id;
        private String goods_shipping_fee;
        private String goods_costprice;
        private String transport_id;
        private String goods_freight;
        private String is_vat;
        private String goods_vat;
        private String goods_storage;
        private String goods_storage_alarm;
        private String is_fcode;
        private String have_gift;
        private String is_more_discount;
        private Object groupbuy_info;
        private Object xianshi_info;
        private String wholesale_price;
        private int goods_points_offset;
        private String is_visit_comm;
        private String is_Independent_comm;
        private Object comm_rule;
        private String goods_total;
        private String goods_image_url;
        private String goods_costtotal;
        private List<?> miaosha_info;

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
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

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getBl_id() {
            return bl_id;
        }

        public void setBl_id(String bl_id) {
            this.bl_id = bl_id;
        }

        public String getCart_type() {
            return cart_type;
        }

        public void setCart_type(String cart_type) {
            this.cart_type = cart_type;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public boolean isStorage_state() {
            return storage_state;
        }

        public void setStorage_state(boolean storage_state) {
            this.storage_state = storage_state;
        }

        public String getGoods_commonid() {
            return goods_commonid;
        }

        public void setGoods_commonid(String goods_commonid) {
            this.goods_commonid = goods_commonid;
        }

        public String getGc_id() {
            return gc_id;
        }

        public void setGc_id(String gc_id) {
            this.gc_id = gc_id;
        }

        public String getGoods_shipping_fee() {
            return goods_shipping_fee;
        }

        public void setGoods_shipping_fee(String goods_shipping_fee) {
            this.goods_shipping_fee = goods_shipping_fee;
        }

        public String getGoods_costprice() {
            return goods_costprice;
        }

        public void setGoods_costprice(String goods_costprice) {
            this.goods_costprice = goods_costprice;
        }

        public String getTransport_id() {
            return transport_id;
        }

        public void setTransport_id(String transport_id) {
            this.transport_id = transport_id;
        }

        public String getGoods_freight() {
            return goods_freight;
        }

        public void setGoods_freight(String goods_freight) {
            this.goods_freight = goods_freight;
        }

        public String getIs_vat() {
            return is_vat;
        }

        public void setIs_vat(String is_vat) {
            this.is_vat = is_vat;
        }

        public String getGoods_vat() {
            return goods_vat;
        }

        public void setGoods_vat(String goods_vat) {
            this.goods_vat = goods_vat;
        }

        public String getGoods_storage() {
            return goods_storage;
        }

        public void setGoods_storage(String goods_storage) {
            this.goods_storage = goods_storage;
        }

        public String getGoods_storage_alarm() {
            return goods_storage_alarm;
        }

        public void setGoods_storage_alarm(String goods_storage_alarm) {
            this.goods_storage_alarm = goods_storage_alarm;
        }

        public String getIs_fcode() {
            return is_fcode;
        }

        public void setIs_fcode(String is_fcode) {
            this.is_fcode = is_fcode;
        }

        public String getHave_gift() {
            return have_gift;
        }

        public void setHave_gift(String have_gift) {
            this.have_gift = have_gift;
        }

        public String getIs_more_discount() {
            return is_more_discount;
        }

        public void setIs_more_discount(String is_more_discount) {
            this.is_more_discount = is_more_discount;
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

        public String getWholesale_price() {
            return wholesale_price;
        }

        public void setWholesale_price(String wholesale_price) {
            this.wholesale_price = wholesale_price;
        }

        public int getGoods_points_offset() {
            return goods_points_offset;
        }

        public void setGoods_points_offset(int goods_points_offset) {
            this.goods_points_offset = goods_points_offset;
        }

        public String getIs_visit_comm() {
            return is_visit_comm;
        }

        public void setIs_visit_comm(String is_visit_comm) {
            this.is_visit_comm = is_visit_comm;
        }

        public String getIs_Independent_comm() {
            return is_Independent_comm;
        }

        public void setIs_Independent_comm(String is_Independent_comm) {
            this.is_Independent_comm = is_Independent_comm;
        }

        public Object getComm_rule() {
            return comm_rule;
        }

        public void setComm_rule(Object comm_rule) {
            this.comm_rule = comm_rule;
        }

        public String getGoods_total() {
            return goods_total;
        }

        public void setGoods_total(String goods_total) {
            this.goods_total = goods_total;
        }

        public String getGoods_image_url() {
            return goods_image_url;
        }

        public void setGoods_image_url(String goods_image_url) {
            this.goods_image_url = goods_image_url;
        }

        public String getGoods_costtotal() {
            return goods_costtotal;
        }

        public void setGoods_costtotal(String goods_costtotal) {
            this.goods_costtotal = goods_costtotal;
        }

        public List<?> getMiaosha_info() {
            return miaosha_info;
        }

        public void setMiaosha_info(List<?> miaosha_info) {
            this.miaosha_info = miaosha_info;
        }
    }
}

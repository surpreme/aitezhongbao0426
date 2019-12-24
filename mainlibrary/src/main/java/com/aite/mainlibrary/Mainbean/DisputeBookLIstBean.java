package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class DisputeBookLIstBean extends ErrorBean implements Serializable {

    /**
     * code : 200
     * hasmore : false
     * page_total : 1
     * datas : {"list":[{"complain_id":"6","order_id":"48","order_goods_id":"13","accuser_id":"7","accuser_name":"18614079738","accused_id":"2","accused_name":"艾特技术","complain_subject_content":"态度不好","complain_subject_id":"5","complain_content":"uikyukyhujk","complain_pic1":"","complain_pic2":"","complain_pic3":"","complain_datetime":"1576642411","complain_handle_datetime":"0","complain_handle_member_id":"0","appeal_message":"","appeal_datetime":"0","appeal_pic1":"","appeal_pic2":"","appeal_pic3":"","final_handle_message":"","final_handle_datetime":"0","final_handle_member_id":"0","complain_state":"10","complain_active":"1","complain_state_desc":"新投诉","goods_array":{"rec_id":"13","order_id":"8","goods_id":"12","goods_name":"测试助餐套餐","goods_price":"0.01","goods_costprice":"0.00","goods_num":"1","goods_image":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg","goods_pay_price":"0.01","store_id":"2","buyer_id":"7","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"16","start_time":"0","end_time":"0","dates":null,"days":"0","price_items":null,"hotel_msg":null,"store_points_offset":"0","store_points_offset_amount":"0.00","platform_points_offset":"0","platform_points_offset_amount":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null}},{"complain_id":"5","order_id":"55","order_goods_id":"12","accuser_id":"7","accuser_name":"18614079738","accused_id":"2","accused_name":"艾特技术","complain_subject_content":"态度不好","complain_subject_id":"5","complain_content":"","complain_pic1":"","complain_pic2":"","complain_pic3":"","complain_datetime":"1576642377","complain_handle_datetime":"0","complain_handle_member_id":"0","appeal_message":"","appeal_datetime":"0","appeal_pic1":"","appeal_pic2":"","appeal_pic3":"","final_handle_message":"","final_handle_datetime":"0","final_handle_member_id":"0","complain_state":"10","complain_active":"1","complain_state_desc":"新投诉","goods_array":{"rec_id":"12","order_id":"8","goods_id":"13","goods_name":"助餐商品","goods_price":"0.01","goods_costprice":"0.00","goods_num":"1","goods_image":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/22/2_06277498241227640_240.jpg","goods_pay_price":"0.01","store_id":"2","buyer_id":"7","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"16","start_time":"0","end_time":"0","dates":null,"days":"0","price_items":null,"hotel_msg":null,"store_points_offset":"0","store_points_offset_amount":"0.00","platform_points_offset":"0","platform_points_offset_amount":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null}}],"goods_list":[{"rec_id":"12","order_id":"8","goods_id":"13","goods_name":"助餐商品","goods_price":"0.01","goods_costprice":"0.00","goods_num":"1","goods_image":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/22/2_06277498241227640_240.jpg","goods_pay_price":"0.01","store_id":"2","buyer_id":"7","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"16","start_time":"0","end_time":"0","dates":null,"days":"0","price_items":null,"hotel_msg":null,"store_points_offset":"0","store_points_offset_amount":"0.00","platform_points_offset":"0","platform_points_offset_amount":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null},{"rec_id":"13","order_id":"8","goods_id":"12","goods_name":"测试助餐套餐","goods_price":"0.01","goods_costprice":"0.00","goods_num":"1","goods_image":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg","goods_pay_price":"0.01","store_id":"2","buyer_id":"7","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"16","start_time":"0","end_time":"0","dates":null,"days":"0","price_items":null,"hotel_msg":null,"store_points_offset":"0","store_points_offset_amount":"0.00","platform_points_offset":"0","platform_points_offset_amount":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null}]}
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
        private List<ListBean> list;
        private List<GoodsListBean> goods_list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class ListBean {
            /**
             * complain_id : 6
             * order_id : 48
             * order_goods_id : 13
             * accuser_id : 7
             * accuser_name : 18614079738
             * accused_id : 2
             * accused_name : 艾特技术
             * complain_subject_content : 态度不好
             * complain_subject_id : 5
             * complain_content : uikyukyhujk
             * complain_pic1 :
             * complain_pic2 :
             * complain_pic3 :
             * complain_datetime : 1576642411
             * complain_handle_datetime : 0
             * complain_handle_member_id : 0
             * appeal_message :
             * appeal_datetime : 0
             * appeal_pic1 :
             * appeal_pic2 :
             * appeal_pic3 :
             * final_handle_message :
             * final_handle_datetime : 0
             * final_handle_member_id : 0
             * complain_state : 10
             * complain_active : 1
             * complain_state_desc : 新投诉
             * goods_array : {"rec_id":"13","order_id":"8","goods_id":"12","goods_name":"测试助餐套餐","goods_price":"0.01","goods_costprice":"0.00","goods_num":"1","goods_image":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg","goods_pay_price":"0.01","store_id":"2","buyer_id":"7","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"16","start_time":"0","end_time":"0","dates":null,"days":"0","price_items":null,"hotel_msg":null,"store_points_offset":"0","store_points_offset_amount":"0.00","platform_points_offset":"0","platform_points_offset_amount":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null}
             */

            private String complain_id;
            private String order_id;
            private String order_goods_id;
            private String accuser_id;
            private String accuser_name;
            private String accused_id;
            private String accused_name;
            private String complain_subject_content;
            private String complain_subject_id;
            private String complain_content;
            private String complain_pic1;
            private String complain_pic2;
            private String complain_pic3;
            private String complain_datetime;
            private String complain_handle_datetime;
            private String complain_handle_member_id;
            private String appeal_message;
            private String appeal_datetime;
            private String appeal_pic1;
            private String appeal_pic2;
            private String appeal_pic3;
            private String final_handle_message;
            private String final_handle_datetime;
            private String final_handle_member_id;
            private String complain_state;
            private String complain_active;
            private String complain_state_desc;
            private GoodsArrayBean goods_array;

            public String getComplain_id() {
                return complain_id;
            }

            public void setComplain_id(String complain_id) {
                this.complain_id = complain_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_goods_id() {
                return order_goods_id;
            }

            public void setOrder_goods_id(String order_goods_id) {
                this.order_goods_id = order_goods_id;
            }

            public String getAccuser_id() {
                return accuser_id;
            }

            public void setAccuser_id(String accuser_id) {
                this.accuser_id = accuser_id;
            }

            public String getAccuser_name() {
                return accuser_name;
            }

            public void setAccuser_name(String accuser_name) {
                this.accuser_name = accuser_name;
            }

            public String getAccused_id() {
                return accused_id;
            }

            public void setAccused_id(String accused_id) {
                this.accused_id = accused_id;
            }

            public String getAccused_name() {
                return accused_name;
            }

            public void setAccused_name(String accused_name) {
                this.accused_name = accused_name;
            }

            public String getComplain_subject_content() {
                return complain_subject_content;
            }

            public void setComplain_subject_content(String complain_subject_content) {
                this.complain_subject_content = complain_subject_content;
            }

            public String getComplain_subject_id() {
                return complain_subject_id;
            }

            public void setComplain_subject_id(String complain_subject_id) {
                this.complain_subject_id = complain_subject_id;
            }

            public String getComplain_content() {
                return complain_content;
            }

            public void setComplain_content(String complain_content) {
                this.complain_content = complain_content;
            }

            public String getComplain_pic1() {
                return complain_pic1;
            }

            public void setComplain_pic1(String complain_pic1) {
                this.complain_pic1 = complain_pic1;
            }

            public String getComplain_pic2() {
                return complain_pic2;
            }

            public void setComplain_pic2(String complain_pic2) {
                this.complain_pic2 = complain_pic2;
            }

            public String getComplain_pic3() {
                return complain_pic3;
            }

            public void setComplain_pic3(String complain_pic3) {
                this.complain_pic3 = complain_pic3;
            }

            public String getComplain_datetime() {
                return complain_datetime;
            }

            public void setComplain_datetime(String complain_datetime) {
                this.complain_datetime = complain_datetime;
            }

            public String getComplain_handle_datetime() {
                return complain_handle_datetime;
            }

            public void setComplain_handle_datetime(String complain_handle_datetime) {
                this.complain_handle_datetime = complain_handle_datetime;
            }

            public String getComplain_handle_member_id() {
                return complain_handle_member_id;
            }

            public void setComplain_handle_member_id(String complain_handle_member_id) {
                this.complain_handle_member_id = complain_handle_member_id;
            }

            public String getAppeal_message() {
                return appeal_message;
            }

            public void setAppeal_message(String appeal_message) {
                this.appeal_message = appeal_message;
            }

            public String getAppeal_datetime() {
                return appeal_datetime;
            }

            public void setAppeal_datetime(String appeal_datetime) {
                this.appeal_datetime = appeal_datetime;
            }

            public String getAppeal_pic1() {
                return appeal_pic1;
            }

            public void setAppeal_pic1(String appeal_pic1) {
                this.appeal_pic1 = appeal_pic1;
            }

            public String getAppeal_pic2() {
                return appeal_pic2;
            }

            public void setAppeal_pic2(String appeal_pic2) {
                this.appeal_pic2 = appeal_pic2;
            }

            public String getAppeal_pic3() {
                return appeal_pic3;
            }

            public void setAppeal_pic3(String appeal_pic3) {
                this.appeal_pic3 = appeal_pic3;
            }

            public String getFinal_handle_message() {
                return final_handle_message;
            }

            public void setFinal_handle_message(String final_handle_message) {
                this.final_handle_message = final_handle_message;
            }

            public String getFinal_handle_datetime() {
                return final_handle_datetime;
            }

            public void setFinal_handle_datetime(String final_handle_datetime) {
                this.final_handle_datetime = final_handle_datetime;
            }

            public String getFinal_handle_member_id() {
                return final_handle_member_id;
            }

            public void setFinal_handle_member_id(String final_handle_member_id) {
                this.final_handle_member_id = final_handle_member_id;
            }

            public String getComplain_state() {
                return complain_state;
            }

            public void setComplain_state(String complain_state) {
                this.complain_state = complain_state;
            }

            public String getComplain_active() {
                return complain_active;
            }

            public void setComplain_active(String complain_active) {
                this.complain_active = complain_active;
            }

            public String getComplain_state_desc() {
                return complain_state_desc;
            }

            public void setComplain_state_desc(String complain_state_desc) {
                this.complain_state_desc = complain_state_desc;
            }

            public GoodsArrayBean getGoods_array() {
                return goods_array;
            }

            public void setGoods_array(GoodsArrayBean goods_array) {
                this.goods_array = goods_array;
            }

            public static class GoodsArrayBean {
                /**
                 * rec_id : 13
                 * order_id : 8
                 * goods_id : 12
                 * goods_name : 测试助餐套餐
                 * goods_price : 0.01
                 * goods_costprice : 0.00
                 * goods_num : 1
                 * goods_image : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg
                 * goods_pay_price : 0.01
                 * store_id : 2
                 * buyer_id : 7
                 * goods_type : 1
                 * promotions_id : 0
                 * commis_rate : 200
                 * gc_id : 16
                 * start_time : 0
                 * end_time : 0
                 * dates : null
                 * days : 0
                 * price_items : null
                 * hotel_msg : null
                 * store_points_offset : 0
                 * store_points_offset_amount : 0.00
                 * platform_points_offset : 0
                 * platform_points_offset_amount : 0.00
                 * is_visit_comm : 0
                 * is_Independent_comm : 0
                 * comm_rule : null
                 */

                private String rec_id;
                private String order_id;
                private String goods_id;
                private String goods_name;
                private String goods_price;
                private String goods_costprice;
                private String goods_num;
                private String goods_image;
                private String goods_pay_price;
                private String store_id;
                private String buyer_id;
                private String goods_type;
                private String promotions_id;
                private String commis_rate;
                private String gc_id;
                private String start_time;
                private String end_time;
                private Object dates;
                private String days;
                private Object price_items;
                private Object hotel_msg;
                private String store_points_offset;
                private String store_points_offset_amount;
                private String platform_points_offset;
                private String platform_points_offset_amount;
                private String is_visit_comm;
                private String is_Independent_comm;
                private Object comm_rule;

                public String getRec_id() {
                    return rec_id;
                }

                public void setRec_id(String rec_id) {
                    this.rec_id = rec_id;
                }

                public String getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(String order_id) {
                    this.order_id = order_id;
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

                public String getGoods_costprice() {
                    return goods_costprice;
                }

                public void setGoods_costprice(String goods_costprice) {
                    this.goods_costprice = goods_costprice;
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

                public String getGoods_pay_price() {
                    return goods_pay_price;
                }

                public void setGoods_pay_price(String goods_pay_price) {
                    this.goods_pay_price = goods_pay_price;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
                }

                public String getBuyer_id() {
                    return buyer_id;
                }

                public void setBuyer_id(String buyer_id) {
                    this.buyer_id = buyer_id;
                }

                public String getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(String goods_type) {
                    this.goods_type = goods_type;
                }

                public String getPromotions_id() {
                    return promotions_id;
                }

                public void setPromotions_id(String promotions_id) {
                    this.promotions_id = promotions_id;
                }

                public String getCommis_rate() {
                    return commis_rate;
                }

                public void setCommis_rate(String commis_rate) {
                    this.commis_rate = commis_rate;
                }

                public String getGc_id() {
                    return gc_id;
                }

                public void setGc_id(String gc_id) {
                    this.gc_id = gc_id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public Object getDates() {
                    return dates;
                }

                public void setDates(Object dates) {
                    this.dates = dates;
                }

                public String getDays() {
                    return days;
                }

                public void setDays(String days) {
                    this.days = days;
                }

                public Object getPrice_items() {
                    return price_items;
                }

                public void setPrice_items(Object price_items) {
                    this.price_items = price_items;
                }

                public Object getHotel_msg() {
                    return hotel_msg;
                }

                public void setHotel_msg(Object hotel_msg) {
                    this.hotel_msg = hotel_msg;
                }

                public String getStore_points_offset() {
                    return store_points_offset;
                }

                public void setStore_points_offset(String store_points_offset) {
                    this.store_points_offset = store_points_offset;
                }

                public String getStore_points_offset_amount() {
                    return store_points_offset_amount;
                }

                public void setStore_points_offset_amount(String store_points_offset_amount) {
                    this.store_points_offset_amount = store_points_offset_amount;
                }

                public String getPlatform_points_offset() {
                    return platform_points_offset;
                }

                public void setPlatform_points_offset(String platform_points_offset) {
                    this.platform_points_offset = platform_points_offset;
                }

                public String getPlatform_points_offset_amount() {
                    return platform_points_offset_amount;
                }

                public void setPlatform_points_offset_amount(String platform_points_offset_amount) {
                    this.platform_points_offset_amount = platform_points_offset_amount;
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
            }
        }

        public static class GoodsListBean {
            /**
             * rec_id : 12
             * order_id : 8
             * goods_id : 13
             * goods_name : 助餐商品
             * goods_price : 0.01
             * goods_costprice : 0.00
             * goods_num : 1
             * goods_image : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/22/2_06277498241227640_240.jpg
             * goods_pay_price : 0.01
             * store_id : 2
             * buyer_id : 7
             * goods_type : 1
             * promotions_id : 0
             * commis_rate : 200
             * gc_id : 16
             * start_time : 0
             * end_time : 0
             * dates : null
             * days : 0
             * price_items : null
             * hotel_msg : null
             * store_points_offset : 0
             * store_points_offset_amount : 0.00
             * platform_points_offset : 0
             * platform_points_offset_amount : 0.00
             * is_visit_comm : 0
             * is_Independent_comm : 0
             * comm_rule : null
             */

            private String rec_id;
            private String order_id;
            private String goods_id;
            private String goods_name;
            private String goods_price;
            private String goods_costprice;
            private String goods_num;
            private String goods_image;
            private String goods_pay_price;
            private String store_id;
            private String buyer_id;
            private String goods_type;
            private String promotions_id;
            private String commis_rate;
            private String gc_id;
            private String start_time;
            private String end_time;
            private Object dates;
            private String days;
            private Object price_items;
            private Object hotel_msg;
            private String store_points_offset;
            private String store_points_offset_amount;
            private String platform_points_offset;
            private String platform_points_offset_amount;
            private String is_visit_comm;
            private String is_Independent_comm;
            private Object comm_rule;

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public String getGoods_costprice() {
                return goods_costprice;
            }

            public void setGoods_costprice(String goods_costprice) {
                this.goods_costprice = goods_costprice;
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

            public String getGoods_pay_price() {
                return goods_pay_price;
            }

            public void setGoods_pay_price(String goods_pay_price) {
                this.goods_pay_price = goods_pay_price;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(String buyer_id) {
                this.buyer_id = buyer_id;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getPromotions_id() {
                return promotions_id;
            }

            public void setPromotions_id(String promotions_id) {
                this.promotions_id = promotions_id;
            }

            public String getCommis_rate() {
                return commis_rate;
            }

            public void setCommis_rate(String commis_rate) {
                this.commis_rate = commis_rate;
            }

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public Object getDates() {
                return dates;
            }

            public void setDates(Object dates) {
                this.dates = dates;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public Object getPrice_items() {
                return price_items;
            }

            public void setPrice_items(Object price_items) {
                this.price_items = price_items;
            }

            public Object getHotel_msg() {
                return hotel_msg;
            }

            public void setHotel_msg(Object hotel_msg) {
                this.hotel_msg = hotel_msg;
            }

            public String getStore_points_offset() {
                return store_points_offset;
            }

            public void setStore_points_offset(String store_points_offset) {
                this.store_points_offset = store_points_offset;
            }

            public String getStore_points_offset_amount() {
                return store_points_offset_amount;
            }

            public void setStore_points_offset_amount(String store_points_offset_amount) {
                this.store_points_offset_amount = store_points_offset_amount;
            }

            public String getPlatform_points_offset() {
                return platform_points_offset;
            }

            public void setPlatform_points_offset(String platform_points_offset) {
                this.platform_points_offset = platform_points_offset;
            }

            public String getPlatform_points_offset_amount() {
                return platform_points_offset_amount;
            }

            public void setPlatform_points_offset_amount(String platform_points_offset_amount) {
                this.platform_points_offset_amount = platform_points_offset_amount;
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
        }
    }
}

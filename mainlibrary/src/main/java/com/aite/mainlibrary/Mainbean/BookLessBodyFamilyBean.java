package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class BookLessBodyFamilyBean extends ErrorBean implements Serializable {

    /**
     * list_total : 9
     * is_nextpage : 0
     * order_list : [{"order_id":"422","order_sn":"180630604407668007","store_id":"2","store_name":"艾特技术","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"18614079738","add_time":"2019-12-25 15:53:27","payment_code":"predeposit","payment_time":"1577260408","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"0.01","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"0.01","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"49","goods_name":"小可爱","goods_price":"0.01","goods_num":"1","goods_image":"2019/12/25/2_06305855393241963.jpeg","commis_rate":"200","gc_id":"13","gc_id2":"5","vr_indate":"1609430399","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"0","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"157001407227030772","if_apply":0,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/12/25/2_06305855393241963_240.jpeg"},{"order_id":"381","order_sn":"330630522638651007","store_id":"2","store_name":"艾特技术","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"13208983000","add_time":"2019-12-24 17:10:38","payment_code":"predeposit","payment_time":"1577178640","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"1000.00","goods_points_offset":"10","order_points_offset":"0","order_costamount":"100.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"1000.00","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"21","goods_name":"每天真高兴","goods_price":"1000.00","goods_num":"1","goods_image":"2019/11/26/2_06281100703405005.png","commis_rate":"200","gc_id":"13","gc_id2":"5","vr_indate":"1577635199","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"0","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"856001407212047973","if_apply":0,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/11/26/2_06281100703405005_240.png"},{"order_id":"371","order_sn":"770630014990614007","store_id":"6","store_name":"医保中","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"18614079738","add_time":"2019-12-18 20:09:50","payment_code":"predeposit","payment_time":"1576670991","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"49.80","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"49.80","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"48","goods_name":"日托服务","goods_price":"49.80","goods_num":"1","goods_image":"2019/12/17/6_06299207469041355.jpg","commis_rate":"200","gc_id":"19","gc_id2":"5","vr_indate":"1577807999","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"0","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"942004207232026622","if_apply":0,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/6/2019/12/17/6_06299207469041355_240.jpg"},{"order_id":"370","order_sn":"380630011032729007","store_id":"6","store_name":"医保中","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"18614079738","add_time":"2019-12-18 19:03:52","payment_code":"predeposit","payment_time":"1576667034","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"49.80","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"49.80","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"48","goods_name":"日托服务","goods_price":"49.80","goods_num":"1","goods_image":"2019/12/17/6_06299207469041355.jpg","commis_rate":"200","gc_id":"19","gc_id2":"5","vr_indate":"1577807999","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"0","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"934004207249058886","if_apply":0,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/6/2019/12/17/6_06299207469041355_240.jpg"},{"order_id":"369","order_sn":"750630010915195007","store_id":"6","store_name":"医保中","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"18614079738","add_time":"2019-12-18 19:01:55","payment_code":"predeposit","payment_time":"1576666916","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"49.80","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"49.80","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"48","goods_name":"日托服务","goods_price":"49.80","goods_num":"1","goods_image":"2019/12/17/6_06299207469041355.jpg","commis_rate":"200","gc_id":"19","gc_id2":"5","vr_indate":"1577807999","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"0","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"365004207263038662","if_apply":0,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/6/2019/12/17/6_06299207469041355_240.jpg"},{"order_id":"318","order_sn":"770629766122214007","store_id":"2","store_name":"艾特技术","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"18614079738","add_time":"2019-12-15 23:02:02","payment_code":"predeposit","payment_time":"1576563155","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"0.01","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"0.01","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"1","goods_name":"测试日托服务商品","goods_price":"0.01","goods_num":"1","goods_image":"2019/10/29/2_06256787071214709.jpg","commis_rate":"200","gc_id":"13","gc_id2":"5","vr_indate":"1670601599","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"1","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"859001407276062164","if_apply":1,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/29/2_06256787071214709_240.jpg"},{"order_id":"276","order_sn":"490629575320413007","store_id":"2","store_name":"艾特技术","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"13208983000","add_time":"2019-12-13 18:02:00","payment_code":"predeposit","payment_time":"1576563628","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"0.10","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"0.10","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"2","goods_name":"日托服务商品","goods_price":"0.10","goods_num":"1","goods_image":"2019/10/31/2_06258484919002918.jpg","commis_rate":"200","gc_id":"13","gc_id2":"5","vr_indate":"1734191999","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"1","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"509001407294066268","if_apply":1,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg"},{"order_id":"108","order_sn":"880628818017529007","store_id":"2","store_name":"艾特技术","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"18614079738","add_time":"2019-12-04 23:40:17","payment_code":"predeposit","payment_time":"1576563713","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"0.10","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"0.10","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"2","goods_name":"日托服务商品","goods_price":"0.10","goods_num":"1","goods_image":"2019/10/31/2_06258484919002918.jpg","commis_rate":"200","gc_id":"13","gc_id2":"5","vr_indate":"1734191999","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"1","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"411001407308051888","if_apply":1,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg"},{"order_id":"106","order_sn":"460628817985013007","store_id":"2","store_name":"艾特技术","buyer_id":"7","buyer_name":"18614079738","buyer_phone":"18614079738","add_time":"2019-12-04 23:39:44","payment_code":"predeposit","payment_time":"1576563610","trade_no":null,"close_time":"0","close_reason":null,"finnshed_time":null,"order_amount":"0.10","goods_points_offset":"0","order_points_offset":"0","order_costamount":"0.00","refund_amount":"0.00","rcb_amount":"0.00","pd_amount":"0.10","order_state":"20","refund_state":"0","buyer_msg":null,"delete_state":"0","goods_id":"2","goods_name":"日托服务商品","goods_price":"0.10","goods_num":"1","goods_image":"2019/10/31/2_06258484919002918.jpg","commis_rate":"200","gc_id":"13","gc_id2":"5","vr_indate":"1734191999","vr_send_times":"0","vr_invalid_refund":"0","order_promotion_type":"0","promotions_id":"0","order_from":"2","evaluation_state":"0","evaluation_time":"0","use_state":"0","first_comm":"0.00","second_comm":"0.00","three_comm":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"is_buy_apply":"1","meal_info":null,"order_shipping_fee":"0.00","order_state_text":"已支付","payment_name":"站内余额支付","page_type":1,"if_cancel":0,"if_pay":0,"if_detail":1,"is_verify":1,"vr_code":"799001407322028658","if_apply":1,"if_evaluation":0,"goods_image_url":"http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/10/31/2_06258484919002918_240.jpg"}]
     */

    private String list_total;
    private int is_nextpage;
    private List<OrderListBean> order_list;

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

    public List<OrderListBean> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderListBean> order_list) {
        this.order_list = order_list;
    }

    public static class OrderListBean {
        /**
         * order_id : 422
         * order_sn : 180630604407668007
         * store_id : 2
         * store_name : 艾特技术
         * buyer_id : 7
         * buyer_name : 18614079738
         * buyer_phone : 18614079738
         * add_time : 2019-12-25 15:53:27
         * payment_code : predeposit
         * payment_time : 1577260408
         * trade_no : null
         * close_time : 0
         * close_reason : null
         * finnshed_time : null
         * order_amount : 0.01
         * goods_points_offset : 0
         * order_points_offset : 0
         * order_costamount : 0.00
         * refund_amount : 0.00
         * rcb_amount : 0.00
         * pd_amount : 0.01
         * order_state : 20
         * refund_state : 0
         * buyer_msg : null
         * delete_state : 0
         * goods_id : 49
         * goods_name : 小可爱
         * goods_price : 0.01
         * goods_num : 1
         * goods_image : 2019/12/25/2_06305855393241963.jpeg
         * commis_rate : 200
         * gc_id : 13
         * gc_id2 : 5
         * vr_indate : 1609430399
         * vr_send_times : 0
         * vr_invalid_refund : 0
         * order_promotion_type : 0
         * promotions_id : 0
         * order_from : 2
         * evaluation_state : 0
         * evaluation_time : 0
         * use_state : 0
         * first_comm : 0.00
         * second_comm : 0.00
         * three_comm : 0.00
         * is_visit_comm : 0
         * is_Independent_comm : 0
         * comm_rule : null
         * is_buy_apply : 0
         * meal_info : null
         * order_shipping_fee : 0.00
         * order_state_text : 已支付
         * payment_name : 站内余额支付
         * page_type : 1
         * if_cancel : 0
         * if_pay : 0
         * if_detail : 1
         * is_verify : 1
         * vr_code : 157001407227030772
         * if_apply : 0
         * if_evaluation : 0
         * goods_image_url : http://zhongbyi.aitecc.com/data/upload/shop/store/goods/2/2019/12/25/2_06305855393241963_240.jpeg
         */

        private String order_id;
        private String order_sn;
        private String store_id;
        private String store_name;
        private String buyer_id;
        private String buyer_name;
        private String buyer_phone;
        private String add_time;
        private String payment_code;
        private String payment_time;
        private Object trade_no;
        private String close_time;
        private Object close_reason;
        private Object finnshed_time;
        private String order_amount;
        private String goods_points_offset;
        private String order_points_offset;
        private String order_costamount;
        private String refund_amount;
        private String rcb_amount;
        private String pd_amount;
        private String order_state;
        private String refund_state;
        private Object buyer_msg;
        private String delete_state;
        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String goods_num;
        private String goods_image;
        private String commis_rate;
        private String gc_id;
        private String gc_id2;
        private String vr_indate;
        private String vr_send_times;
        private String vr_invalid_refund;
        private String order_promotion_type;
        private String promotions_id;
        private String order_from;
        private String evaluation_state;
        private String evaluation_time;
        private String use_state;
        private String first_comm;
        private String second_comm;
        private String three_comm;
        private String is_visit_comm;
        private String is_Independent_comm;
        private Object comm_rule;
        private String is_buy_apply;
        private Object meal_info;
        private String order_shipping_fee;
        private String order_state_text;
        private String payment_name;
        private int page_type;
        private int if_cancel;
        private int if_pay;
        private int if_detail;
        private int is_verify;
        private String vr_code;
        private int if_apply;
        private int if_evaluation;
        private String goods_image_url;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
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

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_name() {
            return buyer_name;
        }

        public void setBuyer_name(String buyer_name) {
            this.buyer_name = buyer_name;
        }

        public String getBuyer_phone() {
            return buyer_phone;
        }

        public void setBuyer_phone(String buyer_phone) {
            this.buyer_phone = buyer_phone;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public Object getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(Object trade_no) {
            this.trade_no = trade_no;
        }

        public String getClose_time() {
            return close_time;
        }

        public void setClose_time(String close_time) {
            this.close_time = close_time;
        }

        public Object getClose_reason() {
            return close_reason;
        }

        public void setClose_reason(Object close_reason) {
            this.close_reason = close_reason;
        }

        public Object getFinnshed_time() {
            return finnshed_time;
        }

        public void setFinnshed_time(Object finnshed_time) {
            this.finnshed_time = finnshed_time;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getGoods_points_offset() {
            return goods_points_offset;
        }

        public void setGoods_points_offset(String goods_points_offset) {
            this.goods_points_offset = goods_points_offset;
        }

        public String getOrder_points_offset() {
            return order_points_offset;
        }

        public void setOrder_points_offset(String order_points_offset) {
            this.order_points_offset = order_points_offset;
        }

        public String getOrder_costamount() {
            return order_costamount;
        }

        public void setOrder_costamount(String order_costamount) {
            this.order_costamount = order_costamount;
        }

        public String getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(String refund_amount) {
            this.refund_amount = refund_amount;
        }

        public String getRcb_amount() {
            return rcb_amount;
        }

        public void setRcb_amount(String rcb_amount) {
            this.rcb_amount = rcb_amount;
        }

        public String getPd_amount() {
            return pd_amount;
        }

        public void setPd_amount(String pd_amount) {
            this.pd_amount = pd_amount;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
        }

        public Object getBuyer_msg() {
            return buyer_msg;
        }

        public void setBuyer_msg(Object buyer_msg) {
            this.buyer_msg = buyer_msg;
        }

        public String getDelete_state() {
            return delete_state;
        }

        public void setDelete_state(String delete_state) {
            this.delete_state = delete_state;
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

        public String getGc_id2() {
            return gc_id2;
        }

        public void setGc_id2(String gc_id2) {
            this.gc_id2 = gc_id2;
        }

        public String getVr_indate() {
            return vr_indate;
        }

        public void setVr_indate(String vr_indate) {
            this.vr_indate = vr_indate;
        }

        public String getVr_send_times() {
            return vr_send_times;
        }

        public void setVr_send_times(String vr_send_times) {
            this.vr_send_times = vr_send_times;
        }

        public String getVr_invalid_refund() {
            return vr_invalid_refund;
        }

        public void setVr_invalid_refund(String vr_invalid_refund) {
            this.vr_invalid_refund = vr_invalid_refund;
        }

        public String getOrder_promotion_type() {
            return order_promotion_type;
        }

        public void setOrder_promotion_type(String order_promotion_type) {
            this.order_promotion_type = order_promotion_type;
        }

        public String getPromotions_id() {
            return promotions_id;
        }

        public void setPromotions_id(String promotions_id) {
            this.promotions_id = promotions_id;
        }

        public String getOrder_from() {
            return order_from;
        }

        public void setOrder_from(String order_from) {
            this.order_from = order_from;
        }

        public String getEvaluation_state() {
            return evaluation_state;
        }

        public void setEvaluation_state(String evaluation_state) {
            this.evaluation_state = evaluation_state;
        }

        public String getEvaluation_time() {
            return evaluation_time;
        }

        public void setEvaluation_time(String evaluation_time) {
            this.evaluation_time = evaluation_time;
        }

        public String getUse_state() {
            return use_state;
        }

        public void setUse_state(String use_state) {
            this.use_state = use_state;
        }

        public String getFirst_comm() {
            return first_comm;
        }

        public void setFirst_comm(String first_comm) {
            this.first_comm = first_comm;
        }

        public String getSecond_comm() {
            return second_comm;
        }

        public void setSecond_comm(String second_comm) {
            this.second_comm = second_comm;
        }

        public String getThree_comm() {
            return three_comm;
        }

        public void setThree_comm(String three_comm) {
            this.three_comm = three_comm;
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

        public String getIs_buy_apply() {
            return is_buy_apply;
        }

        public void setIs_buy_apply(String is_buy_apply) {
            this.is_buy_apply = is_buy_apply;
        }

        public Object getMeal_info() {
            return meal_info;
        }

        public void setMeal_info(Object meal_info) {
            this.meal_info = meal_info;
        }

        public String getOrder_shipping_fee() {
            return order_shipping_fee;
        }

        public void setOrder_shipping_fee(String order_shipping_fee) {
            this.order_shipping_fee = order_shipping_fee;
        }

        public String getOrder_state_text() {
            return order_state_text;
        }

        public void setOrder_state_text(String order_state_text) {
            this.order_state_text = order_state_text;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public int getPage_type() {
            return page_type;
        }

        public void setPage_type(int page_type) {
            this.page_type = page_type;
        }

        public int getIf_cancel() {
            return if_cancel;
        }

        public void setIf_cancel(int if_cancel) {
            this.if_cancel = if_cancel;
        }

        public int getIf_pay() {
            return if_pay;
        }

        public void setIf_pay(int if_pay) {
            this.if_pay = if_pay;
        }

        public int getIf_detail() {
            return if_detail;
        }

        public void setIf_detail(int if_detail) {
            this.if_detail = if_detail;
        }

        public int getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(int is_verify) {
            this.is_verify = is_verify;
        }

        public String getVr_code() {
            return vr_code;
        }

        public void setVr_code(String vr_code) {
            this.vr_code = vr_code;
        }

        public int getIf_apply() {
            return if_apply;
        }

        public void setIf_apply(int if_apply) {
            this.if_apply = if_apply;
        }

        public int getIf_evaluation() {
            return if_evaluation;
        }

        public void setIf_evaluation(int if_evaluation) {
            this.if_evaluation = if_evaluation;
        }

        public String getGoods_image_url() {
            return goods_image_url;
        }

        public void setGoods_image_url(String goods_image_url) {
            this.goods_image_url = goods_image_url;
        }
    }
}

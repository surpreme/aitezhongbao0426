package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;

public class SystemMsgBean extends ErrorBean implements Serializable {

    /**
     * new_num : {"newcommon":"0","newsystem":"89","newpersonal":"0","isallowsend":true}
     * message_array : [{"message_id":"178","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-26 09:51:53 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 950630669113310007，可用金额变化 ：-1.00元，冻结金额变化：1.00元。","message_time":"1577325113","message_update_time":"1577325113","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-26"},{"message_id":"177","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-26 00:27:13 账户资金有变化，描述：下单，支付预存款，订单号: 450630635231793007，可用金额变化 ：-2.50元，冻结金额变化：0.00元。","message_time":"1577291233","message_update_time":"1577291233","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-26"},{"message_id":"176","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 23:45:13 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 680630632713192007，可用金额变化 ：-1.00元，冻结金额变化：1.00元。","message_time":"1577288713","message_update_time":"1577288713","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"},{"message_id":"175","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 23:43:11 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 520630632591771007，可用金额变化 ：-12.00元，冻结金额变化：12.00元。","message_time":"1577288591","message_update_time":"1577288591","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"},{"message_id":"174","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 23:15:15 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 110630630915594007，可用金额变化 ：-2.00元，冻结金额变化：2.00元。","message_time":"1577286915","message_update_time":"1577286915","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"},{"message_id":"173","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 22:29:26 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 580630628166034007，可用金额变化 ：-1.00元，冻结金额变化：1.00元。","message_time":"1577284166","message_update_time":"1577284166","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"},{"message_id":"172","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 22:26:39 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 670630627999970007，可用金额变化 ：-12.00元，冻结金额变化：12.00元。","message_time":"1577283999","message_update_time":"1577283999","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"},{"message_id":"171","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 22:13:05 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 760630627185512007，可用金额变化 ：-1.00元，冻结金额变化：1.00元。","message_time":"1577283185","message_update_time":"1577283185","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"},{"message_id":"170","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 21:34:19 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 580630624859200007，可用金额变化 ：-12.00元，冻结金额变化：12.00元。","message_time":"1577280859","message_update_time":"1577280859","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"},{"message_id":"169","message_parent_id":"0","from_member_id":"0","to_member_id":"7","message_title":null,"message_body":"你的账户于 2019-12-25 21:13:38 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 550630623618651007，可用金额变化 ：-1.00元，冻结金额变化：1.00元。","message_time":"1577279618","message_update_time":"1577279618","message_open":"0","message_state":"0","message_type":"1","read_member_id":"","del_member_id":"","message_ismore":"0","from_member_name":"系统消息","to_member_name":"","message_time_text":"2019-12-25"}]
     */

    private NewNumBean new_num;
    private List<MessageArrayBean> message_array;

    public NewNumBean getNew_num() {
        return new_num;
    }

    public void setNew_num(NewNumBean new_num) {
        this.new_num = new_num;
    }

    public List<MessageArrayBean> getMessage_array() {
        return message_array;
    }

    public void setMessage_array(List<MessageArrayBean> message_array) {
        this.message_array = message_array;
    }

    public static class NewNumBean {
        /**
         * newcommon : 0
         * newsystem : 89
         * newpersonal : 0
         * isallowsend : true
         */

        private String newcommon;
        private String newsystem;
        private String newpersonal;
        private boolean isallowsend;

        public String getNewcommon() {
            return newcommon;
        }

        public void setNewcommon(String newcommon) {
            this.newcommon = newcommon;
        }

        public String getNewsystem() {
            return newsystem;
        }

        public void setNewsystem(String newsystem) {
            this.newsystem = newsystem;
        }

        public String getNewpersonal() {
            return newpersonal;
        }

        public void setNewpersonal(String newpersonal) {
            this.newpersonal = newpersonal;
        }

        public boolean isIsallowsend() {
            return isallowsend;
        }

        public void setIsallowsend(boolean isallowsend) {
            this.isallowsend = isallowsend;
        }
    }

    public static class MessageArrayBean {
        /**
         * message_id : 178
         * message_parent_id : 0
         * from_member_id : 0
         * to_member_id : 7
         * message_title : null
         * message_body : 你的账户于 2019-12-26 09:51:53 账户资金有变化，描述：申请提现，冻结预存款，提现单号: 950630669113310007，可用金额变化 ：-1.00元，冻结金额变化：1.00元。
         * message_time : 1577325113
         * message_update_time : 1577325113
         * message_open : 0
         * message_state : 0
         * message_type : 1
         * read_member_id :
         * del_member_id :
         * message_ismore : 0
         * from_member_name : 系统消息
         * to_member_name :
         * message_time_text : 2019-12-26
         */

        private String message_id;
        private String message_parent_id;
        private String from_member_id;
        private String to_member_id;
        private Object message_title;
        private String message_body;
        private String message_time;
        private String message_update_time;
        private String message_open;
        private String message_state;
        private String message_type;
        private String read_member_id;
        private String del_member_id;
        private String message_ismore;
        private String from_member_name;
        private String to_member_name;
        private String message_time_text;

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }

        public String getMessage_parent_id() {
            return message_parent_id;
        }

        public void setMessage_parent_id(String message_parent_id) {
            this.message_parent_id = message_parent_id;
        }

        public String getFrom_member_id() {
            return from_member_id;
        }

        public void setFrom_member_id(String from_member_id) {
            this.from_member_id = from_member_id;
        }

        public String getTo_member_id() {
            return to_member_id;
        }

        public void setTo_member_id(String to_member_id) {
            this.to_member_id = to_member_id;
        }

        public Object getMessage_title() {
            return message_title;
        }

        public void setMessage_title(Object message_title) {
            this.message_title = message_title;
        }

        public String getMessage_body() {
            return message_body;
        }

        public void setMessage_body(String message_body) {
            this.message_body = message_body;
        }

        public String getMessage_time() {
            return message_time;
        }

        public void setMessage_time(String message_time) {
            this.message_time = message_time;
        }

        public String getMessage_update_time() {
            return message_update_time;
        }

        public void setMessage_update_time(String message_update_time) {
            this.message_update_time = message_update_time;
        }

        public String getMessage_open() {
            return message_open;
        }

        public void setMessage_open(String message_open) {
            this.message_open = message_open;
        }

        public String getMessage_state() {
            return message_state;
        }

        public void setMessage_state(String message_state) {
            this.message_state = message_state;
        }

        public String getMessage_type() {
            return message_type;
        }

        public void setMessage_type(String message_type) {
            this.message_type = message_type;
        }

        public String getRead_member_id() {
            return read_member_id;
        }

        public void setRead_member_id(String read_member_id) {
            this.read_member_id = read_member_id;
        }

        public String getDel_member_id() {
            return del_member_id;
        }

        public void setDel_member_id(String del_member_id) {
            this.del_member_id = del_member_id;
        }

        public String getMessage_ismore() {
            return message_ismore;
        }

        public void setMessage_ismore(String message_ismore) {
            this.message_ismore = message_ismore;
        }

        public String getFrom_member_name() {
            return from_member_name;
        }

        public void setFrom_member_name(String from_member_name) {
            this.from_member_name = from_member_name;
        }

        public String getTo_member_name() {
            return to_member_name;
        }

        public void setTo_member_name(String to_member_name) {
            this.to_member_name = to_member_name;
        }

        public String getMessage_time_text() {
            return message_time_text;
        }

        public void setMessage_time_text(String message_time_text) {
            this.message_time_text = message_time_text;
        }
    }
}

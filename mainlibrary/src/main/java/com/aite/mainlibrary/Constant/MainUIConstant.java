package com.aite.mainlibrary.Constant;

import com.aite.mainlibrary.R;

public class MainUIConstant {

    public static class MainFragmentRecyConstant {

        public static String[] settingTv = {
                "助餐", "助医", "时间银行", "喘息服务", "其他服务"
        };

    }

    public static class MainFragmentConstant {
        public static int[] settingImg = {
                R.drawable.baomu,
                R.mipmap.learn_work,
                R.drawable.finduser,
                R.drawable.lessbodything,
                R.drawable.elseservice,
        };

        public static String[] settingTv = {
                "日托", "培训", "就业", "助残活动", "其他服务"
        };

    }

    //   R.drawable.fact_money,
//  "提现",
    public static class MoneycartActivityConstant {
        public static int[] settingImg = {

                R.drawable.nofact_money,
                R.drawable.redcard,
                R.mipmap.bankcard,
        };

        public static String[] settingTv = {
                "充值", "账单明细", "银行卡"
        };

    }

    public static class ElseHelpConstant {
        public static int[] settingImg = {
                R.drawable.doctor,
                R.mipmap.bed,

                R.mipmap.medicen,
                R.mipmap.film,

                R.mipmap.peoplegray,
                R.mipmap.heard
        };
        public static int[] backgrondImg = {
                R.mipmap.blue_else,
                R.mipmap.violet_else,

                R.mipmap.orange_else,
                R.mipmap.yellow_else,

                R.mipmap.green_else,
                R.mipmap.greeen_else_white
        };

        public static String[] settingTv = {
                "中西专区", "床位服务",
                "助急服务", "文娱服务",
                "专项服务", "老人平安打卡"
        };

    }


    public static class MessageConstant {
        public static int[] settingImg = {
                R.mipmap.dangrous,
                R.mipmap.oclock,
                R.mipmap.talkman,
        };

        public static String[] settingTv = {
                "报警信息", "在线客服", "系统通知"
        };

    }

    public static class MinePostServiceConstant {
        public static int[] settingImg = {
                R.mipmap.service,
                R.mipmap.helpdoctor,
                R.mipmap.elsesevice,
        };

        public static String[] settingTv = {
                "时间银行", "助医服务", "喘息服务"
        };

    }

    /**
     * R.mipmap.elsesevice,
     * R.mipmap.bankcard
     * "全部", "早餐", "午餐", "助餐"
     */
    public static class MirningNoonbookConstant {
        public static int[] settingImg = {
                R.mipmap.nooneat,
                R.drawable.choiceeat2,

        };

        public static String[] settingTv = {
                "早午餐", "菜品"
        };

    }

    /**
     * R.mipmap.house,
     * "参与的活动",
     */
    public static class MineConstant {
        public static int[] settingImg = {
                R.mipmap.mine_device,
                R.mipmap.activity,

                R.mipmap.collect,
        };

        public static String[] settingTv = {
                "我的设备", "我的社区", "我的收藏"
        };

        public static int[] bookImg = {
                R.mipmap.service_order_icon,
                R.mipmap.eatoder_icon,
                R.mipmap.consult_icon
//                R.mipmap.helpdoctor,

//                R.mipmap.service,
//                R.mipmap.elsesevice,
//
//                R.drawable.baomu,
//                R.mipmap.learn_work,
//
//                R.drawable.finduser,
//                R.drawable.lessbodything

        };
        //        "助医", "喘息服务",
        //        "助餐", "其它服务",
        //        "日托", "培训", "辅助就业", "助残活动"
        public static String[] bookTv = {
                "服务订单", "助餐订单", "咨询订单"
        };

        public static int[] elseImg = {
                R.mipmap.money,
                R.mipmap.health,
                R.mipmap.opennew,
                R.mipmap.error,
        };
        public static String[] elseTv = {
                "我的钱包", "健康档案", "我的发布", "交易投诉"
        };
        public static int[] deviceImg = {
                R.mipmap.add_blue
        };
        public static String[] deviceTv = {
                "添加设备"
        };


        /*---------医生 ---------*/
        public static String[] mDoctorApplyTv = {
                "全部订单", "医生资料","咨询列表"
        };


        public static int[] mDoctorApplyImg = {
                R.mipmap.doctor_icon1,
                R.mipmap.doctor_icon2,
                R.mipmap.doctor_icon3,
//                R.mipmap.doctor_icon4,
        };


        public static String[] mDoctorElseTv = {
                "我的钱包", "咨询设置",
        };


        public static int[] mDoctorElseImg = {
                R.mipmap.doctor_icon5,
                R.mipmap.doctor_icon6,
        };

        /*===========义工==========*/
        public static String[] mVolunteerApplyTv = {
                "全部订单", "我的活动", "我的社区", "义工资料"
        };


        public static int[] mVolunteerApplyImg = {

                R.mipmap.volunteer_icon1,
                R.mipmap.volunteer_icon2,
                R.mipmap.volunteer_icon3,
                R.mipmap.volunteer_icon4,
        };


        public static String[] mVolunteerElseTv = {
                "我的钱包",
        };


        public static int[] mVolunteerElseImg = {
                R.mipmap.doctor_icon5,
        };

    }
}

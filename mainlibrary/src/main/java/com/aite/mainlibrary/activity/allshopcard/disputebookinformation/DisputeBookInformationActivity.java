package com.aite.mainlibrary.activity.allshopcard.disputebookinformation;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aite.mainlibrary.Mainbean.DisputeInformationBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.bumptech.glide.Glide;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DisputeBookInformationActivity extends BaseActivity<DisputeBookInformationContract.View, DisputeBookInformationPresenter> implements DisputeBookInformationContract.View {

    @BindView(R2.id.seekBar)
    SeekBar seekBar;
    @BindView(R2.id.title_tv)
    TextView titleTv;
    @BindView(R2.id.store_name_tv)
    TextView storeNameTv;
    @BindView(R2.id.information_tv)
    TextView informationTv;
    @BindView(R2.id.time_tv)
    TextView timeTv;
    @BindView(R2.id.state_tv)
    TextView stateTv;
    @BindView(R2.id.dispute_first_img)
    ImageView disputeFirstImg;
    @BindView(R2.id.dispute_sencond_img)
    ImageView disputeSencondImg;
    @BindView(R2.id.dispute_thrid_img)
    ImageView disputeThridImg;

    @Override
    protected int getLayoutResId() {
        return R.layout.dispute_information;
    }

    @Override
    protected void initView() {
        initToolbar("投诉详情");
        seekBar.setMax(10);
        seekBar.setEnabled(false);

    }

    @Override
    protected void initDatas() {
        mPresenter.getDispute(initListHttpParams(true, new ContentValue("complain_id", getIntent().getStringExtra("complain_id"))));

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    /**
     * 返回字段	类型	说明
     * complain_id	整型	投诉id
     * order_id	整型	订单ID
     * order_goods_id	整型	订单商品ID
     * accuser_id	整型	原告id
     * accuser_name	字符串	原告名称
     * accused_id	整型	被告id
     * accused_name	字符串	被告名称
     * complain_subject_content	字符串	投诉主题
     * complain_content	字符串	投诉内容
     * complain_datetime_text	字符串	投诉时间
     * complain_pic1	字符串	投诉图片1
     * complain_pic2	字符串	投诉图片2
     * complain_pic3	字符串	投诉图片3
     * appeal_message	字符串	商家申诉内容
     * appeal_datetime	整型	商家申诉时间
     * list[].appeal_pic1	字符串	商家申诉图片1
     * list[].appeal_pic2	字符串	商家申诉图片2
     * list[].appeal_pic3	字符串	商家申诉图片3
     *
     * @param msg
     */
    @Override
    public void onGetDisputeSuccess(Object msg) {
        DisputeInformationBean disputeInformationBean = (DisputeInformationBean) msg;
        if (disputeInformationBean != null) {
            titleTv.setText(String.format("%s%s", disputeInformationBean.getComplain_subject_content(), disputeInformationBean.getAppeal_message()));
            timeTv.setText(String.format("投诉时间: %s", disputeInformationBean.getComplain_datetime_text()));
            storeNameTv.setText(String.format("被投诉商家名称： %s", disputeInformationBean.getAccused_name()));
            stateTv.setText(String.format("当前投诉进度：%s", disputeInformationBean.getComplain_state_text()));
            informationTv.setText(String.format("投诉信息%s", disputeInformationBean.getComplain_content()));
            if (disputeInformationBean.getComplain_pic1() != null) {
                Glide.with(context).load(disputeInformationBean.getComplain_pic1()).into(disputeFirstImg);
            }
            if (disputeInformationBean.getComplain_pic2() != null) {
                Glide.with(context).load(disputeInformationBean.getComplain_pic2()).into(disputeSencondImg);
            }
            if (disputeInformationBean.getComplain_pic3() != null) {
                Glide.with(context).load(disputeInformationBean.getComplain_pic3()).into(disputeThridImg);
            }
            switch (disputeInformationBean.getComplain_state()) {
                case "1":
                case "10":
                    seekBar.setProgress(2);
                    break;
                case "2":
                case "20":
                    seekBar.setProgress(4);
                    break;
                case "3":
                case "30":
                    seekBar.setProgress(6);
                    break;
                case "4":
                case "40":
                    seekBar.setProgress(8);
                    break;
                case "99":
                case "9":
                    seekBar.setProgress(10);
                    break;
                default:
                    seekBar.setProgress(10);
                    break;
            }
        }


    }


}

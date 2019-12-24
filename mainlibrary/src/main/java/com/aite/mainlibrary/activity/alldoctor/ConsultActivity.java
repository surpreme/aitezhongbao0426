package com.aite.mainlibrary.activity.alldoctor;


import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.lxj.xpopup.XPopup;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.view.AlterPopup;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间 2019/12/20 18:13
 * 描述: 价格咨询
 */
public class ConsultActivity extends BaseActivity {
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_title_right)
    TextView mTvTitleRight;

    @BindView(R2.id.tv_time)
    TextView mTvTime;

    @BindView(R2.id.tv_price)
    TextView mTvPrice;


    @BindView(R2.id.ll_rg)
    RadioGroup mLlRg;
    @BindView(R2.id.tv_alter)
    TextView mTvAlter;
    private AlterPopup mAlterPopup;

    @Override
    protected int getLayoutResId() {
        return R.layout.acticity_consult;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @OnClick({R2.id.tv_alter})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_alter) {
            //修改
            if (mAlterPopup == null) {
                mAlterPopup = new AlterPopup(context);

                mAlterPopup.setListener(new AlterPopup.onSubmitClickListener() {
                    @Override
                    public void onSubmitClick(String tiem, String price) {
                        mTvTime.setText(tiem+"分钟");
                        mTvPrice.setText(price+"元");
                    }
                });
            }

            new XPopup.Builder(context)
                    .asCustom(mAlterPopup)
                    .show();
        }
    }
}

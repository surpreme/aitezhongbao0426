package com.aite.mainlibrary.activity.im.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.im.util.ConversationFragment;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.view.StatusBarUtils;

import butterknife.BindView;

public class
ChatShowActivity extends BaseActivity {
    @BindView(R2.id.fragment_group)
    FrameLayout fragmentGroup;


    private ConversationFragment getFragment() {
        ConversationFragment fragment = new ConversationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("server", getIntent().getSerializableExtra("server"));
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView() {
        initToolbar("聊天");
        StatusBarUtils.setColor(this, getResources().getColor(R.color.white));
        getFragmentManager().beginTransaction().replace(R.id.fragment_group, getFragment()).commitAllowingStateLoss();
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
}

package com.aite.aitezhongbao.activity;


import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.aitezhongbao.R;
import com.aite.aitezhongbao.activity.login.LoginActivity;
import com.aite.aitezhongbao.adapter.WelcomeAdapter;
import com.lzy.basemodule.view.StatusBarUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.logcat.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WelcomeActivity extends BaseWelcomeActivity {
    @Override
    protected void startYourActivity() {
        startActivity(LoginActivity.class);

    }

    @Override
    protected void initDatas() {
        urls.add(String.valueOf(R.drawable.welcome11));
        urls.add(String.valueOf(R.drawable.welcome22));
        urls.add(String.valueOf(R.drawable.welcome33));
        welcomeAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initResume() {
        welcomeAdapter.setOnStartclickViewListener(new WelcomeAdapter.OnclickViewListener() {
            @Override
            public void postion(int i) {
                startActivity(LoginActivity.class);

            }
        });

    }

    @Override
    protected void initReStart() {

    }
}

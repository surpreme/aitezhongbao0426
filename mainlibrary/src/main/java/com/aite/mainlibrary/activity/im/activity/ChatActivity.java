package com.aite.mainlibrary.activity.im.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.im.fragment.ChatFragment;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

public class ChatActivity extends BaseIMActivity {

    private ChatFragment mChatFragment;
    private ChatInfo mChatInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            startSplashActivity();
        } else {
            mChatInfo = (ChatInfo) bundle.getSerializable("chatInfo");
            if (mChatInfo == null) {
                startSplashActivity();
                return;
            }
            mChatFragment = new ChatFragment();
            mChatFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.empty_view, mChatFragment).commitAllowingStateLoss();
        }
    }

    private void startSplashActivity() {
//        Intent intent = new Intent(ChatActivity.this, SplashActivity.class);
//        startActivity(intent);
//        finish();
    }
}

package com.aite.mainlibrary.activity.im.mallchateruser;


import android.view.View;

import com.aite.mainlibrary.Mainbean.MallChaterBean;
import com.aite.mainlibrary.R;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.mvp.MVPBaseActivity;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MallChaterUserActivity extends BaseActivity<MallChaterUserContract.View, MallChaterUserPresenter> implements MallChaterUserContract.View {
    private MallChaterBean mallChaterBean;
    private View mBaseView;
    private ChatLayout mChatLayout;
    private TitleBarLayout mTitleBar;
    private ChatInfo mChatInfo;


    @Override
    protected int getLayoutResId() {
        return R.layout.item_tengxunchat;
    }

    @Override
    protected void initView() {
        if (mChatInfo == null) {
            showToast("数据错误");
            onBackPressed();
        }
//        //从布局文件中获取聊天面板组件
//        mChatLayout = mBaseView.findViewById(R.id.chat_layout);
//        //单聊组件的默认UI和交互初始化
//        mChatLayout.initDefault();
//
//        // TODO 通过api设置ChatLayout各种属性的样例
//        ChatLayoutHelper.customizeChatLayout(getActivity(), mChatLayout);
//
//        /*
//         * 需要聊天的基本信息
//         */
//        mChatLayout.setChatInfo(mChatInfo);
//
//        //获取单聊面板的标题栏
//        mTitleBar = mChatLayout.getTitleBar();
//        mTitleBar.getRightGroup().setVisibility(View.GONE);
//        //单聊面板标记栏返回按钮点击事件，这里需要开发者自行控制
//        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().finish();
//            }
//        });
//        if (mChatInfo.getType() == TIMConversationType.C2C) {
//            mTitleBar.setOnRightClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(DemoApplication.instance(), FriendProfileActivity.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    intent.putExtra(TUIKitConstants.ProfileType.CONTENT, mChatInfo);
////                    DemoApplication.instance().startActivity(intent);
//                }
//            });
//        }
//        mChatLayout.getMessageLayout().setOnItemClickListener(new MessageLayout.OnItemClickListener() {
//            @Override
//            public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
//                //因为adapter中第一条为加载条目，位置需减1
//                mChatLayout.getMessageLayout().showItemPopMenu(position - 1, messageInfo, view);
//            }
//
//            @Override
//            public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
//                if (null == messageInfo) {
//                    return;
//                }
////                ChatInfo info = new ChatInfo();
////                info.setId(messageInfo.getFromUser());
////                Intent intent = new Intent(DemoApplication.instance(), FriendProfileActivity.class);
////                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                intent.putExtra(TUIKitConstants.ProfileType.CONTENT, info);
////                DemoApplication.instance().startActivity(intent);
//            }
//        });

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

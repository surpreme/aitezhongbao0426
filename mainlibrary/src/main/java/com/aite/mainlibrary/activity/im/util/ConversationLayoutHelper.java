package com.aite.mainlibrary.activity.im.util;

import com.aite.mainlibrary.Mainbean.IMAccountInfo;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationProvider;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ConversationLayoutHelper {

    public static void customizeConversation(final ConversationLayout layout, List<IMAccountInfo> infos) {

        ConversationListLayout listLayout = (ConversationListLayout) layout.getConversationList();

        listLayout.setItemTopTextSize(16); // 设置adapter item中top文字大小
        listLayout.setItemBottomTextSize(12);// 设置adapter item中bottom文字大小
        listLayout.setItemDateTextSize(10);// 设置adapter item中timeline文字大小
//        listLayout.enableItemRoundIcon(true);// 设置adapter item头像是否显示圆角，默认是方形
        listLayout.disableItemUnreadDot(false);// 设置adapter item是否不显示未读红点，默认显示

        // 动态插入，删除Item，包括自定义会话

        List<ConversationInfo> conversationInfoList = new ArrayList<>();
        for (IMAccountInfo info : infos) {
            final ConversationInfo customInfo = new ConversationInfo();
            customInfo.setType(ConversationInfo.TYPE_CUSTOM);
            customInfo.setId(info.getIdentifier());
            customInfo.setGroup(false);
            customInfo.setTitle(info.getIdentifierNick());
            List<Object> urls = new ArrayList<>();
            urls.add(info.getHeadurl());
            customInfo.setIconUrlList(urls);
            conversationInfoList.add(customInfo);
        }

        ConversationManagerKit.getInstance().loadConversation(new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                ConversationProvider provider = (ConversationProvider) data;

                for (IMAccountInfo info : infos) {
                    ConversationInfo customInfo = null;
                    for (ConversationInfo conversationInfo : provider.getDataSource()) {
                        if (conversationInfo.getId().equals(info.getIdentifier())) {
                            customInfo = conversationInfo;
                            break;
                        }
                    }
                    if (customInfo == null) {
                        customInfo = new ConversationInfo();
                        customInfo.setType(ConversationInfo.TYPE_COMMON);
                        customInfo.setId(info.getIdentifier());
                        customInfo.setGroup(false);
                        customInfo.setTitle(info.getIdentifierNick());
                        List<Object> urls = new ArrayList<>();
                        urls.add(info.getHeadurl());
                        customInfo.setIconUrlList(urls);
                    }
                    conversationInfoList.add(customInfo);
                }
                for (int i = 0; i < conversationInfoList.size(); i++) {
                    layout.addConversationInfo(i, conversationInfoList.get(i));
                }
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.toastLongMessage("加载消息失败");
            }
        });
    }

    public static void customizeConversation(final ConversationLayout layout, IMAccountInfo info) {

        ConversationListLayout listLayout = (ConversationListLayout) layout.getConversationList();

        listLayout.setItemTopTextSize(16); // 设置adapter item中top文字大小
        listLayout.setItemBottomTextSize(12);// 设置adapter item中bottom文字大小
        listLayout.setItemDateTextSize(10);// 设置adapter item中timeline文字大小
//        listLayout.enableItemRoundIcon(true);// 设置adapter item头像是否显示圆角，默认是方形
        listLayout.disableItemUnreadDot(false);// 设置adapter item是否不显示未读红点，默认显示

        ConversationManagerKit.getInstance().loadConversation(new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                ConversationProvider provider = (ConversationProvider) data;

                ConversationInfo customInfo = null;

                for (ConversationInfo conversationInfo : provider.getDataSource()) {
                    if (conversationInfo.getId().equals(info.getIdentifier())) {
                        customInfo = conversationInfo;
                        break;
                    }
                }
                if (customInfo == null) {
                    customInfo = new ConversationInfo();
                    customInfo.setType(ConversationInfo.TYPE_COMMON);
                    customInfo.setId(info.getIdentifier());
                    customInfo.setGroup(false);
                    customInfo.setTitle(info.getIdentifierNick());
                    List<Object> urls = new ArrayList<>();
                    urls.add(info.getHeadurl());
                    customInfo.setIconUrlList(urls);
                }
                layout.addConversationInfo(0, customInfo);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.toastLongMessage("加载消息失败");
            }
        });
    }

}

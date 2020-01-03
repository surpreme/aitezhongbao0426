package com.aite.mainlibrary.activity.im.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.aite.mainlibrary.R;
import com.google.gson.Gson;
import com.lzy.basemodule.base.BaseApp;
import com.lzy.basemodule.logcat.LogUtils;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.BaseInputFragment;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.inputmore.InputMoreActionUnit;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.IOnCustomMessageDrawListener;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class ChatLayoutHelper {

    private static final String TAG = ChatLayoutHelper.class.getSimpleName();

    public static void customizeChatLayout(final Context context, final ChatLayout layout) {

//        //====== NoticeLayout使用范例 ======//
//        NoticeLayout noticeLayout = layout.getNoticeLayout();
//        noticeLayout.alwaysShow(true);
//        noticeLayout.getContent().setText("现在插播一条广告");
//        noticeLayout.getContentExtra().setText("参看有奖");
//        noticeLayout.setOnNoticeClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.toastShortMessage("赏白银五千两");
//            }
//        });
//
        //====== MessageLayout使用范例 ======//
        MessageLayout messageLayout = layout.getMessageLayout();
//        ////// 设置聊天背景 //////
//        messageLayout.setBackground(new ColorDrawable(0xFFEFE5D4));
//        ////// 设置头像 //////
//        // 设置默认头像，默认与朋友与自己的头像相同
        messageLayout.setAvatar(R.drawable.ic_personal_member);
//        // 设置头像圆角
//        messageLayout.setAvatarRadius(50);
//        // 设置头像大小
//        messageLayout.setAvatarSize(new int[]{48, 48});
//
//        ////// 设置昵称样式（对方与自己的样式保持一致）//////
//        messageLayout.setNameFontSize(12);
//        messageLayout.setNameFontColor(0xFF8B5A2B);
//
//        ////// 设置气泡 ///////
//        // 设置自己聊天气泡的背景
//        messageLayout.setRightBubble(new ColorDrawable(0xFFCCE4FC));
//        // 设置朋友聊天气泡的背景
//        messageLayout.setLeftBubble(new ColorDrawable(0xFFE4E7EB));
//
//        ////// 设置聊天内容 //////
//        // 设置聊天内容字体字体大小，朋友和自己用一种字体大小
//        messageLayout.setChatContextFontSize(15);
//        // 设置自己聊天内容字体颜色
//        messageLayout.setRightChatContentFontColor(0xFFA9A9A9);
//        // 设置朋友聊天内容字体颜色
//        messageLayout.setLeftChatContentFontColor(0xFFA020F0);
//
//        ////// 设置聊天时间 //////
//        // 设置聊天时间线的背景
//        messageLayout.setChatTimeBubble(new ColorDrawable(0xFFE4E7EB));
//        // 设置聊天时间的字体大小
//        messageLayout.setChatTimeFontSize(12);
//        // 设置聊天时间的字体颜色
//        messageLayout.setChatTimeFontColor(0xFF7E848C);
//
//        ////// 设置聊天的提示信息 //////
//        // 设置提示的背景
//        messageLayout.setTipsMessageBubble(new ColorDrawable(0xFFE4E7EB));
//        // 设置提示的字体大小
//        messageLayout.setTipsMessageFontSize(12);
//        // 设置提示的字体颜色
//        messageLayout.setTipsMessageFontColor(0xFF7E848C);
//
        // 设置自定义的消息渲染时的回调
        messageLayout.setOnCustomMessageDrawListener(new CustomMessageDraw(context));
//
//        // 新增一个PopMenuAction
//        PopMenuAction action = new PopMenuAction();
//        action.setActionName("test");
//        action.setActionClickListener(new PopActionClickListener() {
//            @Override
//            public void onActionClick(int position, Object data) {
//                ToastUtil.toastShortMessage("新增一个pop action");
//            }
//        });
//        messageLayout.addPopAction(action);
//
//        final MessageLayout.OnItemClickListener l = messageLayout.getOnItemClickListener();
//        messageLayout.setOnItemClickListener(new MessageLayout.OnItemClickListener() {
//            @Override
//            public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
//                l.onMessageLongClick(view, position, messageInfo);
//                ToastUtil.toastShortMessage("demo中自定义长按item");
//            }
//
//            @Override
//            public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
//                l.onUserIconClick(view, position, messageInfo);
//                ToastUtil.toastShortMessage("demo中自定义点击头像");
//            }
//        });


        //====== InputLayout使用范例 ======//
        InputLayout inputLayout = layout.getInputLayout();

//        // TODO 隐藏音频输入的入口，可以打开下面代码测试
//        inputLayout.disableAudioInput(true);
//        // TODO 隐藏表情输入的入口，可以打开下面代码测试
//        inputLayout.disableEmojiInput(true);
//        // TODO 隐藏更多功能的入口，可以打开下面代码测试
//        inputLayout.disableMoreInput(true);
//        // TODO 可以用自定义的事件来替换更多功能的入口，可以打开下面代码测试
//        inputLayout.replaceMoreInput(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.toastShortMessage("自定义的更多功能按钮事件");
//                MessageInfo info = MessageInfoUtil.buildTextMessage("自定义的消息");
//                layout.sendMessage(info, false);
//            }
//        });
//        // TODO 可以用自定义的fragment来替换更多功能，可以打开下面代码测试
//        inputLayout.replaceMoreInput(new CustomInputFragment().setChatLayout(layout));
//
//        // TODO 可以disable更多面板上的各个功能，可以打开下面代码测试
//        inputLayout.disableCaptureAction(true);
//        inputLayout.disableSendFileAction(true);
//        inputLayout.disableSendPhotoAction(true);
//        inputLayout.disableVideoRecordAction(true);
        // TODO 可以自己增加一些功能，可以打开下面代码测试
        InputMoreActionUnit unit = new InputMoreActionUnit();
        unit.setIconResId(R.drawable.chat_userinfo);
        unit.setTitleId(R.string.app_name);
        unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                CustomMessageData customMessageData = new CustomMessageData();
                String data = gson.toJson(customMessageData);
                MessageInfo info = MessageInfoUtil.buildCustomMessage(data);
                layout.sendMessage(info, false);
            }
        });
//        inputLayout.addAction(unit);
    }

    public static class CustomInputFragment extends BaseInputFragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View baseView = inflater.inflate(R.layout.test_chat_input_custom_fragment, container, false);
            Button btn1 = baseView.findViewById(R.id.test_send_message_btn1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.toastShortMessage("自定义的按钮1");
                    if (getChatLayout() != null) {
                        Gson gson = new Gson();
                        CustomMessageData customMessageData = new CustomMessageData();
                        String data = gson.toJson(customMessageData);
                        MessageInfo info = MessageInfoUtil.buildCustomMessage(data);
                        getChatLayout().sendMessage(info, false);
                    }
                }
            });
            Button btn2 = baseView.findViewById(R.id.test_send_message_btn2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.toastShortMessage("自定义的按钮2");
                    if (getChatLayout() != null) {
                        Gson gson = new Gson();
                        CustomMessageData customMessageData = new CustomMessageData();
                        String data = gson.toJson(customMessageData);
                        MessageInfo info = MessageInfoUtil.buildCustomMessage(data);
                        getChatLayout().sendMessage(info, false);
                    }
                }
            });
            return baseView;
        }

    }

    /**
     * 自定义消息的bean实体，用来与json的相互转化
     */
    public static class CustomMessageData {
        int version = 1;
        String text = "欢迎加入云通信IM大家庭！";
        String link = "https://cloud.tencent.com/document/product/269/3794";
    }

    public static class CustomMessageDraw implements IOnCustomMessageDrawListener {

        private WeakReference<Activity> ref;

        public CustomMessageDraw(Context context) {
            ref = new WeakReference<>((Activity) context);
        }

        /**
         * 自定义消息渲染时，会调用该方法，本方法实现了自定义消息的创建，以及交互逻辑
         *
         * @param parent 自定义消息显示的父View，需要把创建的自定义消息view添加到parent里
         * @param info   消息的具体信息
         */
        @Override
        public void onDraw(ICustomMessageViewGroup parent, MessageInfo info) {

            // 获取到自定义消息的json数据
            if (!(info.getElement() instanceof TIMCustomElem)) {
                return;
            }

            TIMCustomElem elem = (TIMCustomElem) info.getElement();
            // 自定义的json数据，需要解析成bean实例
            try {
                JSONObject jsonObject = new JSONObject(new String(elem.getData()));
                String type = jsonObject.getString("CustomType");
                Gson gson = new Gson();
                switch (type) {
//                    case "orderAddress":
//                        JSONObject datas = jsonObject.getJSONObject("order_info");
//                        CostormChatOrderBean bean = gson.fromJson(datas.toString(), CostormChatOrderBean.class);
//                        configOrderLayout(parent, bean);
//                        break;
//                    case "goods":
//                        JSONObject goodsData = jsonObject.getJSONObject("goods");
//                        CustormChatGoodsBean.GoodsBean goodsBean = gson.fromJson(goodsData.toString(),CustormChatGoodsBean.GoodsBean.class);
//                        configGoodsLayout(parent,goodsBean);
//                        break;
                    default:
                        throw new Exception("aa");
                }

//                LogUtils.e("invalid json: " + new String(elem.getData()));
            } catch (Exception e) {
                LogUtils.e("invalid json: " + new String(elem.getData()));// 把自定义消息view添加到TUIKit内部的父容器里
                View view = LayoutInflater.from(BaseApp.getContext()).inflate(R.layout.test_custom_message_layout1, null, false);
                parent.addMessageContentView(view);

                // 自定义消息view的实现，这里仅仅展示文本信息，并且实现超链接跳转
                TextView textView = view.findViewById(R.id.test_custom_message_tv);
                final String text = "不支持的消息类型：" + new String(elem.getData());
                if (textView != null)
                    textView.setText(text);
                return;
            }


        }

//        private void configGoodsLayout(ICustomMessageViewGroup parent, CustormChatGoodsBean.GoodsBean goodsBean) {
//            View view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.test_custom_message_layout2, null, false);
//            parent.addMessageContentView(view);
//
//            ImageView image = view.findViewById(R.id.image);
//
//            Glide.with(ref.get())
//                    .load(goodsBean.getGoods_image())
//                    .error(R.color.primary_background_shallow_color)
//                    .placeholder(R.color.primary_background_shallow_color)
//                    .into(image);
//
//            setText(view.findViewById(R.id.content),TextEmptyHandlerUtils.getText(goodsBean.getGoods_name()));
//
//            setText(view.findViewById(R.id.price),TextEmptyHandlerUtils.getSymbol(goodsBean.getUser_symbol())+TextEmptyHandlerUtils.getNumber(goodsBean.getGoods_price()));
//
//            view.setOnClickListener(v -> {
//                Intent intent = new Intent(ref.get(), MerchandiseDetailsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(MerchandiseDetailsActivity.class.getName(),goodsBean.getGoods_id());
//                ref.get().startActivity(intent);
//            });
//        }

//        private void configOrderLayout(ICustomMessageViewGroup parent, CostormChatOrderBean bean) {
//            View view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.test_custom_message_layout1, null, false);
//            parent.addMessageContentView(view);
//
//            ImageView image = view.findViewById(R.id.image);
//
//            Group group = view.findViewById(R.id.ready_group);
//
//            TextView status = view.findViewById(R.id.status);
//
//            switch (bean.getConfirm_address()) {
//                case "0":
//                    group.setVisibility(View.VISIBLE);
//                    status.setVisibility(View.GONE);
//                    setText(view.findViewById(R.id.title), ref.get().getString(R.string.chat_order_ready));
//                    break;
//                case "1":
//                    group.setVisibility(View.GONE);
//                    status.setVisibility(View.VISIBLE);
//                    setText(view.findViewById(R.id.title), ref.get().getString(R.string.chat_order_confirm_title));
//                    status.setText(ref.get().getString(R.string.chat_order_info_confirm));
//                    break;
//                default:
//                    group.setVisibility(View.GONE);
//                    status.setVisibility(View.VISIBLE);
//                    setText(view.findViewById(R.id.title), ref.get().getString(R.string.chat_order_cancel_title));
//                    status.setText(ref.get().getString(R.string.chat_order_info_cancel));
//            }
//
//            Glide.with(ref.get())
//                    .load(bean.getGoods_image())
//                    .error(R.color.primary_background_shallow_color)
//                    .placeholder(R.color.primary_background_shallow_color)
//                    .into(image);
//            TextView textView = view.findViewById(R.id.goods_info);
//
//            setText(view.findViewById(R.id.content), TextEmptyHandlerUtils.getText(bean.getGoods_name()));
//            setText(textView
//                    , String.format(
//                            ref.get().getString(R.string.chat_order_info_show), bean.getGoods_total() + ""
//                            , "$" + TextEmptyHandlerUtils.getText(bean.getOrder_amount())
//                    ));
//            setText(view.findViewById(R.id.account), ref.get().getString(R.string.name_ng) + TextEmptyHandlerUtils.getText(bean.getReciver_name()));
//            setText(view.findViewById(R.id.phone), ref.get().getString(R.string.phone) + TextEmptyHandlerUtils.getNumber(bean.getReciver_mobile()));
//            setText(view.findViewById(R.id.address), ref.get().getString(R.string.address_sc) + TextEmptyHandlerUtils.getNumber(bean.getReciver_address()));
//            view.findViewById(R.id.cancel)
//                    .setOnClickListener(v -> {
//                        Intent intent = new Intent(ref.get(), WebActivity.class);
//                        intent.putExtra("url", bean.getEdit_url());
//                        ref.get().startActivity(intent);
//                    });
//            view.findViewById(R.id.confirm)
//                    .setOnClickListener(v -> {
////                        Intent intent = new Intent(ref.get(), WebActivity.class);
////                        intent.putExtra("url", bean.getConfirm_url());
////                        ref.get().startActivity(intent);
//                        requestConfimOrder(bean.getOrder_id());
//                    });
//            view.setOnClickListener(v -> {
//                Intent intent = new Intent(ref.get(), MerchandiseDetailsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(MerchandiseDetailsActivity.class.getName(),bean.getGoods_id());
//                ref.get().startActivity(intent);
//            });
//            requestOrderInfo(bean.getOrder_id(), textView);
//        }

        private void setText(View view, String text) {
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
        }

//        private void requestConfimOrder(String id) {
//            RequestParams params = new RequestParams();
//            params.addBodyParameter("key", MyApp.getToken());
//            params.addBodyParameter("lang", MyApp.getLang());
//            params.addBodyParameter("order_id", id);
//            MyHttpUtil.sendWithHost(HttpRequest.HttpMethod.POST, ApiAddress.REQUEST_IM_CONFIRM_ORDER, params, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//
//                }
//            });
//        }

//        private void requestOrderInfo(String id, TextView view) {
//            RequestParams params = new RequestParams();
//            params.addQueryStringParameter("key", MyApp.getToken());
//            params.addQueryStringParameter("lang", MyApp.getLang());
//            params.addQueryStringParameter("order_id", id);
//            MyHttpUtil.sendWithHost(HttpRequest.HttpMethod.GET, ApiAddress.REQUEST_IM_ORDER, params, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(responseInfo.result);
//                        JSONObject datas = jsonObject.getJSONObject("datas");
//                        JSONObject orderDatas = datas.getJSONObject("order_info");
//                        final String symbol = orderDatas.getString("pay_symbol");
//                        ref.get().runOnUiThread(() -> {
//                            String content = view.getText().toString();
//
//                            content = content.replace("$", symbol);
//
//
//                            view.setText(content);
//                        });
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//
//                }
//            });
//        }
    }

}

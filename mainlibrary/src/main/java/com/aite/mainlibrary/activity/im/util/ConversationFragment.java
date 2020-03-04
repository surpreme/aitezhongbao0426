package com.aite.mainlibrary.activity.im.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.aite.mainlibrary.Mainbean.IMAccountInfo;
import com.aite.mainlibrary.Mainbean.MallChaterBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.im.activity.ChatActivity;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.base.BaseApp;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.BaseFragment;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationAdapter;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ConversationFragment extends BaseFragment {
    Unbinder unbinder;
    private View mBaseView;
    private ConversationLayout mConversationLayout;
    private List<MallChaterBean.PlatformCallcenterBean> platformListBeans;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.conversation_fragment, container, false);
        initView();
        unbinder = ButterKnife.bind(this, mBaseView);
        return mBaseView;
    }

    private void initView() {
        if (getArguments() != null)
            platformListBeans = (List<MallChaterBean.PlatformCallcenterBean>) getArguments().getSerializable("server");
        // 从布局文件中获取会话列表面板
        mConversationLayout = mBaseView.findViewById(R.id.conversation_layout);
        mConversationLayout.getTitleBar().setVisibility(View.GONE);
        // 会话列表面板的默认UI和交互初始化

        if (platformListBeans != null) {
            final IConversationAdapter adapter = new ConversationListAdapter();
            mConversationLayout.getConversationList().setAdapter(adapter);
            requestServerInfo();
        } else {
            mConversationLayout.initDefault();
        }
        mConversationLayout.getConversationList().setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo conversationInfo) {
                //此处为demo的实现逻辑，更根据会话类型跳转到相关界面，开发者可根据自己的应用场景灵活实现
                startChatActivity(conversationInfo);
            }
        });
        mConversationLayout.getConversationList().setOnItemLongClickListener(new ConversationListLayout.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, int position, ConversationInfo conversationInfo) {
//                startPopShow(view, position, conversationInfo);
            }
        });
    }

    private Disposable disposable;

    private void requestServerInfo() {
        disposable = Observable.fromIterable(platformListBeans)
                .flatMap(server -> Observable.create((ObservableOnSubscribe<IMAccountInfo>) emitter -> {
                    HttpParams httpParams = new HttpParams();
                    httpParams.put("key", AppConstant.KEY);
                    httpParams.put("lang", "zh_cn");
                    httpParams.put("member_id", server.getNum());
                    OkGo.<BaseData<IMAccountInfo>>get(AppConstant.GET_IMTENCENTCONFIDINFORMATIONURL)
                            .tag(BaseApp.getContext())
                            .params(httpParams)
                            .execute(new AbsCallback<BaseData<IMAccountInfo>>() {
                                @Override
                                public BaseData<IMAccountInfo> convertResponse(okhttp3.Response response) throws Throwable {
                                    JSONObject jsonObject = new JSONObject(response.body().string());

                                    JSONObject datas = jsonObject.optJSONObject("datas");
                                    Gson gson = new Gson();
                                    if (datas == null)
                                        emitter.onNext(new IMAccountInfo());
                                    else {
                                        IMAccountInfo info = gson.fromJson(datas.toString(), IMAccountInfo.class);
                                        emitter.onNext(info);
                                    }
                                    return null;
                                }

                                @Override
                                public void onStart(Request<BaseData<IMAccountInfo>, ? extends Request> request) {
                                    LogUtils.d("onStart");

                                }

                                @Override
                                public void onSuccess(Response<BaseData<IMAccountInfo>> response) {
                                    LogUtils.d("onSuccess");
                                }
                            });
                }))
//                .collectInto(new ArrayList<IMAccountInfo>(),ArrayList::add)
//                .filter(list -> {
//                    LogHelper.e(list.size()+"        requestServerInfo");
//                    return !list.isEmpty();
//                }).toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(imAccountInfo -> {
                    loginToIM(imAccountInfo.getIdentifier(), imAccountInfo.getUserSig());
                    ConversationLayoutHelper.customizeConversation(mConversationLayout, imAccountInfo);
                });
//                .subscribe(imAccountInfos -> {
//                    ConversationLayoutHelper.customizeConversation(mConversationLayout, imAccountInfos);
//                    LogHelper.e(imAccountInfos.size() + "             requestServerInfo");
//                },throwable -> LogHelper.e(throwable.toString()+"         requestServerInfo"));
    }

    private void startChatActivity(ConversationInfo conversationInfo) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(conversationInfo.isGroup() ? TIMConversationType.Group : TIMConversationType.C2C);
        chatInfo.setId(conversationInfo.getId());
        chatInfo.setChatName(conversationInfo.getTitle());
        Intent intent = new Intent(BaseApp.getContext(), ChatActivity.class);
        intent.putExtra("chatInfo", chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApp.getContext().startActivity(intent);
    }

    private void loginToIM(String identifier, String userSig) {
        TUIKit.login(identifier, userSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                LogUtils.e("success     loginToIM  " + (data != null ? data.toString() : "") + TIMManager.getInstance().getLoginUser());
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                LogUtils.i("imLogin errorCode = " + errCode + ", errorInfo = loginToIM" + errMsg);
            }
        });
    }

    public void logoutIM() {
        //登出
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {

                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d("TAG", "logout failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                //登出成功
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null)
            disposable.dispose();
        unbinder.unbind();
    }
}

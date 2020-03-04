package com.aite.mainlibrary.activity.im.fragment

import android.content.Intent
import android.view.View
import com.aite.mainlibrary.Mainbean.IMAccountInfo
import com.aite.mainlibrary.R
import com.aite.mainlibrary.activity.im.activity.ChatActivity
import com.aite.mainlibrary.activity.im.util.ConversationLayoutHelper
import com.aite.mainlibrary.basekotlin.BaseFragment
import com.aite.mainlibrary.basekotlin.BaseView
import com.google.gson.Gson
import com.lzy.basemodule.BaseConstant.AppConstant
import com.lzy.basemodule.BaseConstant.BaseConstant
import com.lzy.basemodule.bean.BaseData
import com.lzy.basemodule.bean.BeanConvertor
import com.lzy.basemodule.logcat.LogUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.tencent.imsdk.*
import com.tencent.qcloud.tim.uikit.TUIKit
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo
import org.json.JSONObject
import java.util.HashMap

/**

 * @Auther: liziyang

 * @datetime: 2020-01-20

 * @desc: 腾讯im 获取聊天信息列表的页面

 */
class MessageFragment : BaseFragment() {
    private var mConversationLayout: ConversationLayout? = null

    override fun initDatas() {
        getImConfiguration(initParams(), AppConstant.GET_IMTENCENTCONFIDINFORMATIONURL)

    }

    override fun initViews(view: View) {
        mConversationLayout = view.findViewById(R.id.conversation_layout)
        mConversationLayout?.titleBar?.visibility = View.GONE
    }


    override fun getLayoutResId(): Int {
        return R.layout.fragment_message
    }

    private fun initParams(): HttpParams {
        val httpParams = HttpParams()
        httpParams.put("key", AppConstant.KEY)
        httpParams.put("lang", "zh_cn")
        if (AppConstant.MEMBER_ID != null)
            httpParams.put("member_id", AppConstant.MEMBER_ID)
        return httpParams
    }

    private fun getImConfiguration(params: HttpParams, url: String) {
        OkGo.get<BaseData<IMAccountInfo>>(url)
                .tag(mContext)
                .params(params)
                .execute(object : AbsCallback<BaseData<IMAccountInfo>>() {
                    @Throws(Throwable::class)
                    override fun convertResponse(response: okhttp3.Response): BaseData<IMAccountInfo>? {
                        val jsonObject = JSONObject(response.body()!!.string())
                        val datas:JSONObject = jsonObject.optJSONObject("datas")
                        val gson = Gson()
                        val info:IMAccountInfo = gson.fromJson(datas.toString(), IMAccountInfo::class.java)
                        logInToIm(info.identifier,info.userSig)
                        ConversationLayoutHelper.customizeConversation(mConversationLayout, info)
                        return null
                    }

                    override fun onStart(request: Request<BaseData<IMAccountInfo>, out Request<*, *>>?) {
                        LogUtils.d("onStart")

                    }

                    override fun onSuccess(response: Response<BaseData<IMAccountInfo>>) {
                        LogUtils.d("onSuccess")

                    }
                })
    }

    private fun logInToIm(identifier: String, usersig: String) {
        TUIKit.login(identifier, usersig, object : IUIKitCallBack {
            override fun onError(module: String?, errCode: Int, errMsg: String?) {
                LogUtils.e("登录fail${errMsg}错误码：${errCode}")
            }

            override fun onSuccess(data: Any?) {
                LogUtils.d("登录success${data?.toString()}------${TIMManager.getInstance().loginUser}")
                updateProfile()
                activity?.runOnUiThread {
                    mConversationLayout?.initDefault()
                    mConversationLayout?.conversationList?.setOnItemClickListener { view, position, messageInfo ->
                        startChatActivity(messageInfo)
                    }
                }
            }
        })
    }

    private fun startChatActivity(conversationInfo: ConversationInfo) {
        val chatInfo = ChatInfo()
        chatInfo.chatName = conversationInfo.title
        chatInfo.id = conversationInfo.id
        chatInfo.type = TIMConversationType.C2C
        val intent = Intent(mContext, ChatActivity::class.java)
        intent.putExtra("chatInfo", chatInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun updateProfile() {
        val hashMap = HashMap<String, Any>()
        // 头像
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_FACEURL, AppConstant.ICON_URL)
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_NICK, AppConstant.USERNAME)
        TIMFriendshipManager.getInstance().modifySelfProfile(hashMap, object : TIMCallBack {
            override fun onError(p0: Int, p1: String?) {
                LogUtils.e("修改信息fail${p1}--*******--${p0}")
            }
            override fun onSuccess() {
                LogUtils.d("修改信息success")
            }
        })
    }
}

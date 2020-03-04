package com.lzy.basemodule.BaseConstant;

import android.os.Environment;

import com.lzy.basemodule.base.BaseApp;

import java.io.File;

public class BaseConstant {
    public static class PATH {
        public static final String FILE_PROVIDER_AUTHORITY = "com.lzy.base.fileprovider";
        public static final String PATH_DATA = BaseApp.getContext().getCacheDir().getAbsolutePath() + File.separator + "data";

        public static final String PATH_CACHE = PATH_DATA + "/NetCache";

        public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "lzy" + File.separator + "GeekNews";

    }
    //im聊天倒计时
    public static long IM_COUNTDOWN_TIME = 0;
    public static class IM {
        public static final int SDKAPPID = 1400301991;


    }

    public class RESULT_CODE {
        //选择照片
        public static final int REQUEST_CODE_CHOOSE_IMAGE_CLIP = 18715;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_CLIP_TWO = 382815;
        public static final int REQUEST_CODE_CHOOSE_IMAGE = 12048;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_TWO = 13848;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_THREE = 11848;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_FOUR = 12548;
        //拍照
        public static final int REQUEST_CAMERA = 18500;
        //广播
        public static final String BROADCAST_REQUEST = "com.aiTeIMDoctor.timeDown.running.cn";
        //广播销毁
        public static final String OVER_BROADCAST_REQUEST = "com.aiTeIMDoctor.timeDown.overing.cn";


    }

    public class ACTIVITY_RESULT_CODE {
        public static final int REQUEST_CODE_ACTIVITY_RESULT = 4515;

    }

    public class PERMISSION {
        public static final int OVERLAY_PERMISSION_REQ_CODE = 1825;

    }


    /**
     * 微信登录
     * APPID：wxfb45ab55efde3e64
     * AppSecret：55a05f880182902f6e135b628d1bc8d9
     */
    public class WECAHT {
        public static final String APP_ID = "wxfb45ab55efde3e64";
        public static final String APP_SECRET = "55a05f880182902f6e135b628d1bc8d9";
        public static final String SCOPE = "snsapi_userinfo";
        public static final String STATE = "wechat_sdk_demo_test";
        public static final String CHAT_INFO = "chatInfo";

    }

    /**
     * 友盟
     * "5df706653fc195c5e3000f1f"
     */
    public class YOUMENG {
        public static final String KEY = "5df706653fc195c5e3000f1f";

    }
}

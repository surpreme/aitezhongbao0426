package com.example.servicer;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.BaseConstant.BaseConstant;

/**
 * @Auther: liziyang
 * @datetime: 2020-02-04
 * @desc: im倒计时 咨询
 */
public class ImCountdownService extends Service {
    private CountDownTimer mCountDownTimer;


    //客户端通过调用bindService()方法启动服务时执行该方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //客户端通过调用startService()方法启动服务时执行该方法，可以执行多次

    /**
     * CountDownTimer
     * onTick 间隔多长时间执行一次 millisUntilFinished 还剩多长时间
     * onFinish 结束
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            BaseConstant.IM_COUNTDOWN_TIME = intent.getLongExtra("countTime", 0);
        }
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(BaseConstant.IM_COUNTDOWN_TIME*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Intent intent = new Intent(BaseConstant.RESULT_CODE.BROADCAST_REQUEST);
                    intent.putExtra("CURRENT_COUNT_TIME", (int) millisUntilFinished);
                    sendBroadcast(intent);
                    BaseConstant.IM_COUNTDOWN_TIME = (int) millisUntilFinished;
                }

                @Override
                public void onFinish() {
                    BaseConstant.IM_COUNTDOWN_TIME = 0;
                    Intent intent = new Intent(BaseConstant.RESULT_CODE.OVER_BROADCAST_REQUEST);
                    sendBroadcast(intent);
                    stopSelf();
                }
            };
        }
        mCountDownTimer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    //第一次创建服务时执行的方法，且只执行一次
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //客户端调用unBindeService()方法断开服务绑定时执行该方法
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    //服务被销毁时执行的方法，且只执行一次
    @Override
    public void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        super.onDestroy();
    }

}

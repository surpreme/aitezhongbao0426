package com.lzy.basemodule.rxbus;

import android.content.Context;

import com.blankj.rxbus.RxBus;
import com.lzy.basemodule.R;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-01
 * @desc:
 */
public class RxbusSimple {
    public void test(Context context) {
        RxBus.getDefault().post("jumpShopCar", "jumpShopCar");
        RxBus.getDefault().subscribe(context, "jumpShopCar", new RxBus.Callback<String>() {
            @Override
            public void onEvent(String o) {


            }
        });
    }
    //        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> o) {
//
////                if (name.equals("lzy") && password.equals("123456")) {
////                    try {
////                        Thread.sleep(500);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    } finally {
////                        o.onNext("ok");
////                    }
////
////                } else o.onComplete();
//            }
//        })
//                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
//                .subscribeOn(Schedulers.io())//执行在io线程
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        mView.logInSuccess(s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.logInFail("失败");
//                    }
//                });

}

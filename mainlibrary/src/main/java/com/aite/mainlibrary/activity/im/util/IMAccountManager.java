package com.aite.mainlibrary.activity.im.util;


public class IMAccountManager {

    public static IMAccountManager getInstance() {
        return Holder.instance;
    }

    private final static class Holder {
        private final static IMAccountManager instance = new IMAccountManager();
    }



}

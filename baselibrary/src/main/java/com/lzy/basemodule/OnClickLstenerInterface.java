package com.lzy.basemodule;

public class OnClickLstenerInterface {
    public interface OnRecyClickInterface {
        void getPosition(int position);
    }

    public interface OnRecyClickInterfaceAndString {
        void getPosition(String type, int position);
    }

    public interface OnItemRecyClickInterface {
        void getPosition(int position);
    }

    public interface OnThingClickInterface {
        void getString(String msg);
    }

    public interface OnThingClickInterfaces {
        void getPosition(int position);
    }
}

package com.aite.mainlibrary.Mainbean;

import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;
import java.util.List;


public class ChoiceOrderBean extends IBaseBean implements Serializable {
    /**
     * id : 0
     * name : 日托
     */

    private String id;
    private String name;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNasme() {
        return name;
    }

    @Override
    public boolean isIsCheck() {
        return isChecked;
    }


}

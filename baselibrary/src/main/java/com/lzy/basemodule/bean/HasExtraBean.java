package com.lzy.basemodule.bean;

import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-27
 * @desc:
 */
public class HasExtraBean<HEAD, C>  {
    private HEAD head;
    private List<C> list;

    public HasExtraBean() {
    }

    public HEAD getHead() {
        return this.head;
    }

    public void setHead(HEAD head) {
        this.head = head;
    }

    public List<C> getList() {
        return this.list;
    }

    public void setList(List<C> list) {
        this.list = list;
    }
}

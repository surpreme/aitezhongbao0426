package com.lzy.basemodule.bean;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-27
 * @desc:
 */
public class PageBean {
    private boolean hasmore;
    private int page_total;
    private int pageRange;

    public PageBean() {
    }

    public boolean isHasmore() {
        return this.hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getPage_total() {
        return this.page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public int getPageRange() {
        return this.pageRange;
    }

    public void setPageRange(int pageRange) {
        this.pageRange = pageRange;
    }
}

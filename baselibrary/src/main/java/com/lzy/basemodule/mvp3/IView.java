package com.lzy.basemodule.mvp3;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-27
 * @desc:
 */
public interface IView {
    /**
     * Use night mode
     *
     * @param isNightMode if is night mode
     */
    void useNightMode(boolean isNightMode);

    /**
     * 显示 loading
     */
    void showLoading();

    /**
     * 隐藏 loading
     */
    void hideLoading();

    /**
     * showNormal
     */
    void showNormal();

    /**
     * Show error
     */
    void showError();

    /**
     * show error msg
     */
    void showError(String msg);

    /**
     * show empty
     */
    void showEmpty();

    /**
     * Reload
     */
    void reload();

    boolean isShow();
}

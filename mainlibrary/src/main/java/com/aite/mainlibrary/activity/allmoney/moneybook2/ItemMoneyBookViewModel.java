package com.aite.mainlibrary.activity.allmoney.moneybook2;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.aite.mainlibrary.Mainbean.MoneyBookBean;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.TimeUtils;

import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-17
 * @desc:
 */
public class ItemMoneyBookViewModel extends ItemViewModel<ActivityMoneyBookViewModel> {

    public ItemMoneyBookViewModel(@NonNull ActivityMoneyBookViewModel viewModel, MoneyBookBean.ListLogBean listLogBean) {
        super(viewModel);
        try {
            if (listLogBean.getLg_desc() != null)
                titleStr.set(listLogBean.getLg_desc());
            if (listLogBean.getLg_add_time() != null)
                timeStr.set(TimeUtils.stampToDatemm2(Long.parseLong(listLogBean.getLg_add_time())));
            if (listLogBean.getLg_av_amount() != null)
                inforamtionStr.set(listLogBean.getLg_av_amount());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public ObservableField<String> titleStr = new ObservableField<>();
    public ObservableField<String> timeStr = new ObservableField<>();
    public ObservableField<String> inforamtionStr = new ObservableField<>();


}

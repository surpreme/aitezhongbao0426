package com.aite.mainlibrary.activity.allmoney.moneybook2;

import android.annotation.SuppressLint;
import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.base.Mark;
import com.aite.mainlibrary.Mainbean.MoneyBookBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.activity.allsetting.editSosUser.RetrofitInterface;
import com.aite.mainlibrary.activity.allstep.vipStep.StepViewModel;
import com.aite.mainlibrary.databinding.ActivityMoneyBookListBinding;
import com.google.gson.Gson;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.activitylife.ActivityManager;
import com.lzy.basemodule.baseRetrofit.http.RetrofitClient;
import com.lzy.basemodule.bean.BaseData;
import com.lzy.basemodule.bean.BaseDataX;
import com.lzy.basemodule.bean.ErrorBean;
import com.lzy.basemodule.net.RxScheduler;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import okhttp3.ResponseBody;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-17
 * @desc: 具体自定义请看BindingSmartViewModelAdapter
 */
public class ActivityMoneyBookViewModel extends BaseViewModel<BindingSmartViewModelAdapter> {

    public ActivityMoneyBookViewModel(@NonNull Application application) {
        super(application);
        titleString.set("账单明细");
        autoRefresh.set(true);
        isHasMore.set(true);
    }

 
    public BindingCommand onBackOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onBackPressed();


        }
    });
    /**
     * 当前页的索引
     */
    public int mCurrentPage = 1;
    /**
     * 判断是否有下一页 决定取消下拉刷新
     */
    public ObservableBoolean isHasMore = new ObservableBoolean();
    /**
     * 是否打开第一次加载 自动下拉刷新
     */
    public ObservableBoolean autoRefresh = new ObservableBoolean();

    public ObservableField<String> titleString = new ObservableField<>();
    //账单明细
    public ObservableList<ItemMoneyBookViewModel> orderItemList = new ObservableArrayList<>();
    public ItemBinding<ItemMoneyBookViewModel> itemBinding = ItemBinding.of(new OnItemBind<ItemMoneyBookViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, ItemMoneyBookViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_mvvm_money_book_recy);
        }
    });

    @SuppressLint("CheckResult")
    private void onBillingDetails(String key) {
        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onBillingDetails(key, mCurrentPage)
                .compose(RxScheduler.Flo_io_main())
                .filter(moneyBookBeanBaseData -> {
                    if (!moneyBookBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(moneyBookBeanBaseData.getDatas().getError());
                        LogUtils.e(moneyBookBeanBaseData.getDatas().getError());
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseDataX::getDatas)
                .filter(moneyBookBeanBaseData -> {
                    if (moneyBookBeanBaseData.getError() != null) {
                        ToastUtils.showShort(moneyBookBeanBaseData.getError());
                        LogUtils.e(moneyBookBeanBaseData.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(moneyBookBean -> {
            dismissDialog();
            for (MoneyBookBean.ListLogBean listLogBean : moneyBookBean.getList_log()) {
                orderItemList.add(new ItemMoneyBookViewModel(ActivityMoneyBookViewModel.this, listLogBean));
            }
            isHasMore.set(moneyBookBean.getList_log().size() > 0);


        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });
    }

    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> {
        mCurrentPage++;
        refreshLayout.finishLoadMore(1000);
        LogUtils.d("LoadMore");
        onBillingDetails(AppConstant.KEY);


    };


    public OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mCurrentPage = 1;
            refreshLayout.finishRefresh(1000);
            LogUtils.d("Refreshing");
            if (orderItemList.size() > 0) orderItemList.clear();
            onBillingDetails(AppConstant.KEY);


        }
    };

    @SuppressLint("StaticFieldLeak")
    public WaterDropHeader waterDropHeader = new WaterDropHeader(ActivityManager.getInstance().getCurrentActivity());
    @SuppressLint("StaticFieldLeak")
    public ClassicsFooter classicsFooter = new ClassicsFooter(ActivityManager.getInstance().getCurrentActivity());

}

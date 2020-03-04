package com.aite.mainlibrary.activity.allsetting.elderhelphouse;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.HelpElderHouseListBean;
import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.helpeatorderbook.HelpEatOrderBookActivity;
import com.aite.mainlibrary.adapter.HelpElderHouseRecyAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ElderHelpHouseActivity extends BaseActivity<ElderHelpHouseContract.View, ElderHelpHousePresenter> implements ElderHelpHouseContract.View {
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.search_view_edit)
    TextInputEditText searchViewEdit;

    //    @BindView(R2.id.search_view)
//    SearchEditText searchView;
    private HelpElderHouseRecyAdapter helpElderHouseRecyAdapter;
    private List<HelpElderHouseListBean.ListBean> helpelderlistbean = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.recy_toolbar_seacher;
    }

    @Override
    protected void initView() {
        initToolbar("关联养老院");
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(helpElderHouseRecyAdapter = new HelpElderHouseRecyAdapter(context, helpelderlistbean));
        initSmartLayout(true, false);
        searchViewEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.d(s);
                mPresenter.getHelpEdlerHouseInformation(initSearchParams(s.toString()));


            }
        });
        helpElderHouseRecyAdapter.setOnItemRecyClickInterface(new OnClickLstenerInterface.OnItemRecyClickInterface() {
            @Override
            public void getPosition(int position) {
                if (getIntent().getStringExtra("JUMP_TYPE")!=null){
                    if (getIntent().getStringExtra("JUMP_TYPE").equals("CHOICE_ADDRESS")){
                        Intent intent = getIntent();
                        intent.putExtra("address_house", helpelderlistbean.get(position).getStore_name());
                        // 设置返回码和返回携带的数据
                        setResult(Activity.RESULT_OK, intent);
                        onBackPressed();
                    }
                }
            }
        });
        helpElderHouseRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                mPresenter.changeBinderHelpEdlerHouse(
                        initBindingParams(helpelderlistbean.get(postion).getStore_id()),
                        !helpelderlistbean.get(postion).getIs_binding().equals("1") ?
                                AppConstant.BINDHELPELDERHOUSEURL : AppConstant.UNBINDHELPELDERHOUSEURL);

            }
        });

    }

    @Override
    protected void initDatas() {
        mPresenter.getHelpEdlerHouseInformation(initParams());

    }

    @Override
    protected void onSmartLoadMore() {
        super.onSmartLoadMore();
        initDatas();
    }

    @Override
    protected void onSmartRefresh() {
        super.onSmartRefresh();
        if (helpelderlistbean != null && !helpelderlistbean.isEmpty()) {
            helpelderlistbean.clear();
            helpElderHouseRecyAdapter.notifyDataSetChanged();
        }
        initDatas();
    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("curpage", mCurrentPage);
        return httpParams;
    }

    private HttpParams initSearchParams(String s) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("keyword", s);
        return httpParams;
    }

    private HttpParams initBindingParams(String STORE_ID) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        httpParams.put("store_id", STORE_ID);
        return httpParams;
    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }

    @Override
    public void onGetHelpEdlerHouseInformationSuccess(Object mag) {
        if (helpelderlistbean != null && !helpelderlistbean.isEmpty()) {
            helpelderlistbean.clear();
            helpElderHouseRecyAdapter.notifyDataSetChanged();
        }
        hasMore = ((HelpElderHouseListBean) mag).getList().isEmpty();
        helpelderlistbean.addAll(((HelpElderHouseListBean) mag).getList());
        helpElderHouseRecyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChangeBinderHelpEdlerHouseSuccess(Object mag) {
        if (((TwoSuccessCodeBean) mag).getMsg().equals("解绑成功") || ((TwoSuccessCodeBean) mag).getMsg().equals("绑定成功")) {
            showToast(((TwoSuccessCodeBean) mag).getMsg(), Gravity.TOP);
            onBackPressed();
        } else {
            showToast("请稍后再试", Gravity.TOP);
            onBackPressed();
        }


    }


}

package com.aite.mainlibrary.activity.allsetting.adressfix;


import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.SettingAddressListBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allsetting.addadrress.AddAdrressActivity;
import com.aite.mainlibrary.adapter.AdrressFixRecyAdapter;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.dailogwithpop.PopwindowUtils;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AdressFixActivity extends BaseActivity<AdressFixContract.View, AdressFixPresenter> implements AdressFixContract.View {

    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    private AdrressFixRecyAdapter adrressFixRecyAdapter;
    private List<SettingAddressListBean.AddressListBean> addressListBeans = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.sos_user;
    }

    @Override
    protected void initView() {
        initToolbar("我的地址");
        initBottomBtn("添加新地址", v -> startActivity(AddAdrressActivity.class));
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adrressFixRecyAdapter = new AdrressFixRecyAdapter(context, addressListBeans));
        adrressFixRecyAdapter.setOnItemRecyClickInterface(msg -> {
            if (getIntent().getStringExtra("JUMP_TYPE") != null) {
                if (getIntent().getStringExtra("JUMP_TYPE").equals("CHOICE_ADDRESS")) {
                    Intent intent = getIntent();
                    intent.putExtra("address_id", msg);
                    // 设置返回码和返回携带的数据
                    setResult(Activity.RESULT_OK, intent);
                    onBackPressed();
                }
            }
        });
        adrressFixRecyAdapter.setOnClickThingInterface(new AdrressFixRecyAdapter.OnClickThingInterface() {
            @Override
            public void onDelete(String position) {
                PopwindowUtils.getmInstance().showdiadlogPopupWindow(context, "您确定要删除此地址吗?", v -> {
                    mPresenter.dleteAddress(initListHttpParams(true, new ContentValue("address_id", addressListBeans.get(Integer.parseInt(position)).getAddress_id())));
                    PopwindowUtils.getmInstance().dismissPopWindow();

                });

            }

            /**
             * 0 getIs_default 1 默认
             * @param position
             */
            @Override
            public void onedit(String position) {
                startActivityWithCls(AddAdrressActivity.class, 0,
                        new ContentValue("TYPE", "edit"),
                        new ContentValue("address_id", addressListBeans.get(Integer.parseInt(position)).getAddress_id()),
                        new ContentValue("name", addressListBeans.get(Integer.parseInt(position)).getTrue_name()),
                        new ContentValue("phone", addressListBeans.get(Integer.parseInt(position)).getMob_phone()),
                        new ContentValue("address", addressListBeans.get(Integer.parseInt(position)).getArea_info()),
                        new ContentValue("factaddress", addressListBeans.get(Integer.parseInt(position)).getAddress()),
                        new ContentValue("city_id", addressListBeans.get(Integer.parseInt(position)).getCity_id()),
                        new ContentValue("area_id", addressListBeans.get(Integer.parseInt(position)).getArea_id()),
                        new ContentValue("isAlways", addressListBeans.get(Integer.parseInt(position)).getIs_default()));


            }
        });
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {
        mPresenter.getAdressList(initParams());

    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        return httpParams;
    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onGetAdressListSuccess(Object msg) {
        if (!addressListBeans.isEmpty()) {
            addressListBeans.clear();
            adrressFixRecyAdapter.notifyDataSetChanged();
        }
        if (addressListBeans == null) addressListBeans = new ArrayList<>();
        addressListBeans.addAll(((SettingAddressListBean) msg).getAddress_list());
        adrressFixRecyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDleteAdressSuccess(Object msg) {
        if (msg != null) {
            if (msg.toString().equals("1")) {
                showToast("删除成功");
                onBackPressed();
            }
        }

    }
}
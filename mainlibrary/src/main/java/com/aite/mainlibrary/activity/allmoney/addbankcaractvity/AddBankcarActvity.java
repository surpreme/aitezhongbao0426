package com.aite.mainlibrary.activity.allmoney.addbankcaractvity;


import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aite.mainlibrary.Mainbean.TwoSuccessCodeBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.OnClickLstenerInterface;
import com.lzy.basemodule.PopwindowUtils;
import com.lzy.basemodule.adpter.BankNameListBaseRecyAdapter;
import com.lzy.basemodule.base.BaseActivity;
import com.lzy.basemodule.bean.BankNameListBean;
import com.lzy.basemodule.bean.ContentValue;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.mvp.MVPBaseActivity;
import com.lzy.basemodule.util.FileUtils;
import com.lzy.basemodule.util.toast.ToastUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddBankcarActvity extends BaseActivity<AddBankcarActvityContract.View, AddBankcarActvityPresenter> implements AddBankcarActvityContract.View {

    @BindView(R2.id.choice_bank_ll)
    LinearLayout choiceBankLl;
    @BindView(R2.id.bank_tv)
    TextView bankTv;
    @BindView(R2.id.bottom_btn)
    Button bottomBtn;
    @BindView(R2.id.name_get_edit)
    TextInputEditText nameGetEdit;
    @BindView(R2.id.bank_number_edit)
    TextInputEditText bankNumberEdit;
    @BindView(R2.id.bank_son_name_edit)
    TextInputEditText bankSonNameEdit;
    private BankNameListBaseRecyAdapter bankNameListBaseRecyAdapter;
    private List<BankNameListBean> bankNameListBean = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.addbank_carlayout;
    }

    /**
     * 解析list型 json
     */
    @Override
    protected void initView() {
        initToolbar("添加银行卡");
        initBankAssetsData();
        bottomBtn.setText("确认添加银行卡");

    }

    private void initBankAssetsData() {
        String banklistjson = FileUtils.getFromAssets(this, "banklist.json");
        Gson gson = new Gson();
        bankNameListBean = gson.fromJson(banklistjson, new TypeToken<List<BankNameListBean>>() {
        }.getType());
        bankNameListBaseRecyAdapter = new BankNameListBaseRecyAdapter(context, bankNameListBean);
        bankNameListBaseRecyAdapter.setClickInterface(new OnClickLstenerInterface.OnRecyClickInterface() {
            @Override
            public void getPosition(int postion) {
                bankTv.setText(bankNameListBean.get(postion).getText());
                LogUtils.d(bankNameListBean.get(postion).getText() + bankNameListBean.get(postion).getValue());
                PopwindowUtils.getmInstance().dismissPopWindow();
            }
        });
    }

    @OnClick({R2.id.choice_bank_ll, R2.id.bottom_btn})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.choice_bank_ll) {
            hideSoftWare();
            PopwindowUtils.getmInstance().showRecyPopupWindow(context, bankNameListBaseRecyAdapter, new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false), choiceBankLl);
        } else if (v.getId() == R.id.bottom_btn) {
            if (isEditTextEmpty(bankNumberEdit) || isEditTextEmpty(nameGetEdit)) {
                showToast("请检查输入的信息", Gravity.TOP);
                return;
            }
            mPresenter.
                    PostAllBank(
                            initListHttpParams(
                                    true,
                                    new ContentValue("bank_name", bankTv.getText().toString()),
                                    new ContentValue("bank_no", getEditString(bankNumberEdit)),
                                    new ContentValue("bank_user", getEditString(nameGetEdit)),
                                    new ContentValue("branch_name", getEditString(bankSonNameEdit))

                            ));

        }

    }


    @Override
    protected void initDatas() {

    }

    @Override
    protected void initResume() {

    }

    @Override
    protected void initReStart() {

    }


    @Override
    public void onPostAllBankSuccess(Object msg) {
        TwoSuccessCodeBean twoSuccessCodeBean = (TwoSuccessCodeBean) msg;
        if (twoSuccessCodeBean.getMsg().equals("添加成功")) {
            showToast(twoSuccessCodeBean.getMsg());
            onBackPressed();
        }

    }
}

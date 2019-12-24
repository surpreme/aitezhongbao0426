package com.aite.mainlibrary.fragment.doctorInfoFragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.Mainbean.ConsultBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.ReadJsonUtils;
import com.aite.mainlibrary.adapter.CommentListAdapter;
import com.aite.mainlibrary.adapter.ConsultAdapter;
import com.aite.mainlibrary.adapter.QuestionAdapter;
import com.aite.mainlibrary.base.OnClickRecyclerViewListener;
import com.lxj.xpopup.XPopup;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.dailogwithpop.ConventionPopup;
import com.lzy.basemodule.view.UnopenPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建时间 2019/12/11 18:55
 * 描述: 咨询
 */
public class ConsultFagment extends BaseFragment {


    @BindView(R2.id.recy_view1)
    RecyclerView mRecyView1;
    @BindView(R2.id.recy_view2)
    RecyclerView mRecyView2;
    @BindView(R2.id.tv_comment_num)
    TextView mTvCommentNum;
    @BindView(R2.id.recy_view3)
    RecyclerView mRecyView3;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    //咨询模块
    private ConsultAdapter mConsultAdapter;
    //经典问题
    private QuestionAdapter mQuestionAdapter;
    //点评
    private CommentListAdapter mCommentListAdapter;
    private ConventionPopup mConventionPopup;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_consult;
    }


    @Override
    protected void initModel() {

    }

    @Override
    protected void initViews() {
        /*-----咨询类型-----*/
        mConsultAdapter = new ConsultAdapter(context);
        mRecyView1.setLayoutManager(new GridLayoutManager(context, 3));
        mRecyView1.setNestedScrollingEnabled(false);
        mRecyView1.setAdapter(mConsultAdapter);


        /*--------经典问题--------*/
        mQuestionAdapter = new QuestionAdapter(context);
        mRecyView2.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyView2.setNestedScrollingEnabled(false);
        mRecyView2.setAdapter(mQuestionAdapter);


        /*---------点评--------*/
        mCommentListAdapter = new CommentListAdapter(context);
        mRecyView3.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyView3.setNestedScrollingEnabled(false);
        mRecyView3.setAdapter(mCommentListAdapter);

        initData();
        initListener();
    }

    private void initListener() {
        mConsultAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        //图文咨询
                        break;
                    case 1:
                        //电话咨询
                        goTelephone();

                        break;
                    case 2:
                        //视频咨询
                        new XPopup.Builder(context)
                                .asCustom(new UnopenPopup(context, "该板块暂未开放"))
                                .show();
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

    /**
     *
     */
    private void goTelephone() {
        if (mConventionPopup == null) {
            mConventionPopup = new ConventionPopup(context, "该板块暂未开放", "拒绝", "同意");
            mConventionPopup.setOnBut1ClickListener(new ConventionPopup.onBut1ClickListener() {
                @Override
                public void onBut1ClickListener() {
                    mConventionPopup.dismiss();
                }
            });
            mConventionPopup.setOnBut2ClickListener(new ConventionPopup.onBut2ClickListener() {
                @Override
                public void onBut2ClickListener() {
                    mConventionPopup.dismiss();
                }
            });
        }
        new XPopup.Builder(context)
                .asCustom(mConventionPopup)
                .show();
    }

    private void initData() {
        if (ReadJsonUtils.getJson(context, "text1.json") == null) return;
        List<ConsultBean> bean = ReadJsonUtils.getBean(ReadJsonUtils.getJson(context, "text1.json"), "consult_icon");
        mConsultAdapter.appendData(bean);

        /**--------评价列表----------**/
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String s = "222222";
            dataList.add(s);
        }
        mQuestionAdapter.appendData(dataList);

        mCommentListAdapter.appendData(dataList);
    }

    @Override
    public void onClick(View v) {

    }
}
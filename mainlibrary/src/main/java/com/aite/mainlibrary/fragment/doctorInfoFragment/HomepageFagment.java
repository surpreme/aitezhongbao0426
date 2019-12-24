package com.aite.mainlibrary.fragment.doctorInfoFragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.adapter.CommentListAdapter;
import com.aite.mainlibrary.adapter.HospitalListsAdapter;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.util.TextUtil;
import com.lzy.basemodule.view.FlowLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建时间 2019/12/11 18:54
 * 描述:  主页
 */
public class HomepageFagment extends BaseFragment {

    @BindView(R2.id.tv_site)
    TextView mTvSite;
    @BindView(R2.id.recy_view1)
    RecyclerView mRecyView1;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.tv_evaluate_num)
    TextView mTvEvaluateNum;
    @BindView(R2.id.flow_layout)
    FlowLayout mFlowLayout;
    @BindView(R2.id.recy_view2)
    RecyclerView mRecyView2;


    private HospitalListsAdapter mAdapter;

    private CommentListAdapter mCommentListAdapter;


    @Override
    protected void initModel() {

    }

    @Override
    protected void initViews() {

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data.add("慢性腹泻45");
        }
        mAdapter = new HospitalListsAdapter(context);
        mRecyView1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyView1.setNestedScrollingEnabled(false);
        mAdapter.appendData(data);
        mRecyView1.setAdapter(mAdapter);

        //流布局 数据
        loadDataToTypeSearchList(data);


        /**--------评价列表----------**/
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = "222222";
            dataList.add(s);
        }
        mCommentListAdapter = new CommentListAdapter(context);
        mRecyView2.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyView2.setNestedScrollingEnabled(false);
        mCommentListAdapter.appendData(dataList);
        mRecyView2.setAdapter(mCommentListAdapter);
        
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void onClick(View v) {

    }

    private void loadDataToTypeSearchList(List<String> searchList) {
        for (final String searchKeyword : searchList) {
            TextView textView = new TextView(context);
            textView.setText(TextUtil.highlight(context, searchKeyword, "45", "#FFB400", 0, 0));
            textView.setBackgroundResource(R.drawable.search_bg_item);
            textView.setGravity(Gravity.CENTER);
            int padding2 = getResources().getDimensionPixelSize(R.dimen.dp_2);
            int padding8 = getResources().getDimensionPixelSize(R.dimen.dp_8);
            textView.setPadding(padding8, padding2, padding8, padding2);
            textView.setTextColor(context.getResources().getColor(R.color.text1));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///getSearch(searchKeyword);
                }
            });
            mFlowLayout.addView(textView);
        }
    }


}
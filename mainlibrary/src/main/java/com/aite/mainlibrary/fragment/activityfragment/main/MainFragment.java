package com.aite.mainlibrary.fragment.activityfragment.main;

import android.view.View;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.aite.mainlibrary.Constant.MainUIConstant;
import com.aite.mainlibrary.Mainbean.MainUiDataBean;
import com.aite.mainlibrary.R;
import com.aite.mainlibrary.R2;
import com.aite.mainlibrary.activity.allmain.AddDeviceMainActvity;
import com.aite.mainlibrary.activity.allmain.device.DeviceListActivity;
import com.aite.mainlibrary.activity.allmain.elsehelp.ElseHelpActivity;
import com.aite.mainlibrary.activity.allmain.messager.MessagerActivity;
import com.aite.mainlibrary.activity.allmain.soundadvice.SoundAdviceActivity;
import com.aite.mainlibrary.activity.allshopcard.air.AirActivity;
import com.aite.mainlibrary.activity.allshopcard.daytogether.DayTogetherActivity;
import com.aite.mainlibrary.activity.allshopcard.helpdoctor.HelpdoctorActivity;
import com.aite.mainlibrary.activity.allshopcard.helpeat.HelpEatActivity;
import com.aite.mainlibrary.activity.allshopcard.timebank.TimeBankActivity;
import com.aite.mainlibrary.adapter.HelpElderRecyAdapter;
import com.aite.mainlibrary.adapter.LessBodyIconRecyAdapter;
import com.aite.mainlibrary.adapter.RecyMainIconAdapter;
import com.example.ui.activity.StarDoctorPushActvity;
import com.lzy.basemodule.BaseConstant.AppConstant;
import com.lzy.basemodule.adpter.BaseItemDecoration;
import com.lzy.basemodule.base.BaseFragment;
import com.lzy.basemodule.logcat.LogUtils;
import com.lzy.basemodule.util.SystemUtil;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.superluo.textbannerlibrary.TextBannerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainFragment extends BaseFragment<MainContract.View, MainPresenter> implements MainContract.View, OnBannerListener {

    @BindView(R2.id.message_ll)
    LinearLayout messageLl;
    @BindView(R2.id.main_doctor_talker_ll)
    LinearLayout mainDoctorTalkerll;
    @BindView(R2.id.add_deivice_ll)
    LinearLayout addDeivicell;
    @BindView(R2.id.banner)
    Banner banner;
    @BindView(R2.id.lottieAnimationView)
    LottieAnimationView lottieAnimationView;
    @BindView(R2.id.smartlayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.help_elder_recy)
    RecyclerView helpElderRecy;
    @BindView(R2.id.device_list_ll)
    LinearLayout deviceListLl;
    @BindView(R2.id.less_body_revy)
    RecyclerView lessBodyRevy;
    @BindView(R2.id.sound_tv)
    TextBannerView soundTv;
    private HelpElderRecyAdapter helpElderRecyAdapter;
    private LessBodyIconRecyAdapter lessBodyIconRecyAdapter;
    private RecyMainIconAdapter recyMainIconAdapter;
    private List<MainUiDataBean.PensionAdvsBean> pensionAdvsBeans = new ArrayList<>();
    private List<MainUiDataBean.DisAdvsBean> disAdvsBeans = new ArrayList<>();
    private List<MainUiDataBean.ArticleBean> articleBeanList = new ArrayList<>();
    //banner datalist
    private List<String> list_img = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();


    @Override
    protected void initModel() {
        mPresenter.showUiData(initParams());
        LogUtils.d(AppConstant.KEY);


    }

    private HttpParams initParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", AppConstant.KEY);
        return httpParams;
    }

    /**
     * smartlayout出错 动画线程
     */
    @Override
    protected void initViews() {
        //点击监听
        messageLl.setOnClickListener(this);
        mainDoctorTalkerll.setOnClickListener(this);
        addDeivicell.setOnClickListener(this);
        banner.setOnClickListener(this);
        deviceListLl.setOnClickListener(this);
        //smartlayout
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            smartRefreshLayout.finishRefresh(5000/*,false*/);//传入false表示刷新失败
            initModel();
        });
        soundTv.setItemOnClickListener((data, position) -> {
            if (articleBeanList != null && !articleBeanList.isEmpty() && articleBeanList.get(position).getArticle_id() != null)
                startActivity(SoundAdviceActivity.class, "article_id", articleBeanList.get(position).getArticle_id());

        });
        //banner
        initBanner(banner);
        banner.setIndicatorGravity(BannerConfig.RIGHT)
                .setOnBannerListener(this);

        //初始化适配器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager lessBodyLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false);
        helpElderRecy.setLayoutManager(linearLayoutManager);
        lessBodyRevy.setLayoutManager(lessBodyLinearLayoutManager);

//        if (gridLayoutManager != null)
//            lessBodyRevy.setLayoutManager(gridLayoutManager);
//        recyMainIconAdapter = new RecyMainIconAdapter(context,
//                MainUIConstant.MainFragmentConstant.settingImg,
//                MainUIConstant.MainFragmentConstant.settingTv,
//                getScreenwidth());
//        lessBodyRevy.setAdapter(recyMainIconAdapter);

        lessBodyRevy.setAdapter(lessBodyIconRecyAdapter = new LessBodyIconRecyAdapter(context, disAdvsBeans));
        helpElderRecyAdapter = new HelpElderRecyAdapter(context, pensionAdvsBeans);
        helpElderRecy.setAdapter(helpElderRecyAdapter);
        //        helpElderRecy.addItemDecoration(new GridSpacingItemDecoration(5, 20, false));
        if (helpElderRecy.getItemDecorationCount() == 0) {
            helpElderRecy.addItemDecoration(new BaseItemDecoration(SystemUtil.dip2px(context, 0), SystemUtil.dip2px(context, 10)
                    , SystemUtil.dip2px(context, 0), SystemUtil.dip2px(context, 0)
                    , SystemUtil.dip2px(context, 0), SystemUtil.dip2px(context, 0)
                    , ContextCompat.getColor(context, R.color.noglay), context
                    , 5f, "7.1:12"));
        } if (lessBodyRevy.getItemDecorationCount() == 0) {
            lessBodyRevy.addItemDecoration(new BaseItemDecoration(SystemUtil.dip2px(context, 0), SystemUtil.dip2px(context, 10)
                    , SystemUtil.dip2px(context, 0), SystemUtil.dip2px(context, 0)
                    , SystemUtil.dip2px(context, 0), SystemUtil.dip2px(context, 0)
                    , ContextCompat.getColor(context, R.color.noglay), context
                    , 5f, "7.1:12"));
        }

        //设置适配器点击监听
        lessBodyIconRecyAdapter.setLstenerInterface(postion -> {
            switch (postion) {
                case 0:
                    startActivity(DayTogetherActivity.class, "type", "1");
                    break;
                case 1:
                    startActivity(DayTogetherActivity.class, "type", "2");
                    break;
                case 2:
                    startActivity(DayTogetherActivity.class, "type", "3");
                    break;
                case 3:
                    startActivity(DayTogetherActivity.class, "type", "4");
                    break;
                case 4:
                    startActivity(DayTogetherActivity.class, "type", "5");
                    break;
                default:
                    break;
            }
        });
        helpElderRecyAdapter.setLstenerInterface(position -> {
            if (position == 0) {
                startActivity(HelpEatActivity.class);
            } else if (position == 1) {
                startActivity(HelpdoctorActivity.class);
            } else if (position == 2) {
                startActivity(TimeBankActivity.class);
            } else if (position == 3) {
                startActivity(AirActivity.class);
            } else if (position == 4) {
                startActivity(ElseHelpActivity.class);
            }
        });


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_fragment;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.message_ll) {
            startActivity(MessagerActivity.class);
        } else if (v.getId() == R.id.main_doctor_talker_ll) {
            startActivity(StarDoctorPushActvity.class);
        } else if (v.getId() == R.id.add_deivice_ll) {
            startActivity(AddDeviceMainActvity.class);
        } else if (v.getId() == R.id.device_list_ll)
            startActivity(DeviceListActivity.class);


    }


    @Override
    public void getUiDataSuccess(Object msg) {
        if (!((MainUiDataBean) msg).getPension_advs().isEmpty()) pensionAdvsBeans.clear();
        if (!((MainUiDataBean) msg).getAdv_list().isEmpty()) pensionAdvsBeans.clear();

        if (((MainUiDataBean) msg).getPension_advs() != null || ((MainUiDataBean) msg).getPension_advs().size() > 0) {
            pensionAdvsBeans.addAll(((MainUiDataBean) msg).getPension_advs());
            helpElderRecyAdapter.notifyDataSetChanged();
        }
        MainUiDataBean mainUiDataBean = ((MainUiDataBean) msg);
        if (mainUiDataBean.getDis_advs() != null) {
            if (disAdvsBeans == null) disAdvsBeans = new ArrayList<>();
            if (!disAdvsBeans.isEmpty()) disAdvsBeans.clear();
            disAdvsBeans.addAll(mainUiDataBean.getDis_advs());
            lessBodyIconRecyAdapter.notifyDataSetChanged();
        }
        //设置数据 textbanner
        List<String> soundlist = new ArrayList<>();
        if (((MainUiDataBean) msg).getArticle() != null) {
            if (articleBeanList == null) articleBeanList = new ArrayList<>();
            if (!articleBeanList.isEmpty()) articleBeanList.clear();
            articleBeanList.addAll(((MainUiDataBean) msg).getArticle());
            for (int i = 0; i < ((MainUiDataBean) msg).getArticle().size(); i++) {
                soundlist.add(((MainUiDataBean) msg).getArticle().get(i).getArticle_title());
            }
            soundTv.setDatas(soundlist);

        }
//        mTvBanner.setDatasWithDrawableIcon(mList,drawable,18, Gravity.LEFT);
        if (((MainUiDataBean) msg).getAdv_list() != null || ((MainUiDataBean) msg).getAdv_list().size() > 0) {
            if (!list_img.isEmpty()) list_img.clear();
            if (!list_title.isEmpty()) list_title.clear();
            for (MainUiDataBean.AdvListBean advListBean : ((MainUiDataBean) msg).getAdv_list()) {
                list_img.add(advListBean.getAdv_content().getAdv_pic());
                list_title.add(advListBean.getAdv_title());
                LogUtils.d(advListBean.getAdv_title());

            }
            banner.setImages(list_img);
            banner.setBannerTitles(list_title);
            banner.startAutoPlay();
            banner.start();
        }


    }

    @Override
    public void OnBannerClick(int position) {

    }

    //处理textbanner的问题
    @Override
    public void onResume() {
        super.onResume();
        soundTv.startViewAnimator();
    }

    @Override
    public void onStop() {
        super.onStop();
        soundTv.stopViewAnimator();
    }

}

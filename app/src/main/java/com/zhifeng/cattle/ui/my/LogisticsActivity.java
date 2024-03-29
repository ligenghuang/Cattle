package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.LogisticsAction;
import com.zhifeng.cattle.adapters.LogisticsListAdapter;
import com.zhifeng.cattle.modules.LogisticsDto;
import com.zhifeng.cattle.ui.impl.LogisticsView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 查看物流
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/10/17 10:20
 * @Version: 1.0
 */

public class LogisticsActivity extends UserBaseActivity<LogisticsAction> implements LogisticsView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_logistics_logo)
    ImageView ivLogisticsLogo;
    @BindView(R.id.tv_logistics_name)
    TextView tvLogisticsName;
    @BindView(R.id.tv_logistics_no)
    TextView tvLogisticsNo;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_logistics_more)
    TextView tvLogisticsMore;

    List<LogisticsDto.DataBean.ResultBean.ListBean> allList = new ArrayList<>();
    List<LogisticsDto.DataBean.ResultBean.ListBean> list = new ArrayList<>();

    LogisticsListAdapter logisticsListAdapter;

    int orderId;

    @Override
    public int intiLayout() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected LogisticsAction initAction() {
        return new LogisticsAction(this, this);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("LogisticsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.logistics_tab_2));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        orderId = getIntent().getIntExtra("orderId", 0);

        logisticsListAdapter = new LogisticsListAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(logisticsListAdapter);

        getLogostocs();
    }

    /**
     * 查看物流
     */
    @Override
    public void getLogostocs() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getLogistics(orderId + "");
        }
    }

    /**
     * 获取物流信息成功
     * @param logostocsDto
     */
    @Override
    public void getLogostocsSuccess(LogisticsDto logostocsDto) {
        loadDiss();
//        String json = new GetJsonDataUtil().getJson(mContext,"logostocs.json");
//        LogisticsDto logostocsDto1 = new Gson().fromJson(json, new TypeToken<LogisticsDto>() {
//        }.getType());
        try {
            LogisticsDto.DataBean.ResultBean resultBean = logostocsDto.getData().getResult();
            list.add(new LogisticsDto.DataBean.ResultBean.ListBean("", resultBean.getAddress()));

            List<LogisticsDto.DataBean.ResultBean.ListBean> listBeans = resultBean.getList();
            if (listBeans.size() > 1) {
                list.add(listBeans.get(0));
                for (int i = 1; i < listBeans.size(); i++) {
                    allList.add(listBeans.get(i));
                }
            } else {
                tvLogisticsMore.setVisibility(View.GONE);
            }
            GlideUtil.setImage(mContext, resultBean.getLogo(), ivLogisticsLogo);
            tvLogisticsName.setText(resultBean.getExpName());
            tvLogisticsNo.setText(ResUtil.getFormatString(R.string.logistics_tab_3, resultBean.getNumber()));

            logisticsListAdapter.refresh(list);
        }catch (Exception e){
            showNormalToast("查询物流信息失败");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }
    }

    /**
     * 查询物流信息失败
     * @param msg
     */
    @Override
    public void getLogisticsError(String msg) {
        loadDiss();
        showNormalToast(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @OnClick(R.id.tv_logistics_more)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_logistics_more:
                //todo 查看更多
                logisticsListAdapter.loadMore(allList);
                break;
        }
    }
}

package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.data.MySp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnTouch;

/**
 * @ClassName: 我的订单
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/11 12:16
 * @Version: 1.0
 */

public class OrderActivity extends UserBaseActivity {

    private static final String TAG = "MainActivity";
    public static int Position = 0;
    private static final int POIONTONE = 0;
    private static final int POIONTTWO = 1;
    private static final int POIONTTHREE = 2;
    private static final int POIONTFOUR = 3;
    private static final int POIONTFIVE = 4;

    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private int fragmentSize = 5;

    OrderFrament orderFrament1,orderFrament2,orderFrament3,orderFrament4,orderFrament5;

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_order_1)
    LinearLayout llOrder1;
    @BindView(R.id.ll_order_2)
    LinearLayout llOrder2;
    @BindView(R.id.ll_order_3)
    LinearLayout llOrder3;
    @BindView(R.id.ll_order_4)
    LinearLayout llOrder4;
    @BindView(R.id.ll_order_5)
    LinearLayout llOrder5;
    @BindView(R.id.my_pager)
    CustomViewPager myPager;


    @Override
    public int intiLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        mImmersionBar
//                .statusBarView(R.id.top_view)
                .fullScreen(true)
                .navigationBarWithKitkatEnable(false)
                .statusBarDarkFont(true)
                .addTag("order")  //给上面参数打标记，以后可以通过标记恢复
                .init();

        Position = getIntent().getIntExtra("type",0);

        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < fragmentSize; i++) {
            switch (i) {
                case POIONTONE://
                    orderFrament1 = new OrderFrament(POIONTONE);
                    if (Position != POIONTONE) {
                        orderFrament1.setUserVisibleHint(false);//
                    }

                    fragments.add(orderFrament1);
                    break;
                case POIONTTWO://
                    orderFrament2 = new OrderFrament(POIONTTWO);
                    if (Position != POIONTTWO) {
                        orderFrament2.setUserVisibleHint(false);//
                    }
                    fragments.add(orderFrament2);
                    break;
                case POIONTTHREE://
                    orderFrament3 = new OrderFrament(POIONTTHREE);
                    if (Position != POIONTTHREE) {
                        orderFrament3.setUserVisibleHint(false);//
                    }
                    fragments.add(orderFrament3);
                    break;
                case POIONTFOUR://
                    orderFrament4 = new OrderFrament(POIONTTHREE);
                    if (Position != POIONTFOUR) {
                        orderFrament4.setUserVisibleHint(false);
                    }
                    fragments.add(orderFrament4);
                    break;
                case POIONTFIVE://
                    orderFrament5 = new OrderFrament(POIONTFIVE);
                    if (Position != POIONTFIVE) {
                        orderFrament5.setUserVisibleHint(false);
                    }
                    fragments.add(orderFrament5);
                    break;
                default:
                    break;
            }
        }

        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragments);

        fragmentPagerAdapter.setFragments(fragments);
        setSelectedLin(Position);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myPager.setAdapter(fragmentPagerAdapter);
                myPager.setCurrentItem(Position, false);
                myPager.setOffscreenPageLimit(fragmentSize);


            }
        }, 500);

    }

    @OnTouch({R.id.ll_order_1, R.id.ll_order_2, R.id.ll_order_3,R.id.ll_order_4,R.id.ll_order_5})
    public boolean onTouch(View v) {
        switch (v.getId()) {
            case R.id.ll_order_1:
                Position = POIONTONE;
                break;
            case R.id.ll_order_2:
                Position = POIONTTWO;
                break;
            case R.id.ll_order_3:
                Position = POIONTTHREE;
                break;
            case R.id.ll_order_4:
                Position = POIONTFOUR;
                break;
            case R.id.ll_order_5:
                Position = POIONTFIVE;
                break;
            default:
                break;
        }
        setSelectedLin(Position);
        myPager.setCurrentItem(Position, false);
        return false;
    }

    /**
     * 选择
     *
     * @param position
     */
    private void setSelectedLin(int position) {
        llOrder1.setSelected(false);
        llOrder2.setSelected(false);
        llOrder3.setSelected(false);
        llOrder4.setSelected(false);
        llOrder5.setSelected(false);
        //设置状态栏黑色字体与图标
//        QMUIStatusBarHelper.setStatusBarLightMode(this);
        mImmersionBar.statusBarDarkFont(true);
        switch (position) {
            case 0:
                llOrder1.setSelected(true);
                break;
            case 1:
                llOrder2.setSelected(true);
                break;
            case 2:
                llOrder3.setSelected(true);
                break;
            case 3:
                llOrder4.setSelected(true);
                break;
            case 4:
                llOrder5.setSelected(true);
                break;
            default:
                break;
        }
    }
}

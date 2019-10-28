package com.zhifeng.cattle.utils.popup;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.animator.ScrollScaleAnimator;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.zhifeng.cattle.R;

/**
  *
  * @ClassName:     充值方式popup
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/10/28 11:26
  * @Version:        1.0
 */

public class PayTypePopup extends AttachPopupView {
    Context context;
    OnListClickListener onListClickListener;
    int width;

    public PayTypePopup(@NonNull Context context,int width,OnListClickListener onListClickListener) {
        super(context);
        this.context = context;
        this.width = width;
        this.onListClickListener = onListClickListener;
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.layout_pay_type_popup;
    }
    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        //todo 支付宝支付
        findViewById(R.id.tv_pay_ali).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onListClickListener.onAliPay();
                dismiss();
            }
        });
        //todo 微信支付
        findViewById(R.id.tv_pay_watch).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onListClickListener.onWatchPay();
                dismiss();
            }
        });
    }

    protected int getPopupWidth() {
        return width;
    }

    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return new ScrollScaleAnimator(getPopupContentView(), PopupAnimation.ScrollAlphaFromLeftTop);
    }

    public interface OnListClickListener {
        void onAliPay();
        void onWatchPay();
    }


}

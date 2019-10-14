package com.zhifeng.cattle.utils.popup;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.adapters.CatenavListAdapter;
import com.zhifeng.cattle.modules.RecommendHomeDto;

import java.util.List;

public class CustomPopup extends PartShadowPopupView {
    Context context;
    List<RecommendHomeDto.DataBean.Catenav1Bean> list;
    OnListClickListener onListClickListener;


    public CustomPopup(@NonNull Context context, List<RecommendHomeDto.DataBean.Catenav1Bean> list, OnListClickListener onListClickListener) {
        super(context);
        this.context = context;
        this.list = list;
        this.onListClickListener = onListClickListener;
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_popup;
    }

    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        ImageView ivClose = findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv_classify);
        CatenavListAdapter catenavListAdapter = new CatenavListAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.setAdapter(catenavListAdapter);
        catenavListAdapter.refresh(list);
        catenavListAdapter.setOnClickListener(new CatenavListAdapter.OnClickListener() {
            @Override
            public void onClick(int id, String name, int position) {
                //todo 跳转页面
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setClick(id == list.get(i).getCat_id());
                }
//                Intent i = new Intent(context, ListPageActivity.class);
//                i.putExtra("cat_id", id);
//                i.putExtra("name", name);
//                context.startActivity(i);
                dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onListClickListener.onClick(position);
                    }
                }, 500);
            }
        });

    }

    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        PopupAnimator animator = new PopupAnimator() {
            @Override
            public void initAnimator() {

            }

            @Override
            public void animateShow() {

            }

            @Override
            public void animateDismiss() {

            }
        };
        return super.getPopupAnimator();
    }

    public interface OnListClickListener {
        void onClick(int position);
    }

}

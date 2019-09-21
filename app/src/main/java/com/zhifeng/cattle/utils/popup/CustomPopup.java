package com.zhifeng.cattle.utils.popup;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.adapters.CatenavListAdapter;
import com.zhifeng.cattle.modules.Catenav2Bean;
import com.zhifeng.cattle.ui.home.ListPageActivity;

import java.util.List;

public class CustomPopup extends PartShadowPopupView {
    Context context;
    List<Catenav2Bean> list;
    public CustomPopup(@NonNull Context context,List<Catenav2Bean> list) {
        super(context);
        this.context = context;
        this.list = list;
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
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        recyclerView.setAdapter(catenavListAdapter);
        catenavListAdapter.refresh(list);
        catenavListAdapter.setOnClickListener(new CatenavListAdapter.OnClickListener() {
            @Override
            public void onClick(int id,String name) {
                //todo 跳转页面
                Intent i = new Intent(context, ListPageActivity.class);
                i.putExtra("cat_id", id);
                i.putExtra("name", name);
                context.startActivity(i);
            }
        });

    }

    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
    }

}

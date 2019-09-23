package com.zhifeng.cattle.utils.view;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName:
 * @Description: 间隔均匀的表格布局
 * @Author: Administrator
 * @Date: 2019/9/23 15:15
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position != 0 || (parent.getAdapter() != null && position == parent.getAdapter().getItemCount() - 1)) {
            outRect.left = space;
        }
    }
}

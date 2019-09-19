package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.GoodsComment;

public interface GoodsCommentsView extends BaseView {
    void getGoodsComments();

    void getGoodsCommentsSuccess(GoodsComment goodsComment);
}

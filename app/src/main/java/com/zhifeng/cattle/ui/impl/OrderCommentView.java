package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.OrderCommentResult;

public interface OrderCommentView extends BaseView {
     void postCommentSuccess(OrderCommentResult orderCommentResult);
}

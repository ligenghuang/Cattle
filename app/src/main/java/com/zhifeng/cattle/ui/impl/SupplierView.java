package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.Supplier;

/**
 * @ClassName:
 * @Description: 申请供应商
 * @Author: Administrator
 * @Date: 2019/9/21 10:16
 */
public interface SupplierView extends BaseView {
    void postSupplier();

    void postSupplierSuccess(Supplier supplier);
}

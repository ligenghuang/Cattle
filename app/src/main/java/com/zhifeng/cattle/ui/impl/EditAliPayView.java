package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.AliPayDto;
import com.zhifeng.cattle.modules.GeneralDto;

/**
  *
  * @ClassName:     填写支付宝账号
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 17:14
  * @Version:        1.0
 */

public interface EditAliPayView extends BaseView {

    /**
     * 填写支付宝账号
     * @param alipay_name
     * @param alipay
     */
    void editAlipay(String alipay_name,String alipay);
    void editAlipaySuccess(AliPayDto generalDto);
}

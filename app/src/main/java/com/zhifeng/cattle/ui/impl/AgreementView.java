package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;

/**
  *
  * @ClassName:     用户协议
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/9 16:21
  * @Version:        1.0
 */

public interface AgreementView extends BaseView {
    /**
     * 获取用户协议
     */
    void getAgreement();

    void getAgreementSuccess(String data);
}

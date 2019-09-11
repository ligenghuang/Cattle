package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BalanceDto;

/**
  *
  * @ClassName:     我的余额
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 9:44
  * @Version:        1.0
 */

public interface BalanceView extends BaseView {

    void getBalance();
    void getBalanceSuccess(BalanceDto balanceDto);
}

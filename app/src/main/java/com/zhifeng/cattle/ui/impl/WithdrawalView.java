package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BalanceDto;
import com.zhifeng.cattle.modules.BaseDto;
import com.zhifeng.cattle.modules.GeneralDto;

/**
  *
  * @ClassName:     提现
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 16:31
  * @Version:        1.0
 */

public interface WithdrawalView extends BaseView {

    /**
     * 获取余额
     */
    void getBalance();
    void getBalanceSuccess(BalanceDto balanceDto);

    /**
     * 提现
     * @param money
     */
    void withdrawal(double money);
    void withdrawalSuccess(BaseDto generalDto);
}

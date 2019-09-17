package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.WithdrawalListDto;

/**
  *
  * @ClassName:     提现明细
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 14:03
  * @Version:        1.0
 */

public interface WithdrawalDetailView extends BaseView {

    /**
     * 获取提现明细
     */
    void getWithdrawalList();
    void moreWithdrawalList();
    void getWithdrawalListSuccess(WithdrawalListDto withdrawalListDto);
}

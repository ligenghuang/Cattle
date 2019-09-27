package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BankBto;
import com.zhifeng.cattle.modules.BankImgListDto;
import com.zhifeng.cattle.modules.BankListDto;
import com.zhifeng.cattle.modules.GeneralDto;

/**
  *
  * @ClassName:     充值
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/20 10:59
  * @Version:        1.0
 */

public interface RechargeView extends BaseView {

    /**
     * 获取已绑定银行卡列表
     */
    void getBankList();
    void getBankListSuccess(BankListDto bankListDto);

    /**
     * 获取银行图标列表
     */
    void getBankImgList();
    void getBankImgListSuccess(BankImgListDto bankListDto);

    /**
     * 充值
     * @param num
     * @param pwd
     */
    void recharge(double num,String pwd);
    void rechargeSuccess(GeneralDto generalDto);
}

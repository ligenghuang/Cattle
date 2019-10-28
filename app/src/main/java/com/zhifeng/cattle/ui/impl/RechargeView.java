package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BankBto;
import com.zhifeng.cattle.modules.BankImgListDto;
import com.zhifeng.cattle.modules.BankListDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.RateDto;
import com.zhifeng.cattle.modules.RechargeTypeDto;

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
     * 获取充值方式
     */
    void getRechargeType();
    void getRechargeTypeSuccess(RechargeTypeDto rechargeTypeDto);

    /**
     * 获取汇率
     */
    void getRate();
    void getRateSuccess(RateDto rateDto);


    /**
     * 充值
     * @param num
     * @param pwd
     */
    void recharge(double num,String pwd);
    void rechargeSuccess(GeneralDto generalDto);
}

package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BaseDto;

/**
  *
  * @ClassName:     修改支付密码
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 10:17
  * @Version:        1.0
 */

public interface ModifyPayPwdView extends BaseView {

    /**
     * 修改支付密码
     * @param oldPwd
     * @param newPwd
     */
    void modifyPayPwd(String oldPwd,String newPwd);
    void modifyPayPwdSuccess(BaseDto baseDto);
}

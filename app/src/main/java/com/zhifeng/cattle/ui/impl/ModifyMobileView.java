package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BaseDto;
import com.zhifeng.cattle.modules.GeneralDto;

/**
  *
  * @ClassName:     修改手机号码  验证手机号码
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/16 17:21
  * @Version:        1.0
 */

public interface ModifyMobileView extends BaseView {

    /**
     * 验证手机号
     * @param phone
     */
    void verifyPhone(String phone);
    void verifyPhoneSuccess(BaseDto generalDto);
}

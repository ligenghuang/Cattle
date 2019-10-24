package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BindMobileDto;

/**
 *
 * @ClassName:     微信登录 绑定手机
 * @Description:
 * @Author:         lgh
 * @CreateDate:     2019/10/24 10:32
 * @Version:        1.0
 */
public interface BindMobileView extends BaseView {

    /**
     * 获取验证码
     */
    void getCode(String phone);
    void getCodeSuccess(String msg);

    /**
     * 绑定手机号
     */
    void bindMobile(String verify_code,String phone);
    void bindMobileSuccess(BindMobileDto bindMobileDto);
}

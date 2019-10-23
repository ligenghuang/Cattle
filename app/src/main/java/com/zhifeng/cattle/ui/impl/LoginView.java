package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.LoginDto;
import com.zhifeng.cattle.modules.WxLoginDto;

/**
  *
  * @ClassName:     登录注册页
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/9 14:39
  * @Version:        1.0
 */

public interface LoginView extends BaseView {

    /**
     * 获取验证码
     */
    void getCodeSuccess(String msg);

    /**
     * 登录或注册
     */
    void loginOrRegisteredSuccess(LoginDto loginDto);

    /**
     * 微信登录成功
     */
    void wxLoginSuccess(WxLoginDto wxLoginDto);

}

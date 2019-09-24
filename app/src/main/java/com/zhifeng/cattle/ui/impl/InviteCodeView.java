package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
/**
  *
  * @ClassName:     填写邀请码
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/24 10:07
  * @Version:        1.0
 */

public interface InviteCodeView extends BaseView {

    void inviteCode(String code);
    void inviteCodeSuccess(String msg);
}

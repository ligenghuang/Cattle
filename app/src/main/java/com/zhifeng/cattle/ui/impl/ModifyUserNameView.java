package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;

/**
  *
  * @ClassName:     修改用户名
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/10/9 14:28
  * @Version:        1.0
 */

public interface ModifyUserNameView extends BaseView {

    void modifyUserName(String name);
    void modifyUserNameSuccess(String msg);
}

package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.UserInfoDto;

/**
  *
  * @ClassName:     我的
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 18:42
  * @Version:        1.0
 */

public interface MyView extends BaseView {

    /**
     * 获取用户信息
     */
    void getUserInfo();
    void getUserInfoSuccess(UserInfoDto userInfoDto);

    /**
     * 修改头像
     * @param path
     */
    void updataAvatar(String path);
    void updataAvatarSuccess(String url);

    void onLoginNo();
}

package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.MyTeamDto;

/**
  *
  * @ClassName:     我的团队
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 12:25
  * @Version:        1.0
 */

public interface MyTeamView extends BaseView {

    /**
     * 获取我的团队
     */
    void getMyTeam();
    void getMyTeamSuccess(MyTeamDto myTeamDto);
}

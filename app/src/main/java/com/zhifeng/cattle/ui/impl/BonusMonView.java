package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BonusDayDto;
import com.zhifeng.cattle.modules.BonusMonDto;

/**
  *
  * @ClassName:     当月累计奖金
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 10:41
  * @Version:        1.0
 */

public interface BonusMonView extends BaseView {

    void getBonusMon();
    void getBonusMonSuccess(BonusMonDto bonusMonDto);
}

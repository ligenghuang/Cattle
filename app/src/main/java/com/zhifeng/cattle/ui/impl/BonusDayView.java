package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BonusDayDto;

/**
  *
  * @ClassName:     当日累计奖金
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 10:41
  * @Version:        1.0
 */

public interface BonusDayView extends BaseView {

    void getBonusDay();
    void getBonusDaySuccess(BonusDayDto bonusDayDto);
}

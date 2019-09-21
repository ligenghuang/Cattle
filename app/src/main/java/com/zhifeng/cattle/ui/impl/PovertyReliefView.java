package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.PovertyReliefDto;

/**
  *
  * @ClassName:     兴农扶贫
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 10:16
  * @Version:        1.0
 */

public interface PovertyReliefView extends BaseView {

    /**
     * 获取首页兴农扶贫数据
     */
    void getPovertyRelief();
    void getPovertyReliefSuccess(PovertyReliefDto povertyReliefDto);
}

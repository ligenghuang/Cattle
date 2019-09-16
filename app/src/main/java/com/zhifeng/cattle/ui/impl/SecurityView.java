package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.SafeInfoDto;

/**
  *
  * @ClassName:     安全中心
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/16 16:34
  * @Version:        1.0
 */
public interface SecurityView extends BaseView {

    /**
     * 安全中心信息
     */
    void getSafeInfo();
    void getSafeInfoSuccess(SafeInfoDto safeInfoDto);
}

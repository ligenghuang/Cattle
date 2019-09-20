package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.RecommendHomeDto;

/**
  *
  * @ClassName:     推荐
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/20 17:17
  * @Version:        1.0
 */

public interface RecommendHomeView extends BaseView {

    /**
     * 获取首页推荐
     */
    void getRecommendHome();
    void getRecommendHomeSuccess(RecommendHomeDto recommendHomeDto);
}

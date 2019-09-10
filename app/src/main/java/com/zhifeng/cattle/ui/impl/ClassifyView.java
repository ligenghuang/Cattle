package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.CategoryListDto;

/**
  *
  * @ClassName:     分类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 10:13
  * @Version:        1.0
 */

public interface ClassifyView extends BaseView {

    void getCategoryList();
    void getCategoryListSuccess(CategoryListDto categoryListDto);
}

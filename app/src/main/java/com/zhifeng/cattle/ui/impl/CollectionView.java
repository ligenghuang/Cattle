package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.CollectionListDto;

/**
  *
  * @ClassName:     关注
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 10:59
  * @Version:        1.0
 */

public interface CollectionView extends BaseView {

    /**
     * 获取关注列表
     */
    void getCollectionList();
    void getCollectionListSuccess(CollectionListDto collectionListDto);

    /**
     * 取消关注
     * @param goods_id
     */
    void deleteCollection(String goods_id);
    void deleteCollectionSuccess(String msg);
}

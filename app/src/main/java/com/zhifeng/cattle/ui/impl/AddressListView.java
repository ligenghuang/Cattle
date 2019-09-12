package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.AddressListDto;
import com.zhifeng.cattle.modules.GeneralDto;

/**
  *
  * @ClassName:     地址管理
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 15:41
  * @Version:        1.0
 */

public interface AddressListView extends BaseView {

    /**
     * 获取地址列表
     */
    void getAddressList();
    void getAddressListSuccess(AddressListDto addressListDto);

    /**
     * 删除地址
     */
    void deteleAddress(int id);
    void deteleAddressSuccess(GeneralDto generalDto);

    /**
     * 设置默认地址
     */
    void setDefaultAddress(int id);
    void setDefaultAddressSuccess(GeneralDto generalDto);
}

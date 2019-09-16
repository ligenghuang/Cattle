package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.AddressDetailDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.RegionDto;
import com.zhifeng.cattle.modules.post.AddOrEditAddressPost;

/**
 *
 * @ClassName:     添加或修改收货地址
 * @Description:
 * @Author:         lgh
 * @CreateDate:     2019/9/16 9:56
 * @Version:        1.0
 */
public interface AddAddressView extends BaseView {

    /**
     * 获取收货地址详情
     * @param id
     */
    void getAddress(int id);
    void getAddressSuccess(AddressDetailDto addressDetailDto);

    /**
     * 编辑添加地址
     * @param post
     */
    void addOrEditAddress(AddOrEditAddressPost post);
    void addOrEditAddressSuccess(GeneralDto generalDto);

    void getRegion(String code);
    void getRegionSuccess(RegionDto regionDto);
    void getRegionError();
}

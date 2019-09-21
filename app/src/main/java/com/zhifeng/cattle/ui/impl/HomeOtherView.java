package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.FoodDrinkDto;
import com.zhifeng.cattle.modules.HomeImportDto;

/**
 *
 * @ClassName:     首页进口货物和食品酒水
 * @Description:
 * @Author:         lgh
 * @CreateDate:     2019/9/21 11:01
 * @Version:        1.0
 */
public interface HomeOtherView extends BaseView {

    /**
     * 获取进口货物数据
     */
    void getImport();
    void getImportSuccess(HomeImportDto homeImportDto);

    /**
     * 获取食品酒水
     */
    void getFoodDrink();
    void getFoodDrinkSuccess(FoodDrinkDto foodDrinkDto);
}

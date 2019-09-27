package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.BindBankCardDto;

public interface BindBankCardView extends BaseView {
    void bindBankCard();

    void bindBankCardSuccess(BindBankCardDto bindBankCardDto);
}

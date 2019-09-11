package com.zhifeng.cattle.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.SalesReturnAction;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.ui.impl.SalesReturnView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

/**
  *
  * @ClassName:     退货
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 17:48
  * @Version:        1.0
 */

public class SalesReturnActivity extends UserBaseActivity<SalesReturnAction> implements SalesReturnView {

    @Override
    public int intiLayout() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_return);
    }

    @Override
    protected SalesReturnAction initAction() {
        return null;
    }

    @Override
    public void getSalesReturnList() {

    }

    @Override
    public void getSalesReturnListSucces(OrderListDto orderListDto) {

    }

    @Override
    public void onError(String message, int code) {

    }
}

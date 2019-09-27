package com.zhifeng.cattle.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.adapters.BankListAdapter;
import com.zhifeng.cattle.modules.BankImgListDto;
import com.zhifeng.cattle.modules.BankListDto;
import com.zhifeng.cattle.ui.my.BindBankCardActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
  *
  * @ClassName:     已绑定银行卡列表
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/27 12:21
  * @Version:        1.0
 */

public class BankListDialog extends Dialog {

    List<BankListDto.DataBean> list = new ArrayList<>();
    List<BankImgListDto.DataBean> dataBeanList = new ArrayList<>();
    @BindView(R.id.rv_bank_list)
    RecyclerView rvBankList;

    BankListAdapter adapter;
    Context context;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BankListDialog(@NonNull Context context, int themeResId, List<BankListDto.DataBean> list, List<BankImgListDto.DataBean> dataBeanList) {
        super(context, themeResId);
        this.context = context;
        this.dataBeanList = dataBeanList;
        this.list = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bank_list);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        Window win = this.getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        win.setGravity(Gravity.BOTTOM);

        initView();
    }

    private void initView() {
        adapter = new BankListAdapter(context);
        rvBankList.setLayoutManager(new LinearLayoutManager(context));
        rvBankList.setAdapter(adapter);
        adapter.setDataBeanList(dataBeanList);
        adapter.refresh(list);
        adapter.setOnClickListener(new BankListAdapter.OnClickListener() {
            @Override
            public void OnClcik(BankListDto.DataBean model) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setClick(model.getBank_card().equals(list.get(i).getBank_card()));
                }
                adapter.notifyDataSetChanged();
                onClickListener.OnClick(list,model);
                dismiss();
            }
        });
    }

    @OnClick(R.id.iv_add_bank)
    public void onViewClicked() {
        //todo 跳转至绑定银行卡页面
        Intent i=new Intent(context, BindBankCardActivity.class);
        context.startActivity(i);
        dismiss();
    }

    public interface OnClickListener {
        void OnClick(List<BankListDto.DataBean> list,BankListDto.DataBean model);
    }
}

package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class BankListDto {


    /**
     * status : 200
     * msg : 获取成功！
     * data : [{"bank_card":"46846465456","bank_name":"建行"},{"bank_card":"6225768655197620","bank_name":"中国农业银行"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bank_card : 46846465456
         * bank_name : 建行
         */

        private String bank_card;
        private String bank_name;
        private boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public String getBank_card() {
            return bank_card == null ? "" : bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card == null ? "" : bank_card;
        }

        public String getBank_name() {
            return bank_name == null ? "" : bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name == null ? "" : bank_name;
        }
    }
}

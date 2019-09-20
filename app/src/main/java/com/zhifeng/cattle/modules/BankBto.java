package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     银行卡列表实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/20 11:10
  * @Version:        1.0
 */

public class BankBto {


    /**
     * status : 200
     * msg : 获取成功！
     * data : {"bank_card":"62231545455454465456","bank_name":"工商银行"}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bank_card : 62231545455454465456
         * bank_name : 工商银行
         */

        private String bank_card;
        private String bank_name;

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

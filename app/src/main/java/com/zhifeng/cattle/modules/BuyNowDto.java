package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     立即购买接口返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/19 16:42
  * @Version:        1.0
 */

public class BuyNowDto {


    /**
     * status : 1
     * msg : 成功！
     * data : 1739
     */

    private int status;
    private String msg;
    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

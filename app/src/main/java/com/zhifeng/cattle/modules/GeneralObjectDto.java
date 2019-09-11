package com.zhifeng.cattle.modules;

/**
 * 通用实体类
 */
public class GeneralObjectDto {


    /**
     * status : 200
     * msg : success
     * data : 发送成功！
     */

    private int status;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

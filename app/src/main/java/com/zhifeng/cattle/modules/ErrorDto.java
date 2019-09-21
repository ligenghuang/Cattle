package com.zhifeng.cattle.modules;

import java.util.List;

public class ErrorDto {

    /**
     * data : []
     * msg : token已过期！
     * status : 999
     */

    private String msg;
    private int status;
    private List<?> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}

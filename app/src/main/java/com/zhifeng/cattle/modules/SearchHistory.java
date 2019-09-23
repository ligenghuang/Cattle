package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class SearchHistory {

    /**
     * status : 1
     * msg : 获取数据成功
     * data : [{"id":31,"keyword":"矿"},{"id":30,"keyword":"dc"},{"id":29,"keyword":"fsdaf"},{"id":28,"keyword":"123"},{"id":27,"keyword":"111"},{"id":24,"keyword":"小鸟"}]
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
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
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
         * id : 31
         * keyword : 矿
         */

        private int id;
        private String keyword;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword == null ? "" : keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword == null ? "" : keyword;
        }
    }
}

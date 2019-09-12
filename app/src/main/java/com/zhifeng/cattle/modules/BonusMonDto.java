package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

/**
  * 
  * @ClassName:
  * @Description:    
  * @Author:         lgh
  * @CreateDate:     2019/9/12 14:49
  * @Version:        1.0
 */

public class BonusMonDto {

    /**
     * status : 200
     * msg : 获取成功
     * data : [{"first_leader":27875,"realname":"默认昵称","num":6,"createtime":1568262113},{"first_leader":27894,"realname":"默认昵称","num":3,"createtime":1567578858},{"first_leader":27864,"realname":"默认昵称","num":1,"createtime":1568262113},{"first_leader":27873,"realname":"默认昵称","num":1,"createtime":1568262113},{"first_leader":27870,"realname":"默认昵称","num":1,"createtime":1568262113},{"first_leader":27867,"realname":"默认昵称","num":1,"createtime":1568262113}]
     * total : 5599.9.00
     */

    private int status;
    private String msg;
    private String total;
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

    public String getTotal() {
        return total == null ? "" : total;
    }

    public void setTotal(String total) {
        this.total = total == null ? "" : total;
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
         * first_leader : 27875
         * realname : 默认昵称
         * num : 6
         * createtime : 1568262113
         */

        private int first_leader;
        private String realname;
        private int num;
        private int createtime;

        public int getFirst_leader() {
            return first_leader;
        }

        public void setFirst_leader(int first_leader) {
            this.first_leader = first_leader;
        }

        public String getRealname() {
            return realname == null ? "" : realname;
        }

        public void setRealname(String realname) {
            this.realname = realname == null ? "" : realname;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }
    }
}

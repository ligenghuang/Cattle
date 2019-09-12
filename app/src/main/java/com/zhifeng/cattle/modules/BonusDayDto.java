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

public class BonusDayDto {

    /**
     * status : 200
     * msg : 获取成功
     * data : [{"first_leader":27875,"realname":"默认昵称","num":5},{"first_leader":27864,"realname":"默认昵称","num":1},{"first_leader":27873,"realname":"默认昵称","num":1}]
     * total : 0.00
     * lottery_time : 2019-09-13 00:00
     */

    private int status;
    private String msg;
    private String total;
    private String lottery_time;
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

    public String getLottery_time() {
        return lottery_time == null ? "" : lottery_time;
    }

    public void setLottery_time(String lottery_time) {
        this.lottery_time = lottery_time == null ? "" : lottery_time;
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
         * num : 5
         */

        private int first_leader;
        private String realname;
        private int num;

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
    }
}

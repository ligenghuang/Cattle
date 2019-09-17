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
     * data : [{"first_leader":27875,"realname":"默认昵称","mobile":"18665679407","num":6,"No":1},{"first_leader":27869,"realname":"默认昵称","mobile":"13286971460","num":1,"No":2},{"first_leader":27867,"realname":"默认昵称","mobile":"17875592622","num":1,"No":3},{"first_leader":27874,"realname":"默认昵称","mobile":"18688677754","num":1,"No":4},{"first_leader":27761,"realname":"默认昵称","mobile":"15089607528","num":1,"No":5},{"first_leader":27872,"realname":"默认昵称","mobile":"15279401908","num":1,"No":6},{"first_leader":27870,"realname":"默认昵称","mobile":"13719364739","num":1,"No":7},{"first_leader":27868,"realname":"默认昵称","mobile":"13178489831","num":1,"No":8},{"first_leader":27864,"realname":"默认昵称","mobile":"15024070309","num":1,"No":9},{"first_leader":27873,"realname":"默认昵称","mobile":"13763004669","num":1,"No":10}]
     * total : 938.00
     * lottery_time : 2019-09-18 00:00
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
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
    }

    public String getTotal() {
        return total == null ? "" : total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLottery_time() {
        return lottery_time == null ? "" : lottery_time;
    }

    public void setLottery_time(String lottery_time) {
        this.lottery_time = lottery_time ;
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
         * mobile : 18665679407
         * num : 6
         * No : 1
         */

        private int first_leader;
        private String realname;
        private String mobile;
        private int num;
        private int No;

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

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile == null ? "" : mobile;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getNo() {
            return No;
        }

        public void setNo(int no) {
            No = no;
        }
    }
}

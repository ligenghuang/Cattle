package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     我的团队接口返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 12:26
  * @Version:        1.0
 */

public class MyTeamDto {

    /**
     * status : 200
     * msg : success
     * data : {"id":27924,"realname":"默认昵称","estimate_money":0,"distribut_money":"30383.50","reward":1258549348,"first_leader":27923,"first_leadername":null,"team_count":14}
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
         * id : 27924
         * realname : 默认昵称
         * estimate_money : 0
         * distribut_money : 30383.50
         * reward : 1258549348
         * first_leader : 27923
         * first_leadername : null
         * team_count : 14
         */

        private int id;
        private String realname;
        private int estimate_money;
        private String distribut_money;
        private int reward;
        private int first_leader;
        private String first_leadername;
        private int team_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRealname() {
            return realname == null ? "" : realname;
        }

        public void setRealname(String realname) {
            this.realname = realname == null ? "" : realname;
        }

        public int getEstimate_money() {
            return estimate_money;
        }

        public void setEstimate_money(int estimate_money) {
            this.estimate_money = estimate_money;
        }

        public String getDistribut_money() {
            return distribut_money == null ? "" : distribut_money;
        }

        public void setDistribut_money(String distribut_money) {
            this.distribut_money = distribut_money == null ? "" : distribut_money;
        }

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public int getFirst_leader() {
            return first_leader;
        }

        public void setFirst_leader(int first_leader) {
            this.first_leader = first_leader;
        }

        public String getFirst_leadername() {
            return first_leadername == null ? "" : first_leadername;
        }

        public void setFirst_leadername(String first_leadername) {
            this.first_leadername = first_leadername == null ? "" : first_leadername;
        }

        public int getTeam_count() {
            return team_count;
        }

        public void setTeam_count(int team_count) {
            this.team_count = team_count;
        }
    }
}

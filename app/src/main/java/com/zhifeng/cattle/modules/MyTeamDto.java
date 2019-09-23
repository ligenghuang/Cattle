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
     * msg : 成功！
     * data : {"id":27760,"realname":"默认昵称","first_leader":27761,"first_leader_name":"默认昵称","performance":null,"reward":116.64,"team_number":1}
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
         * id : 27760
         * realname : 默认昵称
         * first_leader : 27761
         * first_leader_name : 默认昵称
         * performance : null
         * reward : 116.64
         * team_number : 1
         */

        private int id;
        private String realname;
        private int first_leader;
        private String first_leadername;
        private String performance;
        private double reward;
        private int team_number;

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

        public int getFirst_leader() {
            return first_leader;
        }

        public void setFirst_leader(int first_leader) {
            this.first_leader = first_leader;
        }

        public String getFirst_leader_name() {
            return first_leadername == null ? "" : first_leadername;
        }

        public void setFirst_leader_name(String first_leader_name) {
            this.first_leadername = first_leader_name == null ? "" : first_leader_name;
        }

        public String getPerformance() {
            return performance == null ? "0.00" : performance;
        }

        public void setPerformance(String performance) {
            this.performance = performance == null ? "" : performance;
        }

        public double getReward() {
            return reward;
        }

        public void setReward(double reward) {
            this.reward = reward;
        }

        public int getTeam_number() {
            return team_number;
        }

        public void setTeam_number(int team_number) {
            this.team_number = team_number;
        }
    }
}

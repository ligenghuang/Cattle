package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     用户信息实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 18:44
  * @Version:        1.0
 */

public class UserInfoDto {


    /**
     * status : 200
     * msg : success
     * data : {"realname":"默认昵称","mobile":"13696325874","id":27894,"remainder_money":"0.00000000","distribut_money":"0.00000000","createtime":1568100291,"avatar":"http://zf8020.com/static/images/headimg/20190711156280864771502.png","level":3,"estimate_money":0,"refund":0,"not_pay":0,"not_delivery":0,"not_receiving":0,"not_evaluate":0,"collection":0,"levelname":"水牛","today_rec":2,"team_count":0,"month":12,"day":6}
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
         * realname : 默认昵称
         * mobile : 13696325874
         * id : 27894
         * remainder_money : 0.00000000
         * distribut_money : 0.00000000
         * createtime : 1568100291
         * avatar : http://zf8020.com/static/images/headimg/20190711156280864771502.png
         * level : 3
         * estimate_money : 0
         * refund : 0
         * not_pay : 0
         * not_delivery : 0
         * not_receiving : 0
         * not_evaluate : 0
         * collection : 0
         * levelname : 水牛
         * today_rec : 2
         * team_count : 0
         * month : 12
         * day : 6
         */

        private String realname;
        private String mobile;
        private int id;
        private String remainder_money;
        private String distribut_money;
        private int createtime;
        private String avatar;
        private int level;
        private int estimate_money;
        private int refund;
        private int not_pay;
        private int not_delivery;
        private int not_receiving;
        private int not_evaluate;
        private int collection;
        private String levelname;
        private int today_rec;
        private int team_count;
        private double month;
        private double day;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRemainder_money() {
            return remainder_money == null ? "0" : remainder_money;
        }

        public void setRemainder_money(String remainder_money) {
            this.remainder_money = remainder_money == null ? "" : remainder_money;
        }

        public String getDistribut_money() {
            return distribut_money == null ? "0" : distribut_money;
        }

        public void setDistribut_money(String distribut_money) {
            this.distribut_money = distribut_money == null ? "" : distribut_money;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getAvatar() {
            return avatar == null ? "" : avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar == null ? "" : avatar;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getEstimate_money() {
            return estimate_money;
        }

        public void setEstimate_money(int estimate_money) {
            this.estimate_money = estimate_money;
        }

        public int getRefund() {
            return refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }

        public int getNot_pay() {
            return not_pay;
        }

        public void setNot_pay(int not_pay) {
            this.not_pay = not_pay;
        }

        public int getNot_delivery() {
            return not_delivery;
        }

        public void setNot_delivery(int not_delivery) {
            this.not_delivery = not_delivery;
        }

        public int getNot_receiving() {
            return not_receiving;
        }

        public void setNot_receiving(int not_receiving) {
            this.not_receiving = not_receiving;
        }

        public int getNot_evaluate() {
            return not_evaluate;
        }

        public void setNot_evaluate(int not_evaluate) {
            this.not_evaluate = not_evaluate;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public String getLevelname() {
            return levelname == null ? "" : levelname;
        }

        public void setLevelname(String levelname) {
            this.levelname = levelname == null ? "" : levelname;
        }

        public int getToday_rec() {
            return today_rec;
        }

        public void setToday_rec(int today_rec) {
            this.today_rec = today_rec;
        }

        public int getTeam_count() {
            return team_count;
        }

        public void setTeam_count(int team_count) {
            this.team_count = team_count;
        }

        public double getMonth() {
            return month;
        }

        public void setMonth(double month) {
            this.month = month;
        }

        public double getDay() {
            return day;
        }

        public void setDay(double day) {
            this.day = day;
        }
    }
}

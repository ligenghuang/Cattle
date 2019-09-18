package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class RankingList {

    /**
     * status : 200
     * msg : 获取成功
     * data : [{"user_id":27896,"avatar":"http://zf8020.com/static/images/headimg/20190711156280864771502.png","realname":"默认昵称","total":"3002.00","different_last":0,"No":1},{"user_id":27864,"avatar":"http://new_retail_test.zhifengwangluo.com/upload/images/tou/20190729156439615037925.png","realname":"默认昵称","total":"2900.00","different_last":102,"No":2},{"user_id":27759,"avatar":"http://new_retail_test.zhifengwangluo.com/static/images/headimg/20190711156280864771502.png","realname":"默认昵称","total":"2400.00","different_last":500,"No":3},{"user_id":27871,"avatar":"http://newretailwebtest.zhifengwangluo.com/static/images/headimg/20190711156280864771502.png","realname":"默认昵称","total":"1509.90","different_last":890.1,"No":4},{"user_id":27872,"avatar":"http://newretailwebtest.zhifengwangluo.com/static/images/headimg/20190711156280864771502.png","realname":"默认昵称","total":"1509.90","different_last":0,"No":5},{"user_id":27873,"avatar":"http://newretailwebtest.zhifengwangluo.com/static/images/headimg/20190711156280864771502.png","realname":"默认昵称","total":"1509.90","different_last":0,"No":6},{"user_id":27876,"avatar":"http://zf8020.com/public/idcard_pic/20190831156721940096313.png","realname":"默认昵称","total":"601.00","different_last":908.9,"No":7},{"user_id":27891,"avatar":"http://zf8020.com/static/images/headimg/20190711156280864771502.png","realname":"默认昵称","total":"501.00","different_last":100,"No":8}]
     * my_ranking : 1
     */

    private int status;
    private String msg;
    private int my_ranking;
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

    public int getMy_ranking() {
        return my_ranking;
    }

    public void setMy_ranking(int my_ranking) {
        this.my_ranking = my_ranking;
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
         * user_id : 27896
         * avatar : http://zf8020.com/static/images/headimg/20190711156280864771502.png
         * realname : 默认昵称
         * total : 3002.00
         * different_last : 0
         * No : 1
         */

        private int user_id;
        private String avatar;
        private String realname;
        private String total;
        private double different_last;
        private int No;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAvatar() {
            return avatar == null ? "" : avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar == null ? "" : avatar;
        }

        public String getRealname() {
            return realname == null ? "" : realname;
        }

        public void setRealname(String realname) {
            this.realname = realname == null ? "" : realname;
        }

        public String getTotal() {
            return total == null ? "" : total;
        }

        public void setTotal(String total) {
            this.total = total == null ? "" : total;
        }

        public double getDifferent_last() {
            return different_last;
        }

        public void setDifferent_last(double different_last) {
            this.different_last = different_last;
        }

        public int getNo() {
            return No;
        }

        public void setNo(int no) {
            No = no;
        }
    }
}

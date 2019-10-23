package com.zhifeng.cattle.modules;

public class WxLoginDto {

    /**
     * status : 200
     * msg : success
     * data : {"openid":"openid2","nickname":"nicheng","sex":"2","province":"13698547123","city":"3541354","country":"1222222","headimgurl":"http","privilege":"","unionid":"","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEQyIsImlhdCI6MTU3MTgyNDU0MiwiZXhwIjoxNTcxODYwNTQyLCJ1c2VyX2lkIjoiMjgwMjkifQ.GWZC0JgSK9JUfYpgUTSL-eNR9cxZ0FXly9n-lW_sObU","mobile":0,"id":"28029","is_first":1}
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
         * openid : openid2
         * nickname : nicheng
         * sex : 2
         * province : 13698547123
         * city : 3541354
         * country : 1222222
         * headimgurl : http
         * privilege :
         * unionid :
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEQyIsImlhdCI6MTU3MTgyNDU0MiwiZXhwIjoxNTcxODYwNTQyLCJ1c2VyX2lkIjoiMjgwMjkifQ.GWZC0JgSK9JUfYpgUTSL-eNR9cxZ0FXly9n-lW_sObU
         * mobile : 0
         * id : 28029
         * is_first : 1
         */

        private String openid;
        private String nickname;
        private String sex;
        private String province;
        private String city;
        private String country;
        private String headimgurl;
        private String privilege;
        private String unionid;
        private String token;
        private String mobile;
        private String id;
        private int is_first;

        public String getOpenid() {
            return openid == null ? "" : openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid == null ? "" : openid;
        }

        public String getNickname() {
            return nickname == null ? "" : nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname == null ? "" : nickname;
        }

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex == null ? "" : sex;
        }

        public String getProvince() {
            return province == null ? "" : province;
        }

        public void setProvince(String province) {
            this.province = province == null ? "" : province;
        }

        public String getCity() {
            return city == null ? "" : city;
        }

        public void setCity(String city) {
            this.city = city == null ? "" : city;
        }

        public String getCountry() {
            return country == null ? "" : country;
        }

        public void setCountry(String country) {
            this.country = country == null ? "" : country;
        }

        public String getHeadimgurl() {
            return headimgurl == null ? "" : headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl == null ? "" : headimgurl;
        }

        public String getPrivilege() {
            return privilege == null ? "" : privilege;
        }

        public void setPrivilege(String privilege) {
            this.privilege = privilege == null ? "" : privilege;
        }

        public String getUnionid() {
            return unionid == null ? "" : unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid == null ? "" : unionid;
        }

        public String getToken() {
            return token == null ? "" : token;
        }

        public void setToken(String token) {
            this.token = token == null ? "" : token;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile == null ? "" : mobile;
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id == null ? "" : id;
        }

        public int getIs_first() {
            return is_first;
        }

        public void setIs_first(int is_first) {
            this.is_first = is_first;
        }
    }
}

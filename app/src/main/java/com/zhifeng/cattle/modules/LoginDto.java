package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     登录注册返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/9 15:33
  * @Version:        1.0
 */

public class LoginDto {

    /**
     * status : 200
     * msg : success
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEQyIsImlhdCI6MTU2NTY3NjM5MSwiZXhwIjoxNTY1NzEyMzkxLCJ1c2VyX2lkIjoiMjc4ODEifQ.-QzQVrEzoSlF8NLN--EdSX4d5iKSrEbZVW9HMQep48w","mobile":"13569854123","id":"27881"}
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
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEQyIsImlhdCI6MTU2NTY3NjM5MSwiZXhwIjoxNTY1NzEyMzkxLCJ1c2VyX2lkIjoiMjc4ODEifQ.-QzQVrEzoSlF8NLN--EdSX4d5iKSrEbZVW9HMQep48w
         * mobile : 13569854123
         * id : 27881
         */

        private String token;
        private String mobile;
        private String id;

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
    }
}

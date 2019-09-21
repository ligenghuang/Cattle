package com.zhifeng.cattle.modules;

public class SharePoster {

    /**
     * status : 200
     * msg : success
     * data : {"realname":"默认昵称","avatar":"http://zf8020.com/static/images/headimg/20190711156280864771502.png","mobile":"13696325274","invitation_code":27896,"qrcode":"http://zf8020.com/public/shareposter/27896-poster.png"}
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
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
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
         * avatar : http://zf8020.com/static/images/headimg/20190711156280864771502.png
         * mobile : 13696325274
         * invitation_code : 27896
         * qrcode : http://zf8020.com/public/shareposter/27896-poster.png
         */

        private String realname;
        private String avatar;
        private String mobile;
        private int invitation_code;
        private String qrcode;

        public String getRealname() {
            return realname == null ? "" : realname;
        }

        public void setRealname(String realname) {
            this.realname = realname == null ? "" : realname;
        }

        public String getAvatar() {
            return avatar == null ? "" : avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar == null ? "" : avatar;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile == null ? "" : mobile;
        }

        public int getInvitation_code() {
            return invitation_code;
        }

        public void setInvitation_code(int invitation_code) {
            this.invitation_code = invitation_code;
        }

        public String getQrcode() {
            return qrcode == null ? "" : qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode == null ? "" : qrcode;
        }
    }
}

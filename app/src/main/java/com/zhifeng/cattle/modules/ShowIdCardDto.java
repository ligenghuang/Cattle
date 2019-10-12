package com.zhifeng.cattle.modules;

public class ShowIdCardDto {

    /**
     * status : 200
     * msg : success
     * data : {"pic_front":"http://cattle.zhifengwangluo.com/public\\upload\\images/public/idcard_pic/20191012157084644939387.png","pic_back":"http://cattle.zhifengwangluo.com/public\\upload\\images/public/idcard_pic/2019101215708464491261.png","name":"陈陈陈","idcard":"450325541223061239"}
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
         * pic_front :
         * pic_back :
         * name : 陈陈陈
         * idcard : 450325541223061239
         */

        private String pic_front;
        private String pic_back;
        private String name;
        private String idcard;

        public String getPic_front() {
            return pic_front == null ? "" : pic_front;
        }

        public void setPic_front(String pic_front) {
            this.pic_front = pic_front == null ? "" : pic_front;
        }

        public String getPic_back() {
            return pic_back == null ? "" : pic_back;
        }

        public void setPic_back(String pic_back) {
            this.pic_back = pic_back == null ? "" : pic_back;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name == null ? "" : name;
        }

        public String getIdcard() {
            return idcard == null ? "" : idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard == null ? "" : idcard;
        }
    }
}

package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     认证成功 返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/20 14:42
  * @Version:        1.0
 */

public class CertificationDto {


    /**
     * status : 1
     * msg : 上传成功
     * data : {"pic_front":"http://zf8020.com/public/idcard_pic/20190831156722013723019.png","pic_back":"http://zf8020.com/public/idcard_pic/2019083115672201372569.png","idcard":"456465465","name":"的"}
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
         * pic_front : http://zf8020.com/public/idcard_pic/20190831156722013723019.png
         * pic_back : http://zf8020.com/public/idcard_pic/2019083115672201372569.png
         * idcard : 456465465
         * name : 的
         */

        private String pic_front;
        private String pic_back;
        private String idcard;
        private String name;

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

        public String getIdcard() {
            return idcard == null ? "" : idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard == null ? "" : idcard;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name == null ? "" : name;
        }
    }
}

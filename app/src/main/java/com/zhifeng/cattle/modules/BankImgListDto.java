package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class BankImgListDto {

    /**
     * status : 200
     * msg : 获取成功！
     * data : [{"name":"中国建设银行","img":"http://zf8020.com/public/uploads/images-out/CCB_OUT.gif"},{"name":"交通银行","img":"http://zf8020.com/public/uploads/images-out/COMM_OUT.gif"},{"name":"中国农业银行","img":"http://zf8020.com/public/uploads/images-out/ABC_OUT.gif"},{"name":"中信银行","img":"http://zf8020.com/public/uploads/images-out/CITIC_OUT.gif"},{"name":"中国银行","img":"http://zf8020.com/public/uploads/images-out/BOC_OUT.gif"},{"name":"广发银行","img":"http://zf8020.com/public/uploads/images-out/GDB_OUT.gif"},{"name":"兴业银行","img":"http://zf8020.com/public/uploads/images-out/CIB_OUT.gif"},{"name":"中国邮政储蓄银行","img":"http://zf8020.com/public/uploads/images-out/PSBC_OUT.gif"},{"name":"中国光大银行","img":"http://zf8020.com/public/uploads/images-out/CEB_OUT.gif"},{"name":"平安银行","img":"http://zf8020.com/public/uploads/images-out/SPABANK_OUT.gif"},{"name":"杭州银行","img":"http://zf8020.com/public/uploads/images-out/HZCB_OUT.gif"},{"name":"中国民生银行","img":"http://zf8020.com/public/uploads/images-out/CMBC_OUT.gif"},{"name":"北京农商银行","img":"http://zf8020.com/public/uploads/images-out/BJRCB_OUT.gif"},{"name":"招商银行","img":"http://zf8020.com/public/uploads/images-out/to_cmb.jpg"},{"name":"中国工商银行","img":"http://zf8020.com/public/uploads/images-out/to_icbc.jpg"},{"name":"上海银行","img":"http://zf8020.com/public/uploads/images-out/to_bos.jpg"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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
         * name : 中国建设银行
         * img : http://zf8020.com/public/uploads/images-out/CCB_OUT.gif
         */

        private String name;
        private String img;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name == null ? "" : name;
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public void setImg(String img) {
            this.img = img == null ? "" : img;
        }
    }
}

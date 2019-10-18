package com.zhifeng.cattle.modules;

import java.util.List;

public class CommentSuccessDto {

    /**
     * status : 200
     * msg : 成功！
     * data : [{"content":"fff","descrie":"1","goods_id":92,"img":[],"logistics":"1","order_id":3585,"service":"1","sku_id":94,"user_id":27964,"add_time":1571372953}]
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
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : fff
         * descrie : 1
         * goods_id : 92
         * img : []
         * logistics : 1
         * order_id : 3585
         * service : 1
         * sku_id : 94
         * user_id : 27964
         * add_time : 1571372953
         */

        private String content;
        private String descrie;
        private int goods_id;
        private String logistics;
        private int order_id;
        private String service;
        private int sku_id;
        private int user_id;
        private int add_time;
        private List<?> img;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescrie() {
            return descrie;
        }

        public void setDescrie(String descrie) {
            this.descrie = descrie;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getLogistics() {
            return logistics;
        }

        public void setLogistics(String logistics) {
            this.logistics = logistics;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public List<?> getImg() {
            return img;
        }

        public void setImg(List<?> img) {
            this.img = img;
        }
    }
}

package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ClassName:     关注列表返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 11:00
  * @Version:        1.0
 */

public class CollectionListDto {

    /**
     * status : 200
     * msg : 成功！
     * data : [{"goods_id":40,"goods_name":"礼品商品","price":"500.00","img":"http://local.ldp2.com/upload/images/goods/2019071015627391512588.png"}]
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
         * goods_id : 40
         * goods_name : 礼品商品
         * price : 500.00
         * img : http://local.ldp2.com/upload/images/goods/2019071015627391512588.png
         */

        private int goods_id;
        private String goods_name;
        private String price;
        private String img;
        private boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name == null ? "" : goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name == null ? "" : goods_name;
        }

        public String getPrice() {
            return price == null ? "0" : price;
        }

        public void setPrice(String price) {
            this.price = price == null ? "" : price;
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public void setImg(String img) {
            this.img = img == null ? "" : img;
        }
    }
}

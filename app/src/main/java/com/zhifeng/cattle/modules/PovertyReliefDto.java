package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class PovertyReliefDto {


    /**
     * status : 200
     * msg : 获取成功
     * data : {"catenav1":[{"cat_id":1,"cat_name":"推荐"},{"cat_id":5,"cat_name":"兴农扶贫"},{"cat_id":9,"cat_name":"食品酒水"},{"cat_id":13,"cat_name":"进口货物"}],"banners":[{"picture":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190904\\a386819162ebd483bb791b8da346d4c3.png","title":"测试","url":"www.baidu.com"},{"picture":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\c82117917fe5e75788457f676cd0aa90.jpeg","title":"测试1","url":"www.baidu.com"},{"picture":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190904\\38f8199bea0c3ad5d091d181815f8396.png","title":"测试33","url":"www.baidu.com"}],"catenav2":[{"cat_id":28,"cat_name":"时令水果","img":"http://zf8020.com/public/upload/images/category/20190919156887424731884.png"},{"cat_id":38,"cat_name":"肉禽蛋类","img":"http://zf8020.com/public/upload/images/category/2019091915688743636942.png"},{"cat_id":39,"cat_name":"新鲜蔬菜","img":"http://zf8020.com/public/upload/images/category/20190919156887437928833.png"},{"cat_id":40,"cat_name":"粮油调味","img":"http://zf8020.com/public/upload/images/category/20190919156887440242617.png"},{"cat_id":41,"cat_name":"南北干货","img":"http://zf8020.com/public/upload/images/category/20190919156887441580438.png"},{"cat_id":42,"cat_name":"海鲜水产","img":"http://zf8020.com/public/upload/images/category/20190919156887443726867.png"},{"cat_id":43,"cat_name":"乳品烘焙","img":"http://zf8020.com/public/upload/images/category/20190919156887446865664.png"},{"cat_id":44,"cat_name":"粮食面点","img":"http://zf8020.com/public/upload/images/category/20190919156887449291726.png"}],"selfnav":[{"title":"肉类","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\3cf7db22e1b39891bc825cfa73f1275d.jpeg","url":"https://www.baidu.com/"},{"title":"电脑","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190907\\e9e0bd637752403b0a1a11bd33684ad8.png","url":"https://www.baidu.com/"},{"title":"测试33","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190907\\c23fa611f3722e1e633214c9da225acc.png","url":"www.baidu.com"},{"title":"测试","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\3c0d927f9211cd9a2a87b424eaaf84d0.png","url":"www.baidu.com"}],"youxuan_goods":[{"id":7,"title":"商品1","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\96505c2def598697de1f16db6fd7b55f.jpeg","status":1,"create_time":1568881912,"url":"www.baidu.com","location":2},{"id":8,"title":"测试商品2","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\bf23d641d747e62723c270743e5c0299.jpeg","status":1,"create_time":1568882050,"url":"www.baidu.com","location":2}],"like":[{"goods_id":72,"goods_name":"商务保温杯","img":"http://zf8020.com/public/upload/images/goods/20190716156324016790465.png","desc":"","price":"500.00","original_price":"0.00"},{"goods_id":45,"goods_name":"礼品专区商品1","img":"http://zf8020.com/public/upload/images/goods/20190710156274057499262.png","desc":"神秘礼物","price":"500.00","original_price":"0.00"},{"goods_id":40,"goods_name":"礼品商品","img":"http://zf8020.com/public/upload/images/goods/2019071015627391512588.png","desc":"","price":"500.00","original_price":"500.00"}]}
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
        private List<Catenav1Bean> catenav1;
        private List<BannersBean> banners;
        private List<Catenav2Bean> catenav2;
        private List<SelfnavBean> selfnav;
        private List<SpreeDto> youxuan_goods;
        private List<LikeBean> like;

        public List<Catenav1Bean> getCatenav1() {
            if (catenav1 == null) {
                return new ArrayList<>();
            }
            return catenav1;
        }

        public void setCatenav1(List<Catenav1Bean> catenav1) {
            this.catenav1 = catenav1;
        }

        public List<BannersBean> getBanners() {
            if (banners == null) {
                return new ArrayList<>();
            }
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public List<Catenav2Bean> getCatenav2() {
            if (catenav2 == null) {
                return new ArrayList<>();
            }
            return catenav2;
        }

        public void setCatenav2(List<Catenav2Bean> catenav2) {
            this.catenav2 = catenav2;
        }

        public List<SelfnavBean> getSelfnav() {
            if (selfnav == null) {
                return new ArrayList<>();
            }
            return selfnav;
        }

        public void setSelfnav(List<SelfnavBean> selfnav) {
            this.selfnav = selfnav;
        }

        public List<SpreeDto> getYouxuan_goods() {
            if (youxuan_goods == null) {
                return new ArrayList<>();
            }
            return youxuan_goods;
        }

        public void setYouxuan_goods(List<SpreeDto> youxuan_goods) {
            this.youxuan_goods = youxuan_goods;
        }

        public List<LikeBean> getLike() {
            if (like == null) {
                return new ArrayList<>();
            }
            return like;
        }

        public void setLike(List<LikeBean> like) {
            this.like = like;
        }

        public static class Catenav1Bean {
            /**
             * cat_id : 1
             * cat_name : 推荐
             */

            private int cat_id;
            private String cat_name;

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name() {
                return cat_name == null ? "" : cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name == null ? "" : cat_name;
            }
        }

        public static class BannersBean {
            /**
             * picture :
             * title : 测试
             * url : www.baidu.com
             */

            private String picture;
            private String title;
            private String url;

            public String getPicture() {
                return picture == null ? "" : picture;
            }

            public void setPicture(String picture) {
                this.picture = picture == null ? "" : picture;
            }

            public String getTitle() {
                return title == null ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title == null ? "" : title;
            }

            public String getUrl() {
                return url == null ? "" : url;
            }

            public void setUrl(String url) {
                this.url = url == null ? "" : url;
            }
        }

        public static class SelfnavBean {
            /**
             * title : 肉类
             * image :
             * url : https://www.baidu.com/
             */

            private String title;
            private String image;
            private String url;

            public String getTitle() {
                return title == null ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title == null ? "" : title;
            }

            public String getImage() {
                return image == null ? "" : image;
            }

            public void setImage(String image) {
                this.image = image == null ? "" : image;
            }

            public String getUrl() {
                return url == null ? "" : url;
            }

            public void setUrl(String url) {
                this.url = url == null ? "" : url;
            }
        }

        public static class LikeBean {
            /**
             * goods_id : 72
             * goods_name : 商务保温杯
             * img : http://zf8020.com/public/upload/images/goods/20190716156324016790465.png
             * desc :
             * price : 500.00
             * original_price : 0.00
             */

            private int goods_id;
            private String goods_name;
            private String img;
            private String desc;
            private String price;
            private String original_price;

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

            public String getImg() {
                return img == null ? "" : img;
            }

            public void setImg(String img) {
                this.img = img == null ? "" : img;
            }

            public String getDesc() {
                return desc == null ? "" : desc;
            }

            public void setDesc(String desc) {
                this.desc = desc == null ? "" : desc;
            }

            public String getPrice() {
                return price == null ? "" : price;
            }

            public void setPrice(String price) {
                this.price = price == null ? "" : price;
            }

            public String getOriginal_price() {
                return original_price == null ? "" : original_price;
            }

            public void setOriginal_price(String original_price) {
                this.original_price = original_price == null ? "" : original_price;
            }
        }
    }
}

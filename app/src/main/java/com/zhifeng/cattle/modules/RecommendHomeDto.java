package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class RecommendHomeDto {

    /**
     * status : 200
     * msg : 获取成功
     * data : {"catenav1":[{"cat_id":1,"cat_name":"推荐"},{"cat_id":5,"cat_name":"兴农扶贫"},{"cat_id":9,"cat_name":"食品酒水"},{"cat_id":13,"cat_name":"进口货物"}],"banners":[{"picture":"http://zf8020.com/public\\uploads\\fixed_picture\\20190904\\a386819162ebd483bb791b8da346d4c3.png","title":"测试","url":"www.baidu.com"}],"catenav2":[{"cat_id":27,"cat_name":"11111","img":"http://zf8020.com/public/upload/images/category/20190919156887540392102.png"},{"cat_id":30,"cat_name":"22222","img":"http://zf8020.com/public/upload/images/"},{"cat_id":31,"cat_name":"太热","img":"http://zf8020.com/public/upload/images/"},{"cat_id":32,"cat_name":"5","img":"http://zf8020.com/public/upload/images/"},{"cat_id":33,"cat_name":"7","img":"http://zf8020.com/public/upload/images/"},{"cat_id":34,"cat_name":"22222","img":"http://zf8020.com/public/upload/images/"},{"cat_id":35,"cat_name":"太热1","img":"http://zf8020.com/public/upload/images/"},{"cat_id":36,"cat_name":"hj0","img":"http://zf8020.com/public/upload/images/"}],"buy_now":[{"goods_id":75,"goods_name":"企业版 矿机","img":"http://zf8020.com/public/upload/images/goods/20190803156482339365585.png","desc":"","price":"19900.00","original_price":"0.00"},{"goods_id":74,"goods_name":"小鸟鸟","img":"http://zf8020.com/public/upload/images/goods/20190717156332971828319.png","desc":"","price":"1.00","original_price":"0.00"},{"goods_id":73,"goods_name":"444","img":"http://zf8020.com/public/upload/images/goods/20190716156324887558693.png","desc":"","price":"0.00","original_price":"0.00"}],"youxuan_goods":[{"id":1,"title":"苹果","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\c395b1a63fe5be1738862c403262612c.jpeg","status":1,"create_time":1569664,"url":"https://www.baidu.com/","location":1},{"id":3,"title":"女鞋","image":"http://zf8020.com/public//uploads/fixed_picture/20190710/6a08bac0afec27c32e435f1e65550c89.jpeg","status":1,"create_time":1562407536,"url":"https://www.baidu.com/","location":1},{"id":4,"title":"玩具","image":"http://zf8020.com/public//uploads/fixed_picture/20190710/01f90438dc9d73809022ba67446b835f.jpeg","status":1,"create_time":1562407796,"url":"https://www.baidu.com/","location":1},{"id":5,"title":"测试33","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190907\\c23fa611f3722e1e633214c9da225acc.png","status":1,"create_time":1567827831,"url":"www.baidu.com","location":1}]}
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
        private List<BuyNowBean> buy_now;
        private List<YouxuanGoodsBean> youxuan_goods;

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

        public List<BuyNowBean> getBuy_now() {
            if (buy_now == null) {
                return new ArrayList<>();
            }
            return buy_now;
        }

        public void setBuy_now(List<BuyNowBean> buy_now) {
            this.buy_now = buy_now;
        }

        public List<YouxuanGoodsBean> getYouxuan_goods() {
            if (youxuan_goods == null) {
                return new ArrayList<>();
            }
            return youxuan_goods;
        }

        public void setYouxuan_goods(List<YouxuanGoodsBean> youxuan_goods) {
            this.youxuan_goods = youxuan_goods;
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
             * picture : http://zf8020.com/public\uploads\fixed_picture\20190904\a386819162ebd483bb791b8da346d4c3.png
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

        public static class Catenav2Bean {
            /**
             * cat_id : 27
             * cat_name : 11111
             * img : http://zf8020.com/public/upload/images/category/20190919156887540392102.png
             */

            private int cat_id;
            private String cat_name;
            private String img;

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

            public String getImg() {
                return img == null ? "" : img;
            }

            public void setImg(String img) {
                this.img = img == null ? "" : img;
            }
        }

        public static class BuyNowBean {
            /**
             * goods_id : 75
             * goods_name : 企业版 矿机
             * img : http://zf8020.com/public/upload/images/goods/20190803156482339365585.png
             * desc :
             * price : 19900.00
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

        public static class YouxuanGoodsBean {
            /**
             * id : 1
             * title : 苹果
             * image : http://zf8020.com/public/\uploads\fixed_picture\20190919\c395b1a63fe5be1738862c403262612c.jpeg
             * status : 1
             * create_time : 1569664
             * url : https://www.baidu.com/
             * location : 1
             */

            private int id;
            private String title;
            private String image;
            private int status;
            private int create_time;
            private String url;
            private int location;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getUrl() {
                return url == null ? "" : url;
            }

            public void setUrl(String url) {
                this.url = url == null ? "" : url;
            }

            public int getLocation() {
                return location;
            }

            public void setLocation(int location) {
                this.location = location;
            }
        }
    }
}

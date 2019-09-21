package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkDto {

    /**
     * status : 200
     * msg : 获取成功
     * data : {"catenav1":[{"cat_id":1,"cat_name":"推荐"},{"cat_id":5,"cat_name":"兴农扶贫"},{"cat_id":9,"cat_name":"食品酒水"},{"cat_id":13,"cat_name":"进口货物"}],"banners":[{"picture":"http://zf8020.com/public\\uploads\\fixed_picture\\20190920\\cada82fd1f363ced7d158ed605ba6de0.jpeg","title":"进口测试","url":"www.baidu.com"}],"catenav2":[{"cat_id":46,"cat_name":"发","img":"http://zf8020.com/public/upload/images/"},{"cat_id":47,"cat_name":"特送","img":"http://zf8020.com/public/upload/images/"},{"cat_id":48,"cat_name":"热污染","img":"http://zf8020.com/public/upload/images/"},{"cat_id":49,"cat_name":"任务书若翁","img":"http://zf8020.com/public/upload/images/"},{"cat_id":50,"cat_name":"让我若","img":"http://zf8020.com/public/upload/images/"},{"cat_id":51,"cat_name":"绕弯儿","img":"http://zf8020.com/public/upload/images/"},{"cat_id":52,"cat_name":"热恶","img":"http://zf8020.com/public/upload/images/"},{"cat_id":53,"cat_name":"套听","img":"http://zf8020.com/public/upload/images/"}],"selfnav":[{"title":"肉类","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\3cf7db22e1b39891bc825cfa73f1275d.jpeg","url":"https://www.baidu.com/"},{"title":"电脑","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190907\\e9e0bd637752403b0a1a11bd33684ad8.png","url":"https://www.baidu.com/"},{"title":"测试33","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190907\\c23fa611f3722e1e633214c9da225acc.png","url":"www.baidu.com"},{"title":"测试","image":"http://zf8020.com/public/\\uploads\\fixed_picture\\20190919\\3c0d927f9211cd9a2a87b424eaaf84d0.png","url":"www.baidu.com"}],"food_drink":[{"goods_id":66,"goods_name":"等会就删","img":"http://zf8020.com/public/upload/images/goods/20190712156291884914241.png","desc":"","price":"399.00","original_price":"0.00"}]}
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
        private List<HomeOtherSelfnavBean> selfnav;
        private List<HomeImportOrFoodBean> food_drink;

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

        public List<HomeOtherSelfnavBean> getSelfnav() {
            if (selfnav == null) {
                return new ArrayList<>();
            }
            return selfnav;
        }

        public void setSelfnav(List<HomeOtherSelfnavBean> selfnav) {
            this.selfnav = selfnav;
        }

        public List<HomeImportOrFoodBean> getFood_drink() {
            if (food_drink == null) {
                return new ArrayList<>();
            }
            return food_drink;
        }

        public void setFood_drink(List<HomeImportOrFoodBean> food_drink) {
            this.food_drink = food_drink;
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

    }
}

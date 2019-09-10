package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ClassName:     分类列表返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 10:19
  * @Version:        1.0
 */

public class CategoryListDto {


    /**
     * status : 200
     * msg : 获取成功
     * data : [{"cat_id":62,"cat_name":"粮油","pid":0,"level":1,"img":"","is_show":1,"desc":"","sort":1,"children":[{"cat_id":63,"cat_name":"大米","pid":62,"level":2,"img":"","is_show":1,"desc":"","sort":1,"children":[{"cat_id":64,"cat_name":"黑米","pid":63,"level":3,"img":"","is_show":1,"desc":"","sort":1}]}],"goods":[{"goods_id":75,"goods_name":"企业版 矿机","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190803156482339365585.png","price":"19900.00","original_price":"0.00","attr_name":[],"comment":4},{"goods_id":74,"goods_name":"小鸟鸟","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190717156332971828319.png","price":"1.00","original_price":"0.00","attr_name":[],"comment":0}]},{"cat_id":26,"cat_name":"存储空间","pid":0,"level":1,"img":"","is_show":1,"desc":"存储空间分类","sort":1,"goods":[{"goods_id":75,"goods_name":"企业版 矿机","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190803156482339365585.png","price":"19900.00","original_price":"0.00","attr_name":[],"comment":4},{"goods_id":74,"goods_name":"小鸟鸟","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190717156332971828319.png","price":"1.00","original_price":"0.00","attr_name":[],"comment":0}]},{"cat_id":25,"cat_name":"礼品专区","pid":0,"level":1,"img":"","is_show":1,"desc":"","sort":1,"goods":[{"goods_id":72,"goods_name":"商务保温杯","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190716156324016790465.png","price":"500.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":45,"goods_name":"礼品专区商品1","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190710156274057499262.png","price":"500.00","original_price":"0.00","attr_name":[],"comment":1},{"goods_id":40,"goods_name":"礼品商品","img":"http://cattle.zhifengwangluo.com/upload/images/goods/2019071015627391512588.png","price":"500.00","original_price":"500.00","attr_name":[],"comment":0}]},{"cat_id":13,"cat_name":"进口货物","pid":0,"level":1,"img":"category/20190516155797968450728.png","is_show":1,"desc":"","sort":1,"children":[{"cat_id":61,"cat_name":"慝revvv","pid":13,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":60,"cat_name":"发帖染色费","pid":13,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":59,"cat_name":"天rffgf","pid":13,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":58,"cat_name":"sefsd","pid":13,"level":2,"img":"category/20190906156774163514923.png","is_show":1,"desc":"","sort":1},{"cat_id":57,"cat_name":"挺有","pid":13,"level":2,"img":"category/20190906156774160273571.png","is_show":1,"desc":"","sort":1},{"cat_id":56,"cat_name":"vdf","pid":13,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":55,"cat_name":"非常","pid":13,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":54,"cat_name":"认为","pid":13,"level":2,"img":"","is_show":1,"desc":"","sort":1}],"goods":[{"goods_id":43,"goods_name":"SKII神仙水国际版","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190710156272870581188.png","price":"1500.00","original_price":"2000.00","attr_name":["包邮","精选","新上"],"comment":0},{"goods_id":39,"goods_name":"本草","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190704156222257233167.png","price":"200.00","original_price":"250.00","attr_name":["热卖","限时卖","精选"],"comment":1},{"goods_id":18,"goods_name":"美的（Midea） 三门冰箱 风冷无霜家","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190704156222261239875.png","price":"2188.00","original_price":"2588.00","attr_name":["新上","热卖"],"comment":0}]},{"cat_id":9,"cat_name":"食品酒水","pid":0,"level":1,"img":"","is_show":1,"desc":"","sort":1,"children":[{"cat_id":53,"cat_name":"套听","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":52,"cat_name":"热恶","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":51,"cat_name":"绕弯儿","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":50,"cat_name":"让我若","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":49,"cat_name":"任务书若翁","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":48,"cat_name":"热污染","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":47,"cat_name":"特送","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":46,"cat_name":"发","pid":9,"level":2,"img":"","is_show":1,"desc":"","sort":1}],"goods":[{"goods_id":71,"goods_name":"真钱支付测试","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190715156319762633997.png","price":"0.01","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":69,"goods_name":"功夫","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190715156317546299541.png","price":"9.00","original_price":"9.00","attr_name":["新上"],"comment":0},{"goods_id":66,"goods_name":"等会就删","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190712156291884914241.png","price":"399.00","original_price":"0.00","attr_name":["热卖"],"comment":0},{"goods_id":52,"goods_name":"玩具车","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190710156275131156106.png","price":"998.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":22,"goods_name":"美的（Midea）333","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190704156222264985070.png","price":"3455.00","original_price":"4566.00","attr_name":[],"comment":0},{"goods_id":19,"goods_name":"美的（Midea） 三门冰箱1","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190704156222271022686.png","price":"2000.00","original_price":"2500.00","attr_name":["新上"],"comment":0}]},{"cat_id":5,"cat_name":"兴农扶贫","pid":0,"level":1,"img":"","is_show":1,"desc":"","sort":1,"children":[{"cat_id":45,"cat_name":"10","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":44,"cat_name":"9","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":43,"cat_name":"8","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":42,"cat_name":"7","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":41,"cat_name":"6","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":40,"cat_name":"5","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":39,"cat_name":"4","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":38,"cat_name":"3","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":28,"cat_name":"22222","pid":5,"level":2,"img":"","is_show":1,"desc":"","sort":1,"children":[{"cat_id":37,"cat_name":"2","pid":28,"level":3,"img":"","is_show":1,"desc":"","sort":1}]}],"goods":[{"goods_id":65,"goods_name":"加多宝1","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190712156290542115317.png","price":"5.00","original_price":"7.00","attr_name":["新上"],"comment":0},{"goods_id":59,"goods_name":"脉动","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190711156282891142252.png","price":"5.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":58,"goods_name":"能喝的化妆水","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190711156281407614651.png","price":"199.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":57,"goods_name":"元气森林","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190711156280828257001.png","price":"7.00","original_price":"0.00","attr_name":["新上"],"comment":0},{"goods_id":55,"goods_name":"加多宝1","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190710156275951851718.png","price":"6.00","original_price":"0.00","attr_name":["热卖"],"comment":0},{"goods_id":54,"goods_name":"加多宝","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190710156275920154250.png","price":"5.50","original_price":"0.00","attr_name":["包邮","新上"],"comment":0},{"goods_id":44,"goods_name":"王老吉","img":"http://cattle.zhifengwangluo.com/upload/images/goods/2019071015627291261611.png","price":"5.00","original_price":"7.00","attr_name":[],"comment":0},{"goods_id":21,"goods_name":"砍一刀测试商品","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190704156222273958950.png","price":"2400.00","original_price":"3000.00","attr_name":[],"comment":0}]},{"cat_id":1,"cat_name":"推荐","pid":0,"level":1,"img":"category/20190716156325040874643.png","is_show":1,"desc":"","sort":1,"children":[{"cat_id":36,"cat_name":"hj0","pid":1,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":35,"cat_name":"太热1","pid":1,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":34,"cat_name":"22222","pid":1,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":33,"cat_name":"7","pid":1,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":32,"cat_name":"5","pid":1,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":31,"cat_name":"太热","pid":1,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":30,"cat_name":"22222","pid":1,"level":2,"img":"","is_show":1,"desc":"","sort":1},{"cat_id":27,"cat_name":"11111","pid":1,"level":2,"img":"","is_show":1,"desc":"ffd","sort":1}],"goods":[{"goods_id":73,"goods_name":"444","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190716156324887558693.png","price":"0.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":70,"goods_name":"鸡鸡汤","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190715156317690815533.png","price":"59.00","original_price":"78.00","attr_name":[],"comment":0},{"goods_id":68,"goods_name":"三人拼团","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190715156315929338268.png","price":"399.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":67,"goods_name":"水蜜桃","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190712156292059951103.png","price":"0.01","original_price":"0.01","attr_name":["热卖"],"comment":0},{"goods_id":64,"goods_name":"小猪肉","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190712156289736273579.png","price":"10.00","original_price":"10.00","attr_name":["热卖"],"comment":0},{"goods_id":62,"goods_name":"五金工具","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190711156284682742658.png","price":"9.00","original_price":"9.00","attr_name":["热卖"],"comment":0},{"goods_id":60,"goods_name":"五金工具","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190711156283962465877.png","price":"9.00","original_price":"9.00","attr_name":["热卖"],"comment":0},{"goods_id":53,"goods_name":"测试礼包1","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190711156281743498139.png","price":"78.00","original_price":"100.00","attr_name":["精选"],"comment":1},{"goods_id":51,"goods_name":"天然小鸟","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190710156275122646699.png","price":"2.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":35,"goods_name":"美的（Midea） 三门冰箱3","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190704156222280427620.png","price":"2199.00","original_price":"0.00","attr_name":[],"comment":0},{"goods_id":20,"goods_name":"美的（Midea） 三门冰箱 2","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190704156222275856273.png","price":"5000.00","original_price":"5500.00","attr_name":[],"comment":0}]}]
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
         * cat_id : 62
         * cat_name : 粮油
         * pid : 0
         * level : 1
         * img :
         * is_show : 1
         * desc :
         * sort : 1
         * children : [{"cat_id":63,"cat_name":"大米","pid":62,"level":2,"img":"","is_show":1,"desc":"","sort":1,"children":[{"cat_id":64,"cat_name":"黑米","pid":63,"level":3,"img":"","is_show":1,"desc":"","sort":1}]}]
         * goods : [{"goods_id":75,"goods_name":"企业版 矿机","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190803156482339365585.png","price":"19900.00","original_price":"0.00","attr_name":[],"comment":4},{"goods_id":74,"goods_name":"小鸟鸟","img":"http://cattle.zhifengwangluo.com/upload/images/goods/20190717156332971828319.png","price":"1.00","original_price":"0.00","attr_name":[],"comment":0}]
         */

        private int cat_id;
        private String cat_name;
        private int pid;
        private int level;
        private String img;
        private int is_show;
        private String desc;
        private int sort;
        private List<ChildrenBeanX> children;
        private List<GoodsBean> goods;
        boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public void setImg(String img) {
            this.img = img == null ? "" : img;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public String getDesc() {
            return desc == null ? "" : desc;
        }

        public void setDesc(String desc) {
            this.desc = desc == null ? "" : desc;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<ChildrenBeanX> getChildren() {
            if (children == null) {
                return new ArrayList<>();
            }
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        public List<GoodsBean> getGoods() {
            if (goods == null) {
                return new ArrayList<>();
            }
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class ChildrenBeanX {
            /**
             * cat_id : 63
             * cat_name : 大米
             * pid : 62
             * level : 2
             * img :
             * is_show : 1
             * desc :
             * sort : 1
             * children : [{"cat_id":64,"cat_name":"黑米","pid":63,"level":3,"img":"","is_show":1,"desc":"","sort":1}]
             */

            private int cat_id;
            private String cat_name;
            private int pid;
            private int level;
            private String img;
            private int is_show;
            private String desc;
            private int sort;
            private List<ChildrenBean> children;

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

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getImg() {
                return img == null ? "" : img;
            }

            public void setImg(String img) {
                this.img = img == null ? "" : img;
            }

            public int getIs_show() {
                return is_show;
            }

            public void setIs_show(int is_show) {
                this.is_show = is_show;
            }

            public String getDesc() {
                return desc == null ? "" : desc;
            }

            public void setDesc(String desc) {
                this.desc = desc == null ? "" : desc;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ChildrenBean> getChildren() {
                if (children == null) {
                    return new ArrayList<>();
                }
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * cat_id : 64
                 * cat_name : 黑米
                 * pid : 63
                 * level : 3
                 * img :
                 * is_show : 1
                 * desc :
                 * sort : 1
                 */

                private int cat_id;
                private String cat_name;
                private int pid;
                private int level;
                private String img;
                private int is_show;
                private String desc;
                private int sort;

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

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getImg() {
                    return img == null ? "" : img;
                }

                public void setImg(String img) {
                    this.img = img == null ? "" : img;
                }

                public int getIs_show() {
                    return is_show;
                }

                public void setIs_show(int is_show) {
                    this.is_show = is_show;
                }

                public String getDesc() {
                    return desc == null ? "" : desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc == null ? "" : desc;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }
        }

        public static class GoodsBean {
            /**
             * goods_id : 75
             * goods_name : 企业版 矿机
             * img : http://cattle.zhifengwangluo.com/upload/images/goods/20190803156482339365585.png
             * price : 19900.00
             * original_price : 0.00
             * attr_name : []
             * comment : 4
             */

            private int goods_id;
            private String goods_name;
            private String img;
            private String price;
            private String original_price;
            private int comment;
            private List<?> attr_name;

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

            public int getComment() {
                return comment;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public List<?> getAttr_name() {
                if (attr_name == null) {
                    return new ArrayList<>();
                }
                return attr_name;
            }

            public void setAttr_name(List<?> attr_name) {
                this.attr_name = attr_name;
            }
        }
    }
}

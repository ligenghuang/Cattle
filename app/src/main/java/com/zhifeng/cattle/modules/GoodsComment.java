package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class GoodsComment {

    /**
     * status : 1
     * msg : 获取成功
     * data : {"total":4,"per_page":20,"current_page":1,"last_page":1,"data":[{"mobile":"1*********2","avatar":"http://new_retail_test.zhifengwangluo.com/upload/images/tou/20190729156439615037925.png","user_id":27864,"comment_id":87,"content":"好棒~","star_rating":3,"replies":null,"praise":0,"add_time":1563517781,"img":["http://zf8020.com/upload/images/comment/20190719156351778198880.png","http://zf8020.com/upload/images/comment/20190719156351778113257.png"],"sku_id":23,"spec":"规格:默认","is_praise":0},{"mobile":"","avatar":null,"user_id":27741,"comment_id":86,"content":"广东省给对方","star_rating":2,"replies":null,"praise":0,"add_time":1563281083,"img":["http://zf8020.com/upload/images/comment/20190716156328108360680.png","http://zf8020.com/upload/images/comment/20190716156328108385286.png","http://zf8020.com/upload/images/comment/20190716156328108387209.png"],"sku_id":62,"spec":"规格:大小","is_praise":0},{"mobile":"","avatar":null,"user_id":27740,"comment_id":85,"content":"3恶热","star_rating":4,"replies":null,"praise":0,"add_time":1563281028,"img":["http://zf8020.com/upload/images/comment/20190716156328102866297.png"],"sku_id":69,"spec":"规格:黑色","is_praise":0},{"mobile":"","avatar":null,"user_id":27741,"comment_id":84,"content":"我发的发的等会发时代峻峰","star_rating":2,"replies":null,"praise":0,"add_time":1563281008,"img":["http://zf8020.com/upload/images/comment/2019071615632810083778.png"],"sku_id":22,"spec":"规格:颜色","is_praise":0}]}
     */

    private int status;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 4
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"mobile":"1*********2","avatar":"http://new_retail_test.zhifengwangluo.com/upload/images/tou/20190729156439615037925.png","user_id":27864,"comment_id":87,"content":"好棒~","star_rating":3,"replies":null,"praise":0,"add_time":1563517781,"img":["http://zf8020.com/upload/images/comment/20190719156351778198880.png","http://zf8020.com/upload/images/comment/20190719156351778113257.png"],"sku_id":23,"spec":"规格:默认","is_praise":0},{"mobile":"","avatar":null,"user_id":27741,"comment_id":86,"content":"广东省给对方","star_rating":2,"replies":null,"praise":0,"add_time":1563281083,"img":["http://zf8020.com/upload/images/comment/20190716156328108360680.png","http://zf8020.com/upload/images/comment/20190716156328108385286.png","http://zf8020.com/upload/images/comment/20190716156328108387209.png"],"sku_id":62,"spec":"规格:大小","is_praise":0},{"mobile":"","avatar":null,"user_id":27740,"comment_id":85,"content":"3恶热","star_rating":4,"replies":null,"praise":0,"add_time":1563281028,"img":["http://zf8020.com/upload/images/comment/20190716156328102866297.png"],"sku_id":69,"spec":"规格:黑色","is_praise":0},{"mobile":"","avatar":null,"user_id":27741,"comment_id":84,"content":"我发的发的等会发时代峻峰","star_rating":2,"replies":null,"praise":0,"add_time":1563281008,"img":["http://zf8020.com/upload/images/comment/2019071615632810083778.png"],"sku_id":22,"spec":"规格:颜色","is_praise":0}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
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
             * mobile : 1*********2
             * avatar : http://new_retail_test.zhifengwangluo.com/upload/images/tou/20190729156439615037925.png
             * user_id : 27864
             * comment_id : 87
             * content : 好棒~
             * star_rating : 3
             * replies : null
             * praise : 0
             * add_time : 1563517781
             * img : ["http://zf8020.com/upload/images/comment/20190719156351778198880.png","http://zf8020.com/upload/images/comment/20190719156351778113257.png"]
             * sku_id : 23
             * spec : 规格:默认
             * is_praise : 0
             */

            private String mobile;
            private String avatar;
            private int user_id;
            private int comment_id;
            private String content;
            private int star_rating;
            private Object replies;
            private int praise;
            private String add_time;
            private int sku_id;
            private String spec;
            private int is_praise;
            private List<String> img;

            public String getMobile() {
                return mobile == null ? "" : mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile == null ? "" : mobile;
            }

            public String getAvatar() {
                return avatar == null ? "" : avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar == null ? "" : avatar;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content == null ? "" : content;
            }

            public int getStar_rating() {
                return star_rating;
            }

            public void setStar_rating(int star_rating) {
                this.star_rating = star_rating;
            }

            public Object getReplies() {
                return replies;
            }

            public void setReplies(Object replies) {
                this.replies = replies;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public int getSku_id() {
                return sku_id;
            }

            public void setSku_id(int sku_id) {
                this.sku_id = sku_id;
            }

            public String getSpec() {
                return spec == null ? "" : spec;
            }

            public void setSpec(String spec) {
                this.spec = spec == null ? "" : spec;
            }

            public int getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(int is_praise) {
                this.is_praise = is_praise;
            }

            public List<String> getImg() {
                if (img == null) {
                    return new ArrayList<>();
                }
                return img;
            }

            public void setImg(List<String> img) {
                this.img = img;
            }
        }
    }
}

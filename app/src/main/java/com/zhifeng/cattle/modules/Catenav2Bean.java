package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     首页二级分类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 9:36
  * @Version:        1.0
 */

public class Catenav2Bean {

    /**
     * cat_id : 27
     * cat_name : 11111
     * img : http://zf8020.com/public/upload/images/category/20190919156887540392102.png
     */

    private int cat_id;
    private String cat_name;
    private String img;
    private boolean isOnClick;

    public boolean isOnClick() {
        return isOnClick;
    }

    public void setOnClick(boolean onClick) {
        isOnClick = onClick;
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

    public String getImg() {
        return img == null ? "" : img;
    }

    public void setImg(String img) {
        this.img = img == null ? "" : img;
    }
}

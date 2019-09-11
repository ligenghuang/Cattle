package com.zhifeng.cattle.net;

import com.zhifeng.cattle.BuildConfig;

public class WebUrlUtil {


    static {
        //配合retrofit，需要以/结尾
        if (BuildConfig.DEBUG) {
            BASE_URL = "http://cattle.zhifengwangluo.com/api/";
        } else {
            BASE_URL = "http://cattle.zhifengwangluo.com/api/";
        }
    }

    public static String BASE_URL;

    /**
     * 登录或注册
     */
    public static final String POST_LOGIN = "user/doLogin";

    /**
     * 发送验证码
     */
    public static final String POST_SEND_CODE = "user/sendVerifyCode";

    /**
     * 用户协议
     */
    public static final String POST_USER_CONSULT = "user/consult";

    /**
     * 商品分类列表
     */
    public static final String POST_CATEGORY_LIST = "goods/categoryList";

    /**
     * 购物车列表
     */
    public static final String POST_CART_list = "Cart/cartlist";

    /**
     * 删除购物车商品
     */
    public static final String POST_DELETE_CART_LIST = "cart/delCart";

    /**
     * 我的信息
     */
    public static final String POST_MY_INFO = "user/user_info";

    /**
     * 我的余额
     */
    public static final String POST_REMAINDER = "user/user_remainder";

    /**
     * 关注列表
     */
    public static final String POST_COLLECTION_LIST = "Collection/collection_list";

    /**
     * 取消关注
     */
    public static final String POST_DELETE_COLLECTION = "Collection/collection";

    /**
     * 订单列表
     */
    public static final String POST_ORDER_LIST = "order/order_list";

    /**
     * 订单详情
     */
    public static final String POST_ORDER_DETAIL = "order/order_detail";

}

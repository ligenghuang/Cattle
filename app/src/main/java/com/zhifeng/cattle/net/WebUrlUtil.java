package com.zhifeng.cattle.net;

import com.zhifeng.cattle.BuildConfig;
import com.zhifeng.cattle.modules.SafeInfoDto;

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

    /**
     * 当日累计奖金
     */
    public static final String POST_TODAY_BONUS = "user/today_bonus";

    /**
     * 当月累计奖金
     */
    public static final String POST_MON_BONUS = "user/mon_bonus";

    /**
     * 我的团队
     */
    public static final String POST_USER_TEAM = "user/team";

    /**
     * 地址管理
     */
    public static final String POST_ADDRESS_LIST ="Address/addressList";

    /**
     * 删除地址
     */
    public static final String POST_DEL_ADDRESS ="Address/delAddress";

    /**
     * 设为默认地址
     */
    public static final String POST_SET_DEFAULT_ADDRESS = "Address/set_default_address";

    /**
     * 编辑添加地址
     */
    public static final String POST_ADD_ADDRESS = "Address/addAddress";

    /**
     * 获取收货地址详情
     */
    public static final String POST_ADDRESS_DETAIL = "Address/my_address";

    /**
     * 获取下级地址
     */
    public static final String POST_ADDRESS_GET_REGION = "Address/get_region";

    /**
     * 安全中心信息
     */
    public static final String POST_SAFE_INDEX = "safe/index";

    /**
     * 修改手机号码  验证手机号
     */
    public static final String POST_SAFE_CHECK_NEW_PHONE = "safe/check_new_phone";

    /**
     * 换绑手机
     */
    public static final String POST_SAFE_CHANGE_PHONE = "safe/change_phone";

}

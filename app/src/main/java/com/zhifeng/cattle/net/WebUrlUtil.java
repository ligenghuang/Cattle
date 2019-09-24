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

    /**
     * 修改支付密码
     */
    public static final  String POST_EDIT_PAY_PWD = "user/edit_pay_password";

    /**
     * 忘记支付密码
     */
    public static final  String POST_RESET_PAY_PWD = "user/paypwd_reset";

    /**
     * 设置支付密码
     */
    public static final  String POST_SET_PAY_PWD = "user/set_pay_password";

    /**
     * 提现明细
     */
    public static final String POST_WITHDRAWAL_LIST = "user/withdrawal_list";

    /**
     * 明细记录
     */
    public static final String POST_DetailRecord_LIST = "Team/detail_record";

    /**
     * 团队列表
     */
    public static final String POST_Team_LIST = "user/team_list";

    /**
     * 团队列表查看
     */
    public static final String POST_TeamOrder_LIST = "Team/team_order";

    /**
     * 充值明细
     */
    public static final String POST_RechargeDetail_LIST = "user/recharge";

    /**
     * 排行榜
     */
    public static final String POST_Ranking_LIST = "user/ranking_list";
    /**
     * 提现
     */
    public static final String POST_WITHDRAWAL = "user/withdrawal";

    /**
     * 填写支付宝账号
     */
    public static final String POST_EDIT_ALIPAY = "user/zfb_edit";

    /**
     * 商品详情
     */
    public static final String POST_GOODS_DETAILS ="goods/goodsDetail";

    /**
     * 添加关注
     */
    public static final String POST_ADD_COLLECTION = "Collection/add_collection";

    /**
     * 商品评价列表
     */
    public static final String POST_COMMENT_LIST = "goods/comment_list";

    /**
     * 商品评价
     */
    public static final String POST_ORDER_COMMENT = "order/order_comment";

    /**
     * 申请退款
     */
    public static final String POST_ORDER_APPLY_REFUND = "order/apply_refund";


    /**
     * 立即购买
     */
    public static final String POST_IMMEDIATELYORDER = "order/immediatelyOrder";

    /**
     * 购物车提交订单接口
     */
    public static final String POST_TEMPORARY = "order/temporary";

    /**
     * 充值
     */
    public static final String POST_RECHARGE = "user/balance_recharge";

    /**
     * 获取银行卡列表
     */
    public static final String POST_BANK_NUMBER = "user/get_bank_number";

    /**
     * 修改订单状态
     */
    public static final String POST_EDIT_ORDER_STATUS = "order/edit_status";

    /**
     * 身份证认证
     */
    public static final String POST_UPDATA_IDCARD_PIC = "Index/updata_idcard_pic";

    /**
     * 获取默认地址
     */
    public static final String POST_DEFAULT_CITY = "Address/get_default_city";

    /**
     * 首页推荐
     */
    public static final String POST_RECOMMENDhOME = "index/index";

    /**
     * 分享邀请
     */
    public static final String POST_SHAREPOSTER = "user/sharePoster";

    /**
     * 申请供应商
     */
    public static final String POST_SUPPLIER = "Team/supplier";
    /**
     * 首页兴农扶贫
     */
    public static final String POST_POVEERYRELIEF = "index/indexfarm";

    /**
     * 首页食品酒水
     */
    public static final String POST_FOODDRINK = "index/index_food_drink";

    /**
     * 首页进口货物
     */
    public static final String POST_IMPORT = "index/index_import";

    /**
     * 首页列表页
     */
    public static final String POST_LIST_PAGE = "index/list_page";

    /**
     * 首页搜索
     */
    public static final String POST_SEARCH_GOODS = "goods/search_goods";
    /**
     * 提交订单
     */
    public static final String POST_SUBMITORDER = "order/submitOrder";

    /**
     * 购物车数量增加
     */
    public static final String POST_CHAGE_CART_NUM = "cart/change_num";

    /**
     * 购物车数量减少
     */
    public static final String POST_REDUCE_CART_NUM = "cart/reduce_num";

    /**
     * 购物车数量修改
     */
    public static final String POST_CART_NUM= "cart/car_num";

    /**
     * 首页搜索历史
     */
    public static final String POST_SEARCH_HISTORY= "search/search_history";

    /**
     * 订单支付
     */
    public static final String POST_PAY_ORDER = "pay/payment";

    /**
     * 我的-提现余额-账单明细
     */
    public static final String POST_CHECK_DETAIL = "user/remainder_list";

    /**
     * 申请退款
     */
    public static final String POST_APPLY_REFUND = "order/apply_refund";
}

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

}

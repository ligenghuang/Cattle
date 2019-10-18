package com.zhifeng.cattle.utils.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;

/**
  * 
  * @ClassName:     
  * @Description:    
  * @Author:         lgh
  * @CreateDate:     2019/8/30 14:01
  * @Version:        1.0
 */

public class MySp extends MySharedPreferencesUtil {
    /**
     * 清空缓存
     * @param context
     */
    public static void clearAllSP(Context context) {
        setAccessToken(context,null);
        setMobile(context,null);
        setPwd(context,0);
    }

    /**
     * 判断是否登录
     * @param context
     * @return
     */
    public static boolean iSLoginLive(Context context) {

            String accessToken = getAccessToken(context);
            if (accessToken != null) {
                L.e("MySharedPreferencesUtil", " 登陆了");
                return true;
            } else {
                L.e("MySharedPreferencesUtil", " 没有 登陆");
                return false;
            }
    }


    /**
     * 获取 签名
     *
     * @param context
     * @return
     */
    public static String getAccessToken(Context context) {
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("token", null);    }

    /**
     * 设置 签名
     *
     * @param context
     * @param token
     */
    public static boolean setAccessToken(Context context, String token) {
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("token", token).commit();
    }
    /**
     * 保存用户列表
     * @param context
     * @param json
     * @return
     */
    public static boolean setUserList(Context context,String json){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("UserJson", json).commit();
    }

    /**
     * 获取用户列表
     * @param context
     * @return
     */
    public static String getUserList(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("UserJson", null);
    }

    /**
     * 保存手机号
     * @param context
     * @param mobile
     * @return
     */
    public static boolean setMobile(Context context,String mobile){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("mobile", mobile).commit();
    }

    /**
     * 获取手机号
     * @param context
     * @return
     */
    public static String getMobile(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("mobile", null);
    }

    /**
     * 是否设置支付密码
     * @param context
     * @param pwd
     * @return
     */
    public static boolean setPwd(Context context,int pwd){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putInt("Pwd", pwd).commit();
    }

    /**
     * 是否设置支付密码
     * @param context
     * @return
     */
    public static int getPwd(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getInt("Pwd", 0);
    }
}

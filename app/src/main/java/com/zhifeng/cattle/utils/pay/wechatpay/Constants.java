package com.zhifeng.cattle.utils.pay.wechatpay;

public class Constants {
	// APP_ID 替换为你的应用从官方网站申请到的合法appId
//	public static final String APP_ID = "2018060260323550";
	public static final String APP_ID = "wx59c400521dfb2fed";
	public static final String APP_SECRET_WX = "3af4635cbb6df2de8a25dc23b6ff322b";

//37296626  290768974

	public static final String APP_KEY= "37296626";

	public static final String REDIRECT_URL = "http://www.sina.com";
	/**
	 * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
	 * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
	 * 选择赋予应用的功能。
	 *
	 * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
	 * 使用权限，高级权限需要进行申请。
	 *
	 * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
	 *
	 * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
	 * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
	 */
	public static final String SCOPE =
			"email,direct_messages_read,direct_messages_write,"
					+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
					+ "follow_app_official_microblog," + "invitation_write";

    public static class ShowMsgActivity {
		public static final String STitle = "showmsg_title";
		public static final String SMessage = "showmsg_message";
		public static final String BAThumbData = "showmsg_thumb_data";
	}
}

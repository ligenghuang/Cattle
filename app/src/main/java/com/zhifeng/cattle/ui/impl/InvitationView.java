package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.SharePoster;

/**
 * @ClassName:
 * @Description: 邀请分享
 * @Author: Administrator
 * @Date: 2019/9/21 10:16
 */
public interface InvitationView extends BaseView {
    void getSharePoster();

    void getSharePosterSuccess(SharePoster sharePoster);
}

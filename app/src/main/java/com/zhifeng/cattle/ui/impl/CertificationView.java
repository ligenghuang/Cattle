package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.CertificationDto;
import com.zhifeng.cattle.modules.post.CertificationPost;

/**
  *
  * @ClassName:     身份证认证
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/20 14:39
  * @Version:        1.0
 */

public interface CertificationView extends BaseView {

    /**
     * 认证
     */
    void certification(CertificationPost certificationPost);
    void certificationSuccess(CertificationDto certificationDto);
}

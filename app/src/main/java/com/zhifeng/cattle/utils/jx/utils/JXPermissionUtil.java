/**
 * Copyright (C) 2015-2016 Guangzhou Xunhong Network Technology Co., Ltd. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhifeng.cattle.utils.jx.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;


import androidx.core.content.ContextCompat;

import com.zhifeng.cattle.BuildConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理工具
 * <p>
 * Date: 2017年2月28日
 * </p>
 */
public class JXPermissionUtil {

    private static final String TAG = "JXPermissionUtil";

    private int mRequestCode = -1;

    private boolean isGranted = false;

    private OnPermissionCallback mCallback;

    /**
     * 权限申请callback 
     */
    public static interface OnPermissionCallback {
        /**
         * 权限申请成功
         */
        public void onGranted();

        /**
         * 权限申请失败
         */
        public void onDenied();
    }


    @TargetApi(android.os.Build.VERSION_CODES.M)
    public void requestPermissions(Context context, int requestCode
            , String[] permissions, OnPermissionCallback callback) {
        if (context instanceof Activity) {
            mRequestCode = requestCode;
            Activity activity = (Activity) context;
            List<String> deniedPermissions = getDeniedPermissions(context, permissions);
            if (!deniedPermissions.isEmpty()) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "request permissions >>>" + deniedPermissions);
                }
                activity.requestPermissions(deniedPermissions
                        .toArray(new String[deniedPermissions.size()]), requestCode);
                mCallback = callback;
            } else {
                if (callback != null) {
                    callback.onGranted();
                }
            }
        } else {
            throw new RuntimeException("Context must be an Activity");
        }
    }

    /**
     * 获取请求权限中需要授权的权限
     */
    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    /**
     * 请求权限结果，对应Activity中onRequestPermissionsResult()方法。
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mCallback != null) {
                if (verifyPermissions(grantResults)) {
                    isGranted = true;
                    mCallback.onGranted();
                } else {
                    isGranted = false;
                    mCallback.onDenied();
                }
            }
        }
    }

    /**
     * 验证所有权限是否都已经授权
     */
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void unRigisterCallback() {
        mCallback = null;
    }
}

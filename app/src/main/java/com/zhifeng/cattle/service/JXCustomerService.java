/**
 * Copyright (C) 2015-2016 Guangzhou Xunhong Network Technology Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhifeng.cattle.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jxccp.im.callback.JXEventListner;
import com.jxccp.im.callback.JXUserSelfQueueListener;
import com.jxccp.im.chat.common.message.JXMessage;
import com.jxccp.im.chat.common.message.JXMessage.Type;
import com.jxccp.im.chat.manager.JXEventNotifier;
import com.jxccp.im.chat.manager.JXImManager;
import com.jxccp.im.util.log.JXLog;
import com.lgh.huanglib.util.L;
import com.zhifeng.cattle.utils.jx.utils.JXNotificationUtils;
import com.zhifeng.cattle.utils.jx.JXUiHelper;

/**
 * @Description: 用户请求的服务
 * @date 2015-9-7 下午8:04:33
 */
public class JXCustomerService extends Service implements JXUserSelfQueueListener{
    static final String TAG = "JXCustomerService";


    @Override
    public void onCreate() {
        super.onCreate();
        JXLog.i("JXCustomerService onCreate");
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
//            JXLog.d("JXCustomerService start foreground");
//            startForeground(1, new Notification());
//        }
        JXImManager.Message.getInstance().registerEventListener(messageEventListner);
        JXImManager.Message.getInstance().setMessageCallback(JXUiHelper.getInstance());
        JXImManager.McsUser.getInstance().addUserSelfQueueListeners(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JXImManager.Message.getInstance().removeEventListener(messageEventListner);
        JXImManager.McsUser.getInstance().removeUserSelfQueueListeners(this);
    }

    /**
     * 新消息接收到时的监听器
     */
    JXEventListner messageEventListner = new JXEventListner() {
        @Override
        public void onEvent(final JXEventNotifier eventNotifier) {
            JXLog.d("messageEventListner , eventNotifier:"+eventNotifier.getEvent());
            final JXMessage message = (JXMessage)eventNotifier.getData();
            if (message.getType() == Type.EVALUATION) {
                JXUiHelper.getInstance().setRecEvaluateMessage(true);
            }
            switch (eventNotifier.getEvent()) {
                case MESSAGE_NEW:
                case MESSAGE_OFFLINE:
                case MESSAGE_REVOKE:
                    L.d("lgh_jx","messageEventListner , has new message incoming.");
                    if (!JXUiHelper.getInstance().isChatPage()) {
                        JXNotificationUtils.showIncomingMessageNotify(
                                getApplicationContext(), message);
                    }
                    break;
                case MESSAGE_PUSH:
                    int count = JXUiHelper.getInstance().getMessageBoxUnreadCount();
                    JXUiHelper.getInstance().setMessageBoxUnreadCount(count+1);
                    final JXMessage jxmessage = (JXMessage)eventNotifier.getData();
                    JXNotificationUtils.showMessagePushNotify(getApplicationContext(), jxmessage);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onUserSelfQueueUpdate(String skillsId, int currentPosition) {
        JXUiHelper.getInstance().setCurrentPosition(currentPosition);
    }

    @Override
    public void onUserSelfStatus(String skillsId, final int status, final String nickName) {
        if (status != JXUserSelfQueueListener.USER_STATUS_WAITING
                && status != JXUserSelfQueueListener.USER_STATUS_PENDING) {
            JXUiHelper.getInstance().setCurrentPosition(-1);
        }
        if(status == JXUserSelfQueueListener.USER_STATUS_RECALL){
            if (!JXUiHelper.getInstance().isChatPage()) {
                JXNotificationUtils.notifyRecallIncoming(getApplicationContext(), skillsId);
            }
        }
    }

    @Override
    public void onEnded(String skillsId, int reasonCode) {
        JXUiHelper.getInstance().setCurrentPosition(-1);
    }
}

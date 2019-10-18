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

package com.zhifeng.cattle.utils.jx.utils;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

import com.jxccp.im.chat.common.message.JXConversation;
import com.jxccp.im.chat.common.message.JXConversation.SessionStatus;
import com.jxccp.im.chat.common.message.JXMessage;
import com.jxccp.im.chat.common.message.JXMessage.Type;
import com.jxccp.im.chat.common.message.JXMessageAttribute;
import com.jxccp.im.chat.common.message.TextMessage;
import com.jxccp.im.chat.manager.JXImManager;
import com.lgh.huanglib.util.L;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.ui.home.JXChatUIActivity;
import com.zhifeng.cattle.utils.jx.JXConstants;
import com.zhifeng.cattle.utils.jx.JXUiHelper;
import com.zhifeng.cattle.utils.jx.view.JXChatView;

import org.json.JSONObject;

public class JXNotificationUtils {
    public static NotificationManager notificationManager;
    /**
     * 震动提示
     *
     * @param context
     */
    public static void vibrate(Context context) {
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[] {
                0, 180, 80, 120
        }, -1);
    }


    public static void notifyRecallIncoming(Context context, String skillsId){
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, JXChatUIActivity.class);
        intent.putExtra(JXConstants.EXTRA_INTENT_TYPE, false);
        intent.putExtra(JXChatView.CHAT_KEY, skillsId);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        @SuppressLint("StringFormatMatches") String tickerText = context.getString(R.string.jx_imcoming_message_ticker_text, context.getString(R.string.jx_recall_tips));

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true).setContentTitle("[回呼]").setTicker(tickerText)
                .setContentText("有客服回呼您").setContentIntent(contentIntent)
                .setVibrate(new long[] {
                        0, 180, 80, 120
                }).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        if (JXUiHelper.getInstance().getmNoificationIcon()!= JXUiHelper.INVALID_INT){
            mBuilder.setSmallIcon(JXUiHelper.getInstance().getmNoificationIcon());
        }else {
            mBuilder.setSmallIcon(R.drawable.jx_ic_chat_default_contact);
        }
        Notification notification = mBuilder.build();
        notificationManager.notify(R.string.jx_imcoming_message_ticker_text, notification);
    }

    /**
     * 收到消息时通知提示
     */
    public static void showIncomingMessageNotify(Context context, JXMessage jxmessage){
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        String from = jxmessage.getFrom();
        Intent intent = new Intent(context, JXChatUIActivity.class);
        long conversationId = jxmessage.getConversationId();
        JXConversation conversation = JXImManager.Conversation.getInstance().getConversationById(conversationId);
        String skillId = null;
        if(jxmessage.fromRobot() && conversation != null){
            skillId = conversation.getSkillsId();
        }else{
            skillId = (String)jxmessage.getAttributes().get(JXMessageAttribute.SKILLS_ID.value());
        }
        String type = (String)jxmessage.getAttributes().get(JXMessageAttribute.TYPE.value());
        intent.putExtra(JXChatView.CHAT_KEY, skillId);
        L.i("lgh_jx","type:"+type);
        if(type !=null ){
            if (type.endsWith(JXMessageAttribute.TYPE_VALUE_EVALUATED)) {
                return;
            }
            intent.putExtra(JXConstants.EXTRA_INTENT_TYPE, true);
            from = context.getString(R.string.jx_admin);
        }else{
            if(conversation.getSessionStatus() != SessionStatus.Deactived){
                intent.putExtra(JXConstants.EXTRA_INTENT_TYPE, false);
            }else{
                intent.putExtra(JXConstants.EXTRA_INTENT_TYPE, true);
            }
        }
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Type messageType = jxmessage.getType();
        String message = "";
        if (jxmessage.getStatus() == JXMessage.Status.REVOKE){
            message = context.getString(R.string.jx_agent_revoke_a_message);
        }else {
            switch (messageType) {
                case TEXT:
                    message = ((TextMessage)jxmessage).getContent();
                    message =  message.replaceAll("\\[.{2,3}\\]", context.getString(R.string.jx_expression_tips));
                    if (JXCommonUtils.isHtmlText(message)) {
                        message = context.getString(R.string.jx_rich_text_message);
                    }
                    break;
                case IMAGE:
                    message = context.getString(R.string.jx_image_message);
                    break;
                case VOICE:
                    message = context.getString(R.string.jx_voice_message);
                    break;
                case RICHTEXT:
                    message = context.getString(R.string.jx_rich_message);
                case VIDEO:
                    message = context.getString(R.string.jx_video_message);
                    break;
                case FILE:
                    message = context.getString(R.string.jx_file_message);
                    break;
                case LOCATION:
                    message = context.getString(R.string.jx_location_message);
                    break;
                default:
                    break;
            }
        }
        @SuppressLint("StringFormatMatches") String tickerText = context.getString(R.string.jx_imcoming_message_ticker_text, message);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true).setContentTitle(from).setTicker(tickerText)
                .setContentText(message).setContentIntent(contentIntent)
                .setVibrate(new long[] {
                        0, 180, 80, 120
        }).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        if (JXUiHelper.getInstance().getmNoificationIcon()!= JXUiHelper.INVALID_INT){
            mBuilder.setSmallIcon(JXUiHelper.getInstance().getmNoificationIcon());
        }else {
            mBuilder.setSmallIcon(R.drawable.jx_ic_chat_default_contact);
        }

        Notification notification = mBuilder.build();
        notificationManager.notify(R.string.jx_imcoming_message_ticker_text, notification);


    }

    public static void showMessagePushNotify(Context context, JXMessage jxmessage){
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        intent.setClass(context, JXChatUIActivity.class);
        intent.putExtra(JXChatView.CHAT_TYPE, JXChatView.CHATTYPE_MESSAGE_BOX);
        intent.putExtra(JXChatView.CHAT_SKILLS_DISPLAYNAME,
                context.getString(R.string.jx_message_center));
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Type messageType = jxmessage.getType();
        if(Type.TEXT == messageType){
            TextMessage textMessage = (TextMessage)jxmessage;
            String message = textMessage.getContent();
            @SuppressLint("StringFormatMatches") String tickerText = context.getString(R.string.jx_push_message_ticker_text, message);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true).setContentTitle(context.getString(R.string.jx_push_message_title)).setTicker(tickerText)
                    .setContentText(message).setContentIntent(contentIntent)
                    .setVibrate(new long[] {
                            0, 180, 80, 120
            }).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            if (JXUiHelper.getInstance().getmNoificationIcon()!= JXUiHelper.INVALID_INT){
                mBuilder.setSmallIcon(JXUiHelper.getInstance().getmNoificationIcon());
            }else {
                mBuilder.setSmallIcon(R.drawable.jx_ic_chat_default_contact);
            }
            Notification notification = mBuilder.build();
            notificationManager.notify(R.string.jx_push_message_ticker_text, notification);
        }
    }

    public static void cancelAllNotifty(){
        if(notificationManager != null){
            notificationManager.cancelAll();
        }
    }

    public static void cancelNotify(int id){
        if(notificationManager != null){
            notificationManager.cancel(id);;
        }
    }

    /**
     * @param context
     */
    public static void notify(Context context, int titleResId, int msgResId) {
        Notification notification = new Notification();

        // FIXME
        Intent notificationIntent = new Intent(context, LauncherActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true).setContentTitle(context.getString(titleResId))
                .setContentText(context.getString(msgResId)).setContentIntent(contentIntent)
                .setVibrate(new long[] {
                        0, 180, 80, 120
        }).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        if (JXUiHelper.getInstance().getmNoificationIcon()!= JXUiHelper.INVALID_INT){
            mBuilder.setSmallIcon(JXUiHelper.getInstance().getmNoificationIcon());
        }else {
            mBuilder.setSmallIcon(R.drawable.jx_ic_chat_default_contact);
        }
        notification = mBuilder.build();

        NotificationManager notificationManager = (NotificationManager)context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
        notificationManager.notify(1, notification);
    }

    public static final String REASON_CHAT = "chat";

    /**
     * 收到离线推送消息提示
     */
    public static void showOfflinePushMessageNotify(Context context, String messageJsonStr){
        try {
            notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            JSONObject jsonMessage = new JSONObject(messageJsonStr);
            String from = jsonMessage.getString("fromName");
            String skillId = jsonMessage.getString("workgroupId");
            String messageReason =  jsonMessage.getString("messageReason");
            Intent intent = null;
//            if (messageReason.equals(REASON_CHAT)) {
//                intent = new Intent(context, JXInitActivity.class);
//                intent.putExtra(JXConstants.EXTRA_CHAT_KEY, skillId);
//            }else {
//                intent = new Intent(context, JXInitActivity.class);
//                intent.putExtra(JXChatView.CHAT_TYPE, JXChatView.CHATTYPE_MESSAGE_BOX);
//                intent.putExtra(JXChatView.CHAT_SKILLS_DISPLAYNAME,
//                        context.getString(R.string.jx_message_center));
//                from = context.getString(R.string.jx_push_message_title);
//            }
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            int messageType = jsonMessage.getInt("messageType");
            String message = "";
            switch (Type.valueOf(messageType)) {
                case TEXT:
                    message = jsonMessage.getString("content");
                    message =  message.replaceAll("\\[.{2,3}\\]", context.getString(R.string.jx_expression_tips));
                    if (JXCommonUtils.isHtmlText(message)) {
                        message = context.getString(R.string.jx_rich_text_message);
                    }
                    break;
                case IMAGE:
                    message = context.getString(R.string.jx_image_message);
                    break;
                case VOICE:
                    message = context.getString(R.string.jx_voice_message);
                    break;
                case VIDEO:
                    message = context.getString(R.string.jx_video_message);
                    break;
                case RICHTEXT:
                    message = context.getString(R.string.jx_rich_message);
                    break;
                case FILE:
                    message = context.getString(R.string.jx_file_message);
                    break;
                case LOCATION:
                    message = context.getString(R.string.jx_location_message);
                    break;
                case CHATSTATE:
                case EVALUATION:
                case REVOKE:
                    return;
                default:
                    break;
            }
            @SuppressLint("StringFormatMatches") String tickerText = context.getString(R.string.jx_imcoming_message_ticker_text, message);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true).setContentTitle(from).setTicker(tickerText)
                    .setContentText(message).setContentIntent(contentIntent)
                    .setVibrate(new long[] {
                            0, 180, 80, 120
                    }).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            if (JXUiHelper.getInstance().getmNoificationIcon()!= JXUiHelper.INVALID_INT){
                mBuilder.setSmallIcon(JXUiHelper.getInstance().getmNoificationIcon());
            }else {
                mBuilder.setSmallIcon(R.drawable.jx_ic_chat_default_contact);
            }

            Notification notification = mBuilder.build();
            notificationManager.notify(R.string.jx_imcoming_message_ticker_text, notification);
        } catch (Exception e) {
            L.e("lgh_jx", e);
        }
    }
}

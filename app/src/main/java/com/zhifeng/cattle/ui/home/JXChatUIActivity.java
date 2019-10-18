package com.zhifeng.cattle.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.jxccp.im.callback.JXEventListner;
import com.jxccp.im.chat.manager.JXEventNotifier;
import com.jxccp.im.chat.manager.JXImManager;
import com.lgh.huanglib.util.L;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.utils.Constanst;
import com.zhifeng.cattle.utils.jx.JXUiHelper;
import com.zhifeng.cattle.utils.jx.activity.JXBaseFragmentActivity;
import com.zhifeng.cattle.utils.jx.entities.JXCommodity;
import com.zhifeng.cattle.utils.jx.listener.JXChatGeneralCallback;
import com.zhifeng.cattle.utils.jx.utils.JXNotificationUtils;
import com.zhifeng.cattle.utils.jx.utils.JXPermissionUtil;
import com.zhifeng.cattle.utils.jx.view.JXChatView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ClassName:     客服对话界面
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/10/17 17:27
  * @Version:        1.0
 */

public class JXChatUIActivity extends JXBaseFragmentActivity implements JXEventListner, JXChatGeneralCallback, JXPermissionUtil.OnPermissionCallback{

    public static final String TAG = "chat";

    private JXChatFragment chatFragment;

    private List<Integer> functionImages = new ArrayList<>();

    private List<String> functionName =new ArrayList<>();

    private GetQuickQuestionTask getQuickQuestionTask;

    JXPermissionUtil mJXPermissionUtil;

    int permissionRequestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_jxchat_ui);
        // 聊天界面加载JXChatFragment
        chatFragment = new JXChatFragment();
        Bundle args = new Bundle();
        // 设置相应属性,CHAT_KEY为聊天的对象,访客这里为技能组的ID，CHAT_SKILLS_DISPLAYNAME为技能组的昵称
        args.putInt(JXChatView.CHAT_TYPE, getIntent().getIntExtra(JXChatView.CHAT_TYPE, 0));
        args.putString(JXChatFragment.CHAT_KEY, getIntent().getStringExtra(JXChatView.CHAT_KEY));
        args.putString(JXChatFragment.CHAT_SKILLS_DISPLAYNAME, getIntent().getStringExtra(JXChatView.CHAT_SKILLS_DISPLAYNAME));
        // 扩展信息
        args.putString(JXChatView.EXTEND_DATA, getIntent().getStringExtra(JXChatView.EXTEND_DATA));

        configFuntionRes();

        chatFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, chatFragment, TAG)
                .commit();

        chatFragment.configFunctionPanel(functionImages, functionName);

        chatFragment.setJXChatGeneralCallback(this);

        JXImManager.Message.getInstance().registerEventListener(this);

        JXUiHelper.getInstance().setChatPage(true);
        mJXPermissionUtil = new JXPermissionUtil();
    }

    private void configFuntionRes() {
        // 聊天界面功能面板的图片资源的id,这里数组的顺序与下面配置的string的顺序对应
        functionImages.add(R.drawable.bg_image_click);
        functionImages.add(R.drawable.bg_take_photo_click);
        if (JXImManager.McsUser.getInstance().canSendVideo()) {
            functionImages.add(R.drawable.bg_function_video);
        }

        functionName.add(getString(R.string.jx_image));
        functionName.add(getString(R.string.jx_take_photo));
        if (JXImManager.McsUser.getInstance().canSendVideo()) {
            functionName.add(getString(R.string.jx_videomsg));
        }

        if (JXImManager.McsUser.getInstance().isVisitorSatisfyOpen()) {
            functionImages.add(R.drawable.bg_evaluate_click);
            functionName.add(getString(R.string.jx_satisfaction_evaluate));
        }

        getQuickQuestionTask = new GetQuickQuestionTask(this);
        getQuickQuestionTask.execute();
    }

    /**发送图文消息**/
    public void sendRichTextMessage(JXCommodity commodity){
        chatFragment.sendRichText(commodity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JXNotificationUtils.cancelAllNotifty();
        JXUiHelper.getInstance().setChatPage(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JXImManager.Message.getInstance().removeEventListener(this);
        if (getQuickQuestionTask != null){
            getQuickQuestionTask.cancel(true);
        }
        mJXPermissionUtil = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        JXUiHelper.getInstance().setChatPage(false);
    }

    public boolean isVisiable(){
        if(chatFragment != null){
            return chatFragment.isVisiable;
        }
        return false;
    }

    /**
     * 当前界面是否为消息中心界面
     * @return
     */
    public boolean isMessagebox(){
        if(messageBoxFragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag("messageBox");
            if(fragment != null){
                return true;
            }
        }
        if(chatFragment != null){
            return chatFragment.isMessagebox();
        }
        return false;
    }

    @Override
    public void onFunctionItemClick(int position, int drawableResId) {
        if (drawableResId == R.drawable.bg_image_click) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.putExtra("return-data", true);
            chatFragment.startActivityForResult(intent, JXChatView.IMAGE_REQUEST_CODE);
        } else if (drawableResId == R.drawable.bg_take_photo_click) {
            permissionRequestCode = R.drawable.bg_take_photo_click;
            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M ){
                String[] permissions = new String[]{ Manifest.permission.CAMERA};
                mJXPermissionUtil.requestPermissions(this, permissionRequestCode, permissions, this);
            }else{
                takePhoto();
            }
        }else if (drawableResId == R.drawable.bg_function_video) {
            chatFragment.navToVideo();
        } else if (drawableResId == R.drawable.bg_evaluate_click) {
            chatFragment.showEvaluateWindow(null,false);
        } else if (drawableResId == R.drawable.bg_quick_question_click) {
            chatFragment.showQuickQuestionWindows();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (chatFragment.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onEvent(JXEventNotifier eventNotifier) {
        if (eventNotifier.getEvent() != JXEventNotifier.Event.MESSAGE_REVOKE) {
            JXNotificationUtils.vibrate(this);
        }
    }


    @Override
    public void onChatSession(boolean isWithRobot) {
        if(chatFragment != null){
            if(isWithRobot){
                List<Integer> robotFunction = new ArrayList<>();
                List<String> robotFunctionName = new ArrayList<>();
                robotFunction.add(R.drawable.bg_quick_question_click);
                robotFunctionName.add(getString(R.string.jx_function_quick_question));
                chatFragment.configFunctionPanel(robotFunction, robotFunctionName);
            }else{
                chatFragment.configFunctionPanel(functionImages, functionName);
            }
        }
    }

    private JXChatFragment messageBoxFragment =null;
    @Override
    public void onMessageBoxClick() {
        messageBoxFragment = new JXChatFragment();
        Bundle args = new Bundle();
        args.putInt(JXChatView.CHAT_TYPE, JXChatView.CHATTYPE_MESSAGE_BOX);
        args.putString(JXChatFragment.CHAT_SKILLS_DISPLAYNAME, getString(R.string.jx_message_center));
        messageBoxFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, messageBoxFragment, "messageBox")
                .addToBackStack("messageBox")
                .commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        JXChatFragment chatFragment = new JXChatFragment();
        Bundle args = new Bundle();
        // 设置相应属性,CHAT_KEY为聊天的对象,访客这里为技能组的ID，CHAT_SKILLS_DISPLAYNAME为技能组的昵称
        int chatType = intent.getIntExtra(JXChatView.CHAT_TYPE, 0);
        if(chatType == JXChatView.CHATTYPE_MESSAGE_BOX){
            args.putInt(JXChatView.CHAT_TYPE, chatType);
            args.putString(JXChatFragment.CHAT_KEY, intent.getStringExtra(JXChatView.CHAT_KEY));
            args.putString(JXChatFragment.CHAT_SKILLS_DISPLAYNAME, intent.getStringExtra(JXChatView.CHAT_SKILLS_DISPLAYNAME));
            chatFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag("messageBox");
            if(fragment != null){
                fragmentManager.popBackStackImmediate();
                fragmentManager.beginTransaction().remove(fragment).commit();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_container, chatFragment, "messageBox")
                        .addToBackStack("messageBox")
                        .commit();
            }else{
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, chatFragment, "messageBox")
                        .commit();
            }
        }
    }

    @Override
    public void onGranted() {
        takePhoto();
    }

    @Override
    public void onDenied() {
        Toast.makeText(getApplicationContext(), getString(R.string.jx_permission_denied), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println("activity permission code  = "+requestCode);
        mJXPermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (chatFragment!= null){
            chatFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    static class GetQuickQuestionTask extends AsyncTask<Boolean,Boolean,Boolean> {
        private WeakReference<JXChatUIActivity> weakAty;

        public GetQuickQuestionTask(JXChatUIActivity activity) {
            weakAty = new WeakReference<JXChatUIActivity>(activity);
        }

        @Override
        protected Boolean doInBackground(Boolean... params) {
            final List<String> quickQuestions = JXImManager.McsUser.getInstance().getQuickQuestions(JXUiHelper.getInstance().getSuborgId());
            if(isCancelled()){
                return false;
            }
            if (quickQuestions != null && quickQuestions.size()>0){
                return true;
            }else {
                return  false;
            }
        }

        @Override
        protected void onPostExecute(Boolean hasQuestions) {
            super.onPostExecute(hasQuestions);

            JXChatUIActivity activity = weakAty.get();
            if (activity == null
                    || activity.isFinishing()) {
                // activity没了,就结束可以了
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity == null
                        || activity.isDestroyed()) {
                    // activity没了,就结束可以了
                    return;
                }
            }

            JXChatUIActivity mActivity;
            if ((mActivity = (JXChatUIActivity) weakAty.get()) != null) {
                if (hasQuestions){
                    mActivity.functionImages.add(R.drawable.bg_quick_question_click);
                    mActivity.functionName.add(mActivity.getString(R.string.jx_function_quick_question));
                    mActivity.chatFragment.configFunctionPanel(mActivity.functionImages, mActivity.functionName);
                }
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("Mytask", "执行了取消");
        }
    }

    public void takePhoto(){
        if(chatFragment.isChatWithRobot){
            return;
        }
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                File dir = JXChatUIActivity.this.getExternalFilesDir("JXCameraPhoto");
                if (!dir.exists()){
                    dir.mkdirs();
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                chatFragment.cameraPhoto = new File(dir , System.currentTimeMillis() + ".jpg");
                Uri u;
                if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.M){
                    u = Uri.fromFile(chatFragment.cameraPhoto);
                }else {
                    String providerAuth = getApplication().getPackageName()+".fileprovider";
                    u = FileProvider.getUriForFile(this, Constanst.PROVIDER,chatFragment.cameraPhoto);
                }
                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
                chatFragment.startActivityForResult(intent, JXChatView.GET_IMAGE_VIA_CAMERA);
            } catch (Exception e) {
                L.e("lgh_jx","e  = "+e.getMessage());
                Toast.makeText(JXChatUIActivity.this, getString(R.string.jx_storage_dir_not_found), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(JXChatUIActivity.this, getString(R.string.jx_sdcard_not_found), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void finish() {
        hideInput();
        super.finish();
    }

    /**
     * 隱藏 輸入法
     */
    public void hideInput() {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
}

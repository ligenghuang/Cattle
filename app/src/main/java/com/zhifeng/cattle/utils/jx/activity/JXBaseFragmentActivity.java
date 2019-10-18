package com.zhifeng.cattle.utils.jx.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.fragment.app.FragmentActivity;

import com.jxccp.im.util.log.JXLog;
import com.zhifeng.cattle.utils.jx.JXContextWrapper;
import com.zhifeng.cattle.utils.jx.JXUiHelper;

import java.util.Locale;

/**
 * Created by xy on 2017/5/12.
 */
public class JXBaseFragmentActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        JXLog.e("basefragment oncreate");
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        Locale mLocate = JXUiHelper.getInstance().getLanguge();
        Context context = JXContextWrapper.wrap(newBase, mLocate);
        super.attachBaseContext(context);
    }
}

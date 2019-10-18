
package com.zhifeng.cattle.utils.jx.activity;

import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

import com.zhifeng.cattle.R;


public class JXWebViewActivity extends JXBaseActivity {

    public WebView webView;

    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.jx_activity_web_view);

        webView = (WebView)findViewById(R.id.wv_gif);

        url = getIntent().getStringExtra("gifUrl");
        
        if (!url.startsWith("http")) {
            url = "http://jiaxin.faqrobot.org" + url;
        } else {
            if (url.startsWith("https")) {
                url = url.replace("https", "http");
            }
        }

        webView.loadUrl(url);
    }
}

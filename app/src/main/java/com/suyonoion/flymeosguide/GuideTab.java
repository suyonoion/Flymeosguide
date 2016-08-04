package com.suyonoion.flymeosguide;

/**
 * Created by Suyono on 4/11/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GuideTab extends Activity {

    WebView webview;
    WebSettings websettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guidetab);

        webview=(WebView)findViewById(R.id.webview);
        websettings=webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("file:///android_asset/www/index.html");

    }
}
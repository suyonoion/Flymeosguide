package com.suyonoion.flymeosguide;

/**
 * Created by Suyono on 4/13/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class fitur extends Activity {

    WebView webview1;
    WebSettings websettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fitur);

        webview1=(WebView)findViewById(R.id.webview1);
        websettings=webview1.getSettings();
        websettings.setJavaScriptEnabled(true);
        webview1.setWebViewClient(new WebViewClient());
        webview1.loadUrl("file:///android_asset/www/fitur.html");

    }
}
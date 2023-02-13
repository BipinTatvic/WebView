package com.example.webview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url);
    }
}

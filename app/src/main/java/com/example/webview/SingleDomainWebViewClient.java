package com.example.webview;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SingleDomainWebViewClient extends WebViewClient {

    private static final String TAG = "SingleDomain";
    private final String url = "https://sublime-ga4.web.app/";
    private final Uri mDomainUrl;

    public SingleDomainWebViewClient(String domainUrl) {
        mDomainUrl = Uri.parse(url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Uri targetUrl = Uri.parse(url);
        if (!targetUrl.getHost().equals(mDomainUrl.getHost())) {
            // The WebView is trying to load a URL outside the specified domain,
            // override and ignore this request for security.
            Log.d(TAG, "Blocking request to " + url);

            // Reload home page
            webView.loadUrl(mDomainUrl.toString());
            return true;
        }

        return false;
    }

}

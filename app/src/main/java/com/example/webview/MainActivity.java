package com.example.webview;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private AnalyticsWebInterface analyticsWebInterface;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        analyticsWebInterface = new AnalyticsWebInterface(this);
        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
                                                                                         //            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    String user_pseudo_id = task.getResult();
                    Log.d("App instance ID ",user_pseudo_id);
                }
            }
        });
        // Restrict requests in the WebView to a single domain (in this case, our Firebase
        // Hosting domain) so that no other websites can call into our Java code.
        //String hostingUrl = getHostingUrl();
        webView.setWebViewClient(new SingleDomainWebViewClient("https://sublime-ga4.web.app/"));

        // [START add_javascript_interface]
        // Only add the JavaScriptInterface on API version JELLY_BEAN_MR1 and above, due to
        // security concerns, see link below for more information:
        // https://developer.android.com/reference/android/webkit/WebView.html#addJavascriptInterface(java.lang.Object,%20java.lang.String)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView.addJavascriptInterface(
                    new AnalyticsWebInterface(this), AnalyticsWebInterface.TAG);
        } else {
            Log.w(TAG, "Not adding JavaScriptInterface, API Version: " + Build.VERSION.SDK_INT);
        }
        // [END add_javascript_interface]

        // Navigate to site
        webView.loadUrl("https://sublime-ga4.web.app/");
    }

    /**
     * Determine the Firebase Hosting URL for this application by modifying the Firebase Database
     * URL. This constant will be used to limit the URLs that the WebView can display.
     */
//    private String getHostingUrl() {
//        // Database URl is https://<app-name>.firebaseio.com
//        String databaseUrl = getString(R.string.firebase_database_url);
//
//        // Hosting URL is https://<app-name>.firebaseapp.com
//        return databaseUrl.replace("firebaseio", "firebaseapp");
//    }

}

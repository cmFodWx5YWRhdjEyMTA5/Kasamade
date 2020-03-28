package Adapter;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by comsoftpc2 on 2/24/2016.
 */
public class MyBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}

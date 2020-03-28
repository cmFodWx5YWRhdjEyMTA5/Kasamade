package Adapter;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Sachin Khairnar on 5/6/2017.
 */

public class ourViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url)
    {
        v.loadUrl(url);
        return true;
    }
}
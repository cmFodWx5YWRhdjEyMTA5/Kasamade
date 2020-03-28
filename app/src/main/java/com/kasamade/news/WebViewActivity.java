package com.kasamade.news;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import Adapter.GifImageView;
import Adapter.ourViewClient;

import static android.view.View.SCROLL_INDICATOR_END;
import static android.view.View.SCROLL_INDICATOR_START;

public class WebViewActivity extends AppCompatActivity {
    //private WebView webview;

    private WebView mWebView;

    ProgressBar progressBar;
    TextView textView;
    LinearLayout li1;
    SwipeRefreshLayout swipeRefreshLayout;
    private GifImageView gifImageView;
    TextView nonetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_web_view);
      //  this.webview = (WebView)findViewById(R.id.webView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        textView = (TextView) findViewById(R.id.textView1212);
        final android.support.v7.app
                .ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("कसमादे न्यूज बर्ड");
        // WebView webView = (WebView) findViewById(R.id.webview);
        //webView.setVisibility(View.GONE);
        ColorDrawable newColor = new ColorDrawable(getResources().getColor(R.color.peach));//your color from res
        // newColor.setAlpha(200);//from 0(0%) to 256(100%)
        actionBar.setBackgroundDrawable(newColor);
        actionBar.setShowHideAnimationEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setShowHideAnimationEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);

        gifImageView = (GifImageView) findViewById(R.id.image1);
        nonetwork=(TextView) findViewById(R.id.nonetwork);
        li1=(LinearLayout)  findViewById(R.id.li1);





        mWebView = (WebView) findViewById(R.id.webview);
        getSupportActionBar().setTitle( (Html.fromHtml("<br><br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><small>   कसमादे न्यूज बर्ड</small></br></br>")));


        Bundle bundle = getIntent().getExtras();
        String URL = bundle.getString("URL");
        if(Config.isConnectingToInternet(getApplicationContext())==true) {
            startWebView(URL);
        }else{


            mWebView.setVisibility(View.GONE);
            li1.setVisibility(View.GONE);
            gifImageView.setVisibility(View.VISIBLE);
            nonetwork.setVisibility(View.VISIBLE);
            gifImageView.setGifImageResource(R.drawable.bird);


            nonetwork.setText("Sorry..No Internet Connection...!");
        }


        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setWebViewClient(new ourViewClient());

        mWebView.getSettings().setBuiltInZoomControls(true);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                progressBar.setProgress(progress);
                textView.setText(progress + " %");
            }
        });



       /* webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebViewClient(new ourViewClient());

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);






        webview.loadUrl(URL);*/





     /*   mWebView.setListener(this, this);
        mWebView.setGeolocationEnabled(false);
        mWebView.setMixedContentAllowed(true);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);
        mWebView.refreshDrawableState();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setDesktopMode(true);*/


       /* li1=(LinearLayout)  findViewById(R.id.li1);
        li1.setVisibility(View.GONE);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.Red, R.color.sky_blue);
        swipeRefreshLayout.setProgressViewOffset(false, SCROLL_INDICATOR_START,SCROLL_INDICATOR_END);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                if(isConnectingToInternet(getApplicationContext())==true) {
                  gifImageView.setVisibility(View.GONE);
                    nonetwork.setVisibility(View.GONE);

                }else{
                    li1.setVisibility(View.GONE);
                    nonetwork.setText("No Internet Connection...!");

                }

            }

        });
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }

        });

        //mWebView.addHttpHeader("X-Requested-With", "");


        if(isConnectingToInternet(getApplicationContext())==true) {
            //gifImageView.setVisibility(View.GONE);
            mWebView.loadUrl(URL);
        }else{
            li1.setVisibility(View.GONE);
          //  gifImageView.setGifImageResource(R.drawable.no_internet);

        }



        if (ActivityCompat.checkSelfPermission(WebViewActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // External Storage permission has not been granted.
            requestPermission();
        }




*/

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.Red, R.color.sky_blue);
        swipeRefreshLayout.setProgressViewOffset(false, SCROLL_INDICATOR_START,SCROLL_INDICATOR_END);
        swipeRefreshLayout.setEnabled(true);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                String webUrl = mWebView.getUrl();


                startWebView(webUrl);

                swipeRefreshLayout.setRefreshing(false);



            }
        });
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(false);



                                        //requestMsgData(PHOTO_NAME_URL);
                                    }
                                }
        );



    }





    @SuppressLint("JavascriptInterface")
    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        mWebView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;




            //If you do not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(WebViewActivity.this);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();

                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                    progressDialog.dismiss();


                }
            }

        });

        // Javascript inabled on webview
        mWebView.getSettings().setJavaScriptEnabled(true);

        // Other webview options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        */

        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null);
         */

        //Load url in webview
        mWebView.loadUrl(url);


    }


    private void requestPermission() {
        Log.i("Hi", "External Storage permission has NOT been granted. Requesting permission.");
        ActivityCompat.requestPermissions(WebViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2909: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", "Granted");
                } else {
                    Log.e("Permission", "Denied");
                }
                return;
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }*/

   /* @Override
    public void onPageStarted(String url, Bitmap favicon) {
        //mWebView.setVisibility(View.INVISIBLE);
  //  pDialog.show();



        //swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(String url) {

     //   pDialog.dismiss();
        li1.setVisibility(View.VISIBLE);
        gifImageView.setVisibility(View.GONE);
        nonetwork.setVisibility(View.GONE);
        // gifImageView.setVisibility(View.INVISIBLE);

        //  swipeRefreshLayout.setVisibility(View.INVISIBLE);
    }*/

    /*@Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Toast.makeText(WebViewActivity.this, "onPageError(errorCode = "+errorCode+",  description = "+description+",  failingUrl = "+failingUrl+")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        Toast.makeText(WebViewActivity.this, "onDownloadRequested(url = "+url+",  suggestedFilename = "+suggestedFilename+",  mimeType = "+mimeType+",  contentLength = "+contentLength+",  contentDisposition = "+contentDisposition+",  userAgent = "+userAgent+")", Toast.LENGTH_LONG).show();

        if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
            // download successfully handled
            Toast.makeText(WebViewActivity.this, "Successful", Toast.LENGTH_LONG).show();
        }
        else {
            // download couldn't be handled because user has disabled download manager app on the device
        }
    }

    @Override
    public void onExternalPageRequest(String url) {
        Toast.makeText(WebViewActivity.this, "onExternalPageRequest(url = "+url+")", Toast.LENGTH_SHORT).show();
    }*/
}

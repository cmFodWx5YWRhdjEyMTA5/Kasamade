package com.kasamade.news;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import Adapter.MyBrowser;


/**
 * Created by admin on 1/29/2016.
 */
public class Config {

    //public final static String global_link = "http://192.168.0.120/ifs/";
    //public final static String global_link = "http://www.samosahub.com/demo/ifs/";
    public final static String global_link = "http://kasamadenews.androideducator.com/";

    public final static String global_link1 = "http://kasamadenews.androideducator.com/photos/";
    public final static String WEB_SERVICE_URL="http://www.test9.modelspoint.com/tptwebservice.asmx";
    public final static String NAMESPACE ="http://comsoftindia.com/";
    public final static String APP_KEY ="requestfromtptapp";
    public static String SOAP_ACTION_FOR_NOTIFICAION;

    public static boolean NIGHT_MODE=false;
    public static boolean NOTIFICATION;
    public final static String COLOR_WHITE="#ffffff";
    public final static String COLOR_BLACK="#000000";
    public final static String COLOR_NIGHT="#808080";
    public static int Menu =0;

    public static String str_registrationId = "";
    public static String SOAP_ACTION_FOR_ID ="GetTableIds";
    public static String SOAP_ACTION_FOR_DATA ="GetInfo";
    public static String IMAGE_URL="http://www.test9.modelspoint.com/Admin/NewsImage/";
    public static String VIDEO_URL[];

    public static int CID=-1;
    public static String CHANNEL_ID[];
    public static String SELECTED_CHANNEL_NAME;
    public static String CHANNEL_NAME[];
    public static String CHANNEL_IMG[];

    public static int LID=-1;
    public static String SELECTED_LANGUAGE_NAME;
    public static String LANGUAGE_ID[];
    public static String LANGUAGE_NAME[];



    public static int TRANSFORMER;

    public static String  IMAGE_NAME = "/kasamadenewsbird/ans.png";
    public static String  SHARE_BODY = "वेळ वाचवा.कसमादे न्यूज बर्ड मधील प्रत्येक बातमी ६० शब्दाची असेल,आता download करा. \nhttps://play.google.com/store/apps/details?id=com.kasamade.news"  + "\n\n" + "Application Developed By Sachin Khairnar" + "\n" + "http://www.androideducator.com";

    public static LruCache<String, Bitmap> mMemoryCache;


    public static String WEB_PAGE_URL="http://www.test9.modelspoint.com/NewsArticleDetail.aspx?aid=";
    public static final String YOUTUBE_API_KEY = "YOUR API KEY";



    //public final static String COLOR_WHITE="";
    public static boolean isNetworkAvailable(final Context context,WebView webView) {

        if(((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() == null)
        {
            webView.setVisibility(View.VISIBLE);
            //Toast.makeText(getApplicationContext(), "Turn on Internet Connection", Toast.LENGTH_SHORT).show();
            MyBrowser myBrowser = new MyBrowser();
            myBrowser.shouldOverrideUrlLoading(webView, "file:///android_asset/nonetwork.html");
            return false;
        }
        else
        {
            return  true;
        }
    }


    public  static void setCacheMemory()
    {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
         mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public static void setDayNight(TextView tv_title, TextView tv_desc, TextView tv_Tpt, LinearLayout backgroud){
        if (Config.NIGHT_MODE) {
            tv_title.setTextColor(Color.parseColor(Config.COLOR_WHITE));
            tv_desc.setTextColor(Color.parseColor(Config.COLOR_WHITE));
            tv_Tpt.setTextColor(Color.parseColor(Config.COLOR_WHITE));
            backgroud.setBackgroundColor(Color.parseColor(Config.COLOR_NIGHT));
        } else {
            tv_title.setTextColor(Color.parseColor(Config.COLOR_BLACK));
            tv_desc.setTextColor(Color.parseColor(Config.COLOR_BLACK));
            tv_Tpt.setTextColor(Color.parseColor(Config.COLOR_BLACK));
            backgroud.setBackgroundColor(Color.parseColor(Config.COLOR_WHITE));
        }

    }
    public final static boolean isConnectingToInternet(Context con) {
        ConnectivityManager connectivity = (ConnectivityManager) con
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static Uri copyAssets(Activity act) {


        File file = new File(Environment.getExternalStorageDirectory().toString().concat(Config.IMAGE_NAME));
        File dir= new File(Environment.getExternalStorageDirectory().toString().concat("/kasamadenewsbird"));
        dir.mkdir();

        Uri imageUri = Uri.parse("") ;
        //Config.copyAssets(act);
        if(file.exists())
        {
            imageUri = Uri.fromFile(file);
        }
        else{

            imageUri = Uri.fromFile(file);
        }


        String destFile = Environment.getExternalStorageDirectory().toString().concat(IMAGE_NAME);


        try {

            File f2 = new File(destFile);
            InputStream in = act.getAssets().open("ic_launcher1.png");
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
        } catch (FileNotFoundException ex) {
            System.out
                    .println(ex.getMessage() + " in the specified directory.");

        } catch (IOException e) {
            System.out.println("- "+e.getMessage());
        }
        return  imageUri;
    }

    public static Uri share_screen_shots(View item_view ) {
        Uri bmpUri = Uri.parse("");
        try{
        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + new Random() + ".jpg";


        // create bitmap screen capture
        View v1 = item_view;
        v1.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);

        File imageFile = new File(mPath);

        FileOutputStream outputStream = new FileOutputStream(imageFile);
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        outputStream.flush();
        outputStream.close();
         bmpUri = Uri.fromFile(imageFile);
         }
        catch (Throwable e) {

        // Several error may come out with file handling or OOM
        Log.i("Screenshot",e.toString());
    }
        return bmpUri;
    }

}



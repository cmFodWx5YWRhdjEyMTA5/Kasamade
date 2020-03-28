package com.kasamade.news;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import Adapter.Util1;
import io.fabric.sdk.android.Fabric;

public class Splash_Screen_Activity extends AppCompatActivity {

    AppPrefs appPrefs;
    public static final String MY_PREFS_NAME1 = "MyPrefsFile1";
    public static final String PREF_USER_FIRST_TIME1 = "user_first_time1";
    public static SharedPreferences initialization;
    public static SharedPreferences.Editor editor;
    boolean isUserFirstTime1;
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {

        @Override public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request newRequest;

            newRequest = request.newBuilder()
                    .header("Cache-Control", "max-age=120")
                    .addHeader("Cache-Control", "max-age=120")
                    .build();
            Response originalResponse = chain.proceed(newRequest);
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=120")
                    .build();
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash__screen_);
        isUserFirstTime1 = Boolean.valueOf(Util1.readSharedSetting1(Splash_Screen_Activity.this, PREF_USER_FIRST_TIME1, "true"));
        try {
        appPrefs = new AppPrefs(Splash_Screen_Activity.this);
            initialization = getSharedPreferences(MY_PREFS_NAME1, MODE_PRIVATE);
            editor = getSharedPreferences(MY_PREFS_NAME1, MODE_PRIVATE).edit();
        getSupportActionBar().hide();




            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    appPrefs = new AppPrefs(Splash_Screen_Activity.this);

                    if (isUserFirstTime1) {
                        Intent introIntent = new Intent(Splash_Screen_Activity.this, LoginActivity.class);
                        introIntent.putExtra(PREF_USER_FIRST_TIME1, isUserFirstTime1);
                        startActivity(introIntent);
                        finish();

                    } else {
                        startActivity(new Intent(Splash_Screen_Activity.this, MainActivity.class));
                        finish();

                    }

                }
            }, 2000);

        }
        catch (Exception e)
        {
            System.out.println("catch");
            generateNoteOnSD(getApplicationContext(),"IFS Log.txt",e.getMessage());
        }


    }
    public static void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
   //     alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);
       /* TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
        textView.setTextSize(40);*/
        // Setting alert dialog icon
       alertDialog.setIcon((status) ? R.drawable.sucess : R.drawable.fail);


        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "IFS");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    @NonNull
    public static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        return okHttpClient;
    }
}

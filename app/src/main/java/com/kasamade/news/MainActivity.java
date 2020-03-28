package com.kasamade.news;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.LruCache;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import Adapter.CustomViewPager;
import Adapter.Custom_Language_Popup_List;
import Adapter.Util;
import AllFragment.News_Article_Fragment;
import gcm.QuickstartPreferences;
import gcm.RegistrationIntentService;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CustomViewPager viewPager;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    Activity act;
    public static final String PHOTO_BASE_URL = "http://kasamadenews.androideducator.com/photos/";
    LruCache mMemoryCache;

    public static Context context;
    NavigationView navigationView;
    DrawerLayout drawer_layout;
    private boolean showActions = false;
    boolean isUserFirstTime;
    int id;
    Typeface tf;
    LinearLayout layout_news_articles, layout_birthday, layout_video,layout_all_paper, layout_offer,
            layout_place, layout_tc, layout_weather, layout_films, layout_add_to_wishlist,
            layout_share, layout_language_selection, layout_search, layout_bookmark, layout_trending,layout_science,layout_death,layout_Contact,layout_all_job;

    TextView tv_news_articles, tv_birthday, tv_videos,
            tv_offer, tv_place, tv_tc, tv_weather,
            tv_films, tv_add_to_wishlist, tv_share, tv_language, tv_search,tv_bookmark,tv_trending;
    ListView list;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Custom_Language_Popup_List custom_Language_Popup_List;
    CoordinatorLayout cordinCoordinatorLayout;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    FrameLayout frame_container;
    AppPrefs appPrefs;
    Dialog myDialog;
    int flagvalue=0;
    String[] language_id;
    String[] laungage_name;
    ListView list_language;
    CheckBox checkBox;
    Button submit;
    int noitifyid;
    android.support.v7.app.ActionBar actionBar;
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    ImageView actionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        isUserFirstTime = Boolean.valueOf(Util.readSharedSetting(MainActivity.this, PREF_USER_FIRST_TIME, "true"));

        Intent introIntent = new Intent(MainActivity.this, PagerActivity.class);
       // Intent introIntent1 = new Intent(MainActivity.this, WebViewActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        if (isUserFirstTime) {
            System.out.println("Sachin");
            startActivity(introIntent);

         //   startActivity(introIntent1);


        }



        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }



        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_main);
        context = MainActivity.this;


        Intent intent1 = getIntent();
        try {

            if(intent1.getAction().toUpperCase().isEmpty())
            {
                id=0;
            }else{
                id = Integer.parseInt(intent1.getAction().toUpperCase());

                flagvalue=1;

            }


        }catch(Exception e)
        {
            id=0;
        }



        tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        appPrefs = new AppPrefs(MainActivity.this);
        Config.TRANSFORMER = appPrefs.getTransformer_ID();


        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<br><br><br></br><br></br><br></br><br></br><small>इंडिया न्यूज बर्ड</small></br></br>"));

        ColorDrawable newColor = new ColorDrawable(getResources().getColor(R.color.peach));//your color from res
        // newColor.setAlpha(200);//from 0(0%) to 256(100%)
        actionBar.setBackgroundDrawable(newColor);
        actionBar.setShowHideAnimationEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setShowHideAnimationEnabled(true);

        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);


        Log.i("Main", "Create");


        act = this;

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);


        fetchID();
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {

                } else {

                }
            }
        };

       /* try
        {
            GCMRegistrar.checkDevice(MainActivity.this);
            GCMRegistrar.checkManifest(MainActivity.this);
            GCMRegistrar.register(MainActivity.this, GCMIntentService.SENDER_ID);
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }*/


        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.

            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
            //language_popup();
        }



    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

    private void fetchID() {
        tv_news_articles = (TextView) findViewById(R.id.tv_news_articles);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_videos = (TextView) findViewById(R.id.tv_videos);
        tv_offer = (TextView) findViewById(R.id.tv_offer);
        tv_place = (TextView) findViewById(R.id.tv_place);
        tv_tc = (TextView) findViewById(R.id.tv_tc);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
      //  tv_films = (TextView) findViewById(R.id.tv_films);
      //  tv_add_to_wishlist = (TextView) findViewById(R.id.tv_add_to_wishlist);
        tv_share = (TextView) findViewById(R.id.tv_share);
       // tv_language = (TextView) findViewById(R.id.tv_language);
     //   tv_search = (TextView) findViewById(R.id.tv_search);
        tv_bookmark=(TextView)findViewById(R.id.tv_Bookmark);

       // tv_trending=(TextView)findViewById(R.id.tv_trending);
      //  tv_films=(TextView)findViewById(R.id.tv_films) ;

        tv_news_articles.setTypeface(tf);
        tv_birthday.setTypeface(tf);
        tv_videos.setTypeface(tf);
        tv_offer.setTypeface(tf);
        tv_place.setTypeface(tf);
        tv_tc.setTypeface(tf);
        tv_weather.setTypeface(tf);
//        tv_films.setTypeface(tf);
      //  tv_add_to_wishlist.setTypeface(tf);
        tv_share.setTypeface(tf);
    //    tv_language.setTypeface(tf);
    //    tv_search.setTypeface(tf);
        tv_bookmark.setTypeface(tf);
//        tv_trending.setTypeface(tf);


        layout_bookmark = (LinearLayout) findViewById(R.id.layout_Bookmark);
      //  layout_trending = (LinearLayout) findViewById(R.id.layout_trending);

        layout_news_articles = (LinearLayout) findViewById(R.id.layout_news_articles);
        layout_birthday = (LinearLayout) findViewById(R.id.layout_birthday);
        layout_video = (LinearLayout) findViewById(R.id.layout_video);
        layout_offer = (LinearLayout) findViewById(R.id.layout_offer);
        layout_place = (LinearLayout) findViewById(R.id.layout_place);
        layout_tc = (LinearLayout) findViewById(R.id.layout_tc);
        layout_weather = (LinearLayout) findViewById(R.id.layout_weather);
        layout_science = (LinearLayout) findViewById(R.id.layout_science);
        layout_death = (LinearLayout) findViewById(R.id.layout_death);
        layout_share = (LinearLayout) findViewById(R.id.layout_share);
        layout_Contact=(LinearLayout)findViewById(R.id.layout_Contact);
       // layout_language_selection = (LinearLayout) findViewById(R.id.layout_language_selection);
        layout_search = (LinearLayout) findViewById(R.id.layout_search);

        layout_all_paper=(LinearLayout) findViewById(R.id.layout_all_paper);


        layout_all_job=(LinearLayout) findViewById(R.id.layout_all_job);

        int displayid = id % 10;

        noitifyid=id/10;
        displayView(displayid);
        frame_container = (FrameLayout) findViewById(R.id.frame_container);
        frame_container.setOnTouchListener(new View.OnTouchListener() {
            float x1, x2, y1, y2;
            int MIN_DISTANCE = 150;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Toast.makeText(MainActivity.this, "Touch", Toast.LENGTH_SHORT).show();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();
                        float deltaX = x2 - x1;
                        float deltaY = y2 - y1;

                        if (Math.abs(deltaX) > MIN_DISTANCE || Math.abs(deltaY) > MIN_DISTANCE) {

                        } else {
                            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                            if (actionBar.isShowing()) {
                                actionBar.hide();
                            } else {
                                actionBar.show();
                            }
                        }
                        break;

                }
                return false;

            }
        });

        layout_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                share.putExtra(Intent.EXTRA_TEXT, Config.SHARE_BODY);

                System.out.println("imageUri " + Config.IMAGE_NAME);

                share.putExtra(Intent.EXTRA_STREAM, Config.copyAssets(MainActivity.this));
                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(share);
            }
        });


        layout_news_articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle((Html.fromHtml("<font color='#FFBF00'>  न्यूज बर्ड</font>")));

                displayView(0);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("0");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });
        layout_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("    न्यूज बर्ड");

                displayView(1);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("2");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });
        layout_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");

                displayView(2);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("3");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });
        layout_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");



                displayView(3);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("4");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });
        layout_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");

                displayView(4);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("5");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }

            }
        });
        layout_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle((Html.fromHtml("<small>  न्यूज बर्ड</small>")));


                displayView(5);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("6");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });
        layout_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");
                displayView(6);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("1");


                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });


        layout_all_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");
                displayView(6);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("1");


                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });


        layout_death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");
                displayView(7);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("8");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });



        layout_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");
                displayView(8);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("7");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }


            }
        });




        layout_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");


                displayView(9);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("10");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });


        layout_all_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");


                displayView(10);
            //    appPrefs.remove_Positon_id_prefs();
              //  appPrefs.setPositon_id("10");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });





        layout_all_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");


                displayView(11);
                //    appPrefs.remove_Positon_id_prefs();
                //  appPrefs.setPositon_id("10");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });


        layout_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("   न्यूज बर्ड");
                String url="http://kasamadenews.androideducator.com/other/contact.php";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("URL",url );
                startActivity(intent);


                /*displayView(11);
                appPrefs.remove_Positon_id_prefs();
                appPrefs.setPositon_id("10");
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                }*/
            }
        });



    }




    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final Dialog dialog = new Dialog(MainActivity.this);
            // Include dialog.xml file
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);

            dialog.setContentView(R.layout.dialog1);
            dialog.show();



            Button declineButton = (Button) dialog.findViewById(R.id.btn_dialog);
            // if decline button is clicked, close the custom dialog
            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    dialog.dismiss();
                }
            });




            Button declineButton1 = (Button) dialog.findViewById(R.id.btn_dialog1);
            // if decline button is clicked, close the custom dialog
            declineButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                   finish();
                }
            });








        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (drawer_layout.isDrawerOpen(navigationView) == true) {
                drawer_layout.closeDrawer(Gravity.LEFT);
            } else {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        }

      /*  if (id == R.id.action_arrow) {

            displayView(Integer.parseInt(appPrefs.getPositon_id()));
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Main", "Restart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main", "Resume" + Config.Menu);


        frame_container.setOnTouchListener(new View.OnTouchListener() {
                                               float x1, x2, y1, y2;
                                               int MIN_DISTANCE = 150;

                                               @Override
                                               public boolean onTouch(View v, MotionEvent event) {

                                                   switch (event.getAction()) {
                                                       case MotionEvent.ACTION_DOWN:
                                                           x1 = event.getX();
                                                           y1 = event.getY();
                                                           break;
                                                       case MotionEvent.ACTION_UP:
                                                           x2 = event.getX();
                                                           y2 = event.getY();
                                                           float deltaX = x2 - x1;
                                                           float deltaY = y2 - y1;

                                                           if (Math.abs(deltaX) > MIN_DISTANCE || Math.abs(deltaY) > MIN_DISTANCE) {

                                                           } else {
                                                               android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                                                               if (actionBar.isShowing())
                                                                   actionBar.hide();
                                                               else
                                                                   actionBar.show();
                                                           }
                                                           break;

                                                   }
                                                   return false;

                                               }
                                           }

        );
        try

        {

        } catch (
                Exception e
                )

        {
            Log.i("Main ", e.toString());
        }



    }



    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }


    protected void setactionbar() {

        ActionBar mActionBar = getSupportActionBar();
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        tv.setLayoutParams(lp);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);


        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setShowHideAnimationEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater mInFlater = LayoutInflater.from(MainActivity.this);

        View mCustomView = mInFlater.inflate(R.layout.custom_actionbar, null);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        mActionBar.setCustomView(mCustomView, layoutParams);


        TextView tv_action_title = (TextView) mCustomView.findViewById(R.id.tv_action_title);
        ImageView im_left_image = (ImageView) mCustomView.findViewById(R.id.im_left_image);
        ImageView im_right_image = (ImageView) mCustomView.findViewById(R.id.im_right_image);


        tv_action_title.setTypeface(tf);


        im_left_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub\
                if (drawer_layout.isDrawerOpen(navigationView) == true) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);
                }

            }
        });



        mActionBar.setCustomView(mCustomView);


    }

    @Override
    protected void onPause() {
        Log.i("Main", "Pause" + Config.Menu);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();

    }










    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:

                if(flagvalue==0) {
                    fragment = new News_Article_Fragment();
                    displayActionTitle();
                }else if(flagvalue==1)
                {

                    fragment = new News_Article_Fragment();

                    Bundle args = new Bundle();
                    args.putString("noitifyid", String.valueOf(noitifyid));
                    args.putString("Notification", "on");
                    fragment.setArguments(args);
                    displayActionTitle();
                    flagvalue=0;

                }




                break;
            case 1:
                //startActivity(new Intent(NavigationMenu.this,SelectCity.class));

                fragment = new AllFragment.Video_Fragment();
                Bundle args = new Bundle();
                args.putString("noitifyid", String.valueOf(noitifyid));
                args.putString("Notification", "on");
                fragment.setArguments(args);
                displayActionTitle();



                break;
            case 2:
                //startActivity(new Intent(NavigationMenu.this,NotificationList.class));\
                fragment = new AllFragment.Offer_Fragment();

                Bundle args1 = new Bundle();
                args1.putString("noitifyid", String.valueOf(noitifyid));
                args1.putString("Notification", "on");
                fragment.setArguments(args1);
                displayActionTitle();
                //getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");

                break;
            case 3:
                //  startActivity(new Intent(NavigationMenu.this,NearBy_Search.class));
                fragment = new AllFragment.Place_Fragment();


                Bundle args2 = new Bundle();
                args2.putString("noitifyid", String.valueOf(noitifyid));
                args2.putString("Notification", "on");
                fragment.setArguments(args2);
                displayActionTitle();
                // getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");

                break;
            case 4:
                //startActivity(new Intent(NavigationMenu.this,BuyMembership.class));
                fragment = new AllFragment.Precautions_Fragment();
                //  getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");

                break;
            case 5:
                //startActivity(new Intent(NavigationMenu.this,ContactUs.class));
                fragment = new AllFragment.Weather_Fragment();
                //  getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");

                break;
            case 6:
                //startActivity(new Intent(NavigationMenu.this,Terms_and_condition.class));

                fragment = new AllFragment.Birthday_Fragment();


                Bundle args3 = new Bundle();
                args3.putString("noitifyid", String.valueOf(noitifyid));
                args3.putString("Notification", "on");
                fragment.setArguments(args3);
                displayActionTitle();


                //getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");


                break;
            case 7:
                //startActivity(new Intent(NavigationMenu.this,Terms_and_condition.class));
                fragment = new AllFragment.Death_Fragment();

                Bundle args4 = new Bundle();
                args4.putString("noitifyid", String.valueOf(noitifyid));
                args4.putString("Notification", "on");
                fragment.setArguments(args4);
                displayActionTitle();

                //  getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");

                break;
            case 8:
                //startActivity(new Intent(NavigationMenu.this,Terms_and_condition.class));
                fragment = new AllFragment.Science_Fragment();


                Bundle args5 = new Bundle();
                args5.putString("noitifyid", String.valueOf(noitifyid));
                args5.putString("Notification", "on");
                fragment.setArguments(args5);
                displayActionTitle();
                //   getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");

                break;


            case 9:
                //startActivity(new Intent(NavigationMenu.this,Terms_and_condition.class));
                fragment = new AllFragment.Bookmark_Fragment();
                //  getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");




                break;


            case 10:
                //startActivity(new Intent(NavigationMenu.this,Terms_and_condition.class));
                fragment = new AllFragment.All_News_Paper();
                //  getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");




                break;


            case 11:
                //startActivity(new Intent(NavigationMenu.this,Terms_and_condition.class));
                fragment = new AllFragment.All_Jobs();
                //  getSupportActionBar().setTitle("   कसमादे न्यूज बर्ड");




                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
            drawer_layout.closeDrawer(navigationView);


        } else {
            // error in creating fragment

        }

    }

    private void displayActionTitle()
    {
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarlayout);
        actionView=(ImageView) findViewById(R.id.im_left_image);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
    }




}

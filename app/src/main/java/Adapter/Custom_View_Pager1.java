package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kasamade.news.Config;
import com.kasamade.news.R;
import com.kasamade.news.WebViewActivity;
import com.kasamade.news.YouTube_Test;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import Bean.Bean_Master;
import ConnetionHandler.SQLController;
import ImageLoader.ImageLoader;

/**
 * Created by Prince on 7/22/2016.
 */
public class Custom_View_Pager1 extends PagerAdapter {


    private Context ctx;
    private Activity act;
    private LayoutInflater layoutInflater;

    private WebView wv;
    public static SQLController dbconMsg;
    String Position;
    private LruCache<String, Bitmap> mMemoryCache;

    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

    ArrayList<Bean_Master> array_bean = new ArrayList<Bean_Master>();
    ImageLoader imageloader;
    public Custom_View_Pager1(final Context context,Activity act, ArrayList<Bean_Master> array_bean) {
        this.ctx = context;
        this.act=act;
        imageloader = new ImageLoader(act);
        this.array_bean = array_bean;
    }

    @Override
    public int getCount()
    {
        return array_bean.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        final  View item_view = (View) layoutInflater.inflate(R.layout.swipe_layout, container, false);
        item_view.setBackgroundColor(Color.parseColor("#ffffff"));
        final TextView tv_desc = (TextView) item_view.findViewById(R.id.desc);
        final TextView tv_title = (TextView) item_view.findViewById(R.id.posttitile);
        TextView tv_postby = (TextView) item_view.findViewById(R.id.postby);
        Button btn_readmore = (Button) item_view.findViewById(R.id.btn_readmore);


        final ImageView[] imageView = {(ImageView) item_view.findViewById(R.id.imageView)};
        ImageView youtube_icon = (ImageView) item_view.findViewById(R.id.outside_imageview);
        wv=(WebView) item_view.findViewById(R.id.webView1);
        Typeface myfont = Typeface.createFromAsset(ctx.getAssets(), "fonts/Roboto-Light.ttf");
        Typeface myfontbold = Typeface.createFromAsset(ctx.getAssets(), "fonts/Roboto-Regular.ttf");
        Button btn_readmore1= (Button) item_view.findViewById(R.id.btn_readmore1);

        TextView postby = (TextView) item_view.findViewById(R.id.postby);
        btn_readmore1.setTypeface(myfont);
//        postby.setTypeface(myfont);

        ImageButton fab = (ImageButton)item_view.findViewById(R.id.fab);
        final ImageButton btn_bookmark = (ImageButton) item_view.findViewById(R.id.bookmark);
        tv_desc.setText(array_bean.get(position).getDesc());
        tv_title.setText(array_bean.get(position).getTitle());
        // tv_postby.setText("IFS by " + array_bean.get(position).getAuthor());
        //btn_readmore.setText(array_bean.get(position).getSource_name());


        tv_title.setTypeface(myfontbold);
        tv_desc.setTypeface(myfont);
        System.out.println("Image url- " + Config.global_link + array_bean.get(position).getImage_url());
        System.out.println("Position url- " +  array_bean.get(position).getId());
        Position=array_bean.get(position).getId();


        try {
            dbconMsg = new SQLController(ctx);
            dbconMsg.open();
            Cursor c = dbconMsg.readData();

            if (c.moveToFirst()) {
                do {
                    //  String positin1=array_bean.get(position+1).getId();
                  /*  System.out.println("Id Stored- " + c.getString(0));
                    System.out.println(" BookMarkidId Stored- " + c.getString(1));
                    System.out.println("title Id Stored- " + c.getString(2));
                    System.out.println("description Id Stored- " + c.getString(3));
                    System.out.println("sourcename Id Stored- " + c.getString(4));*/
                    int value= Integer.parseInt(Position);
                    value=value-1;
                    System.out.println(c.getString(1));
                    System.out.println("Positon- " + Position);

                    System.out.println(c.getString(1).equals(String.valueOf(value)));
                    if (c.getString(1).equals(String.valueOf(Position))) {
                        //  isPressed = true;
                        System.out.println("Done");
                        btn_bookmark.setBackgroundResource(R.drawable.bookmark_black);
                    }
                } while (c.moveToNext());
            }
            dbconMsg.close();
        }catch(Exception e)
        {

        }


    /*try {
        Cursor c = dbconMsg.readData();
        Cursor cAll = dbconMsg.readForAllData();
        System.out.println("Id Stored- " +  c.getString(1));
        int flag=0;
        if (c.moveToFirst() ) {
            do {

               System.out.println("Id Stored- " +  c.getString(1));
                System.out.println("Positon- " +  Position);
                System.out.println(c.getString(1).equals(Position));
              if(c.getString(1).equals(Position) )
                {
                  //  isPressed = true;
                    System.out.println("Done");
                    btn_bookmark.setBackgroundResource(R.drawable.bookmark_black);
                    flag=1;
                }
                else{
                   isPressed = false;
                  //btn_bookmark.setBackgroundResource(R.drawable.bookmark_black);
                 // btn_bookmark.setBackgroundResource(R.drawable.bookmark);

                }


            } while (c.moveToNext() && flag==0);
        }
    }catch (Exception e)
    {

    }
*/



        final boolean[] isPressed = {true};
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isPressed[0]) {
                    try {
                        btn_bookmark.setBackgroundResource(R.drawable.bookmark_black);
                        //    System.out.println("Inser Id- " + Position);                      /* implement book mark here*/
                        System.out.println("Array position"+Position);
                        dbconMsg = new SQLController(ctx);
                        dbconMsg.open();
                        dbconMsg.insertData(Position,array_bean.get(position).getImage_url(),array_bean.get(position).getTitle(), array_bean.get(position).getDesc(),array_bean.get(position).getSource_name(),0);
                        dbconMsg.close();
                    }catch(Exception e)
                    {

                    }
                }
                else {
                    btn_bookmark.setBackgroundResource(R.drawable.bookmark);
                    dbconMsg = new SQLController(ctx);
                    dbconMsg.open();
                    dbconMsg.deleteData(Position);
                    dbconMsg.close();
                    System.out.println("Delete url- " + Position);
                }

                isPressed[0] = !isPressed[0];
            }
        });




        //imageloader.DisplayImage("https://www.ricoh.com/r_dc/caplio/r7/img/sample_01.jpg", R.drawable.banner, imageView);
        Picasso.with(act).load(Config.global_link1 + array_bean.get(position)
                .getImage_url())
                .fit()
                .placeholder(R.drawable.placeholder_dark)
                .into(imageView[0], new Callback() {
                    @Override
                    public void onSuccess() {




                        //MaterialImageLoading.animate(imageView).setDuration(2000).start();
                    }

                    @Override
                    public void onError() {

                    }
                });

        //imageloader.DisplayImage("https://www.ricoh.com/r_dc/caplio/r7/img/sample_01.jpg", R.drawable.banner, imageView);
     /* if(array_bean.get(position).getNews_type().equalsIgnoreCase("null")||array_bean.get(position).getNews_type().equalsIgnoreCase("")) {
            if(array_bean.get(position).getImage_url().equalsIgnoreCase(""))
            {
                imageView[0].setImageResource(R.drawable.banner);
            }
            else {
                Picasso.with(act).load(Config.global_link + array_bean.get(position)
                        .getImage_url())
                        .fit()
                        .placeholder(R.drawable.banner)
                        .into(imageView[0], new Callback() {
                            @Override
                            public void onSuccess() {
                                //MaterialImageLoading.animate(imageView).setDuration(2000).start();
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }
        }
        else
        {
            if (array_bean.get(position).getNews_type().equalsIgnoreCase("1")) {
                youtube_icon.setVisibility(View.GONE);
                if(array_bean.get(position).getImage_url().equalsIgnoreCase(""))
                {
                    imageView[0].setImageResource(R.drawable.banner);
                }
                else {
                    Picasso.with(act).load(Config.global_link + array_bean.get(position)
                            .getImage_url())
                            .fit()

                            .placeholder(R.drawable.banner)
                            .into(imageView[0], new Callback() {

                                @Override
                                public void onSuccess() {
                                   // MaterialImageLoading.animate(imageView).setDuration(2000).start();
                                }

                                @Override
                                public void onError() {

                                }
                            });
                }
            }*/
        if (array_bean.get(position).getNews_type().equalsIgnoreCase("2")) {
            youtube_icon.setVisibility(View.VISIBLE);


            String[] key = array_bean.get(position).getVideo_url().split("=");
            Picasso.with(act).load("http://img.youtube.com/vi/"+key[1]+"/hqdefault.jpg")
                    .fit()

                    .placeholder(R.drawable.placeholder_dark)
                    .into(imageView[0], new Callback() {

                        @Override
                        public void onSuccess() {
                            // MaterialImageLoading.animate(imageView).setDuration(2000).start();
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }


        youtube_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(array_bean.get(position).getNews_type().equalsIgnoreCase("null")||array_bean.get(position).getNews_type().equalsIgnoreCase("")) {
                }
                else
                {
                    if (array_bean.get(position).getNews_type().equalsIgnoreCase("2")) {
                        Intent i = new Intent(ctx, YouTube_Test.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(ctx, tv_title.getText(), Toast.LENGTH_LONG).show();
                        i.putExtra("title", tv_title.getText().toString());
                        i.putExtra("vurl", array_bean.get(position).getVideo_url());
                        i.putExtra("desc", tv_desc.getText());

                        ctx.startActivity(i);
                    }
                }
            }
        });

        btn_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(array_bean.get(position).getRead_more_link()));
                    //wv.loadUrl(array_bean.get(position).getRead_more_link());
                    Intent intent = new Intent(item_view.getContext(), WebViewActivity.class);
                    intent.putExtra("URL", array_bean.get(position).getSource_name());
                    item_view.getContext().startActivity(intent);
                    // act.startActivity(browserIntent);
                    System.out.println("Work good");
                }catch(Exception e)
                {
                }

            }
        });

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri imageURI;
               *//* imageURI = Uri.parse("android.resource://" + act.getPackageName()
                        + "/drawable/" + "icon_share.png");*//*
                File f = new File("/mnt/sdcard/DCIM/DCIM/Facebook/FB_IMG_1447124150387.jpg");
                imageURI = Uri.parse(f.getPath());
               *//* try {
                    // get input stream
                    InputStream ims = act.getAssets().open("avatar.jpg");
                    // load image as Drawable
                    Drawable d = Drawable.createFromStream(ims, null);
                    // set image to ImageView
                    mImage.setImageDrawable(d);
                }
                catch(IOException ex) {
                    return;
                }*//*





                System.out.println("Image URI " + imageURI);

                //String shareBody = "Download Indian Film Shorts and get latest Bollywood news. http://www.indianfilmshorts.com";
                String shareBody = "Save time. Download IFS to read Bollywood news in 60 words. \nhttp://www.indianfilmshorts.com";
                Intent sharingIntent = new Intent();
                sharingIntent.setType("image/jpeg");
                //sharingIntent.setType("text/plain");
                sharingIntent.setAction(Intent.ACTION_SEND);
                //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "IFS");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, imageURI);
                System.out.println("Image uri "+imageURI);

                sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                act.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });*/
        //  btn_bookmark.setOnClickListener(buttonListener);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Log.i("Screenshot", "hi");
                Date now = new Date();
                //Date now = new Date();
                android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                try {
                    // image naming and path  to include sd card  appending name you choose for file
                    /*String mPath = Environment.getExternalStorageDirectory().toString() + "/" + new Random() + ".jpg";

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
                    Uri bmpUri = Uri.fromFile(imageFile);*/


                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");

                    share.putExtra(Intent.EXTRA_TEXT, Config.SHARE_BODY);

                    System.out.println("bmpUri "+Config.share_screen_shots(item_view));


                    share.putExtra(Intent.EXTRA_STREAM, Config.share_screen_shots(item_view));
                    share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    ctx.startActivity(share);

                } catch (Throwable e) {
                    // Several error may come out with file handling or OOM
                    Log.i("Screenshot",e.toString());
                }

            }
        });


        /*try{
            if(ids!=null) {
                *//*try {
                    Config.setDayNight(tv_title, tv_desc, tv_postby, (RelativeLayout) item_view.findViewById(R.id.appbar));
                }catch (Exception e)
                {
                    Log.i("Main ",e.toString());
                }*//*
                try {

                    final ImageView imageView = (ImageView) item_view.findViewById(R.id.imageView);
                    new GetDescripation(ctx,tv_title, tv_desc,tv_postby,btn_readmore,imageView, Integer.parseInt(ids[position]),position).execute();
                    if(Config.Menu==1)
                    {
                        final ImageView imageView1 = (ImageView) item_view.findViewById(R.id.outside_imageview);
                        imageView1.setVisibility(View.VISIBLE);

                        imageView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ctx,YouTube_Test.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Toast.makeText(ctx, tv_title.getText(), Toast.LENGTH_LONG).show();
                                i.putExtra("title", tv_title.getText().toString());
                                i.putExtra("vurl",Config.VIDEO_URL[position]);
                                i.putExtra("desc",tv_desc.getText());

                                ctx.startActivity(i);
                                //Toast.makeText(ctx,"Clicked",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                    {
                        final ImageView imageView1 = (ImageView) item_view.findViewById(R.id.outside_imageview);
                        imageView1.setVisibility(View.INVISIBLE);
                    }
                    //  Typeface custom_font = Typeface.createFromAsset(ctx.getAssets(), "fonts/OpenSans-Regular.ttf");


                    container.addView(item_view);

                } catch (Exception e) {

                    Log.i("Message",e.toString());

                    tv_desc.setText("Error 1 in custom swipe adapter:" + e.toString());

                }
                return item_view;
            }
        }catch (Exception e)
        {
            Log.i("Message","0"+e.toString());
        }*/
        //Toast.makeText(act, ""+item_view.toString(), Toast.LENGTH_SHORT).show();
        container.addView(item_view);
        return  item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}

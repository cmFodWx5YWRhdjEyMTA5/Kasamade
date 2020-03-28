package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kasamade.news.Config;
import com.kasamade.news.R;
import com.kasamade.news.YouTube_Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;


public class custom_swipe_adapter extends PagerAdapter {


    private Context ctx;
    private Activity act;
    private LayoutInflater layoutInflater;
    private String[] ids;
    private LruCache<String, Bitmap> mMemoryCache;
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);


    public custom_swipe_adapter(final Context context,String[] imgids,Activity act) {
        this.ctx = context;
        this.act=act;
        ids = new String[0];
        ids=imgids;
    }

    @Override
    public int getCount()
    {

        return ids.length;


    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        final TextView tv_desc = (TextView) item_view.findViewById(R.id.desc);
        final TextView tv_title = (TextView) item_view.findViewById(R.id.posttitile);
        final TextView tv_postby = (TextView) item_view.findViewById(R.id.postby);
        final Button btn_readmore = (Button) item_view.findViewById(R.id.btn_readmore);


        Typeface myfont = Typeface.createFromAsset(ctx.getAssets(), "fonts/Roboto-Light.ttf");
        Typeface myfontbold = Typeface.createFromAsset(ctx.getAssets(), "fonts/Roboto-Regular.ttf");
        Button btn_readmore1= (Button) item_view.findViewById(R.id.btn_readmore1);

        TextView postby = (TextView) item_view.findViewById(R.id.postby);

        btn_readmore1.setTypeface(myfont);
        postby.setTypeface(myfont);

        ImageButton fab = (ImageButton)item_view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Screenshot","hi");
                Date now = new Date();
                android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                try {
                    // image naming and path  to include sd card  appending name you choose for file
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

                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");
                    Uri bmpUri = Uri.fromFile(imageFile);
                    share.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(share);

                } catch (Throwable e) {
                    // Several error may come out with file handling or OOM
                    Log.i("Screenshot",e.toString());
                }
            }
        });



        try{
            if(ids!=null) {
                try {
                    Config.setDayNight(tv_title, tv_desc,tv_postby, (LinearLayout) item_view.findViewById(R.id.appbar));
                }catch (Exception e)
                {
                    Log.i("Main ",e.toString());
                }
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
                                Toast.makeText(ctx,tv_title.getText(),Toast.LENGTH_LONG).show();
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

                    tv_title.setTypeface(myfontbold);
                    tv_desc.setTypeface(myfont);
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
        }

       return  "Something is wrong!";
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}



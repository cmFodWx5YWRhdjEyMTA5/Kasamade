package Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.kasamade.news.Config;
import com.kasamade.news.R;

import java.io.InputStream;

/**
 * Created by Pruthviraj on 1/21/2016.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    String imgname;
    LruCache<String, Bitmap> mMemoryCache;


    public DownloadImageTask(ImageView bmImage,String imgname) {
        this.bmImage = bmImage;
       this.imgname=imgname;
    }

    protected Bitmap doInBackground(String... urls) {
        Log.i("Downloadimgtask", "Line : 41 " +imgname);
        Bitmap mIcon11 = null;
        if(imgname.matches("NA"))
            return  mIcon11;

        mIcon11=(Bitmap)Cache.getInstance().getLru().get("img_"+ Config.Menu+"_"+imgname);
        //Cache.getInstance().getLru().put("bitmap_image", bitmap);
        if(mIcon11 != null){
            Log.i("Downloadimgtask", "Line : 43 fromcachememory "+imgname+"");
            return mIcon11;
        }
        else
        {
            String urldisplay = urls[0];
            try {
                Log.i("Downloadimgtask", "Line : 48 downloading from :"+ urldisplay);
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                Cache.getInstance().getLru().put("img_"+Config.Menu+"_"+imgname,mIcon11);
                return mIcon11;

            } catch (Exception e) {
                Log.e("downloadimg Error", e.toString());
                e.printStackTrace();
                return mIcon11;
            }

            //return mIcon11;
        }
    }

    protected void onPostExecute(Bitmap result) {

        if (result == null) {
            Log.i("Downloadimgtask","N/A");
            bmImage.setImageResource(R.drawable.nophoto);
        } else {

            bmImage.setImageBitmap(getRoundedCornerBitmap(result,5));
        }
       // Log.i("Downloadimgtask", "Line : 88 image downloaded and loaded!");
    }



    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    }




//start code for downloading.......
           /*   Uri bmpUri = null;
                try {

                    File file =  new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"menu_"+Config.Menu+"_"+imgname);
                    file.getParentFile().mkdirs();
                    FileOutputStream out = new FileOutputStream(file);
                    mIcon11.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.close();
                    bmpUri = Uri.fromFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("Downloadimgtask", "Line : 68 downloaded at"+bmpUri);*/
//End Code downloading.......

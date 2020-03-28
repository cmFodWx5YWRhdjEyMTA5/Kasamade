package Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kasamade.news.Config;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.File;

/**
 * Created by comsoftpc2 on 2/9/2016.
 */

public class GetDescripation extends AsyncTask<String, Void, String> {

    public  static String RETURN_DATA;
    public  Context ctx;
    public ViewPager vp;
    public  String[] imgids;
    public int id;
    public int position;
    public   LayoutInflater  layoutInflater;
    public  TextView tv_desc,tv_title,tv_postby;
    public  ImageView imageView;
    public Button btn_moreat;
    String url;
    boolean fromcache=false;

    @Override
    protected void onPreExecute() {
        try {
            super.onPreExecute();
        } catch (Exception err) {
        }
    }
public GetDescripation(Context ctx,TextView tv_title,TextView tv_desc,TextView tv_postby,Button btn_moreat,ImageView imageView,int id,int position)
{
    this.imageView=imageView;
    this.tv_title = tv_title;
    this.tv_desc = tv_desc;
    this.id=id;
    this.position=position;
    this.tv_postby=tv_postby;
    this.btn_moreat=btn_moreat;
    this.ctx=ctx;


}


    @Override
    protected String doInBackground(String... params) {
        String returndata="";

        if(Cache.getInstance().getLru().get("data_"+ Config.Menu+"_"+id)!=null)
            returndata=Cache.getInstance().getLru().get("data_"+Config.Menu+"_"+id).toString();

        if(returndata.length()>0) {
            Log.i("GetDescripation Line 88","From Cache memory");
            fromcache=true;
            return  returndata;
        }

        fromcache=false;

        try {


            try {

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Message :", "(1) " + e.toString());
            }
        } catch (Exception e) {
            Log.i("Message :", "(2) "+e.toString());
        }
        return returndata;
    }


    protected void onPostExecute(String str) {

        if(!fromcache)
            Cache.getInstance().getLru().put("data_"+Config.Menu+"_"+id,str);

        Log.i("Data :", str);
        String title="",desc="",imgname="",vurl="",postby="";




        try {

           //Decoding Dta form JSON Object
            JSONObject oneObject = new JSONObject(str);
            imgname=oneObject.getString("imageurl");
            title=oneObject.getString("title");
            desc=oneObject.getString("discription");
            postby=oneObject.getString("createdby");


            if(Config.Menu==1)
            {
                vurl=oneObject.getString("videourl");
            }

            if(Config.Menu!=2 || Config.Menu!=5 || Config.Menu!=7)
            {
                final String sourcename = oneObject.getString("sourcename");
                final String sourceurl = oneObject.getString("sourceurl");

                if(sourcename.length()>0)
                btn_moreat.setText(sourcename);

                else
                    btn_moreat.setText("test9.modelspoint.com");



                btn_moreat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try
                        {
                            Intent i = new Intent(Intent.ACTION_VIEW);

                           // if(!sourceurl.matches("") || sourceurl == null)
                              //  i.setData(Uri.parse(Config.WEB_PAGE_URL + id));
                            //else
                                i.setData(Uri.parse(sourceurl));

                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ctx.startActivity(i);
                        }catch (Exception e)
                        {
                                Log.e("GetDescripation ","Line 160`" + e.toString());
                        }


                    }
                });
            }
            else
            {
                btn_moreat.setText("more");

                btn_moreat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try
                        {
                        String url = Config.WEB_PAGE_URL + id;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(i);
                        }catch (Exception e)
                        {
                            Log.e("GetDescripation ","Line 182" + e.toString());
                        }

                    }
                });
            }



        }catch (JSONException je)
        {
            Log.i("GetDescripation ", "Line : 160 "+ je.toString());
        }
        tv_title.setText(Html.fromHtml(title));
        tv_postby.setText(Html.fromHtml(postby));
           // Log.i("GetDescripation ", "Line : 1216 " + str);


            if(Config.Menu==1)
            {
                Config.VIDEO_URL[this.position]=vurl;
                Log.i("textdata", this.position + " " + vurl);
                new DownloadImageTask(imageView,imgname).execute(Config.IMAGE_URL + Uri.encode(imgname));
            }

            if(Config.Menu==2)
            {
                Config.CHANNEL_NAME[position]=title;
                File myFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"menu_"+Config.Menu+"_"+imgname);

                if(Config.CHANNEL_IMG[position]=="menu_"+Config.Menu+"_"+imgname && myFile.exists())
                {
                    imageView.setImageURI(Uri.parse("/storage/sdcard0/Download/" + Config.CHANNEL_IMG[position]));
                    Log.i("GetDescripation"," Line 140: "+" /storage/sdcard0/Download/" + Config.CHANNEL_IMG[position]);
                }
                else
                {

                    Log.i("textdata",Config.IMAGE_URL+id+"/" + Uri.encode(imgname));
                    Config.CHANNEL_IMG[position]="menu_"+Config.Menu+"_"+imgname;
                    new DownloadImageTask(imageView,imgname).execute(Config.IMAGE_URL+id+"/" + Uri.encode(imgname) );
                }

            }
            else if( Config.Menu == 7)
            {

                tv_desc.setText(Html.fromHtml(desc));
                new DownloadImageTask(imageView,imgname).execute(Config.IMAGE_URL + id + "/" + Uri.encode(imgname));
            }
            else
            {

                tv_desc.setText(Html.fromHtml(desc));
                new DownloadImageTask(imageView,imgname).execute(Config.IMAGE_URL + Uri.encode(imgname));
            }
            Log.i("textdata", Config.IMAGE_URL + imgname);





    }



}
package Adapter;

/**
 * Created by comsoftpc2 on 2/29/2016.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kasamade.news.Config;
import com.kasamade.news.R;
//import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ImageLoader.ImageLoader;

public class CustomList extends ArrayAdapter<String>{

    private final Context context;
    ImageLoader imageloader;

    public CustomList(Context context) {
        super(context, R.layout.list_single, Config.CHANNEL_ID);

        this.context = context;
        imageloader = new ImageLoader(context);

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= layoutInflater.inflate(R.layout.list_single, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.img);


        if(Config.CHANNEL_NAME[position]!=null && Config.CHANNEL_IMG[position]!=null)
        {
            if(Integer.parseInt(Config.CHANNEL_ID[position]) == Config.CID) {
                txtTitle.setTextColor(Color.WHITE);
            }
            txtTitle.setText(Config.CHANNEL_NAME[position]);

            if(Config.CHANNEL_IMG[position].equalsIgnoreCase(""))
            {

            }
            else
            {
                System.out.println("Image "+Config.global_link+Config.CHANNEL_IMG[position]);
                //imageloader.DisplayImage(Config.global_link+Config.CHANNEL_IMG[position],R.mipmap.ic_launcher,imageView);
                if(Config.CHANNEL_IMG[position].equalsIgnoreCase(""))
                {
                    imageView.setImageResource(R.drawable.banner);
                }
                else {
                    Picasso.with(context).load(Config.global_link+Config.CHANNEL_IMG[position])
                            .fit()

                            .placeholder(R.mipmap.ic_launcher)
                            .into(imageView, new Callback() {

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
            /*File myFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),Config.CHANNEL_IMG[position]);
            if(myFile.exists())
            imageView.setImageURI(Uri.parse("/storage/sdcard0/Download/"+ Config.CHANNEL_IMG[position]));
            else
                new DownloadImageTask(imageView,Config.CHANNEL_IMG[position]).execute(Config.IMAGE_URL +Config.CHANNEL_ID[position]+"/"+ Uri.encode(Config.CHANNEL_IMG[position]));*/
        }
        if(Integer.parseInt(Config.CHANNEL_ID[position]) == Config.CID) {
            rowView.setBackgroundColor(Color.RED);

        }
        return rowView;
    }
}

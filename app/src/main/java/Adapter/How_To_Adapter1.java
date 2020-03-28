package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kasamade.news.R;


/**
 * Created by Azhar on 5/12/2017.
 */

public class How_To_Adapter1 extends BaseAdapter
{
    private Context context;
    private final String[] mobileValues;

    public How_To_Adapter1(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        View checkbox = null;

        if (convertView == null) {

            gridView = new View(context);
            checkbox= new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_how_to1, null);
            checkbox=inflater.inflate(R.layout.grid_how_to1,null);


        } else {
            gridView = (View) convertView;
        }




        // set value into textview
        TextView textView = (TextView) gridView
                .findViewById(R.id.grid_item_label2);
        textView.setText(mobileValues[position]);
      //  checkbox.setId(position);

        // set image based on selected text
        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.grid_item_image);

        String mobile = mobileValues[position];

        if (mobile.equals("लोकमत"))
        {
            imageView.setImageResource(R.drawable.lokamat);
        }


        else if (mobile.equals("सकाळ"))
        {
            imageView.setImageResource(R.drawable.sakal);
        }

        else if (mobile.equals("महाराष्ट्र टाइम्स"))
        {
            imageView.setImageResource(R.drawable.maharasta_times);
        }

        else if (mobile.equals("लोकसत्ता"))
        {
            imageView.setImageResource(R.drawable.lokasatta);
        }

        else if (mobile.equals("दिव्य मराठी"))
        {
            imageView.setImageResource(R.drawable.divyamarathi);
        }

        else if (mobile.equals("सामना"))
        {
            imageView.setImageResource(R.drawable.samana);
        }

        else if (mobile.equals("पुढारी"))
        {
            imageView.setImageResource(R.drawable.pudhari);
        }

        else if (mobile.equals("नवशक्ती"))
        {
            imageView.setImageResource(R.drawable.lokamat);
        }

        else if (mobile.equals("देशदूत"))
        {
            imageView.setImageResource(R.drawable.deshdut);
        }

        else if (mobile.equals("पुण्यनगरी"))
        {
            imageView.setImageResource(R.drawable.punyanagari);
        }

        else if (mobile.equals("गांवकरी"))
        {
            imageView.setImageResource(R.drawable.gavkari);
        }

        else if (mobile.equals("प्रहार"))
        {
            imageView.setImageResource(R.drawable.prahaar);
        }
        else if (mobile.equals("तरुण भारत"))
        {
            imageView.setImageResource(R.drawable.tarun);
        }


        else if (mobile.equals("माझी नोकरी"))
        {
            imageView.setImageResource(R.drawable.majhi_naukari);
        }

        else if (mobile.equals("महा न्यूज"))
        {
            imageView.setImageResource(R.drawable.mahanews);
        }

        else if (mobile.equals("मराठी जॉब"))
        {
            imageView.setImageResource(R.drawable.marathijobs);
        }

        else if (mobile.equals("सरकारी नोकरी"))
        {
            imageView.setImageResource(R.drawable.sarakari_naukari);
        }

        else if (mobile.equals("naukari.com"))
        {
            imageView.setImageResource(R.drawable.naukari);
        }

        else if (mobile.equals("monster.com"))
        {
            imageView.setImageResource(R.drawable.monster);
        }


        else if (mobile.equals("timejobs.com"))
        {
            imageView.setImageResource(R.drawable.timesjob);
        }


        else if (mobile.equals("indeed.com"))
        {
            imageView.setImageResource(R.drawable.indeed);
        }


        else if (mobile.equals("shine.com"))
        {
            imageView.setImageResource(R.drawable.shine);
        }

        else
        {
            imageView.setImageResource(R.drawable.man);
        }













        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}

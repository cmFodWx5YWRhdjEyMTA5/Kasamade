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

public class How_To_Adapter extends BaseAdapter
{
    private Context context;
    private final String[] mobileValues;

    public How_To_Adapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        View checkbox;

        if (convertView == null) {

            gridView = new View(context);
            checkbox= new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_how_to, null);
            checkbox=inflater.inflate(R.layout.grid_how_to,null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);
            checkbox.setId(position);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];

            if (mobile.equals("मुंबई"))
            {
                imageView.setImageResource(R.drawable.weather1);
                checkbox.setId(position);
            }
            else if (mobile.equals("पुणे"))
            {
                imageView.setImageResource(R.drawable.weather2);
                checkbox.setId(position);
            }
            else if (mobile.equals("नागपुर"))
            {
                imageView.setImageResource(R.drawable.weather3);
            }
            else if (mobile.equals("नाशिक"))
            {
                imageView.setImageResource(R.drawable.weather4);
            }



            else
            {
                imageView.setImageResource(R.drawable.man);
            }

        } else {
            gridView = (View) convertView;
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

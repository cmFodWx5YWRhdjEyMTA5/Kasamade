package Adapter;

/**
 * Created by comsoftpc2 on 2/29/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kasamade.news.Config;
import com.kasamade.news.R;


public class CustomList_Language extends ArrayAdapter<String>{

    private final Context context;



    public CustomList_Language(Context context) {
        super(context, R.layout.list_single_language, Config.LANGUAGE_ID);
        this.context = context;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= layoutInflater.inflate(R.layout.list_single_language, null, true);
        CheckBox cb_language=(CheckBox) rowView.findViewById(R.id.checkBox1);
        TextView txtTitle = (TextView)rowView.findViewById(R.id.txtLanguage);
        if(Config.LANGUAGE_NAME[position]!=null && Config.LANGUAGE_ID[position]!=null)
        {
            cb_language.setChecked(true);
            txtTitle.setText(Config.LANGUAGE_NAME[position]);
        }

        return rowView;
    }
}

package Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kasamade.news.AppPrefs;
import com.kasamade.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prince on 31/8/16.
 */
public class Custom_Language_Popup_List extends BaseAdapter {


    Activity activity;
  public   String[] language_id;
    String[] language_name;
    AppPrefs appPrefs;
    public List<Integer> list = new ArrayList<Integer>();;
    public Custom_Language_Popup_List()
    {

    }

    public Custom_Language_Popup_List(String[] language_id,  String[] language_name, Activity activity)
    {
        this.language_id = language_id;
        this.language_name = language_name;
        this.activity = activity;
        appPrefs = new AppPrefs(activity);
    }
    @Override
    public int getCount() {
        return language_id.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View  v = layoutInflater.inflate(R.layout.language_layout_row , null, true);
        TextView tv_language = (TextView)  v.findViewById(R.id.tv_language_name);
        final CheckBox cb_language=(CheckBox) v.findViewById(R.id.checkBox1);
        tv_language.setText(language_name[position]);
        cb_language.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                list.add(position);
                if(position==0 && appPrefs.getLanguage_ID()!="" ) {
                    appPrefs.remove_all_prefs();
                    System.out.println("All remove");

                }else if(position==0 && appPrefs.getLanguage_ID()==""){
                    appPrefs.setLanguage_ID("0");
                    System.out.println("All Done");
                }

                if(position==1 && appPrefs.getHindi_id()!="" ) {
                    appPrefs.remove_HindiID_prefs();
                    System.out.println("Hindi remove");
                }else if(position==1 && appPrefs.getHindi_id()==""){
                    appPrefs.setHindi_id("6");
                    System.out.println("Hindi Do");
                }

                if(position==2 && appPrefs.getEnglish_id()!="" ) {
                    appPrefs.remove_EnglishID_prefs();
                    System.out.println("English Remove");
                }else if(position==2 && appPrefs.getEnglish_id()==""){
                    appPrefs.setEnglish_id("7");
                    System.out.println("English Do");
                }

                if(position==3 && appPrefs.getBhojpuri_id()!="" ) {
                    appPrefs.remove_Bhojpuri_id_prefs();
                    System.out.println("Bhojpuri");
                }else if(position==3 && appPrefs.getBhojpuri_id()==""){
                    appPrefs.setBhojpuri_id("17");
                    System.out.println("Bhojpuri Do");
                }


                if(position==4 && appPrefs.getGujurati_id()!="" ) {
                    appPrefs.remove_Gujurati_id_prefs();
                    System.out.println("Gujurati");
                }else if(position==4 && appPrefs.getGujurati_id()==""){
                    appPrefs.setGujurati_id("18");
                    System.out.println("Gujurati Do");
                }


                if(position==5 && appPrefs.getPunjabi_id()!="" ) {
                    appPrefs.remove_Punjabi_id_prefs();
                    System.out.println("Punjabi");
                }else if(position==5 && appPrefs.getPunjabi_id()==""){
                    appPrefs.setPunjabi_id("19");
                    System.out.println("Punjabi Do");
                }


                if(position==6 && appPrefs.getBengali_id()!="" ) {
                    appPrefs.remove_Bengali_id_prefs();
                    System.out.println("Bengali");
                }else if(position==6 && appPrefs.getBengali_id()==""){
                    appPrefs.setBengali_id("20");
                    System.out.println("Bengali Do");
                }



                if(position==7 && appPrefs.getTelegu_id()!="" ) {
                    appPrefs.remove_Telegu_id_prefs();
                    System.out.println("Telgu");
                }else if(position==7 && appPrefs.getTelegu_id()==""){
                    appPrefs.setTelegu_id("21");
                    System.out.println("Telgu Do");
                }



                if(position==8 && appPrefs.getMalyalam_id()!="" ) {
                    appPrefs.remove_Malyalam_id_prefs();
                    System.out.println("Malyalam");
                }else if(position==8 && appPrefs.getMalyalam_id()==""){
                    appPrefs.setMalyalam_id("22");
                    System.out.println("Malyalam Do");
                }

                if(position==9 && appPrefs.getKanada_id()!="" ) {
                    appPrefs.remove_Kanada_id_prefs();
                    System.out.println("Kanada");
                }else if(position==9 && appPrefs.getKanada_id()==""){
                    appPrefs.setKanada_id("23");
                    System.out.println("Kanada Do");
                }


                if(position==10 && appPrefs.getMarathi_id()!="" ) {
                    appPrefs.remove_Marathi_id_prefs();
                    System.out.println("Marathi");
                }else if(position==10 && appPrefs.getMarathi_id()==""){
                    appPrefs.setMarathi_id("28");
                    System.out.println("Marathi Do");
                }

                if(position==11 && appPrefs.getTamil_id()!="" ) {
                    appPrefs.remove_Tamil_id_prefs();
                    System.out.println("Tamil");
                }else if(position==11 && appPrefs.getTamil_id()==""){
                    appPrefs.setTamil_id("29");
                    System.out.println("Tamil Do");
                }















            }
            public List<Integer> getList() {
                return list;
            }
        });






        if(appPrefs.getLanguage_ID()!="" && position==0 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }
        if(appPrefs.getHindi_id()!="" && position==1 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }

        if(appPrefs.getEnglish_id()!="" && position==2 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }

        if(appPrefs.getBhojpuri_id()!="" && position==3 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }


        if(appPrefs.getGujurati_id()!="" && position==4 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }


        if(appPrefs.getPunjabi_id()!="" && position==5 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }

        if(appPrefs.getBengali_id()!="" && position==6 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }

        if(appPrefs.getTelegu_id()!="" && position==7 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }

        if(appPrefs.getMalyalam_id()!="" && position==8 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }
        if(appPrefs.getKanada_id()!="" && position==9 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }

        if(appPrefs.getMarathi_id()!="" && position==10 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }


        if(appPrefs.getTamil_id()!="" && position==11 )
        {
            cb_language.setChecked(true);
            tv_language.setTextColor(Color.parseColor("#ED2226"));
        }


      /*  button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cb_language.isChecked()) {
                    System.out.println(position);
                } else {

                }

            }
        });
*/
        return v;
    }
}

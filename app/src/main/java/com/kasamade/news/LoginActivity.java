package com.kasamade.news;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import Adapter.Util1;
import interfaceAPI.login;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {


    private Button bLogin;

  private EditText username,mobileno,email;
    private String username1,mobileno1,pincode1,email1,taluka1;
  //  AutoCompleteTextView pincode;
   // Spinner taluka,pincode;
    private static final String ENDPOINT = "http://kasamadenews.androideducator.com";
        String[] Deola = {"गाव","Bhaur", "Bhavade", "Bhilwad", "Chinchave", "Dahiwad", "Deola", "Deopurpada", "Dongargaon", "Fulemalwadi", "Giranare", "Gunjalnagar", "Hanmantpada", "Kanchane", "Kankapur", "Kapashi", "Khadaktale", "Khalap", "Khamkhede", "Kharde", "Khuntewadi", "Kumbharde", "Lohoner", "Mahalpatane", "Mahatma Fule Nagar", "Malwadi", "Matane", "Meshi", "Nimbole", "Phule Nagar", "Pimpalgaon", "Rameshwar", "Ramnagar", "Sangavi", "Satwaichiwadi", "Savaki [Lohoner]", "Sheri", "Shrirampur", "Subhashnagar", "Tisgaon", "Umarane", "Varhale" ,"Vasol","Vijaynagar","Vithewadi","Wadali Wakhari","Wajgaon","Wakhari","Warshi","Warwandi","Zirepimple"};
    String[] Satana = {"गाव","Ajmir Saundane","Ajande","Akhatwade","Aliyabad","Ambapur","Ambasan","Anandpur","Antapur","Arai","Askheda","Aundane","Avhati","Babhulane","Bahirane","Bhadane","Bhakshi","Bhavnagar","Bhawade","Bhildar","Bhilwad","Bhimkhet","Bhimnagar","Bhuyane","Bijorase","Bijote","Bilpuri","Bodhari","Bordaiwat","Borhate","Bramhangaon","Bramhanpade","Bundhate","Chaugaon","Chaundhane","Chirai","Dagadpada","Dahindule","Dangsaundane","Daregaon","Darhane","Dasane","Daswel","Deolane","Deopur","Deopur","Deothan-Digar","Dhandri","Dholbare","Dodheshwar","Dongrej","Dyane","Eklahare","Fopir","Gandhinagar","Ganeshnagar","Gautamnagar","Golwad","Gorane","Hatnoor","Ijamane","Indiranagar","Jad","Jaikheda","Jaipur","Jaitapur","Jakhod","Jamoti","Joran","Kadyachamala","Kakadgaon","Kandhane","Kapaleshwar","Karanjad","Karanjkhed","Karhe","Katarwel","Kathagad","Kautikpada","Kelzar","Kerovanagar","Kersane","Khamlon","Khamtane","Kharad","Khirmani","Kikwari Bk","Kikwari Kh.","Kondharabad","Kopmal","Kotbel","Kupkhede","Ladud","Lakhamapur","Mahad","Mahatma Phule Nagar","Mailwade","Malegaon Tilwan","Malgaon Bhamer","Malgaon Kh.","Manur","Mohalangi","Morane Sandas","Morane-Digar","More Nagar","Morkure","Mulane","Mulher","Mungase","Munjwad","Nalkas","Nampur","Nandin","Narkol","Nave Nirpur","Navegaon","Navi Shemali","Nikwel","Nirpur","Nitane","Parner","Parshuramnagar","Pathave Digar","Pimpaldar","Pimpalkothe","Pingalwade","Pisore","Rahud","Rajpurpande","Ramtir","Ratir","Raver","Satana","Sakode","Salher","Salwan","Sarade","Sarpargaon","Sarwar","Shemali","Shevare","Shripurvade","Sompur","Surane","Taharabad","Talwade Bhamer","Talwade Digar","Tandulwadi","Tarsali","Tatani","Tembhe","Thengode","Tilwan","Tinghari","Tungan Digar","Utrane","Vade Digar","Vade Kh.","Vanoli","Varche Tembhe","Vatar","Vathode","Vaygaon","Vijaynagar","Vinchure","Virgaon","Wadi Chaulher","Wadipisol","Waghale","Waghambe","Yashawantnagar"};
    String[] Malegoan = {"गाव","Aghar Bk","Aghar Kh","Ajande","Ajande Kh.","Ajang","Astane","Belgaon","Bharadenagar","Bhilkot","Bhuigavhan","Bodhe","Chandanpuri","Chaukatpada","Chikhalohal","Chinchagavhan","Chinchavad","Chinchave","Chondhi","Dabhadi","Dabli","Dahidi","Dahikute","Dahiwal","Dapur","Dasane","Deoghat","Devarpada","Dhavali Vihir","Dongrale","Dubagule","Dundhe","Erandgaon","Galne","Ganeshnagar","Garbad","Garegaon","Ghanegaon","Ghodegaon","Ghodegaonchoki","Gigaon","Gilane","Gugulwad","Hatane","Hiswal Bk","Jalgaon","Jalgaon","Jalku","Jatpade","Jeur","Jwardi Bk.","Jwardi Kh","Kajwade","Kalewadi","Kalwadi","Kandhane","Kankrale","Karanj Gavhan","Kashti","Kaulane","Kaulane","Khadaki","Khakurdi","Khalane","Khayade","Kothare Bk.","Kothare Kh.","Kukane","Lakhani","Lendane","Lonwade","Lulle","Malegaon","Malhanagaon","Manjare","Manke","Mathurpada","Mehune","Mohapada","Mordar","Mungase","Nagaon","Nagzari","Nale","Nandgaon","Nandgaon Kh.","Nardane","Nilgavhan","Nimbait","Nimgaon","Nimgaon Kh","Nimgule","Nimgulekupa","Nimshevadi","Padalde","Palasdare","Pandharun","Patane","Patharde","Pimpalgaon(w)","Pohane","Rajmane","Rampura","Ravalgaon","Ronzane","Roze","Sajvahal","Sakur","Sakuri","Satarpada","Satmane","Saundane","Savandgaon","Savkarwadi","Sawatawadi","Sayane Bk","Sayane Kh","Shendurni","Sherul","Shirsondi","Sitane","Sonaj","Takali","Talwade","Tehare","Tingri","Tipe","Tokade","Umbardhe","Vadel","Vadgaon","Vadner","Vajirkhede","Vake","Valwade","Valwadi","Vanpat","Varhane","Varhanepada","Virane","Yesgaon Bk","Yesgaon Kh","Zadi","Zodge"};
    String[] Kalwan = {"गाव","Abhona","Ambapur","Ambika Ozar","Amburdi Bk.","Amburdi Kh.","Amdar","Asoli","Athambe","Babkhede","Bagadu","Balapur","Barde","Bej","Belbare","Bendipada","Bhadvan","Bhagurdi","Bhakurde","Bhandane","Bhandane","Bhendi","Bhusani","Bhutane","Bijore","Bilwadi","Bordaivat","Chacher","Chankapur","Chaphapada","Chinchore","Dahyane","Dahyane Digar","Dahyanepale","Dalwat","Dare Bhanagi","Daregaon Hatgad","Daregaonwani","Dattanagar","Desgaon","Desrane","Devali Karhad","Devaliwani","Dhaner Digar","Dhanoli","Dharde Digar","Dhekale","Eklahare","Gangapur","Gangavan","Ganore","Gobapur","Golakhal","Gopalkhadi","Gosrane","Hingave","Hundyamokh","Inshi","Jaidar","Jaipur","Jamale","Jamle","Jamle pale","Jamshet","Jirwade","Jirwade Otur","Kakane","Kalamthe","Kalwan Bk.","Kalwan Kh","Kanashi","Kanherwadi","Karambhel Hatgad","Karambhel Kanashi","Karmale","Katalgaon","Kathare Digar","Khadaki","Khadakwan","Kharde Digar","Khedgaon","Khirad","Kosurde","Koswan","Kumsadi","Kundane","Lakhani","Lingame","Machidhodap","Malagaon Bk.","Malagaon Kh.","Malagaon Kh.","Manur","Mehadar","Mohamukh","Mohandari","Mohobari","Mohpada","Mokbhanagi","Mulane","Nakode","Nalid","Nanduri","Narul","Navi Bej","Niwane","Otur","Ozar","Padgan","Palasdar","Pale Bk.","Pale Kh.","Patvihir","Pilakos","Pimple Bk.","Pimple Kh.","Pimpri Markanda","Pimpripale","Pratap Nagar","Punad Nagar","Punegaon","Ravalji","Sadadvihir","Sakore","Sakorepada","Saptashrungagad","Sarale Digar","Savakipale","Savarpada","Shepupada","Sheri Digar","Shingashi","Shirasamani","Shiv-Bhandane","Shrungarwadi","Sidharthanagar","Sukapur Hatgad","Sule","Supale Digar","Tatani","Tirhal Bk.","Tirhal Kh.","Umbardhe","Vanjari","Verule","Virshet","Visapur","Vithewadipale","Wadale","Wadale[Wani]","Wadi Bk.","Wadpada","Warkhede"};

    String[] Taluka = {"तालुका","Deola","Satana","Malegoan","Kalvan"};
    String[] gav={"गाव"};

    private Animation anim;
    private CheckBox condition;
    TextView condition1;
    int currentorpass=0;

    boolean val = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        condition=(CheckBox) findViewById(R.id.condition);

        bLogin = (Button) findViewById(R.id.btLogin);
        bLogin.setOnClickListener(this);
        username=(EditText)findViewById(R.id.edit_username) ;
        mobileno=(EditText)findViewById(R.id.edit_mobile);
       // pincode=(TextView)findViewById(R.id.edit_pin);
        email=(EditText)findViewById(R.id.edit_email) ;
     //   taluka=(Spinner) findViewById(R.id.edit_taluka);
    //    pincode = (Spinner) findViewById(R.id.edit_pin);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, Taluka);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, Deola);
        condition1=(TextView) findViewById(R.id.condition1);
        //Getting the instance of AutoCompleteTextView
      /*  taluka = (AutoCompleteTextView) findViewById(R.id.edit_taluka);
        taluka.setThreshold(1);//will start working from first character
        taluka.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView
        taluka.setTextColor(Color.BLACK);
        taluka.setTextSize(14);




*/


























        CustomSpinnerAdapter1 customSpinnerAdapter1 = new CustomSpinnerAdapter1(LoginActivity.this, gav);
       // pincode.setAdapter(customSpinnerAdapter1);


        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(LoginActivity.this,Taluka);
       // taluka.setAdapter(customSpinnerAdapter);

       //     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, Deola);
            //Getting the instance of AutoCompleteTextView





       /*     pincode.setThreshold(1);//will start working from first character
            pincode.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
            pincode.setTextColor(Color.BLACK);
            pincode.setTextSize(14);*/


        condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);

            }
        });
        condition1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String url="http://kasamadenews.androideducator.com/other/term and condition.php";
                Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
                intent.putExtra("URL",url );
                startActivity(intent);
            }
        });


        anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setBackgroundColor(Animation.ABSOLUTE);
        anim.setRepeatCount(5);

    }

    public void onCheckboxClicked(View v) {

        switch (v.getId()) {

            case R.id.condition:
                   currentorpass=1;

                break;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btLogin:

                if(validate()) {

                        requestLoginData();




                }


                break;
            }




    }
    private boolean validate() {

        //matches 10-digit numbers only
        if(!condition.isChecked())
        {
            condition1.setTextColor(Color.RED);
            val = false;
        }else {
            condition1.setTextColor(Color.WHITE);
            val = true;
        }
        if (username.getText().toString().isEmpty()) {
            username.setError("\n" +
                    "mandatory field");
            val = false;
        }
        if (!mobileno.getText().toString().isEmpty()) {
            if (Patterns.PHONE.matcher((mobileno.getText().toString())).matches()) {
                if ((Long.parseLong(mobileno.getText().toString())) < Long.parseLong("6999999999") || (Long.parseLong(mobileno.getText().toString())) > Long.parseLong("9999999999")) {
                    //if (!Patterns.PHONE.matcher((etPreMob.getText().toString())).matches()&& phone>Integer.parseInt("6999999999")) {
                    val = false;
                    mobileno.setError("\n" +
                            "Invalid mobile number");
                }
            } else {
                val = false;
                mobileno.setError("\n" +
                        "Invalid mobile number");
            }
        } else {
            mobileno.setError("\n" +
                    "mandatory field");
            val = false;
        }
/*
        if (taluka1.equals("तालुका")) {
            taluka.setBackgroundColor(Color.RED);
            val = false;

        }else{
            taluka.setBackgroundColor(Color.WHITE);
        }

      if (pincode1.equals("गाव") ) {
          pincode.setBackgroundColor(Color.RED);
            val = false;

        }else{
          pincode.setBackgroundColor(Color.WHITE);
      }*/

        return val;
    }
    private void  requestLoginData() {
        username1 = username.getText().toString();
       mobileno1=mobileno.getText().toString();
       // pincode1=pincode.getText().toString();
        email1=email.getText().toString();
       // taluka1=taluka.getText().toString();


        RestAdapter adapter = new RestAdapter.Builder().setClient(new OkClient(Splash_Screen_Activity.getOkHttpClient())).setEndpoint(ENDPOINT).build();
        login api = adapter.create(login.class);



        String query = "(`name`,`mobileno`,`taluka`, `pincode`,`email`) VALUES ('" +username1 + "', '" +mobileno1 + "', '" + taluka1+ "', '" + pincode1 + "','" + email1 + "')";
        // String query = "WHERE pid <" + String.valueOf(smallest[0]) + " AND pid > " + String.valueOf(smallest[0] - 10);
        Map<String, String> params = new HashMap<String, String>();
        params.put("link", query);

        api.insertMember(query, new retrofit.Callback<String>() {


            @Override
            public void success(String s, Response response) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                // Include dialog.xml file
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog);
                // Set dialog title
               // dialog.setTitle("धन्यवाद..!");

                // set values for custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
                text.setText("कसमादे न्यूज बर्ड आपले सहर्ष स्वागत करत आहे.");
                ImageView image = (ImageView) dialog.findViewById(R.id.a);
                image.setImageResource(R.drawable.sucess);

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.btn_dialog);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });


                //  Splash_Screen_Activity.showAlertDialog(LoginActivity.this,"    ", "     आभारी आहे.", true);

                Util1.saveSharedSetting1(LoginActivity.this,Splash_Screen_Activity.PREF_USER_FIRST_TIME1, "false");
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();


            }

            @Override
            public void failure(RetrofitError retrofitError) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                // Include dialog.xml file
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog);
                // Set dialog title
              //  dialog.setTitle("क्षमस्व");

                // set values for custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
                text.setText("माफ करा,इंटरनेट बंद आहे.");
                ImageView image = (ImageView) dialog.findViewById(R.id.a);
                image.setImageResource(R.drawable.fail);

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.btn_dialog);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

                // Splash_Screen_Activity.showAlertDialog(LoginActivity.this,"क्षमस्व","      नेटवर्क समस्या.", false);

            }
        });


    }
    public class CustomSpinnerAdapter1 extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private String[] asr;

        public CustomSpinnerAdapter1(Context context, String[] asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.length;
        }

        public Object getItem(int i)
        {
            return asr[i];
        }

        public long getItemId(int i)
        {
            return (long)i;
        }




        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(LoginActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(14);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr[position]);
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(LoginActivity.this);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setCompoundDrawablePadding(50);
            txt.setPadding(0, 16, 16, 16);
            txt.setTextSize(14);
            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pin, 0, 0, 0);

            //  pincode1=txt.getText().toString();
            txt.setText(asr[i]);
            pincode1=txt.getText().toString();
            if(i==0) {
                txt.setTextColor(Color.parseColor("#c3c3c3"));

            }else {
                txt.setTextColor(Color.parseColor("#000000"));
            }
            //  txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }


    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private String[] asr;

        public CustomSpinnerAdapter(Context context, String[] asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.length;
        }

        public Object getItem(int i)
        {
            return asr[i];
        }

        public long getItemId(int i)
        {
            return (long)i;
        }




        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(LoginActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(14);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr[position]);
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(LoginActivity.this);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setPadding(0, 16, 16, 16);
            txt.setTextSize(14);
            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.taluka, 0, 0, 0);
            txt.setCompoundDrawablePadding(50);
            
            txt.setText(asr[i]);
            taluka1=txt.getText().toString();
            if(i==0) {
                txt.setTextColor(Color.parseColor("#c3c3c3"));

            }else {
                txt.setTextColor(Color.parseColor("#000000"));
            }

      if(taluka1.equals("तालुका")) {
                CustomSpinnerAdapter1 customSpinnerAdapter1 = new CustomSpinnerAdapter1(LoginActivity.this, gav);
            //    pincode.setAdapter(customSpinnerAdapter1);
            }

            if(taluka1.equals("Deola")) {
                CustomSpinnerAdapter1 customSpinnerAdapter1 = new CustomSpinnerAdapter1(LoginActivity.this, Deola);
               // pincode.setAdapter(customSpinnerAdapter1);
            }
            if(taluka1.equals("Satana")) {
                CustomSpinnerAdapter1 customSpinnerAdapter1 = new CustomSpinnerAdapter1(LoginActivity.this, Satana);
               // pincode.setAdapter(customSpinnerAdapter1);
            }
            if(taluka1.equals("Malegoan")) {
                CustomSpinnerAdapter1 customSpinnerAdapter1 = new CustomSpinnerAdapter1(LoginActivity.this, Malegoan);
                //pincode.setAdapter(customSpinnerAdapter1);
            }
            if(taluka1.equals("Kalvan")) {
                CustomSpinnerAdapter1 customSpinnerAdapter1 = new CustomSpinnerAdapter1(LoginActivity.this, Kalwan);
              //  pincode.setAdapter(customSpinnerAdapter1);
            }
            return  txt;
        }

    }






}




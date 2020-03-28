package com.kasamade.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ConnetionHandler.ServiceHandler;
import gcm.RegistrationIntentService;

public class Registration_Activity extends AppCompatActivity {


    EditText ed_name, ed_mobile_number, ed_otp;
    Button btn_get_otp, btn_submit;
    String IMEI = "";
    String device_id = "";
    AppPrefs appprefs;
    LinearLayout layout_main_otp;
    String user_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);
        appprefs = new AppPrefs(Registration_Activity.this);
        getSupportActionBar().hide();

        fetchID();

    }
    private void fetchID()
    {



        layout_main_otp = (LinearLayout) findViewById(R.id.layout_main_otp);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_mobile_number = (EditText) findViewById(R.id.ed_mobile_number);
        ed_otp = (EditText) findViewById(R.id.ed_otp);

        btn_get_otp = (Button) findViewById(R.id.btn_get_otp);
        btn_submit = (Button) findViewById(R.id.btn_submit);


        //int permissionCheck = ContextCompat.checkSelfPermission(Registration_Activity.this,"android.permission.READ_PHONE_STATE");

        TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        IMEI = mngr.getDeviceId();



        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        device_id = appprefs.getDevice_ID();

        btn_get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_name.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(Registration_Activity.this, "Please enter full name", Toast.LENGTH_SHORT).show();
                }
                else if(ed_mobile_number.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(Registration_Activity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(ed_mobile_number.getText().toString().length()<10)
                {
                    Toast.makeText(Registration_Activity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new send_registration_detail().execute();
                }

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(ed_otp.getText().toString().equalsIgnoreCase(""))
             {
                 Toast.makeText(Registration_Activity.this, "Please enter OTP code", Toast.LENGTH_SHORT).show();
             }
             else if(ed_otp.getText().toString().length()<4)
             {
                 Toast.makeText(Registration_Activity.this, "Please enter valid OTP code", Toast.LENGTH_SHORT).show();
             }
             else
             {
                    new send_otp_detail().execute();
             }
            }
        });

    }

    private class send_registration_detail extends AsyncTask<Void, Void, String>
    {
        String json = "";
        ProgressDialog pDialog;
        String full_name = ed_name.getText().toString();
        String mobile_number = ed_mobile_number.getText().toString();

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(Registration_Activity.this);
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... arg) {
            // TODO Auto-generated method stub


            try{
                ServiceHandler jsonparser=new ServiceHandler();

                List<NameValuePair> param = new ArrayList<NameValuePair>();
                param.add(new BasicNameValuePair("full_name",full_name));
                param.add(new BasicNameValuePair("mobile_no",mobile_number));
                param.add(new BasicNameValuePair("imei",IMEI));
                param.add(new BasicNameValuePair("device",device_id));


                System.out.println(param);
                json=jsonparser.makeServiceCall(Config.global_link+"AppUsers/add", ConnetionHandler.ServiceHandler.POST,param);

                System.out.println(Config.global_link+"AppUsers/add");


            }
            catch(Exception e)
            {
                Log.e("catch", "" + e);
                pDialog.dismiss();
            }


            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);Void

            try {
                JSONObject jsonObject = new JSONObject(json);

                String status=jsonObject.getString("status");
                if(status.equalsIgnoreCase("false"))
                {
                    Toast.makeText(Registration_Activity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                }
                else if(status.equalsIgnoreCase("true")) {

                    Toast.makeText(Registration_Activity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                    layout_main_otp.setVisibility(View.VISIBLE);
                    user_id = jsonObject.getString("user_id");
                    appprefs.setUser_ID(user_id);

                    //appprefs.setIs_Login("1");


                }
            }
            catch (JSONException e)
            {
                System.out.println(e.getMessage());
                pDialog.dismiss();
            }
            finally {
                pDialog.dismiss();
            }
        }
    }

    private class send_otp_detail extends AsyncTask<Void, Void, String>
    {
        String json = "";
        ProgressDialog pDialog;
        String otp = ed_otp.getText().toString().trim();


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(Registration_Activity.this);
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... arg) {
            // TODO Auto-generated method stub


            try{
                ServiceHandler jsonparser=new ServiceHandler();

                List<NameValuePair> param = new ArrayList<NameValuePair>();
                param.add(new BasicNameValuePair("otp",otp));
                param.add(new BasicNameValuePair("user_id",user_id));


                System.out.println(param);
                json=jsonparser.makeServiceCall(Config.global_link+"appUsers/checkuserOTP", ConnetionHandler.ServiceHandler.POST,param);

                System.out.println(Config.global_link+"appUsers/checkuserOTP");


            }
            catch(Exception e)
            {
                Log.e("catch", "" + e);
                pDialog.dismiss();
            }


            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);Void

            try {
                JSONObject jsonObject = new JSONObject(json);

                String status=jsonObject.getString("status");
                if(status.equalsIgnoreCase("false"))
                {
                    Toast.makeText(Registration_Activity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                }
                else if(status.equalsIgnoreCase("true")) {

                    Toast.makeText(Registration_Activity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    appprefs.setIs_Login("1");
                    startActivity(new Intent(Registration_Activity.this,MainActivity.class));
                    finish();
                    //appprefs.setIs_Login("1");


                }
            }
            catch (JSONException e)
            {
                System.out.println(e.getMessage());
                pDialog.dismiss();
            }
            finally {
                pDialog.dismiss();
            }
        }
    }
}

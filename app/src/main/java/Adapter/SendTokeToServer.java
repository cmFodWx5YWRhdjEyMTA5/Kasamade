package Adapter;

import android.os.AsyncTask;
import android.util.Log;


public class SendTokeToServer extends AsyncTask<Void, Void, String> {

   String Token;
    @Override
    protected void onPreExecute() {
        try {

            super.onPreExecute();

             } catch (Exception err) {

            }
    }
    public SendTokeToServer(String Token) {this.Token=Token;}

    @Override
    protected String doInBackground(Void... methods) {
        String returndata="";
        try {


            try {


            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Message :", "(1) "+e.toString());


            }

        } catch (Exception e) {
            Log.i("Message :", "(2) "+e.toString());

        }
        Log.i("TokenRegTest",Token);
        return returndata;

    }


    protected void onPostExecute(String str) {
        Log.i("TokenRegTest",str);


        }



}
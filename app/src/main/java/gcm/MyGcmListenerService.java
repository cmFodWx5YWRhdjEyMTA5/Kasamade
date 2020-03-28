/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gcm;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.kasamade.news.Config;
import com.kasamade.news.MainActivity;
import com.kasamade.news.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";


    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]

    private String admin;
    private SharedPreferences initialization;

    Bitmap bitmap;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private int id=0;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");

        String imageUri = data.getString("image_path");



        bitmap = getBitmapfromUrl(Config.global_link1+""+imageUri);


        if(data.getString("pid")!=null){
        id=Integer.valueOf(data.getString("pid"));}
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        initialization = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        admin= initialization.getString("username", null);
        if (from.startsWith("/topics/"+admin)) {
            // message received from some topic.
            sendNotification(message,id,bitmap);
        } else if(from.startsWith("/topics/global")){
            sendNotification(message,id,bitmap);
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
       // sendNotification(message);
        // [END_EXCLUDE]
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message, int id, Bitmap image) {


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        /*NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(image)

                .setSmallIcon(R.mipmap.ic_launcher1)
                .setColor(getResources().getColor(R.color.peach))
                //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.maratha_tarun_logo_final)))
                .setContentTitle(getResources().getText(R.string.app_name))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)

                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image))*//*Notification with Image*//*
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);*/









      /*  NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(image)*//*Notification icon image*//*
                .setSmallIcon(R.mipmap.ic_launcher1)
                .setContentTitle(message)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(image))*//*Notification with Image*//*
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);*/




        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        String id1= String.valueOf(id);
        intent.setAction(id1);

        // intent.putExtra("id",id1);
        intent.putExtra("isNotification",true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,PendingIntent.FLAG_ONE_SHOT);


        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notif = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notif = new Notification.Builder(this)
                    .setContentIntent(pendingIntent)

                    .setContentText(message)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(image)
                    .setStyle(new Notification.BigPictureStyle().bigPicture(image))
                    .build();
        }
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(1, notif);




      /*  NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(id *//* ID of notification *//*, notificationBuilder.build());*/
    }








    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}

package com.kasamade.news;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class YouTube_Test extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube__test);
        final String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("vurl");
                TextView videotitle = (TextView) findViewById(R.id.videotitle);
               
                videotitle.setText(extras.getString("title"));

                TextView videodesc = (TextView) findViewById(R.id.video_desc);
                videodesc.setText(extras.getString("desc"));
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("vurl");
        }

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
         onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

             //   String[] key = newString.split("=");

                youTubePlayer.loadVideo(newString);
               // youTubePlayer.loadVideo(key[1]);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize("AIzaSyDAvKJe8f9HCNrWKw0O1iTkD_btNl1RxH4", onInitializedListener);

    }
}

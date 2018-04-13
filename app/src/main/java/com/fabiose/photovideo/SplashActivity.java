package com.fabiose.photovideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

/**
 * Created by fabioestrela on 18-04-10.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        VideoView videoIntro = (VideoView)findViewById(R.id.videoViewIntro);
        String uriPath = "android.resource://com.fabiose.photovideo/"+R.raw.intro;

        Uri uri = Uri.parse(uriPath);
        videoIntro.setVideoURI(uri);
        videoIntro.requestFocus();

        videoIntro.start();

        videoIntro.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });

    }
}

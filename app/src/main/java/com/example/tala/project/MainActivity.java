package com.example.tala.project;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         music = MediaPlayer.create(this, R.raw.backgroundmusic);
        music.start();
    }

    public void music(View v){
        if(music.isPlaying())
            music.pause();
        else
            music.start();

    }

    public void newgame(View v){
        Button game = (Button)v;
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));

    }
    public void exit(View v) {
        finish();
        System.exit(0);
    }
    protected void onPause() {
        super.onPause();
        music.release();
    }
}

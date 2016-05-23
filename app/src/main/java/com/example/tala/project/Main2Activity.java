package com.example.tala.project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {


    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = MediaPlayer.create(this, R.raw.backgroundmusic);
        music.start();

        GameEngine.getInstance().createGrid(this);
    }
    protected void onPause() {
        super.onPause();
        music.release();
    }

}

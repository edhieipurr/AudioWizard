package com.example.aleksandrromanov.audiowizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SoundFilterActivity extends AppCompatActivity {

    private AudioHandler audioHandler;
    private static final String LOG_TAG = "SoundFilterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_filter);
        final ImageView darth = (ImageView) this.findViewById(R.id.vader);
        final ImageView snail = (ImageView) this.findViewById(R.id.snail);
        final ImageView reverse = (ImageView) this.findViewById(R.id.reverse);
        final ImageView hamster = (ImageView) this.findViewById(R.id.hamster);
        audioHandler = new AudioHandler();
        darth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioHandler.PlayVaderSound();
                Log.v(LOG_TAG,"VADER");
            }
        });

        hamster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioHandler.PlayHamsterSound();
                Log.v(LOG_TAG,"HAMSTER");
            }
        });

        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioHandler.PlayReverseSound();
                Log.v(LOG_TAG,"REVERSE");
            }
        });

        snail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioHandler.PlaySnailSound();
                Log.v(LOG_TAG,"SNAIL");
            }
        });


    }
}

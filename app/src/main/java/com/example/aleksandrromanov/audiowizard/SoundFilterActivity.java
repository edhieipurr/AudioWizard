package com.example.aleksandrromanov.audiowizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SoundFilterActivity extends AppCompatActivity {

    private AudioHandler audioHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_filter);
        final ImageView darth = (ImageView) this.findViewById(R.id.vader);
        audioHandler = new AudioHandler();
        darth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                audioHandler.PlayRecorderAudio();

            }
        });
        


    }
}

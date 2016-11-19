package com.example.aleksandrromanov.audiowizard;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.PlaybackParams;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class RecordAudio extends AppCompatActivity {

    private static final String LOG_TAG = "RecordAudio";
    private MediaRecorder recorder;
    MediaPlayer mediaPlayer;
    private String[] perms = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static int MY_PERMISSIONS_REQUEST = 200;


    private void ConfigureMediaRecorder(MediaRecorder recorder){
        try{
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp");
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());
        }
    }

    private void PlayRecorderAudio(MediaPlayer mediaPlayer){
        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp");
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch(permsRequestCode){
            case 200:
                boolean audioAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                boolean externalStorageAccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;
                if(audioAccepted && externalStorageAccepted){

                    ConfigureMediaRecorder(recorder);
                }
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(perms, MY_PERMISSIONS_REQUEST);
        recorder = new MediaRecorder();
        mediaPlayer = new MediaPlayer();
        //Debugging toast item
        Context context = getApplicationContext();
        CharSequence text = "AudioWizard";
        int duration = Toast.LENGTH_SHORT;
        text = Environment.getExternalStorageDirectory().getAbsolutePath();
        final Toast toast = Toast.makeText(context, text, duration);
        setContentView(R.layout.activity_record_audio);
        final Button record = (Button) this.findViewById(R.id.record_button);
        final Button stop = (Button) this.findViewById(R.id.stop_button);
        final Button play = (Button) this.findViewById(R.id.play_button);
        stop.setEnabled(false);
        final TextView status = (TextView) this.findViewById(R.id.record_state);
        record.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                status.setVisibility(View.VISIBLE);
                stop.setEnabled(true);

                    try {
                        recorder.prepare();
                        recorder.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                //toast.show();

            }
        });
        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                status.setVisibility(View.INVISIBLE);

                try{
                    recorder.stop();
                    recorder.reset();
                    ConfigureMediaRecorder(recorder);
                }catch(Exception e){
                    Log.e(LOG_TAG, e.toString());
                }
                stop.setEnabled(false);
            }
        });

        play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    PlayRecorderAudio(mediaPlayer);
            }
        });
    }
}

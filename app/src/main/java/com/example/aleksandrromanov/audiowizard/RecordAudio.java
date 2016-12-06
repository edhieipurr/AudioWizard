package com.example.aleksandrromanov.audiowizard;



import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;



public class RecordAudio extends AppCompatActivity {

    private static final String LOG_TAG = "RecordAudio";
    private String[] perms = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static int MY_PERMISSIONS_REQUEST = 200;
    protected AudioHandler audioHandler = new AudioHandler();


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch(permsRequestCode){
            case 200:
                boolean audioAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                boolean externalStorageAccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;
                if(audioAccepted && externalStorageAccepted){

                    audioHandler.ConfigureMediaRecorder();
                }
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(perms, MY_PERMISSIONS_REQUEST);
        setContentView(R.layout.activity_record_audio);
        final ImageView record = (ImageView) this.findViewById(R.id.mic);
        final TextView status = (TextView) this.findViewById(R.id.record_state);
        final ImageView stop = (ImageView) this.findViewById(R.id.stop);
        stop.setEnabled(false);
        status.setVisibility(View.INVISIBLE);
        record.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                status.setVisibility(View.VISIBLE);
                stop.setEnabled(true);
                record.setEnabled(false);
                record.setImageResource(R.drawable.mic_working);
                   try {
                       audioHandler.recorder.prepare();
                       audioHandler.recorder.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        });
        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                record.setEnabled(true);
                status.setVisibility(View.INVISIBLE);
                record.setImageResource(R.drawable.mix);
                Intent intent = new Intent(getApplicationContext(), SoundFilterActivity.class);

                startActivity(intent);

                try{
                    audioHandler.recorder.stop();
                    audioHandler.recorder.reset();
                    audioHandler.ConfigureMediaRecorder();

                }catch(Exception e){
                    Log.e(LOG_TAG, e.toString());
                }
                stop.setEnabled(true);
            }
        });
    }
}

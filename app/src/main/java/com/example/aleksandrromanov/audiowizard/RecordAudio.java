package com.example.aleksandrromanov.audiowizard;


import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recorder = new MediaRecorder();
        mediaPlayer = new MediaPlayer();
        //Debugging toast item
        Context context = getApplicationContext();
        CharSequence text = "AudioWizard";
        int duration = Toast.LENGTH_SHORT;
        text = Environment.getExternalStorageDirectory().getAbsolutePath();
        final Toast toast = Toast.makeText(context, text, duration);


        //Problem with sdk 23 Runtime permissions (switched to an old target SDK (22) TO include manifest permissions statically)

       // if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
         //   Log.e(LOG_TAG,"Permission Denied");
        //}
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp");
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
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
                        //mediaPlayer.reset();
                        recorder.prepare();
                        recorder.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                toast.show();

            }
        });
        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                status.setVisibility(View.INVISIBLE);

                try{
                    recorder.stop();
                    recorder.reset();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp");
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                }catch(Exception e){
                    Log.e(LOG_TAG, e.toString());
                }
                stop.setEnabled(false);
            }
        });

        play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp");
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch(Exception e){
                    Log.e(LOG_TAG, e.toString());
                }
            }
        });
    }
}

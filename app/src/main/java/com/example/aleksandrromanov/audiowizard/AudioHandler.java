package com.example.aleksandrromanov.audiowizard;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

/**
 * Created by aleksandrromanov on 02/12/2016.
 */

class AudioHandler {

    private static final String LOG_TAG = "AudioHandler";

    MediaRecorder recorder;
    MediaPlayer mediaPlayer;

    public AudioHandler(){

        this.recorder = new MediaRecorder();
        this.mediaPlayer = new MediaPlayer();

    }

    protected void ConfigureMediaRecorder(){
        try{
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.mp4");
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());
        }
    }

    protected void PlayRecorderAudio(){
        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.mp4");
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());
        }
    }

}

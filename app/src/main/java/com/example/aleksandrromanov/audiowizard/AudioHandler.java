package com.example.aleksandrromanov.audiowizard;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.PlaybackParams;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

/**
 * Created by aleksandrromanov on 02/12/2016.
 */

class AudioHandler {

    private PlaybackParams params = new PlaybackParams();
    private static final String LOG_TAG = "AudioHandler";
    protected String pathToMediaFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.mp4";


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
            recorder.setOutputFile(pathToMediaFile);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());
        }
    }

    protected void PlayRecorderAudio(){
        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(pathToMediaFile);
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());

        }
    }

    protected void PlayVaderSound(){

        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(pathToMediaFile);
            //ADD VADER SOUND
            mediaPlayer.setPlaybackParams(params.setPitch(0.65f));
            mediaPlayer.setPlaybackParams(params.setSpeed(0.5f));
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());

        }
    }

    protected void PlayHamsterSound(){

        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(pathToMediaFile);
            mediaPlayer.setPlaybackParams(params.setPitch(2.0f));
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());

        }
    }

    protected void PlaySnailSound(){

        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(pathToMediaFile);
            //mediaPlayer.setPlaybackParams(params.setPitch(1.0f));
            mediaPlayer.setPlaybackParams(params.setSpeed(0.3f));

            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());

        }
    }

    protected void PlayReverseSound(){

        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(pathToMediaFile);
            mediaPlayer.setPlaybackParams(params.setPitch(10f));
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            Log.e(LOG_TAG, e.toString());

        }
    }

}

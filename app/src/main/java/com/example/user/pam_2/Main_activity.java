package com.example.user.pam_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import static android.content.ContentValues.TAG;

public class Main_activity extends Activity {
    MediaPlayer mediaPlayer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        final VideoView videoView =(VideoView)findViewById(R.id.videoView);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int stopTime = preferences.getInt("stopTime", 0);

        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.film1);
        videoView.setMinimumWidth(600);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.seekTo(stopTime);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer){
                videoView.start();
            }
        });

        videoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_main_activity);
        final VideoView videoView =(VideoView)findViewById(R.id.videoView);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int stopTime = preferences.getInt("stopTime", 0);

        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.film1);
        videoView.setMinimumWidth(600);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.seekTo(stopTime);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer){
                videoView.start();
            }
        });

        videoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
            }
        });
    }

    public void pause(View v) {
        Log.d(TAG, "pause called");
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    public void resume(View v) {
        Log.d(TAG, "onResume called");
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    public void activity2(View v){
        final VideoView videoView =(VideoView)findViewById(R.id.videoView);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("stopTime",videoView.getCurrentPosition()+500);
        editor.putInt("duration", videoView.getDuration());
        editor.apply();
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}
package com.example.student.myservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class MyService extends Service {

MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

          mediaPlayer.start();

        return START_STICKY;
    }

    @Override
    public void onCreate() {

        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.nirvanasliver );
    }
}



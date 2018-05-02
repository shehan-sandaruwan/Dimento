package com.example.shehandinuka.alwaysalive;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shehan dinuka on 14/03/2018.
 */

public class SensorService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    @Override
    public  int onStartCommand(Intent intent,int flag,int starId){
        ScreenReceiver sr = new ScreenReceiver();
        sr.onReceive(this,intent);
        System.out.println("service start");
        stopSelf();
        return super.onStartCommand(intent,flag,starId);
    }
}
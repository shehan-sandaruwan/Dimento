package com.example.shehandinuka.screenalive;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by shehan dinuka on 13/03/2018.
 */

public class UpdateService extends Service {
    public void onCreate() {
        super.onCreate();
        // register receiver that handles screen on and screen off logic

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (!screenOn) {
            System.out.println("Screen is off");
        } else {
            System.out.println("Screen is on");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


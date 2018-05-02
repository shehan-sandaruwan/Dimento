package com.example.shehandinuka.automaticallywork;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by shehan dinuka on 14/03/2018.
 */

public class ScreenService extends Service {
    private BroadcastReceiver sReceiver;

    public IBinder onBind(Intent arg){

        return null;

    }

    public  int onStartCommand(Intent intent,int flag, int startIs){

        // Detect screen off

        IntentFilter filter=new IntentFilter(Intent.ACTION_SCREEN_ON);

        filter.addAction(Intent.ACTION_SCREEN_OFF);

        sReceiver=new ScreenReceiver();

        registerReceiver(sReceiver,filter);

        return  START_STICKY;

    }

    public void onDestroy(){

        if(sReceiver!=null)

            unregisterReceiver(sReceiver);

        super.onDestroy();

    }

}

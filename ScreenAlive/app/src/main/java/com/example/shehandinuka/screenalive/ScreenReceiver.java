package com.example.shehandinuka.screenalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by shehan dinuka on 13/03/2018.
 */

public class ScreenReceiver extends BroadcastReceiver {
    public static boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("onReceive ");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            screenOff = true;
            System.out.println("SCREEN TURNED OFF on BroadcastReceiver");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            screenOff = false;
            System.out.println("SCREEN TURNED ON on BroadcastReceiver");
        }
        Intent i = new Intent(context, UpdateService.class);
        i.putExtra("screen_state", screenOff);
        context.startService(i);
    }

}

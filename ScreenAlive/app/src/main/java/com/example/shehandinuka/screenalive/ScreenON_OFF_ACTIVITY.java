package com.example.shehandinuka.screenalive;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScreenON_OFF_ACTIVITY extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
    }
    public void onCreate() {

        // initialize receiver
        System.out.println("onCreate1 ");
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
        System.out.println("onCreate ");
    }

    @Override
    protected void onPause() {
        // when the screen is about to turn off
        if (ScreenReceiver.screenOff) {
            // this is the case when onPause() is called by the system due to a screen state change
            System.out.println("SCREEN TURNED OFF");
        } else {
            // this is when onPause() is called when the screen state has not changed
            System.out.println("this is when onPause() is called when the screen state has not changed ");

        }
        super.onPause();
    }
    @Override
    protected void onResume() {
        // only when screen turns on
        if (!ScreenReceiver.screenOff) {
            // this is when onResume() is called due to a screen state change
            System.out.println("SCREEN TURNED ON");
        } else {
            // this is when onResume() is called when the screen state has not changed
            System.out.println(" this is when onResume() is called when the screen state has not changed ");
        }
        super.onResume();
    }
}

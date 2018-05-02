package com.example.shehandinuka.automaticallywork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){

        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            System.out.println("Hello world");

            Log.e("Log", "The screen is on.");

        }

        else{

            Log.e("Log", "The screen is off.");

        }

    }

}

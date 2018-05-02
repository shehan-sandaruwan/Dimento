package com.example.shehandinuka.autoopen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by shehan dinuka on 15/03/2018.
 */

public class ScreenReceiver extends BroadcastReceiver {
    ScreenReceiver screen;
    Context context=null;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        this.context=context;
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)||intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)||intent.getAction().equals(Intent.ACTION_USER_PRESENT))
        {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

            Toast.makeText(context,"Background Task is Started",
                    Toast.LENGTH_SHORT).show();

        }
    }
}

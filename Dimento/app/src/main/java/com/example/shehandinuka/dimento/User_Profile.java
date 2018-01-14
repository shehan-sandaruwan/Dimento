package com.example.shehandinuka.dimento;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class User_Profile extends AppCompatActivity {

    TextView userName, contact, userType, email;
    String name, type, phone, mail, realType, picUrl;
    ImageView imageView;
    ImageButton notificationButton;

    NotificationCompat.Builder notification;
    private static final int uniqueId = 456;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        HashMap <String, String> user = sessionManager.getUserDetails();

        notificationButton = findViewById(R.id.notification);

        userName = findViewById(R.id.profileName);
        name = user.get(SessionManager.KEY_NAME);
        userName.setText(name);

        userType = findViewById(R.id.userTypeString);
        type = user.get(SessionManager.KEY_TYPE);
        //notification builder
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        if (type == "1") {
            realType = "3D Model Designer";
        }
        if (type == "2") {
            realType = "Individual Customer";
        } else {
            realType = "Business Organization";
        }
        userType.setText(realType);

        contact = findViewById(R.id.phoneString);
        phone = user.get(SessionManager.KEY_PHONE);
        contact.setText(phone);

        email = findViewById(R.id.emailString);
        mail = user.get(SessionManager.KEY_EMAIL);
        email.setText(mail);

        picUrl = user.get(SessionManager.KEY_PIC);


        //Initializing the ImageView
        imageView = findViewById(R.id.profile);

        //Loading Image from URL
        Picasso.with(this)
                .load(picUrl)
                .placeholder(R.drawable.profile)   // optional// optional
                .resize(80, 80)                        // optional
                .into(imageView);
    }

    public void onClick(View view) {

        notification.setTicker("this is new Notification");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("this is you title");
        notification.setContentText("this is your body text");
        if (view.getId() == R.id.notification) {

            Intent intent = new Intent(this, Notification_Viewer.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(uniqueId, notification.build());
        /*
            Intent intent = new Intent(this,Notification_Viewer.class);
            startActivity(intent);*/

        }
    }

}




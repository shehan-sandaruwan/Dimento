package com.example.shehandinuka.alwaysalive;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.shehandinuka.alwaysalive.PythonCaller.line;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonStart,buttonEnd;
    Intent myServiceIntent;
    private SensorService mSensorService;
    private Context ctx;

    public Context getCtx() {

        return ctx;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttontop);
        buttonEnd = findViewById(R.id.buttonbtm);

        buttonStart.setOnClickListener(this);
        buttonEnd.setOnClickListener(this);

        myServiceIntent = new Intent(getApplicationContext(),SensorService.class);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttontop:
                startService(myServiceIntent);
                break;
            case R.id.buttonbtm:
                break;

        }
    }
}

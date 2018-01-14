package com.example.shehandinuka.dimento;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class login extends AppCompatActivity implements View.OnClickListener {
    Button submitt;
    EditText password, username;
    String First_Name,Email,User_Id,Phone,Type,PicUrl;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());

        username = findViewById(R.id.username);
        password =  findViewById(R.id.password);
        submitt =  findViewById(R.id.submit);
        submitt.setOnClickListener(this);


        }

    @Override
    public void onClick(View v) {



        if(v.getId() == R.id.submit) {
            String uname = username.getEditableText().toString();
            String pword = password.getEditableText().toString();
            System.out.println("Givenname :" + uname + " Given password :" + pword);
            BackgroundWorker backgroundWorker = new BackgroundWorker();
            backgroundWorker.execute(uname, pword);


        }


        else if(v.getId() == R.id.create_account) {
            Intent i = new Intent(login.this, Create_account.class);
            startActivity(i);

        }

    }



    public class BackgroundWorker extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... params) {
            String uname = params[0];
            String pword = params[1];


            try {

                URL url = new URL("https://dimento.cf/MobileAPI/login"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("password", pword);
                postDataParams.put("email", uname);
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();

                } else return ("false : " + responseCode);
            } catch (Exception e) {
                return ("Exception: " + e.getMessage());
            }

        }


        @Override
        protected void onPostExecute(String result) {
            try {
                 int status;
                JSONObject json = new JSONObject(result);

                status = json.getInt("status");




                if (status == 0) {

                    Intent intent = new Intent(getApplicationContext(),login.class);
                    startActivity(intent);

                } else {

                    JSONObject data = (JSONObject) json.get("data");
                    JSONObject userdata =(JSONObject) data.get("user");
                    System.out.println("new Session"+ status);
                    First_Name = userdata.getString("first_name");
                    Email = userdata.getString("email");
                    User_Id = userdata.getString("id");
                    Phone = userdata.getString("phone");
                    Type = userdata.getString("type");
                    PicUrl = userdata.getString("profile_pic");

                   session.createLoginSession(User_Id,First_Name,Email,Phone,Type,PicUrl);
                   Toast.makeText(getApplicationContext(), "welcome to 3D world", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(getApplicationContext(), HomePage.class);
                   startActivity(i);
                   Intent intent = new Intent(getApplicationContext(),ImageAdapter.class);
                   startActivity(intent);
                    }
        } catch (JSONException e) {
                e.printStackTrace();
        }
        }


public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

        String key = itr.next();
        Object value = params.get(key);

        if (first)
        first = false;
        else
        result.append("&");

        result.append(URLEncoder.encode(key, "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
        }
    }
}

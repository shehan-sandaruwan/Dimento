package com.example.shehandinuka.dimento;

/**
 * Created by shehan dinuka on 11/12/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HomePage extends Activity{

    static ArrayList<ImageDef> arrayList;
    ListView listView;
    TextView userName;
    SessionManager sessionManager;
    static  int size;
    static String[] imageUrl;
    static  Integer[] imageName;

    public HomePage() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userName = findViewById(R.id.userName);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        HashMap <String, String> user = sessionManager.getUserDetails();

        String name = user.get(SessionManager.KEY_NAME);
        userName.setText("You loged As  "+name);

        Spinner spinner ;
        List <String> categories = new ArrayList <>();
        categories.add("select");
        categories.add("Forum");
        categories.add("My Profile");
        categories.add("Log out");
        spinner = findViewById(R.id.spinner);
        ArrayAdapter <String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int pos, long l) {
                if (adapterView.getItemAtPosition(pos).toString().equals("Forum")) {
                    Intent i = new Intent(getApplicationContext(), Forum.class);
                    startActivity(i);
                }

                if (adapterView.getItemAtPosition(pos).toString().equals("Log out")) {
                    Intent i = new Intent(getApplicationContext(), login.class);
                    startActivity(i);
                }

                if (adapterView.getItemAtPosition(pos).toString().equals("My Profile")) {
                    Intent i = new Intent(getApplicationContext(), User_Profile.class);
                    startActivity(i);
                }
                Toast.makeText(adapterView.getContext(), adapterView.getItemAtPosition(pos).toString() + " selected ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });

        GetImageUrl getImageUrl = new GetImageUrl();//create AsyncTask for make http connection and get imageurl
        getImageUrl.execute();
        // set the imageAdapter for homepage List view
        listView = findViewById(R.id.homePageListview);
        arrayList = new ArrayList <>();
        //listView.setAdapter( new ImageAdapter(this,android.R.layout.simple_list_item_1,imageUrl));
    }

    public class GetImageUrl extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            //System.out.println("fuck");
            String line = "";
            String result;
            try {

                URL url = new URL("https://dimento.cf/MobileAPI/get_models"); // here is your URL pat

                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);

                conn.connect();

                InputStreamReader streamReader = new InputStreamReader(conn.getInputStream());

                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                }

                reader.close();
                streamReader.close();

                result = stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
                //return ("Exception: " + e.getMessage());
            }
            return result;
        }


        @Override
        protected void onPostExecute(String result){

                try {
                    JSONObject json = new JSONObject(result);
                    //data =(String)json.get("data");
                    JSONObject data = (JSONObject)json.get("data");
                    JSONArray imagedata = data.getJSONArray("objects");
                    size = imagedata.length();


                    imageUrl = new String[size];
                    imageName = new Integer[size];

                    for(int l = 0; l<size;l++){
                        JSONObject obj = imagedata.getJSONObject(l);
                        System.out.println("json object" + obj);
                        arrayList.add(new ImageDef(obj.getString("object_location"),
                                                   obj.getString("title"),
                                                   obj.getInt("view_count")));
                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(imageAdapter);
        }
    }
}


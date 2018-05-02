package com.example.shehandinuka.dimento;

/**
 * Created by shehan dinuka on 11/12/2017.
 */

import android.app.Activity;
import android.content.Context;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePage extends Activity implements AdapterView.OnItemSelectedListener {
    TextView userName;
    private static List <String> imageUrl = new ArrayList <>();
    private  static List <String> imageId = new ArrayList <>();
    Context _context;
    SessionManager sessionManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomePage.GetImageUrl getImageUrl = new HomePage.GetImageUrl();
        getImageUrl.execute();
        userName = findViewById(R.id.userName);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetails();

        String name = user.get(SessionManager.KEY_NAME);
        userName.setText(name);


        ListView listView =  this.findViewById(R.id.homePageListview);
        ImageAdapter imageAdapter = new ImageAdapter(this, imageUrl,imageId);
        listView.setAdapter(imageAdapter);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("select");
        categories.add("Forum");
        categories.add("My profile");
        categories.add("Log out");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected (AdapterView<?> adapterView, View view, int pos, long l) {
        //Display Selected option
        if(adapterView.getItemAtPosition(pos).toString().equals("Forum")) {
            Intent i = new Intent(getApplicationContext(),Forum.class);
            startActivity(i);

        }
        if(adapterView.getItemAtPosition(pos).toString().equals("Log out")) {
            Intent i = new Intent(getApplicationContext(),login.class);
            startActivity(i);
        }
        Toast.makeText(adapterView.getContext(), adapterView.getItemAtPosition(pos).toString() + " selected ", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView <?> adapterView) {

    }


    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/
    public class GetImageUrl extends AsyncTask <String, Void, String> {

        protected String doInBackground(String... strings) {
                String result = "";

                try {
                    URL urls = new URL("https://dimento.cf/MobileAPI/get_models");
                    HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
                    conn.setReadTimeout(150000); //milliseconds
                    conn.setConnectTimeout(15000); // milliseconds
                    conn.setRequestMethod("GET");

                    conn.connect();

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                conn.getInputStream(), "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {

                            JSONObject json = new JSONObject(line);
                            JSONObject data =(JSONObject)json.get("data");
                            JSONObject objData = (JSONObject)data.get("objects");
                            String url = objData.getString("texture_location");
                            System.out.println("hello"+url);
                            imageUrl.add(objData.getString("texture_location"));
                            imageId.add(objData.getString("title"));
                            sb.append(line + "\n");

                        }

                        result = sb.toString();
                       // System.out.println("fuck" + result);

                    } else {

                        return "error";
                    }


                } catch (Exception e) {
                    // System.out.println("exception in jsonparser class ........");
                    e.printStackTrace();
                    return "error";
                }

                return result;
            }


            @Override
            protected void onPostExecute (String result) {
            //System.out .println("hello "+result);

                try {

                    JSONObject json = new JSONObject(result);
                    JSONObject data = (JSONObject)json.get("data");
                    JSONObject objData = (JSONObject)json.get("object");
                    imageUrl.add(objData.getString("texture_location"));
                    imageId.add(objData.getString("title"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

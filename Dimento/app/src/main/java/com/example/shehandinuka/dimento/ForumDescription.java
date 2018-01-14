package com.example.shehandinuka.dimento;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ForumDescription extends Activity {
    TextView textView;
    ListView listView;
    ArrayList<String> forumDescription;
    ArrayList<String> postedDate;
    ArrayList<String> title;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_discription);
        Intent intent = getIntent();
        value = intent.getExtras().getString("category");
        textView = findViewById(R.id.selectcategory);
        textView.setText(value);



        GetForumDescription getForumDescription  = new GetForumDescription();
        getForumDescription.execute();
        listView = findViewById(R.id.categoryList);
        forumDescription = new ArrayList <>();
        postedDate = new ArrayList <>();
        title = new ArrayList <>();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.add) {
            Intent i = new Intent(this, yourIdea.class);
            startActivity(i);
        }
    }

    public class GetForumDescription extends AsyncTask <Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            //System.out.println("fuck");
            String line = "";
            String result = null;
            try {

                URL url = new URL("https://dimento.cf/MobileAPI/get_forum_posts/"+value); // here is your URL pat

                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);

                conn.connect();

                InputStreamReader streamReader = new InputStreamReader(conn.getInputStream());

                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                reader.close();
                streamReader.close();

               
                Thread.sleep(4000);
                result = stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
                //return ("Exception: " + e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            try {

                 JSONObject json = new JSONObject(result);
                JSONObject data = (JSONObject)json.get("data");
                JSONArray imagedata = data.getJSONArray("posts");
                int size = imagedata.length();

                for(int l = 0; l<size;l++) {
                    JSONObject obj = imagedata.getJSONObject(l);
                    String desp = (String) obj.get("description");
                    String postdate = (String) obj.get("created_at");
                    String desptitl = (String) obj.get("title");

                    forumDescription.add(desp);
                    postedDate.add(postdate);
                    title.add(desptitl);
                    System.out.println("bitch"+postdate);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



            CustomForumListView customForumListView = new CustomForumListView(getApplicationContext(),android.R.layout.simple_list_item_1,forumDescription,postedDate,title);
            listView.setAdapter(customForumListView);
        }
    }
}



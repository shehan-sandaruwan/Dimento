package com.example.shehandinuka.dimento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class yourIdea extends AppCompatActivity {

    EditText title,description;
    String Title,Description,Category,User;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_idea);
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        HashMap <String, String> user = sessionManager.getUserDetails();
        User = user.get(SessionManager.KEY_USER);


        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        radioGroup = findViewById(R.id.selectionGroup);

        btnDisplay = findViewById(R.id.cmntsubmit);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(selectedId);

                Title = title.getText().toString();
                Description = description.getText().toString();
                Category = radioButton.getText().toString();

                try {
                    GetText();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Toast.makeText(yourIdea.this,"You have Posted Successfully..!",Toast.LENGTH_SHORT).show();
                Intent refresh = new Intent(yourIdea.this, yourIdea.class);
                startActivity(refresh);
            }
        });

    }





    public  void   GetText()  throws UnsupportedEncodingException {



        String data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(Title, "UTF-8");
        data += "&" + URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(Category, "UTF-8");
        data += "&" + URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(Description, "UTF-8");
        data += "&" + URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(User, "UTF-8");

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL("https://dimento.cf/MobileAPI/set_forum_posts");

            // Send POST data request

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();

        } catch (Exception ex) {

        } finally {
            try {

                reader.close();
            }
                catch (Exception ex) {
            }
        }
    }
   /* protected void onPostExecute(String result) {

        if (result != null) {
            Toast.makeText(getApplicationContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
            //Intent i = new Intent(Create_account.this, HomePage.class);
            //startActivity(i);
        }
    }*/
}



package com.example.shehandinuka.dimento;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Create_account extends AppCompatActivity implements View.OnClickListener{

    EditText firstname, email, pWord, lastname, telephone;
    String FirstName, LastName, Password, Telephone, Email,TypeString;
    int Type;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        pWord = findViewById(R.id.pWord);
        email = findViewById(R.id.email);
        telephone = findViewById(R.id.telephone);
        radioGroup =findViewById(R.id.radioGroup1);
        btnDisplay = findViewById(R.id.regsubmit);
        radioGroup = findViewById(R.id.radioGroup1);
        btnDisplay =  findViewById(R.id.regsubmit);
        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton =  findViewById(selectedId);
                try {
                    System.out.println("hello");
                    GetText();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }

        });

    }




    @Override
    public void onClick(View v){
       /* try {
            System.out.println("hello");
            GetText();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }


    public  void   GetText()  throws UnsupportedEncodingException {
        // Get user defined values
        if(radioGroup.getCheckedRadioButtonId()!=-1) {
            int id = radioGroup.getCheckedRadioButtonId();
            radioButton = radioGroup.findViewById(id);
            //RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
        }
        FirstName = firstname.getText().toString();
        LastName = lastname.getText().toString();
        Telephone = telephone.getText().toString();
        Email = email.getText().toString();
        Password = pWord.getText().toString();
        TypeString = radioButton.getText().toString();;


        if(TypeString.equals("3D Model Designer")){
            Type = 1;
        }
        if(TypeString.equals("Individual Customer")){
            Type = 2;
        }
        if (TypeString.equals("Business Organization")){
            Type = 3;
        }
        System.out.println("type of user"+Type);
        // Create data variable for sent values to server

        String data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(FirstName, "UTF-8");
        data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8");
        data += "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(Telephone, "UTF-8");
        data += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(LastName, "UTF-8");
        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");
        data += "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(Type), "UTF-8");

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

        // Defined URL  where to send data
        URL url = new URL("https://dimento.cf/MobileAPI/register");

        // Send POST data request

        URLConnection conn = url.openConnection();
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
        onPostExecute(text);
        } catch (Exception ex) {

        } finally {
        try {

        reader.close();
        } catch (Exception ex) {
        }
        }
        }
    protected void onPostExecute(String result) {
        try {
        JSONObject json = new JSONObject(result);
        int status = json.getInt("status");

        if (status == 0) {
            String error = json.getString("data");
            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Create_account.this, HomePage.class);
        startActivity(i);
        }
        } catch (JSONException e) {
        e.printStackTrace();
        }
        }


}



package com.example.shehandinuka.dimento;

import android.support.v7.app.AppCompatActivity;

public class yourIdea extends AppCompatActivity {
}

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_idea);

        Title= findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        radioGroup =findViewById(R.id.radioGroup1);
    }

    public void onClick(View view) {
    }

    public  void   GetText()  throws UnsupportedEncodingException {
        // Get user defined values
        if(radioGroup.getCheckedRadioButtonId()!=-1) {
            int id = radioGroup.getCheckedRadioButtonId();
            radioButton = radioGroup.findViewById(id);
            //RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
        }
        Title = firstname.getText().toString();
        Description = lastname.getText().toString();



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
*/


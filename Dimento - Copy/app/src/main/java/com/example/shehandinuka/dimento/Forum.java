package com.example.shehandinuka.dimento;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Forum extends Activity {
    ListView lv;
    String[] category = {"Polygon Modelling", "NURBS/NURMS Modelling", "Rendering", "UV Mapping", "Baking","Other"};
    Integer[] imageId = {R.drawable.car, R.drawable.pet, R.drawable.film, R.drawable.education, R.drawable.game,R.drawable.fashion, R.drawable.next};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);


        lv=findViewById(R.id.category);
        lv.setAdapter(new CustomListView(this, category,imageId));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = category[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
package com.example.shehandinuka.dimento;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Forum extends Activity {
    String selectedItem;
    ListView lv;
    String[] category = {"Polygon Modelling", "NURBS or NURMS Modelling", "Rendering", "UV Mapping", "Baking","Architectural 3D","Other"};
    Integer[] imageId = {R.drawable.car, R.drawable.pet, R.drawable.film, R.drawable.education, R.drawable.game,R.drawable.fashion,R.drawable.architech, R.drawable.next};


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
                selectedItem = category[+position];
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();

            }
        });
    }
    }
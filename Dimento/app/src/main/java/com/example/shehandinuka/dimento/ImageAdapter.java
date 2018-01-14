package com.example.shehandinuka.dimento;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<ImageDef> {

    ArrayList<ImageDef> imageDef;
    Context context;
    int resourse;


    public ImageAdapter( Context context, int resource,  ArrayList <ImageDef> objects) {
        super(context, resource, objects);
        this.imageDef =  objects;
        this.context = context;
        this.resourse = resource;

    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_home_page_image_adapter,null,true);
        }

        ImageDef imageDef = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.homePageImage);
        Picasso.with(context).load("https://dimento.cf"+imageDef.getImageUrl()).placeholder(R.drawable.placeholder).error(R.drawable.unnamed).into(imageView);

        TextView imageName = convertView.findViewById(R.id.imageName);
        imageName.setText(imageDef.getImageName());

        TextView viewCount = convertView.findViewById(R.id.views);
        viewCount.setText("View count"+imageDef.getNumberOfView());
        //viewCount.setText("150");

        return convertView;
    }
}







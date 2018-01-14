package com.example.shehandinuka.dimento;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shehan dinuka on 11/12/2017.
 */

public class CustomForumListView extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> description;
    ArrayList<String> date;
    ArrayList<String> title;

    int resourse;



    public CustomForumListView(Context context, int resource, ArrayList<String> objects1, ArrayList<String> objects2,ArrayList<String> object3) {
        super(context, resource, objects1);
        this.context = context;
        this.description = objects1;
        this.date = objects2;
        this.title = object3;
        this.resourse = resource;

    }

    private static class ViewHolder {
        TextView ttl;
        TextView desp;
        TextView pdate;

    }
    

    @NonNull
    @Override
    public View getView(int position,View convertView,  ViewGroup parent) {

        //final String CategoryID = category[pos];
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;
        viewHolder = new ViewHolder();
        if (convertView == null) {


            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.categoryforum, parent, false);
            viewHolder.ttl = convertView.findViewById(R.id.title);
            viewHolder.desp = convertView.findViewById(R.id.description);
            viewHolder.pdate = convertView.findViewById(R.id.date);

            result = convertView;
            convertView.setTag(viewHolder);
        }

            else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

        viewHolder.desp.setText(description.get(position));
        viewHolder.ttl.setText(title.get(position));
        viewHolder.pdate.setText(date.get(position));


        return convertView;
    }
}

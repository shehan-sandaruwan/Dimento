package com.example.shehandinuka.dimento;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomListView extends ArrayAdapter<String > {

    private String [] category;
    private final Activity context;
    private Integer[] imageId;
    private static LayoutInflater inflater=null;

    public CustomListView(Activity context, String[] prgmNameList, Integer[] prgmImages) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.category, prgmNameList);
        this.category= prgmNameList;
        this.context = context;
        this.imageId = prgmImages;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
public class Holder
{

    TextView tv;
    ImageView img;
}
    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {

        final String CategoryID = category[pos];
        Holder holder=new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.category, null,true);
        holder.tv=rowView.findViewById(R.id.categoryName);
        holder.img=rowView.findViewById(R.id.image);
        holder.tv.setText(category[pos]);
        holder.img.setImageResource(imageId[pos]);
      //  holder.count = rowView.findViewById(R.id.count);
//        holder.count.setText(val);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected_category = category[pos];

                Toast.makeText(context, "You Clicked "+category[pos], Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ForumDescription.class);
                intent.putExtra("category",selected_category);
                context.startActivity(intent);
              // Intent intent = new Intent(this, ForumDescription.class);
                /*View view = inflater.inflate( R.layout.categoryforum, null );
                TextView textView= (TextView) view.findViewById( R.id.title );
                textView.setText(category[pos]);*/





            }
        });  return rowView;

    }

}
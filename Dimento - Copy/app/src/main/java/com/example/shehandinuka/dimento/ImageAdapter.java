package com.example.shehandinuka.dimento;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ImageAdapter extends ArrayAdapter<String > {
    private List <String> imageURLArray;
    private List <String> imageIdArray;
    private LayoutInflater inflater;
    private final Activity context;

    public ImageAdapter(Activity context, List <String> imageUrl, List <String> imageId) {
        super(context, R.layout.activity_home_page_image_adapter, imageUrl);
        // TODO Auto-generated constructor stub

        inflater = (context).getLayoutInflater();
        imageURLArray = imageUrl;
        imageIdArray = imageId;
        this.context = context;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
        Bitmap bitmap;
        String imageURL;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_home_page_image_adapter, null);

            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.homePageImage);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.imageURL = imageURLArray.get(position);
        new DownloadAsyncTask().execute(viewHolder);



        viewHolder = (ViewHolder) convertView.getTag();

        //lo    ad image directly
        Bitmap imageBitmap = null;
        try {
            URL imageURL = new URL(imageURLArray.get(position));
            imageBitmap = BitmapFactory.decodeStream(imageURL.openStream());
            viewHolder.imageView.setImageBitmap(imageBitmap);
            viewHolder.textView.setText(imageIdArray.get(position));
        } catch (IOException e) {
            // TODO: handle exception
            Log.e("error", "Downloading Image Failed");
            viewHolder.imageView.setImageResource(R.drawable.noimage);
        }

        return convertView;
    }



    @SuppressLint("StaticFieldLeak")
    private class DownloadAsyncTask extends AsyncTask <ViewHolder, Void, ViewHolder> {

        @Override
        protected ViewHolder doInBackground(ViewHolder... params) {
            // TODO Auto-generated method stub
            //load image directly
            ViewHolder viewHolder = params[0];
            try {
                URL imageURL = new URL(viewHolder.imageURL);
                viewHolder.bitmap = BitmapFactory.decodeStream(imageURL.openStream());
            } catch (IOException e) {
                // TODO: handle exception
                Log.e("error", "Downloading Image Failed");
                viewHolder.bitmap = null;
            }

            return viewHolder;
        }


        @Override
        protected void onPostExecute(ViewHolder result) {
            // TODO Auto-generated method stub
            if (result.bitmap == null) {
                result.imageView.setImageResource(R.drawable.noimage);
            } else {
                result.imageView.setImageBitmap(result.bitmap);
            }
        }
    }




}
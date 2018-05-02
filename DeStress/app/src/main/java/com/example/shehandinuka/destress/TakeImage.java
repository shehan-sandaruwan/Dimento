package com.example.shehandinuka.destress;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
public class TakeImage extends AppCompatActivity {
    Button btnpic;
    ImageView imgTakenPic;
    private static final int CAM_REQUEST=1313;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_image);

        btnpic = findViewById(R.id.button);
        imgTakenPic = findViewById(R.id.imageView);
        btnpic.setOnClickListener(new btnTakePhotoClicker());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTakenPic.setImageBitmap(bitmap);
           // storeImage(bitmap,"ABC");

            String partFileName = currentDateFormat();
            storeCameraphotoInSDCard(bitmap,partFileName);

            String storeFileName ="photo_" + partFileName+".jpg";
            Bitmap mBitmap = getImageFileFromSDcard(storeFileName);
            imgTakenPic.setImageBitmap(mBitmap);
        }
    }
    private String currentDateFormat(){
        simpleDateFormat
    }


    class btnTakePhotoClicker implements  Button.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }

}

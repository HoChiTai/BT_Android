package com.example.btqtcau4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class ImageFileView extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        img = (ImageView) findViewById(R.id.imgView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String path = bundle.getString("path");
            File file = new File(path);
            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            img.setImageBitmap(myBitmap);
        }
    }
}
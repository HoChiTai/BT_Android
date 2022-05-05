package com.example.btqtcau4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class ImageFileAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    ArrayList<ImageFile> arrayFileImage;

    public ImageFileAdapter(Context myContext, int myLayout, ArrayList<ImageFile> arrayFileImage) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayFileImage = arrayFileImage;
    }

    @Override
    public int getCount() {
        return arrayFileImage.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(myLayout, null);

        ImageView imgFile = (ImageView) view.findViewById(R.id.imgFile);
        TextView nameFile = (TextView) view.findViewById(R.id.nameFile);
        Button deleteFile = (Button) view.findViewById(R.id.deleteFile);

        File file = new File(arrayFileImage.get(i).getPath());
        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        imgFile.setImageBitmap(myBitmap);

        nameFile.setText(arrayFileImage.get(i).getName());

        deleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFile(file);
            }
        });

        return view;
    }

    public void deleteFile(File file) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage("Bạn có muốn xóa ảnh này không?");
        alertDialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean deleted = file.delete();

                Intent intent = new Intent(myContext, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);

            }
        });
        alertDialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.show();
    }
}

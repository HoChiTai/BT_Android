package com.example.btqtcau4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.btqtcau4.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int STORAGE_PERMISSiON_CODE = 1;
    private int CAMERA_PERMISSiON_CODE = 2;

    int REQUEST_CODE_CAMERA = 3;
    OutputStream outputStream;

    ListView listImageFile;
    ArrayList<ImageFile> arrayFile;
    File dir = new File(Environment.getExternalStorageDirectory(), "Pictures");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        arrayFile = new ArrayList<>();
        listImageFile = (ListView) findViewById(R.id.listImageFile);

        permissionChecked();
    }

    private void permissionChecked() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            loadImage();
        } else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            requestPermissions(permissions, STORAGE_PERMISSiON_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSiON_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                loadImage();
            } else {
                Toast.makeText(this, "Vui lòng cấp các quyền để thực hiện chức năng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setAlarm();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setAlarm();
    }

    private void setAlarm() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
                );

    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CharSequence name = "foxandroidReminderChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("foxandroid", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater im = getMenuInflater();
        im.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data !=null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            saveImage(bitmap);
            loadImage();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saveImage(Bitmap bitmap) {

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, System.currentTimeMillis()+".jpg");
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadImage() {
        arrayFile.clear();
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (dir.listFiles()!=null) {
            for (File file : dir.listFiles()) {
                String filePath = file.getPath();
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".jpg") || fileName.endsWith("jpeg") || fileName.endsWith("png")) {
                    ImageFile imageFile = new ImageFile(filePath, fileName);
                    arrayFile.add(imageFile);
                }
        }

        }

        ImageFileAdapter adapter = new ImageFileAdapter(
            MainActivity.this,
                    R.layout.item_image_file,
                    arrayFile
        );

        listImageFile.setAdapter(adapter);

        listImageFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ImageFileView.class);
                intent.putExtra("path", arrayFile.get(i).getPath());
                startActivity(intent);
            }
        });

    }

}
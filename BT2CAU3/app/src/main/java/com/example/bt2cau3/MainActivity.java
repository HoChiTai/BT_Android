package com.example.bt2cau3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn;
    AlertDialog dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.abc);
        dlg = new AlertDialog.Builder(this).create();
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                dlg.setMessage("Current time: " + date.toString());
                dlg.show();
            }
        });
    }
}
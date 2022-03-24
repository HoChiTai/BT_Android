package com.example.bt3cau3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout mainWin;
    View.OnClickListener buttonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWin = (LinearLayout) findViewById(R.id.main);
        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button bt = (Button) view;
                switch (bt.getId()) {
                    case R.id.blue:
                        System.out.println("click");
                        mainWin.setBackgroundColor(0xFF1247E6);
                        break;
                    case R.id.red:
                        mainWin.setBackgroundColor(0xFFE61212);
                        break;
                    case R.id.green:
                        mainWin.setBackgroundColor(0xFF27E612);
                        break;
                    case R.id.gray:
                        mainWin.setBackgroundColor(0xFF333333);
                        break;
                }
            }
        };
        Button btn0 = (Button) findViewById(R.id.blue);
        Button btn1 = (Button) findViewById(R.id.red);
        Button btn2 = (Button) findViewById(R.id.green);
        Button btn3 = (Button) findViewById(R.id.gray);

        btn0.setOnClickListener(buttonListener);
        btn1.setOnClickListener(buttonListener);
        btn2.setOnClickListener(buttonListener);
        btn3.setOnClickListener(buttonListener);

    }
}
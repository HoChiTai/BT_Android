package com.example.bt2cau4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText text;
    AlertDialog dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.edit);
        dlg = new AlertDialog.Builder(this).create();

        text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                //back key code on pixel 2
                final int KEYCODE_DPAD_LEFT = 4;
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_DPAD_LEFT) {
                    String msg = text.getText().toString();
                    dlg.setMessage(msg);
                    dlg.show();
                }
                return false;
            }
        });
    }
}
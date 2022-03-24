package com.example.bt3cau2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    Button butt;
    EditText editText;
    CheckBox color;
    CheckBox fontWeight;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butt = (Button) findViewById(R.id.butt);
        editText = (EditText) findViewById(R.id.edit);
        color = (CheckBox) findViewById(R.id.color);
        fontWeight = (CheckBox) findViewById(R.id.font_weight);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color.isChecked())
                    editText.setTextColor(Color.BLUE);
                else
                    editText.setTextColor(Color.BLACK);
                if (fontWeight.isChecked())
                    editText.setTypeface(Typeface.DEFAULT_BOLD);
                else
                    editText.setTypeface(Typeface.DEFAULT);
                editText.setText("You've clicked " + ++count + " time(s)");
            }
        });

    }
}
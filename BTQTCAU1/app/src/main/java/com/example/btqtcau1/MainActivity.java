package com.example.btqtcau1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar sbColor;
    TextView block1, block2, block3, block4, block5;
    Button btVisit, btCancel;
    CustomDialog customDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        block1 = (TextView) findViewById(R.id.block1);
        block2 = (TextView) findViewById(R.id.block2);
        block3 = (TextView) findViewById(R.id.block3);
        block4 = (TextView) findViewById(R.id.block4);
        block5 = (TextView) findViewById(R.id.block5);

        sbColor = (SeekBar) findViewById(R.id.sbColor);

        customDialog = new CustomDialog();



        sbColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int[] redArray = {255, 0, 0};
                int[] blueArray = {0, 0, 255};
                int[] yellowArray = {255, 255, 0};

                redArray[0] = redArray[0] - (255/100)*i;
                redArray[1] = redArray[1] + (229/100)*i;
                redArray[2] = redArray[2] + (238/100)*i;
                blueArray[0] = blueArray[0] + (255/100)*i;
                blueArray[1] = blueArray[1] + (102/100)*i;
                blueArray[2] = blueArray[2] - (255/100)*i;
                yellowArray[0] = yellowArray[0] - (125/100)*i;
                yellowArray[1] = yellowArray[1] - (255/100)*i;
                yellowArray[2] = yellowArray[2] + (130/100)*i;

                block1.setBackgroundColor(Color.rgb(blueArray[0],blueArray[1],blueArray[2]));
                block2.setBackgroundColor(Color.rgb(redArray[0],redArray[1],redArray[2]));
                block3.setBackgroundColor(Color.rgb(redArray[0],redArray[1],redArray[2]));
                block5.setBackgroundColor(Color.rgb(yellowArray[0],yellowArray[1],yellowArray[2]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater im = getMenuInflater();
        im.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        setContentView(R.layout.custom_dialog);
        customDialog.show(getSupportFragmentManager(), null);


        return true;
    }


}
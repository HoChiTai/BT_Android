package com.example.btqtcau2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailCountry extends AppCompatActivity {

    ImageView map;
    TextView capital, population, area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);

        map = (ImageView) findViewById(R.id.map);
        capital = (TextView) findViewById(R.id.capital);
        population = (TextView) findViewById(R.id.population);
        area = (TextView) findViewById(R.id.area);

        Bundle bundle = getIntent().getExtras();

        if (bundle !=null) {

            String strCountryName = bundle.getString("countryName");
            String strCapital = "Thủ đô: " + bundle.getString("capital");
            String strPopulation = bundle.getString("population");
            String strArea = bundle.getString("area");
            String mapCountry = bundle.getString("map");

            getSupportActionBar().setTitle(strCountryName);

            strPopulation = formatPopulation(strPopulation);
            strArea = formatArea(strArea);

            capital.setText(strCapital);
            population.setText(strPopulation);
            area.setText(strArea);

            new LoadImageInternet().execute(mapCountry);
        }
    }

    String formatPopulation(String strPopulation) {
        int numberPopulation = (int)Double.parseDouble(strPopulation);

        int copyNumber = numberPopulation;

        int dem=0,excessNumber;

        String strPopulation2 = "", strExcessNumber = "";

        while ((copyNumber / 1000) > 0) {
            dem++;
            copyNumber /= 1000;
        }

        while (dem == 0 || dem == 1) {
            excessNumber = numberPopulation%1000;
            if (dem != 0) {
                if (excessNumber < 100) {
                    if (excessNumber >= 10) {
                        strExcessNumber += "0" + String.valueOf(excessNumber);
                    }
                    else {
                        strExcessNumber += "00" + String.valueOf(excessNumber);
                    }
                }
                else {
                    strExcessNumber = String.valueOf(excessNumber);
                }
            }
            else {
                strExcessNumber = String.valueOf(excessNumber);
            }

            strPopulation2 = strExcessNumber + "," + strPopulation2;
            dem--;
            strExcessNumber="";
            numberPopulation /= 1000;
        }

        if (dem == 2) {
            strPopulation2 = String.valueOf(numberPopulation / 1000000) + "."
                    + String.valueOf(numberPopulation % 1000000);
            double dpPopulation = Double.parseDouble(strPopulation2);
            double numPopulation = (double) Math.round(dpPopulation * 10) / 10;
            return "Dân số: " + numPopulation + " Triệu người";
        }

        if (dem == 3) {
            strPopulation2 = String.valueOf(numberPopulation / 1000000000) + "."
                    + String.valueOf(numberPopulation % 1000000000);
            double dpPopulation = Double.parseDouble(strPopulation2);
            double numPopulation = (double) Math.round(dpPopulation * 10) / 10;
            return "Dân số: " + numPopulation + " Tỷ người";
        }

        strPopulation2 = strPopulation2.substring(0, strPopulation2.length() - 1);

        strPopulation = "Dân số: " + strPopulation2 + " Người";

        return strPopulation;
    }


    String formatArea(String strArea) {

        int numberArea = (int)Double.parseDouble(strArea);

        int copyNumber = numberArea;

        int dem=0,excessNumber;

        String strArea2 = "", strExcessNumber = "";

        while ((copyNumber / 1000) > 0) {
            dem++;
            copyNumber /= 1000;
        }

        while (dem >= 0) {
            excessNumber = numberArea%1000;
            if (dem != 0) {
                if (excessNumber < 100) {
                    if (excessNumber >= 10) {
                        strExcessNumber += "0" + String.valueOf(excessNumber);
                    }
                    else {
                        strExcessNumber += "00" + String.valueOf(excessNumber);
                    }
                }
                else {
                    strExcessNumber = String.valueOf(excessNumber);
                }
            }
            else {
                strExcessNumber = String.valueOf(excessNumber);
            }

            strArea2 = strExcessNumber + "," + strArea2;
            dem--;
            strExcessNumber="";
            numberArea /= 1000;
        }

        strArea2 = strArea2.substring(0, strArea2.length() - 1);

        strArea = "Diện tích: " + strArea2 + " km\u00B2";

        return strArea;
    }

    class LoadImageInternet extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmapImage = null;

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);

                InputStream inputStream = url.openConnection().getInputStream();

                bitmapImage = BitmapFactory.decodeStream(inputStream);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmapImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            map.setImageBitmap(bitmap);
        }
    }
}
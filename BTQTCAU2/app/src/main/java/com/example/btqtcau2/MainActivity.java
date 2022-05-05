package com.example.btqtcau2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {

    private String pathInfoCountry = "http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=taiho&style=full";
//    private String pathFlagCountry = "https://flagcdn.com/w160/";
    private String pathMapCountry = "https://img.geonames.org/img/country/250/";
    URL url;
    String responseText;
    StringBuffer response;

    ListView countryList;
    ArrayList<Country> arrayCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayCountry = new ArrayList<Country>();
        countryList = (ListView) findViewById(R.id.listCountry);


        new getServerData().execute();

    }

    class getServerData extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                url = new URL(pathInfoCountry);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(60000);
                conn.setConnectTimeout(60000);
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    // Reading response from input Stream
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String output;
                    response = new StringBuffer();

                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();
                }}
            catch(Exception e){
                e.printStackTrace();
            }

            responseText = response.toString();

            try {
                JSONObject object = new JSONObject(responseText);

                JSONArray array = object.getJSONArray("geonames");

                for (int i = 0 ; i < array.length() ; i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    String nameConutry = jsonObject.getString("countryName");
                    String population = jsonObject.getString("population");
                    String area = jsonObject.getString("areaInSqKm");
                    String captital = jsonObject.getString("capital");
                    String countryCode = jsonObject.getString("countryCode");

                    String linkMapCountry = pathMapCountry + countryCode.toUpperCase() + ".png";

                    if (countryCode.equals("DO")) {
                        countryCode += "1";
                    }

                    int flagCountry = getResources().getIdentifier(countryCode.toLowerCase(), "drawable", getPackageName());

                    String linkFlagCountry = String.valueOf(flagCountry);

                    Country country = new Country(nameConutry, population, area, linkFlagCountry, captital, linkMapCountry);

                    arrayCountry.add(country);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            CustomCountryAdapter adapter = new CustomCountryAdapter(
                    MainActivity.this,
                    R.layout.single_country,
                    arrayCountry
            );
            countryList.setAdapter(adapter);

            countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, DetailCountry.class);
                    intent.putExtra("countryName", arrayCountry.get(i).getName());
                    intent.putExtra("map", arrayCountry.get(i).getMap());
                    intent.putExtra("capital", arrayCountry.get(i).getCapital());
                    intent.putExtra("population", arrayCountry.get(i).getPopulation());
                    intent.putExtra("area", arrayCountry.get(i).getArea());
                    startActivity(intent);
                }
            });
        }


    }
}
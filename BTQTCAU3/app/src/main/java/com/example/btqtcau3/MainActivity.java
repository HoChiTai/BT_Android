package com.example.btqtcau3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
    String protocol = "https://";
    String currencyLink = ".fxexchangerate.com/";
    String currencyInfoLink = "https://aud.fxexchangerate.com/rss.xml";
    String countryFlagLink = "https://www.fxexchangerate.com/static/flags/";
    String idCurrency1, idCurrency2;
    Spinner currency1, currency2;
    Button btnConvert;
    EditText number1, number2;
    ListView historyCurrency;
    ArrayList<CountryCurrency> arrayCountryCurrency;
    ArrayList<CurrencyConvert> listHistory;
    ArrayList<String> arrayCountryCurrencyName;
    ArrayList<String> arrayCountryCurrencyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currency1 = (Spinner) findViewById(R.id.currency1);
        currency2 = (Spinner) findViewById(R.id.currency2);
        btnConvert = (Button) findViewById(R.id.btnConvert);
        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);
        historyCurrency = (ListView) findViewById(R.id.historyCurrency);

        arrayCountryCurrency = new ArrayList<CountryCurrency>();
        arrayCountryCurrencyName = new ArrayList<String>();
        arrayCountryCurrencyCode = new ArrayList<String>();
        listHistory = new ArrayList<CurrencyConvert>();

        new LoadRSSCurrencyInfo().execute();

    }

    public class LoadRSSCurrencyInfo extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            String descriptionCurrency = "";
            try {
                URL url = new URL(currencyInfoLink);
                InputStream inputStream = url.openConnection().getInputStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(inputStream, "UTF-8");

                int eventType = xpp.getEventType();

                boolean insideItem = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        }
                        else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                descriptionCurrency = xpp.nextText();
                                String name = descriptionCurrency.substring(descriptionCurrency.indexOf("/")+1, descriptionCurrency.length()-5);
                                String currencyCode = descriptionCurrency.substring(descriptionCurrency.length()-4,descriptionCurrency.length()-1);
                                arrayCountryCurrencyName.add(name);
                                arrayCountryCurrencyCode.add(currencyCode);
                            }
                        }

                    }
                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }
                    eventType = xpp.next();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            int countryCurrencyLength = arrayCountryCurrencyName.size();
            for (int i=0; i<countryCurrencyLength; i++) {
                String flag = countryFlagLink + arrayCountryCurrencyCode.get(i).toLowerCase() + ".webp";
                CountryCurrency item = new CountryCurrency(
                        arrayCountryCurrencyName.get(i),
                        arrayCountryCurrencyCode.get(i),
                        flag
                );
                arrayCountryCurrency.add(item);
            }

            CountryCurrencyAdapter countryCurrencyAdapter = new CountryCurrencyAdapter(
                    MainActivity.this,
                    R.layout.country_currency_item,
                    arrayCountryCurrency
                    );

            currency1.setAdapter(countryCurrencyAdapter);
            currency2.setAdapter(countryCurrencyAdapter);

            currency1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    idCurrency1 = arrayCountryCurrencyCode.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            currency2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    idCurrency2 = arrayCountryCurrencyCode.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            btnConvert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (idCurrency1.equalsIgnoreCase(idCurrency2)) {
                        Toast.makeText(MainActivity.this, "Vui lòng chọn 2 loại tiền tệ khác nhau", Toast.LENGTH_SHORT).show();
                    } else {
                        new LoadRSSCurrency().execute(idCurrency1, idCurrency2);
                    }

                }
            });
        }
    }


    public class LoadRSSCurrency extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... string) {
            String descriptionCurrency = "";
            try {

                String idCountry = protocol + string[0].toLowerCase() + currencyLink
                        + string[1].toLowerCase() + ".xml";
                URL url = new URL(idCountry);
                InputStream inputStream = url.openConnection().getInputStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(inputStream, "UTF-8");

                int eventType = xpp.getEventType();

                boolean insideItem = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                descriptionCurrency = xpp.nextText();
                                break;
                            }
                        }

                    }
                    eventType = xpp.next();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            String strCurrency = descriptionCurrency.substring(descriptionCurrency.indexOf("=") + 1,
                    descriptionCurrency.indexOf(string[1]));
            strCurrency = strCurrency.trim();

            return strCurrency;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            double dbCurrency = Double.valueOf(s);
            double db1 = Double.valueOf(String.valueOf(number1.getText()));
            double dbCurrencyCovert = Math.round(db1 * dbCurrency * 10000.0) / 10000.0;
            number2.setText(String.valueOf(dbCurrencyCovert));
            String currencyConvert1 = number1.getText() + " " + idCurrency1;
            String currencyConvert2 = number2.getText() + " " + idCurrency2;

            CurrencyConvert currencyConvert = new CurrencyConvert(currencyConvert1,currencyConvert2);
            listHistory.add(0, currencyConvert);
            CurrencyConvertAdapter currencyConvertAdapter = new CurrencyConvertAdapter(
                    MainActivity.this,
                    R.layout.currency_convert_item,
                    listHistory
            );
            historyCurrency.setAdapter(currencyConvertAdapter);
        }
    }
}
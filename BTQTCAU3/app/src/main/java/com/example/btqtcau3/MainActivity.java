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
    String[] countryCurrency = {
            "USD", "EUR", "GBP", "JPY", "CHF", "CAD", "AUD", "CNY", "HKD", "RUB",
            "MXN", "ZAR", "ALL", "DZD", "ARS", "AWG", "BSD", "BHD", "BDT", "BBD",
            "BYR", "BZD", "BMD", "BTN", "BOB", "BWP", "BRL", "BND", "BGN", "BIF",
            "KHR", "CVE", "KYD", "XOF", "XAF", "CLP", "COP", "KMF", "CRC", "HRK",
            "CUP", "CZK", "DKK", "DJF", "DOP", "XCD", "EGP", "SVC", "EEK", "ETB",
            "FKP", "FJD", "IDR", "INR", "GMD", "GTQ", "GNF", "GYD", "HTG", "HNL",
            "HUF", "ISK", "IRR", "IQD", "ILS", "JMD", "JOD", "KZT", "KES", "KRW",
            "KWD", "LAK", "LVL", "LBP", "LSL", "LRD", "LYD", "LTL", "MOP", "MKD",
            "MWK", "MYR", "MVR", "MRO", "MUR", "MDL", "MNT", "MAD", "MMK", "NAD",
            "NPR", "ANG", "NZD", "NIO", "NGN", "KPW", "NOK", "OMR", "XPF", "PKR",
            "PAB", "PGK", "PYG", "PEN", "PHP", "PLN", "QAR", "RON", "RWF", "WST",
            "STD", "SAR", "SCR", "SLL", "SGD", "SKK", "SBD", "SOS", "LKR", "SHP",
            "SDG", "SZL", "SEK", "SYP", "THB", "TRY", "TWD", "TZS", "TOP", "TTD",
            "TND", "AED", "UGX", "UAH", "UYU", "VUV", "VND", "YER", "ZMK", "ZWD",
            "VEF", "UZS", "KGS", "GHS", "BYN", "AFN", "AOA", "AMD", "AZN", "BAM",
            "CDF", "ERN", "GEL", "MGA", "MZN", "RSD", "SRD", "TJS", "TMT", "ZMW"
    };

    String[] countryCurrencyName = {
            "United States Dollar", "Euro","British Pound","Japanese Yen",
            "Swiss Franc", "Canadian Dollar", "Australian Dollar", "Chinese Yuan",
            "Hong Kong Dollar", "Russian Rouble", "Mexican Peso", "South African Rand",
            "Albanian Lek", "Algerian Dinar", "Argentine Peso", "Aruba Florin",
            "Bahamian Dollar", "Bahraini Dinar", "Bangladesh Taka", "Barbados Dollar",
            "Belarus Ruble", "Belize Dollar", "Bermuda Dollar", "Bhutan Ngultrum",
            "Bolivian Boliviano", "Botswana Pula", "Brazilian Real", "Brunei Dollar",
            "Bulgarian Lev", "Burundi Franc", "Cambodia Riel", "Cape Verde Escudo",
            "Cayman Islands Dollar", "CFA Franc (BCEAO)", "CFA Franc (BEAC)", "Chilean Peso",
            "Colombian Peso", "Comoros Franc", "Costa Rica Colon", "Croatian Kuna",
            "Cuban Peso", "Czech Koruna", "Danish Krone", "Djibouti Franc",
            "Dominican Peso", "East Caribbean Dollar", "Egyptian Pound", "El Salvador Colon",
            "Estonian Kroon", "Ethiopian Birr", "Falkland Islands Pound", "Fiji Dollar",
            "Indonesian Rupiah", "Indian Rupee", "Gambian Dalasi", "Guatemala Quetzal",
            "Guinea Franc", "Guyana Dollar", "Haiti Gourde", "Honduras Lempira",
            "Hungarian Forint", "Iceland Krona", "Iran Rial", "Iraqi Dinar",
            "Israeli Shekel", "Jamaican Dollar", "Jordanian Dinar", "Kazakhstan Tenge",
            "Kenyan Shilling", "Korean Won", "Kuwaiti Dinar", "Lao Kip",
            "Latvian Lat", "Lebanese Pound", "Lesotho Loti", "Liberian Dollar",
            "Libyan Dinar", "Lithuanian Lita", "Macau Pataca", "Macedonian Denar",
            "Malawi Kwacha", "Malaysian Ringgit", "Maldives Rufiyaa", "Mauritania Ougulya",
            "Mauritius Rupee", "Moldovan Leu", "Mongolian Tugrik", "Moroccan Dirham",
            "Myanmar Kyat", "Namibian Dollar", "Nepalese Rupee", "Neth Antilles Guilder",
            "New Zealand Dollar", "Nicaragua Cordoba", "Nigerian Naira", "North Korean Won",
            "Norwegian Krone", "Omani Rial", "Pacific Franc", "Pakistani Rupee",
            "Panama Balboa", "Papua New Guinea Kina", "Paraguayan Guarani", "Peruvian Nuevo Sol",
            "Philippine Peso", "Polish Zloty", "Qatar Rial", "Romanian New Leu",
            "Rwanda Franc", "Samoa Tala", "Sao Tome Dobra", "Saudi Arabian Riyal",
            "Seychelles Rupee", "Sierra Leone Leone", "Singapore Dollar", "Slovak Koruna",
            "Solomon Islands Dollar", "Somali Shilling", "Sri Lanka Rupee", "St Helena Pound",
            "Sudanese Pound", "Swaziland Lilageni", "Swedish Krona", "Syrian Pound",
            "Thai Baht", "Turkish Lira", "Taiwan Dollar", "Tanzanian Shilling",
            "Tongan paʻanga", "Trinidad Tobago Dollar", "Tunisian Dinar", "UAE Dirham",
            "Ugandan Shilling", "Ukraine Hryvnia", "Uruguayan New Peso", "Vanuatu Vatu",
            "Vietnam Dong", "Yemen Riyal", "Zambian Kwacha", "Zimbabwe dollar",
            "Venezuelan Bolivar", "Uzbekistan Sum", "Kyrgyzstan Som", "Ghanaian Cedi",
            "Belarusian ruble", "Afghan afghani", "Angolan kwanza", "Armenian dram",
            "Azerbaijani manat", "Convertible mark", "Congolese franc", "Eritrean nakfa",
            "Georgian lari", "Malagasy ariary", "Mozambican metical", "Serbian dinar",
            "Surinamese dollar", "Tajikistani somoni", "Turkmenistan manat", "Zambian kwacha",
    };

    String protocol = "https://";
//    String infoLink = ".fxexchangerate.com/rss.xml";
    String currencyLink = ".fxexchangerate.com/";
    String idCurrency1, idCurrency2;
    Spinner currency1, currency2;
    Button btnConvert;
    EditText number1, number2;
    ListView historyCurrency;
    ArrayList<CountryCurrency> arrayCountryCurrency;
    ArrayList<String> listHistory;

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
        listHistory = new ArrayList<String>();
        int countryCurrencyLength = countryCurrency.length;

        for (int i=0; i<countryCurrencyLength; i++) {
            int flag = getResources().getIdentifier(countryCurrency[i].toLowerCase(), "drawable", getPackageName());
            if (countryCurrency[i].equals("BYR")) {
                flag = getResources().getIdentifier("byn", "drawable", getPackageName());
            } else if (countryCurrency[i].equals("TRY")) {
                flag = getResources().getIdentifier(countryCurrency[i].toLowerCase() + "1", "drawable", getPackageName());
            }
            CountryCurrency item = new CountryCurrency(
                    countryCurrencyName[i],
                    countryCurrency[i],
                    flag
            );
            arrayCountryCurrency.add(item);
        }

        CountryCurrencyAdapter countryCurrencyAdapter = new CountryCurrencyAdapter(
                this,
                R.layout.country_currency_item,
                arrayCountryCurrency
                );

        currency1.setAdapter(countryCurrencyAdapter);
        currency2.setAdapter(countryCurrencyAdapter);

        currency1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idCurrency1 = countryCurrency[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        currency2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idCurrency2 = countryCurrency[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadRSSCurrency().execute(idCurrency1,idCurrency2);
            }
        });



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
            String item = number1.getText() + " " + idCurrency1 +  "\u21E8" + number2.getText() + " " + idCurrency2;
            listHistory.add(item);
            ArrayAdapter temp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listHistory);
            historyCurrency.setAdapter(temp);
        }
    }
}